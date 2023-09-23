package org.example;

import java.util.Comparator;

public class BirouCompare implements Comparator<Cerere> {

    @Override
    public int compare(Cerere o1, Cerere o2) {
        if (o1.getNumarPrioritate() > o2.getNumarPrioritate()) {
            return -1;
        }
        if (o1.getNumarPrioritate() < o2.getNumarPrioritate()) {
            return 1;
        }
        if (o1.getDataCerere().after(o2.getDataCerere())) {
            return 1;
        }
        return -1;
    }
}
