package ro.tuc.tp.proiect1.DataModels;

public class MonomialWithIntegerCoefficient {
    private int degree;
    private int coefficient;

    public MonomialWithIntegerCoefficient(int degree, int coefficient) {
        this.degree = degree;
        this.coefficient = coefficient;
    }

    public MonomialWithIntegerCoefficient() {
        this(0,0);
    }

    public int getDegree() {
        return this.degree;
    }

    public int getCoefficient() {
        return this.coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void add(MonomialWithIntegerCoefficient a) {
        if(a.degree!=this.degree)
            System.out.println("Error: You are trying to add monomials(integer coefficient) with different degrees!");
        else this.coefficient += a.coefficient;
    }

    public void subtract(MonomialWithIntegerCoefficient a) {
        if(a.degree!=this.degree)
            System.out.println("Error: You are trying to subtract monomials(integer coefficient) with different degrees!");
        else this.coefficient -= a.coefficient;
    }

    public void multiply(MonomialWithIntegerCoefficient a) {
        this.coefficient *= a.coefficient;
        this.degree = this.degree + a.degree;
    }

    private String printDegree(int a, int []p) {
        String s=new String();
        while(a!=0) {
            s=s+Character.toString((char) p[a % 10]);
            a/=10;
        }
        String r=new String();
        for(int i=s.length()-1; i>=0; i--)
            r=r+s.charAt(i);
        return r;
    }

    public String print(int c) {
        String s=new String();
        int []p={8304, 185, 178, 179, 8308, 8309, 8310, 8311, 8312, 8313};
        if(c!=0 && this.coefficient>0)
            s=s+'+';
        if(this.coefficient==1) {
            if(this.degree==0)
                s=s+this.coefficient;
        }
        else if(this.coefficient==-1) {
            if(this.degree==0)
                s=s+this.coefficient;
            else
                s=s+'-';
        }
        else
            s=s+this.coefficient;
        if(this.degree!=0)
            s=s+'x';
        int a=this.degree;
        if(a>1) s=s+printDegree(a, p);
        return s;
    }

}
