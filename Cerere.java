package org.example;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cerere implements Comparable<Cerere> {

    @Override
    public int compareTo(Cerere o) {
        if (this.getDataCerere().after(o.getDataCerere())) {
            return 1;
        }
        return -1;
    }

    /// patTernuri de cerere ///
    /// enum TIPURI ///
    public enum tipCerere {
        INLOCUIRE_BULETIN, INREGISTRARE_VENIT, INLOCUIRE_CARNET_SOFER,
        INLOCUIRE_CARNET_ELEV, CREARE_ACT_CONSTITUTIV, REINNOIRE_AUTORIZATIE,
        INREGISTRARE_CUPOANE_PENSIE
    }
    String tip;
    String stringTip;
    public void stringTipConverter(tipCerere tip) {
        switch (tip) {
            case INLOCUIRE_BULETIN:
                stringTip = "inlocuire buletin";
                break;
            case INREGISTRARE_VENIT:
                stringTip = "inregistrare venit salarial";
                break;
            case INLOCUIRE_CARNET_SOFER:
                stringTip = "inlocuire carnet de sofer";
                break;
            case INLOCUIRE_CARNET_ELEV:
                stringTip = "inlocuire carnet de elev";
                break;
            case CREARE_ACT_CONSTITUTIV:
                stringTip = "creare act constitutiv";
                break;
            case REINNOIRE_AUTORIZATIE:
                stringTip = "reinnoire autorizatie";
                break;
            case INREGISTRARE_CUPOANE_PENSIE:
                stringTip = "inregistrare cupoane de pensie";
                break;
        }
    }
    int numarPrioritate;
    Date dataCerere;
    Utilizator utilizator;
    public int getNumarPrioritate() {
        return numarPrioritate;
    }
    public Date getDataCerere() {
        return dataCerere;
    }
    public void setNumarPrioritate(int numarPrioritate) {
        this.numarPrioritate = numarPrioritate;
    }
    public void setDataCerere(Date dataCerere) {
        this.dataCerere = dataCerere;
    }
    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String strDate = dateFormat.format(date);
        //System.out.println("Converted String: " + strDate);
        return strDate;
    }
    public tipCerere stringToTip(String tip){
        if(tip.equals("inlocuire buletin")){
            return tipCerere.INLOCUIRE_BULETIN;
        }
        else if(tip.equals("inregistrare venit salarial")){
            return tipCerere.INREGISTRARE_VENIT;
        }
        else if(tip.equals("inlocuire carnet de sofer")){
            return tipCerere.INLOCUIRE_CARNET_SOFER;
        }
        else if(tip.equals("inlocuire carnet de elev")){
            return tipCerere.INLOCUIRE_CARNET_ELEV;
        }
        else if(tip.equals("creare act constitutiv")){
            return tipCerere.CREARE_ACT_CONSTITUTIV;
        }
        else if(tip.equals("reinnoire autorizatie")){
            return tipCerere.REINNOIRE_AUTORIZATIE;
        }
        else
            return tipCerere.INREGISTRARE_CUPOANE_PENSIE;
    }
    /// apelare CREARE CERERE ///
    public Cerere(String tip, int prioritate, Date data, Utilizator utilizator) {
        this.tip = tip;
        this.numarPrioritate = prioritate;
        this.dataCerere = data;
        this.utilizator = utilizator;
    }
    /// compunerea CERERII ///
    public String stringCerere() {
//        stringTipConverter(Cerere.tipCerere);
        String date = dateToString(this.dataCerere);
        Utilizator user = this.utilizator;
        if (user instanceof Persoana) {
            return date + " - Subsemnatul " + user.getNume() +  ", va rog sa-mi aprobati urmatoarea solicitare: " + this.tip;
        } else if (user instanceof Angajat) {
            return date + " - Subsemnatul " + user.getNume() +  ", angajat la compania " + ((Angajat) utilizator).getCompanie() + ", va rog sa-mi aprobati urmatoarea solicitare: " + this.tip;
        } else if (user instanceof Pensionar) {
            return date + " - Subsemnatul " + user.getNume() +  ", va rog sa-mi aprobati urmatoarea solicitare: " + this.tip;
        } else if (user instanceof Elev) {
            return date + " - Subsemnatul " + user.getNume() + ", elev la scoala " + ((Elev) utilizator).getScoala() + ", va rog sa-mi aprobati urmatoarea solicitare: " + this.tip;
        }
        return date + " - Subsemnatul " + ((EntitateJuridica) utilizator).getCompanie() +
                ", reprezentant legal al companiei " + user.getNume() + ", va rog sa-mi aprobati urmatoarea solicitare: " + this.tip;
        }
    public boolean allowedCerere (){
        Utilizator user = this.utilizator;
        if (user instanceof Persoana) {
            if(this.tip.equals("inlocuire buletin")  || this.tip.equals("inlocuire carnet de sofer")) {
                return true;
            }
        } else if (user instanceof Angajat) {
            if(this.tip.equals("inlocuire buletin")  || this.tip.equals("inlocuire carnet de sofer")  || this.tip.equals("inregistrare venit salarial")) {
                return true;
            }
        } else if (user instanceof Pensionar) {
            if(this.tip.equals("inlocuire buletin")  || this.tip.equals("inlocuire carnet de sofer")  || this.tip.equals("inregistrare cupoane de pensie")) {
                return true;
            }
        } else if (user instanceof Elev) {
            if(this.tip.equals("inlocuire buletin")  || this.tip.equals("inlocuire carnet de elev")) {
                return true;
            }
        }
        else if(this.tip.equals("creare act constitutiv")  || this.tip.equals("reinnoire autorizatie")){
            return true;
        }

        return false;
    }

    public String getTip() {
        return this.tip;
    }
    public Utilizator getUtilizator() {
        return this.utilizator;
    }

    @Override
    public String toString() {
        return "Cerere{" +
                "tip='" + tip + '\'' +
                ", numarPrioritate=" + numarPrioritate +
                ", dataCerere=" + dataCerere +
                ", utilizator=" + utilizator.getNume() +
                '}';
    }
}
