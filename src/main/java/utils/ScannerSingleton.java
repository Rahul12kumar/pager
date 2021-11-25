package utils;

import java.util.Scanner;

public class ScannerSingleton {

    private static Scanner sc = null;

    private ScannerSingleton() {
        sc = new Scanner(System.in);
    }

    public static Scanner getInstance() {
        if (sc == null) {
            synchronized(ScannerSingleton.class) {
                if (sc == null)
                    sc = new Scanner(System.in);
            }
        }
        return sc;
    }
}