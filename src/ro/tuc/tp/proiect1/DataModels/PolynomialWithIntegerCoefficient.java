package ro.tuc.tp.proiect1.DataModels;
import ro.tuc.tp.proiect1.Periferic.SortByDegreeInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialWithIntegerCoefficient {
    private int degree;
    private ArrayList<MonomialWithIntegerCoefficient> polynom;

    public PolynomialWithIntegerCoefficient() {
        this.degree = 0;
        this.polynom = new ArrayList<MonomialWithIntegerCoefficient>();
    }

    public int getDegree() {
        return this.degree;
    }

    public void add(MonomialWithIntegerCoefficient a) {
        int ok=0;
        for (MonomialWithIntegerCoefficient j : this.polynom)
            if (a.getDegree() == j.getDegree()) {
                j.add(a);
                ok=1;
                break;
            }
        if(ok==0)
            this.polynom.add(a);

        this.degree=0;
        for (MonomialWithIntegerCoefficient i: this.polynom) {
            if(i.getDegree()>this.degree && i.getCoefficient()!=0)
                this.degree=i.getDegree();
        }

        ok=1;
        while(ok==1) {
            ok=0;
            for (int i = 0; i < this.polynom.size(); i++) {
                if (this.polynom.get(i).getCoefficient() == 0) {
                    this.polynom.remove(i);
                    ok=1;
                }
            }
        }
    }

    public void add(PolynomialWithIntegerCoefficient a) {
        for (MonomialWithIntegerCoefficient i: a.polynom) {
            int ok=0;
            for (MonomialWithIntegerCoefficient j : this.polynom)
                if (i.getDegree() == j.getDegree()) {
                    j.add(i);
                    ok=1;
                    break;
                }
            if(ok==0) {
                MonomialWithIntegerCoefficient m=new MonomialWithIntegerCoefficient(i.getDegree(), i.getCoefficient());
                this.polynom.add(m);
            }
        }

        this.degree=0;
        for (MonomialWithIntegerCoefficient i: this.polynom) {
            if(i.getDegree()>this.degree && i.getCoefficient()!=0)
                this.degree=i.getDegree();
        }

        int ok=1;
        while(ok==1) {
            ok=0;
            for (int i = 0; i < this.polynom.size(); i++) {
                if (this.polynom.get(i).getCoefficient() == 0) {
                    this.polynom.remove(i);
                    ok=1;
                }
            }
        }
    }

    public void subtract(PolynomialWithIntegerCoefficient a) {
        for (MonomialWithIntegerCoefficient i: a.polynom) {
            int ok=0;
            for (MonomialWithIntegerCoefficient j : this.polynom)
                if (i.getDegree() == j.getDegree()) {
                    j.subtract(i);
                    ok=1;
                    break;
                }
            if(ok==0) {
                MonomialWithIntegerCoefficient m=new MonomialWithIntegerCoefficient(i.getDegree(), (-1) * i.getCoefficient());
                this.polynom.add(m);
            }
        }

        this.degree=0;
        for (MonomialWithIntegerCoefficient i: this.polynom) {
            if(i.getDegree()>this.degree && i.getCoefficient()!=0)
                this.degree=i.getDegree();
        }

        int ok=1;
        while(ok==1) {
            ok=0;
            for (int i = 0; i < this.polynom.size(); i++) {
                if (this.polynom.get(i).getCoefficient() == 0) {
                    this.polynom.remove(i);
                    ok=1;
                }
            }
        }
    }

    public void multiply(PolynomialWithIntegerCoefficient a) {
        PolynomialWithIntegerCoefficient p = new PolynomialWithIntegerCoefficient();
        for (MonomialWithIntegerCoefficient i: a.polynom) {
            for (MonomialWithIntegerCoefficient j : this.polynom) {
                MonomialWithIntegerCoefficient m = new MonomialWithIntegerCoefficient(i.getDegree(),0);
                m.add(i);
                m.multiply(j);
                PolynomialWithIntegerCoefficient q = new PolynomialWithIntegerCoefficient();
                q.polynom.add(m);
                q.degree = m.getDegree();
                p.add(q);
            }
        }
        this.polynom = p.polynom;
        this.degree = p.degree;

        this.degree=0;
        for (MonomialWithIntegerCoefficient i: this.polynom) {
            if(i.getDegree()>this.degree && i.getCoefficient()!=0)
                this.degree=i.getDegree();
        }

        int ok=1;
        while(ok==1) {
            ok=0;
            for (int i = 0; i < this.polynom.size(); i++) {
                if (this.polynom.get(i).getCoefficient() == 0) {
                    this.polynom.remove(i);
                    ok=1;
                }
            }
        }
    }

    public String print() {
        Collections.sort(this.polynom, new SortByDegreeInteger());
        int c=0;
        String s=new String();
        for (MonomialWithIntegerCoefficient i: this.polynom) {
            s=s+i.print(c);
            c++;
        }
        if(this.polynom.isEmpty())
            s=s+'0';
        return s;
    }

    public void derive() {
        for (MonomialWithIntegerCoefficient i: this.polynom) {
            i.setCoefficient(i.getCoefficient()*i.getDegree());
            i.setDegree(i.getDegree()-1);
        }

        this.degree=0;
        for (MonomialWithIntegerCoefficient i: this.polynom) {
            if(i.getDegree()>this.degree && i.getCoefficient()!=0)
                this.degree=i.getDegree();
        }

        int ok=1;
        while(ok==1) {
            ok=0;
            for (int i = 0; i < this.polynom.size(); i++) {
                if (this.polynom.get(i).getCoefficient() == 0) {
                    this.polynom.remove(i);
                    ok=1;
                }
            }
        }
    }

    public PolynomialWithRealCoefficient createPolynomialWithRealCoefficient() {
        PolynomialWithRealCoefficient q = new PolynomialWithRealCoefficient();
        for (MonomialWithIntegerCoefficient i: this.polynom) {
            MonomialWithRealCoefficient m=new MonomialWithRealCoefficient(i.getDegree(), (float)i.getCoefficient());
            q.add(m);
        }
        return q;
    }

    public void createPolynomialFromString(String s) {
        String monomialFormat = "([+-]?[^-+]+)";
        Pattern p = Pattern.compile(monomialFormat);
        Matcher m = p.matcher(s);
        while(m.find()) {
            String monomial=m.group(1);
            int poz=0, length=monomial.length(), coefficient=0, degree=0, sign=1, ok;
            if(monomial.charAt(poz)=='-')
                sign=-1;
            if(monomial.charAt(poz)=='-' || monomial.charAt(0)=='+')
                poz++;


            if((monomial.charAt(poz)>='a' && monomial.charAt(poz)<='z')  || (monomial.charAt(poz)>='A' && monomial.charAt(poz)<='Z'))
                coefficient=1;
            else
                while(poz<length && monomial.charAt(poz)>='0' && monomial.charAt(poz)<='9'){
                    coefficient=coefficient*10+(int)monomial.charAt(poz)-(int)'0';
                    poz++;
                }
            coefficient*=sign;


            if(poz!=length) {
                poz++;
                if(poz!=length) {
                    poz++;
                    while(poz<length && monomial.charAt(poz)>='0' && monomial.charAt(poz)<='9'){
                        degree=degree*10+(int)monomial.charAt(poz)-(int)'0';
                        poz++;
                    }
                }
                else if(monomial.charAt(poz-1)=='x')degree=1;
            }

            MonomialWithIntegerCoefficient a=new MonomialWithIntegerCoefficient(degree,coefficient);
            this.add(a);
        }
    }

}
