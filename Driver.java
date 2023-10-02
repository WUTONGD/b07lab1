public class Driver {
  public static void main(String [] args) {
    Polynomial p = new Polynomial();
    System.out.println(p.evaluate(3));
    double [] c1 = {6,5};
    int [] c11 = {0,3};
    Polynomial p1 = new Polynomial(c1,c11);
    double [] c2 = {-2,-9};
    int [] c21 = {1,4};
    Polynomial p2 = new Polynomial(c2,c21);
    Polynomial s = p1.add(p2);
    System.out.println("s(0.1) = " + s.evaluate(0.1));
    Polynomial s2 = p1.multiply(p2);
    System.out.println("s2(0.1) = " + s2.evaluate(0.1));
    if(s.hasRoot(1))
      System.out.println("1 is a root of s");
    else
      System.out.println("1 is not a root of s");
    if(s2.hasRoot(1))
      System.out.println("1 is a root of s2");
    else
      System.out.println("1 is not a root of s2");
  }
}