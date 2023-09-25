public class Polynomial{
  double[] coefficients;

  public Polynomial(){
    double[] array = {0};
    this.coefficients = array;
  }

  public Polynomial(double[] d){
    this.coefficients = d;
  }

  public Polynomial add(Polynomial poly){
    int len = Math.max(poly.coefficients.length, coefficients.length);
    double[] array = new double[len];
    for(int i=0; i < poly.coefficients.length; i++){
      array[i] = array[i] + poly.coefficients[i];
    }
    for(int j=0; j < coefficients.length; j++){
      array[j] = array[j] + coefficients[j];
    }
    Polynomial p = new Polynomial(array);
    return p;
  }

  public double evaluate(double num){
    double sum = 0;
    for(int i=0; i < coefficients.length; i++){
      sum += coefficients[i] * Math.pow(num, i);
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