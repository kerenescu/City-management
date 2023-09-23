package org.example;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import static org.example.Metode.convertTime;
import static org.example.Metode.users;

public class ManagementPrimarie {
    public static void main(String[] args) throws IOException, ParseException {

        Birou.resetQueues();
        Metode.reset2();

        String fileName = null;
        File fileOut = null;
        if (args.length != 0) {
            fileName = args[0];
            fileOut = new File("src/main/resources/output/" + fileName);
            fileOut.createNewFile();
        }
        System.out.println(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/input/" + fileName));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileOut)))) {
            System.out.println(fileName);
            String line = null;

            while ((line = br.readLine()) != null) {
                Birou birou = null;
                if (line.split(";")[0].equals("adauga_utilizator")) {
                    Metode metoda = new Metode();
                    metoda.create(line);
                }
                else if (line.split(";")[0].equals("cerere_noua")) {
                    String nameString = line.split(";")[1].substring(1);
                    String tipCerere = line.split(";")[2].substring(1);
                    String prioritateString = line.split(";")[4].substring(1);
                    int prioritateInt = Integer.parseInt(prioritateString);

                    birou = null;
                    for (Utilizator user : users) {
                        birou = null;
                        if (user.getNume().equals(nameString)) {

                            boolean isInstancAngajate = user instanceof Angajat;
                            boolean isInstancPensionar = user instanceof Pensionar;
                            boolean isInstancElev = user instanceof Elev;
                            boolean isInstancEntitateJuridica = user instanceof EntitateJuridica;
                            boolean isInstanceOfPersoana = user instanceof Persoana;

                            String timeRequest = line.split(";")[3].trim();
                            System.out.println("String data este; " + timeRequest);
                            Date tipData = convertTime(timeRequest);
                            System.out.println("Data este: " + Cerere.dateToString(tipData));


                            Metode metoda = new Metode();

                            birou = new Birou();
                            Cerere cerere = new Cerere(tipCerere, prioritateInt, tipData, user);
                            //System.out.println(cerere.toString());

                            boolean accepted = cerere.allowedCerere();
                            String mesajExceptie = new String();
                            try{
                            if (accepted == true) {
                                birou.addCerere(user, cerere);
                                user.addCerere(user, cerere);
                            } else {
                                if (isInstancAngajate == true) {
                                    mesajExceptie = ("Utilizatorul de tip angajat nu poate inainta o cerere de tip " + cerere.getTip());
                                } else if (isInstancPensionar == true) {
                                    mesajExceptie = ("Utilizatorul de tip pensionar nu poate inainta o cerere de tip " + cerere.getTip());
                                } else if (isInstancElev == true) {
                                    mesajExceptie = "Utilizatorul de tip elev nu poate inainta o cerere de tip " + cerere.getTip();
                                } else if (isInstancEntitateJuridica == true) {
                                    mesajExceptie = ("Utilizatorul de tip entitate juridica nu poate inainta o cerere de tip " + cerere.getTip());
                                } else if (isInstanceOfPersoana == true) {
                                    mesajExceptie = ("Utilizatorul de tip persoana nu poate inainta o cerere de tip " + cerere.getTip());
                                }
                                out.println(mesajExceptie);
                                throw new ExceptionNotAllowed(mesajExceptie);

                            }
                            }catch (ExceptionNotAllowed e){

                            }
                        }
                        String timeRequest = line.split(";")[3].substring(1);
                        Date tipData = convertTime(timeRequest);
                        Cerere cerere = new Cerere(tipCerere, prioritateInt, tipData, user);
                        System.out.println(cerere.toString());
                    }
                }
                else if (line.split(";")[0].equals("afiseaza_cereri_in_asteptare")) {
                    String nameString = line.split(";")[1].substring(1);
                    out.println(nameString + " - cereri in asteptare:");
                    for (Utilizator user : users) {
                        for (Object c : user.coadaCereriUtilizator) {
                            //for (Object c : birou.coadaCereriAngajati) {
                            //System.out.println("Asa 1: " + c.toString());
                            if ((((Cerere) c).getUtilizator().getNume().equals(nameString))) {
                                out.println(((Cerere) c).stringCerere());
                                //}
                            }
                        }
                    }
                }
                else if(line.split(";")[0].equals("retrage_cerere")){
                    String nameString = line.split(";")[1].substring(1);
                    String dataRetragere = line.split(";")[2].trim();
                    System.out.println("dataRetragere: " + dataRetragere);
                    for(Utilizator user : users){
                        for (Object c : user.coadaCereriUtilizator) {
                            System.out.println("each: " + ((Cerere) c).stringCerere().split(" - ")[0] + " vs. " + dataRetragere);
                            if(((Cerere) c).stringCerere().split(" - ")[0].equals(dataRetragere)){
                                System.out.println("DATE EGALE");
                            }
                            System.out.println("Nume crt: " +((Cerere) c).getUtilizator().getNume().equals(nameString) + " vs. nume string: " + nameString);
                            if(user.getNume().equals(nameString)){
                                System.out.println("NUME EGALE");
                            }
                            if ((((Cerere) c).getUtilizator().getNume().equals(nameString)) && (((Cerere) c).stringCerere().split(" - ")[0].equals(dataRetragere))) {
                                ArrayList<Cerere> ceNumeVreau = new ArrayList<>(user.coadaCereriUtilizator);
                                ceNumeVreau.remove(c);
                                user.coadaCereriUtilizator = new TreeSet<>(ceNumeVreau);
                                System.out.println((((Cerere) c).getUtilizator().getNume()) + "si " + ((Cerere) c).stringCerere().split(" - ")[0]);
                                System.out.println();
                                System.out.println("RETRAS");
                                break;
                            }
                        }
                    }
                }
                //System.out.println("Userii din baza de date sunt:");
                for (Utilizator user : users) {
                    //System.out.println("[" + user.getNume() + "]");
                    boolean isInstancAngajate = user instanceof Angajat;
                    boolean isInstancPensionar = user instanceof Pensionar;
                    boolean isInstancElev = user instanceof Elev;
                    boolean isInstancEntitateJuridica = user instanceof EntitateJuridica;
                    boolean isInstancPersoana = user instanceof Persoana;

                    if (isInstancElev == true) {
                        System.out.println("instance of ELEV!");
                        System.out.println(((Elev) user).getScoala());
                    } else if (isInstancAngajate == true) {
                        System.out.println("instance of ANGAJAT!");
                        System.out.println(((Angajat) user).getCompanie());
                    } else if (isInstancEntitateJuridica == true) {
                        System.out.println("instance of EJ!");
                        System.out.println(((EntitateJuridica) user).getCompanie());
                    } else if (isInstancPensionar == true) {
                        System.out.println("instance of PENSIONAR!");
                    } else {
                        System.out.println("instance of PERSOANA!");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

