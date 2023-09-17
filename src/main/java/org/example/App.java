package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Locale;

public class App {

    private static Price[] priser = new Price[24];

    private static Scanner sc;
    public static void main(String[] args) {
        Locale swedishLocale = new Locale("sv", "SE");
        Locale.setDefault(swedishLocale);
        sc = new Scanner(System.in);
        doMenu();
    }

    static void doMenu(){
        boolean keepRunning = true;
        while (keepRunning) {
            printMenu();
            String input = sc.nextLine();
            switch (input) {
                case "1": inmatning(); break;//Inmatning
                case "2": minMax(); break;//Min, Max, Medel
                case "3": sortera();//Sortera
                case "4": best4();//Bästa laddningstid (4h)
                case "e", "E": //Avsluta
                    keepRunning = false;

            }
        }
    }

    static void inmatning(){
        for (int i = 0; i< priser.length; i++){
            System.out.println("Vad är priset mellan klockan "+ String.format("%02d", i) + " och " + String.format("%02d", i+1) + " i öre?");
            priser[i]=new Price(sc.nextInt(), i);
        }
    }

    static void minMax(){
        int hi=priser[0].getPrice();
        int lo=priser[0].getPrice();
        double avg=0;
        int hiPos=0;
        int loPos=0;
        for (int i = 0; i< priser.length;i++){
            if (priser[i].getPrice()>hi) {hi=priser[i].getPrice(); hiPos = i;}
            if (priser[i].getPrice()<lo) {lo=priser[i].getPrice(); loPos = i;}
            avg += priser[i].getPrice();
        }
        avg= avg/24;
        System.out.print( "Lägsta pris: " + String.format("%02d", loPos) +
                        "-" + String.format("%02d", loPos+1) + ", " + lo + " öre/kWh\n" );

        System.out.print( "Högsta pris: " + String.format("%02d", hiPos) +
                "-" + String.format("%02d", hiPos+1) + ", " + hi + " öre/kWh\n" );

        System.out.print( "Medelpris: " + String.format("%.2f", avg )+ " öre/kWh\n" );
    }

    static void printMenu(){
        System.out.print(
                """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """
        );
    }

    static void sortera(){ //printar timmarna sorterade efter vilka som är dyrast. Ändrar ej originella arrayen
        Price[] sorted = Arrays.copyOf(priser,priser.length);
        Arrays.sort(sorted, (x,y) -> Integer.compare(y.getPrice(), x.getPrice()));
        for (Price p : sorted){
            p.printHour();
            System.out.print(" " + p.getPrice() + " öre\n");
        }
    }

    static void best4(){    //printar 4 bästa timmarna i rad
        int bestPrice= Integer.MAX_VALUE;
        int bestHour = 0;

        for (int hour = 0; hour<21; hour++){
            int currPrice = 0;
            for (int i =0; i<4;i++) {
                currPrice += priser[hour+i].getPrice();
            }
            if (currPrice<bestPrice) {
                bestPrice=currPrice;
                bestHour=hour;
            }
        }
        float average = (float) bestPrice / 4;

        System.out.printf("Påbörja laddning klockan %02d\n", bestHour);
        System.out.printf("Medelpris 4h: %.1f öre/kWh\n", average);
    }

}
