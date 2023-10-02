import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class Polynomial {

    double coefficients[];
    int exponents[];

    public Polynomial() {
        coefficients = new double[1];
        exponents = new int[1];
    }

    public Polynomial(double c[], int e[]) {
        //Precondition: c.length == e.length
        this.coefficients = new double[c.length];
        this.exponents = new int[e.length];
        for (int i = 0; i < c.length; ++i) {
            coefficients[i] = c[i];
            exponents[i] = e[i];
        }
    }

    public Polynomial(File file) throws Exception {
        Scanner sc = new Scanner(file);
        String input = sc.nextLine();
        int start = 0;
        double sign = 1;
        if (input.charAt(0) == '-') {
            start = 1;
            sign = -1;
        }
        TreeMap<Integer, Double> map = new TreeMap<Integer, Double>();
        for (int i = start, j = start; i < input.length(); i = j) {
            while (j < input.length() && input.charAt(j) != '-' && input.charAt(j) != '+') {
                ++j;
            }
            String vals[] = input.substring(i, j).split("x");
            double c = sign*Double.parseDouble(vals[0]);
            int e = Integer.parseInt(vals[1]);
            map.put(e, c);
            if (j < input.length()) {
                if (input.charAt(j) == '+') sign = 1;
                else sign = -1;
                ++j;
            }
        }
        int i = 0;
        coefficients = new double[map.size()];
        exponents = new int[map.size()];
        for (int e : map.keySet()) {
            double c = map.get(e);
            coefficients[i] = c;
            exponents[i] = e;
            ++i;
        }
        sc.close();
    }

    public Polynomial(TreeMap<Integer, Double> map) {
        int i = 0;
        coefficients = new double[map.size()];
        exponents = new int[map.size()];
        for (int e : map.keySet()) {
            double c = map.get(e);
            coefficients[i] = c;
            exponents[i] = e;
            ++i;
        }
    }

    public void saveToFile(String fileName) throws Exception {
        FileWriter fw = new FileWriter(fileName);
        String output = "";
        for (int i = 0; i < coefficients.length; ++i) {
            if (i > 0 && coefficients[i] > 0) {
                output += "+";
            }
            output += String.valueOf(coefficients[i]);
            output += "x";
            output += String.valueOf(exponents[i]);
        }
        fw.write(output + "\n");
        fw.close();
    }

    public Polynomial add(Polynomial other) {
        TreeMap<Integer, Double> map = new TreeMap();
        for (int i = 0; i < coefficients.length; ++i) {
            double c = map.getOrDefault(exponents[i], 0.0);
            c += coefficients[i];
            map.put(exponents[i], c);
        }
        for (int i = 0; i < other.coefficients.length; ++i) {
            double c = map.getOrDefault(other.exponents[i], 0.0);
            c += other.coefficients[i];
            map.put(other.exponents[i], c);
        }
        return new Polynomial(map);
    }

    public Polynomial multiply(Polynomial other) {
        TreeMap<Integer, Double> map = new TreeMap();
        for (int i = 0; i < coefficients.length; ++i) {
            for (int j = 0; j < other.coefficients.length; ++j) {
                int e = exponents[i] + other.exponents[j];
                double c = map.getOrDefault(e, 0.0) ;
                c += coefficients[i] * other.coefficients[j];
                map.put(e, c);
            }
        }
        return new Polynomial(map);
    }

    public double evaluate(double x) {
        double y = 0;
        for (int i = 0; i < coefficients.length; ++i) {
            y += coefficients[i]*Math.pow(x, exponents[i]);
        }
        return y;
    }

    public boolean hasRoot(double x) {
        return Math.abs(evaluate(x)) < 1e-9;
    }

}