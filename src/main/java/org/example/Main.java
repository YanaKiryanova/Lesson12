package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File doc = new File("DocumentsNumber.txt");
        File otchet = new File("Otchet.txt");
        File otchetError = new File("OtchetError.txt");
        Docnum docnum = new Docnum(doc, otchet, otchetError);
        System.out.println(docnum.set(doc));
        System.out.println(docnum.map(doc, otchet, otchetError));


    }
}