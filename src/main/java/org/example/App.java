package org.example;

import java.util.Scanner;

public class App {

    private static int[] priser = new int[24];
    public static void main(String[] args) {
        doMenu();
    }

    static void doMenu(){
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning) {
            printMenu();
            String input = sc.nextLine();
            switch (input) {
                case "1": inmatning(); break;//Inmatning
                case "2": minMax(); break;//Min, Max, Medel
                case "3": break;//Sortera
                case "4": break;//Bästa laddningstid (4h)
                case "e", "E": //Avsluta
                    keepRunning = false;

            }
        }
    }

    static void inmatning(){
        for (int i = 0; i< priser.length; i++){
            System.out.println("Vad är priset mellan klockan "+ String.format("%02d", i) + " och " + String.format("%02d", i+1) + " i öre?");
            Scanner sc = new Scanner(System.in);
            priser[i]=sc.nextInt();
        }
    }

    static void minMax(){
        int hi=priser[0];
        int lo=priser[0];
        float avg=0;
        int hiPos=0;
        int loPos=0;
        for (int i = 0; i< priser.length;i++){
            if (priser[i]>hi) {hi=priser[i]; hiPos = i;}
            if (priser[i]<lo) {lo=priser[i]; loPos = i;}
            avg += priser[i];
        }
        avg= avg/24;
        System.out.println( "Lägsta pris: " + String.format("%02d", loPos) +
                        "-" + String.format("%02d", loPos+1) + ", " + lo + " öre/kWh" );

        System.out.println( "Högsta pris: " + String.format("%02d", hiPos) +
                "-" + String.format("%02d", hiPos+1) + ", " + hi + " öre/kWh" );

        System.out.println( "Medelpris: " + avg + " öre/kWh" );
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

}
