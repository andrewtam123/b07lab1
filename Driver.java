import java.io.File;

public class Driver {
    public static void main(String [] args) throws Exception {
        Polynomial p1 = new Polynomial(new File("p1.txt"));
        Polynomial p2 = new Polynomial(new File("p2.txt"));
        Polynomial p3 = p1.add(p2);
        Polynomial p4 = p1.multiply(p2);
        p3.saveToFile("p3.txt");
        p4.saveToFile("p4.txt");
        System.out.println("p1(0.1) = " + p1.evaluate(0.1));
        if(p1.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
    }
}