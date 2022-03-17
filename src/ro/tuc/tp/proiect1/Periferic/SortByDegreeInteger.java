package ro.tuc.tp.proiect1.Periferic;
import ro.tuc.tp.proiect1.DataModels.MonomialWithIntegerCoefficient;

import java.util.Comparator;

public class SortByDegreeInteger implements Comparator<MonomialWithIntegerCoefficient> {
    public int compare(MonomialWithIntegerCoefficient a, MonomialWithIntegerCoefficient b) {
        return b.getDegree() - a.getDegree();
    }
}