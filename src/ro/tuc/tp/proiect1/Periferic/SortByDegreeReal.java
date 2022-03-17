package ro.tuc.tp.proiect1.Periferic;
import ro.tuc.tp.proiect1.DataModels.MonomialWithRealCoefficient;

import java.util.Comparator;

public class SortByDegreeReal implements Comparator<MonomialWithRealCoefficient> {
    public int compare(MonomialWithRealCoefficient a, MonomialWithRealCoefficient b) {
        return b.getDegree() - a.getDegree();
    }
}