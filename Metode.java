package org.example;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Metode {

    public static Set<Utilizator> users;
    public static PriorityQueue<String> cereri;

    public static void reset2 (){
        users = new HashSet<>();
        cereri = new PriorityQueue<>();
    }

    public void create (String line){
            Utilizator deAdaugat;
            if((line.split(";")[1].equals(" angajat"))) {
                deAdaugat = new Angajat(line.split("; ")[2], line.split("; ")[3]);
            }
            else if((line.split(";")[1].equals(" elev"))) {
                deAdaugat = new Elev(line.split("; ")[2], line.split("; ")[3]);
            }
            else if((line.split(";")[1].equals(" entitate juridica"))) {
                deAdaugat = new EntitateJuridica(line.split("; ")[2], line.split("; ")[3]);
            }
            else if((line.split(";")[1].equals(" pensionar"))) {
                deAdaugat = new Pensionar(line.split("; ")[2]);
            }
            else{
                deAdaugat = new Persoana(line.split("; ")[2]);
            }
            users.add(deAdaugat);
    }
    public static Date convertTime(String timeRequest) throws ParseException {
        System.out.println("TIME REQUEST: " + timeRequest);
        SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        Date dateTime = sf.parse(timeRequest);
        System.out.println(dateTime.toString().replace("Sept", "Sep"));
        return dateTime;
    }

}

