package ro.tuc.tp.proiect1.DataModels;
import ro.tuc.tp.proiect1.Periferic.SortByDegreeReal;
import java.util.ArrayList;
import java.util.Collections;

public class PolynomialWithRealCoefficient {
    private int degree;
    private ArrayList<MonomialWithRealCoefficient> polynom;

    public PolynomialWithRealCoefficient() {
        this.degree = 0;
        this.polynom = new ArrayList<MonomialWithRealCoefficient>();
    }

    public int getDegree() {
        return this.degree;
    }

    public void add(MonomialWithRealCoefficient a) {
        int ok=0;
        for (MonomialWithRealCoefficient j : this.polynom)
            if (a.getDegree() == j.getDegree()) {
                j.add(a);
                ok=1;
                break;
            }
        if(ok==0)
            this.polynom.add(a);

        this.degree=0;
        for (MonomialWithRealCoefficient i: this.polynom) {
            if(i.getDegree()>this.degree && i.getCoefficient()!=0)
                this.degree=i.getDegree();
        }

        ok=1;
        while(ok==1) {
            ok=0;
            for (int i = 0; i < this.polynom.size(); i++) {
                if (this.polynom.get(i).getCoefficient()==0) {
                    this.polynom.remove(i);
                    ok=1;
                }
            }
        }
    }

    public void add(PolynomialWithRealCoefficient a) {
        for (MonomialWithRealCoefficient i: a.polynom) {
            int ok=0;
            for (MonomialWithRealCoefficient j : this.polynom)
                if (i.getDegree() == j.getDegree()) {
                    j.add(i);
                    ok=1;
                    break;
                }
            if(ok==0) {
                MonomialWithRealCoefficient m=new MonomialWithRealCoefficient(i.getDegree(), i.getCoefficient());
                this.polynom.add(m);
            }
        }

        this.degree=0;
        for (MonomialWithRealCoefficient i: this.polynom) {
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

    public void subtract(PolynomialWithRealCoefficient a) {
        for (MonomialWithRealCoefficient i: a.polynom) {
            int ok=0;
            for (MonomialWithRealCoefficient j : this.polynom)
                if (i.getDegree() == j.getDegree()) {
                    j.subtract(i);
                    ok=1;
                    break;
                }
            if(ok==0) {
                MonomialWithRealCoefficient m=new MonomialWithRealCoefficient(i.getDegree(), (-1) * i.getCoefficient());
                this.polynom.add(m);
            }
        }

        this.degree=0;
        for (MonomialWithRealCoefficient i: this.polynom) {
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

    public void multiply(PolynomialWithRealCoefficient a) {
        PolynomialWithRealCoefficient p = new PolynomialWithRealCoefficient();
        for (MonomialWithRealCoefficient i: a.polynom) {
            for (MonomialWithRealCoefficient j : this.polynom) {
                MonomialWithRealCoefficient m = new MonomialWithRealCoefficient(i.getDegree(),0);
                m.add(i);
                m.multiply(j);
                PolynomialWithRealCoefficient q = new PolynomialWithRealCoefficient();
                q.polynom.add(m);
                q.degree = m.getDegree();
                p.add(q);
            }
        }
        this.polynom = p.polynom;
        this.degree = p.degree;

        this.degree=0;
        for (MonomialWithRealCoefficient i: this.polynom) {
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

    public PolynomialWithRealCoefficient divide(PolynomialWithRealCoefficient a) {
        PolynomialWithRealCoefficient q = new PolynomialWithRealCoefficient();
        PolynomialWithRealCoefficient r = this.duplicate();
        int k=0;
        while(r.degree>=a.degree) {
            Collections.sort(r.polynom, new SortByDegreeReal());
            Collections.sort(q.polynom, new SortByDegreeReal());
            MonomialWithRealCoefficient m=r.polynom.get(0).divide(a.polynom.get(0));
            q.add(m);
            PolynomialWithRealCoefficient e=new PolynomialWithRealCoefficient();
            e.add(m);
            PolynomialWithRealCoefficient t=a.duplicate();
            t.multiply(e);
            r.subtract(t);

            r.degree=0;
            for (MonomialWithRealCoefficient i: r.polynom) {
                if(i.getDegree()>r.degree && i.getCoefficient()!=0)
                    r.degree=i.getDegree();
            }
            int ok=1;

            while(ok==1) {
                ok=0;
                for (int i = 0; i < r.polynom.size(); i++) {
                    if (r.polynom.get(i).getCoefficient() == 0) {
                        r.polynom.remove(i);
                        ok=1;
                    }
                }
            }
        }
        this.polynom=r.polynom;
        this.degree=r.degree;
        return q;
    }

    public void integrate() {
        for (MonomialWithRealCoefficient i: this.polynom) {
            i.setCoefficient(i.getCoefficient()/(i.getDegree()+1));
            i.setDegree(i.getDegree()+1);
        }

        this.degree=0;
        for (MonomialWithRealCoefficient i: this.polynom) {
            if(i.getDegree()>this.degree && i.getCoefficient()!=0)
                this.degree=i.getDegree();
        }
    }

    public PolynomialWithRealCoefficient duplicate() {
        PolynomialWithRealCoefficient p=new PolynomialWithRealCoefficient();
        for (MonomialWithRealCoefficient i: this.polynom) {
            MonomialWithRealCoefficient m=new MonomialWithRealCoefficient(i.getDegree(), i.getCoefficient());
            p.add(m);
        }
        return p;
    }

    public String print() {
        Collections.sort(this.polynom, new SortByDegreeReal());
        int c=0;
        String s=new String();
        for (MonomialWithRealCoefficient i: this.polynom) {
            s=s+i.print(c);
            c++;
        }
        return s;
    }
}
