package org.example;

import java.util.Comparator;

public class UserCompare implements Comparator<Cerere> {
    @Override
    public int compare(Cerere o1, Cerere o2) {
        if (o1.getDataCerere().after(o2.getDataCerere())) {
            return 1;
        }
        return -1;
    }
}
