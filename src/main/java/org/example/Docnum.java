package org.example;

import java.io.*;
import java.util.*;

public class Docnum {
    private File file;
    private File otchet;
    private File otchetError;

    public Docnum(File file, File otchet, File otchetError) {
        this.file = file;
        this.otchet = otchet;
        this.otchetError = otchetError;

    }

    public Set<String> set(File file) {
        Set<String> strSet = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strSet.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка файла");
        }
        return strSet;
    }

    public Map<String, String> map(File file, File otchet, File otchetError) {
        String commentTrue = "верный номер контракта";
        String commentLenght = "неверная длина контракта";
        String commentName = "неверное имя контракта";
        Set<String> set = set(file);
        Map<String, String> map = new HashMap<>();
        for (String elem : set) {
            if (elem.length() == 15 && elem.startsWith("docnum") || elem.length() == 15 && elem.startsWith("contract")) {
                map.put(elem, commentTrue);
            } else if (elem.length() != 15) {
                map.put(elem, commentLenght);
            } else {
                map.put(elem, commentName);
            }
        }
        for (Map.Entry<String, String> elem : map.entrySet()) {
            if (elem.getValue().equals(commentTrue)) {
                val(elem.getKey(), elem.getValue(), otchet);
            } else if (elem.getValue().equals(commentLenght) || elem.getValue().equals(commentName)) {
                val(elem.getKey(), elem.getValue(), otchetError);
            }
        }

        return map;
    }

    public void val(String str, String strin, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, true); PrintWriter pw = new PrintWriter(fileOutputStream)) {
            pw.println(str + " - " + strin);
        } catch (IOException e) {
            System.out.println("Файл для записи не найден");
        }
    }


}
