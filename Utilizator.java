package org.example;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public abstract class Utilizator {
    public String nume;
    public Utilizator(String nume) {
        this.nume = nume;
    }
    public String getNume() {
        return nume;
    }

    private static final int CAPACITY = 5000;
    public TreeSet<Cerere> coadaCereriUtilizator=  new TreeSet<>(new UserCompare());

    public void addCerere(Utilizator utilizator, Cerere cerere) {
            coadaCereriUtilizator.add(cerere);
    }

}
    class Persoana extends Utilizator {
        public Persoana(String nume) {
            super(nume);
        }
    }
    class Angajat extends Utilizator {
        private String companie;

        public Angajat(String nume, String companie) {
            super(nume);
            this.companie = companie;
        }

        public String getCompanie() {
            return companie;
        }
    }
    class Pensionar extends Utilizator {
        public Pensionar(String nume) {
            super(nume);
        }
    }
    class Elev extends Utilizator {
        private String scoala;
        public Elev(String nume, String scoala) {
            super(nume);
            this.scoala = scoala;
        }
        public String getScoala() {
            return scoala;
        }
    }
    class EntitateJuridica extends Utilizator {
        private String companie;
        public EntitateJuridica(String nume, String companie) {
            super(nume);
            this.companie = companie;
        }
        public String getCompanie() {
            return companie;
        }
    }


