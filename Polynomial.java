import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.PrintStream;
import java.util.Comparator;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Polynomial{
  double[] coefficients;
  int[] exponents;

  public Polynomial(){
    double[] array1 = {};
    this.coefficients = array1;
    int[] array2 = {};
    this.exponents = array2;
  }

  public Polynomial(double[] d, int[] i){
    this.coefficients = d;
    this.exponents = i;
  }

  public Polynomial(File file){
    try{
      BufferedReader input = new BufferedReader(new FileReader(file));
      String s1 = input.readLine(); 
      String[] s2 = s1.split("+|-");
      int len = s2.length;
      double[] coeffs = new double[len];
      int[] exps = new int[len];
      int count = 0;
      for (String str : s2) {
        String[] s3 = str.split("x");
        double coeff = Double.parseDouble(s3[0]);
        int exp = Integer.parseInt(s3[1]);
        coeffs[count] = coeff;
        exps[count] = exp;
        count += 1;
      }
      this.coefficients = coeffs;
      this.exponents = exps;
      input.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveToFile(String name){
    try{
      String s=new String("");
      if(coefficients!=null){
        s.concat(Double.toString(coefficients[0]));
        s.concat("x");
        s.concat(Integer.toString(exponents[0]));
        for(int i=1;i<coefficients.length;i++){
          if(coefficients[i]>0){
            s.concat("+");
            s.concat(Double.toString(coefficients[i]));
          }
          else{
            s.concat(Double.toString(coefficients[i]));
          }
          s.concat("x");
          s.concat(Integer.toString(exponents[i]));
        }
      }
      File file = new File(name);
      file.createNewFile();
      PrintStream output = new PrintStream(file);
      output.println(s);
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Polynomial add(Polynomial poly){
    int len = poly.coefficients.length + coefficients.length;
    double[] array1 = new double[len];
    int[] array2 = new int[len];
    int count = coefficients.length;
    for(int i=0; i<coefficients.length; i++){
      array2[i] = exponents[i];
      array1[i] = coefficients[i];
    }
    for(int i=0; i<poly.coefficients.length;i++){
      if(poly.exponents[i]==0){
        for(int j=0;j<len;j++){
          if(array2[j]==0 && array1[j]!=0){
            array1[j] += poly.coefficients[i];
          }
        }
      }
      else{
        int inside = 0;
        for(int j=0;j<len;j++){
          if(array2[j]==poly.exponents[i]){
            inside = 1;
            array1[j] += poly.coefficients[i];
          }
        }
        if(inside==0){
          array1[count] = poly.coefficients[i];
          array2[count] = poly.exponents[i];
          count += 1;
        }
      }
    }
    count = 0;
    for(int i=0; i<len;i++){
      if(array1[i]!=0){
        count+=1;
      }
    }
    if(count==0){
      Polynomial p = new Polynomial();
      return p;
    }
    double[] arrayd = new double[count];
    int[] arrayi = new int[count];
    count = 0;
    for(int i=0;i<len;i++){
      if(array1[i]!=0){
        arrayd[count] = array1[i];
        arrayi[count] = array2[i];
        count += 1;
      }
    }
    Polynomial p = new Polynomial(arrayd, arrayi);
    return p;
  }

  public Polynomial multiply(Polynomial poly){
    ArrayList<Integer> list2 = new ArrayList<>();
    ArrayList<Double> list1 = new ArrayList<>();
    for(int i=0; i<coefficients.length;i++){
      for(int j=0;j<poly.coefficients.length;j++){
        int exp = exponents[i] + poly.exponents[j];
        double coeff = coefficients[i] * poly.coefficients[j];
        int index = list2.indexOf(exp);
        if(index==-1){
          list1.add(coeff);
          list2.add(exp);
        }
        else{
          double num = list1.get(index) + coeff;
          list1.set(index, num);
        }
      }
    }
    int find = list1.indexOf(0.0);
    while(find!=-1){
      list1.remove(find);
      list2.remove(find);
      find = list1.indexOf(0.0);
    }
    int len = list1.size();
    double[] array1 = new double[len];
    int[] array2 = new int[len];
    for(int i=0; i<len; i++){
      array1[i] = list1.get(i);
      array2[i] = list2.get(i);
    }
    if(len == 0){
      Polynomial p = new Polynomial();
      return p;
    }
    Polynomial p = new Polynomial(array1, array2);
    return p;
  }

  public double evaluate(double num){
    double sum = 0;
    for(int i=0; i < coefficients.length; i++){
      int a =exponents[i];
      sum += coefficients[i] * Math.pow(num, a);
    }
    return sum;
  }

  public boolean hasRoot(double num){
    double sum = evaluate(num);
    if(sum == 0){
      return true;
    }
    return false;
  }
}