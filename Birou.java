package org.example;

import java.util.TreeSet;

public class Birou <T extends Utilizator> {

    private static final int CAPACITY = 5000;
    public static TreeSet<Cerere> coadaCereriPersoane;
    public static TreeSet<Cerere> coadaCereriAngajati;
    public static TreeSet<Cerere> coadaCereriPensionari;
    public static TreeSet<Cerere> coadaCereriElevi;
    public static TreeSet<Cerere> coadaCereriEJ;
    public void addCerere(T utilizator, Cerere cerere) {
        if (utilizator instanceof Persoana) {
            coadaCereriPersoane.add(cerere);
        } else if (utilizator instanceof Angajat) {
            coadaCereriAngajati.add(cerere);
        } else if (utilizator instanceof Pensionar) {
            coadaCereriPensionari.add(cerere);
        } else if (utilizator instanceof Elev) {
            coadaCereriElevi.add(cerere);
        } else if (utilizator instanceof EntitateJuridica) {
            coadaCereriEJ.add(cerere);
        }
    }

    public static void resetQueues(){
        coadaCereriPersoane= new TreeSet<>(new BirouCompare());
        coadaCereriAngajati= new TreeSet<>(new BirouCompare());
        coadaCereriPensionari= new TreeSet<>(new BirouCompare());
        coadaCereriElevi= new TreeSet<>(new BirouCompare());
        coadaCereriEJ= new TreeSet<>(new BirouCompare());
    }

    public TreeSet coadaMea(T utilizator){
        if (utilizator instanceof Persoana) {
            return coadaCereriPersoane;
        } else if (utilizator instanceof Angajat) {
            return coadaCereriAngajati;
        } else if (utilizator instanceof Pensionar) {
            return coadaCereriPensionari;
        } else if (utilizator instanceof Elev) {
            return coadaCereriElevi;
        }
        return coadaCereriEJ;
    }

}
