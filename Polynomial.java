public class Polynomial {

    double c[];

    public Polynomial() {
        c = new double[1];
    }

    public Polynomial(double c[]) {
        this.c = c;
    }

    public Polynomial add(Polynomial other) {
        int len = Math.max(this.c.length, other.c.length);
        double newC[] = new double[len];
        for (int i = 0; i < len; ++i) {
            if (i < this.c.length) {
                newC[i] += this.c[i];
            }
            if (i < other.c.length) {
                newC[i] += other.c[i];
            }
        }
        return new Polynomial(newC);
    }

    public double evaluate(double x) {
        double y = 0;
        for (int i = 0; i < this.c.length; ++i) {
            y += c[i]*Math.pow(x, i);
        }
        return y;
    }

    public boolean hasRoot(double x) {
        return Math.abs(evaluate(x)) < 1e-9;
    }

}