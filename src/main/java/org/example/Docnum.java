package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Docnum {
    private File file;
    private File otchet;
    private File otchetError;

    public Docnum(File file, File otchet, File otchetError){
        this.file = file;
        this.otchet = otchet;
        this.otchetError = otchetError;

    }
    public void readFile () {
        try{
            Path strFile = Path.of(file.toURI());
            List<String> allStrings = Files.readAllLines(strFile);
            for (String numDoc : allStrings){
                System.out.println(numDoc);
            }
        }catch (IOException e){
            System.out.println("Файл не найден");
        }finally {
            System.out.println("Работа окончена");
        }
    }
    public void otchet (){
        try{
            Path strFile = Path.of(file.toURI());
            List<String> allStrings = Files.readAllLines(strFile);
            for (int i = 0; i < allStrings.size(); i++){
                String one = allStrings.get(i);
                if (one.length() == 15 && one.startsWith("docnum")|| one.length() == 15 && one.startsWith("contract") ){
                    val(one, otchet);
                }else{
                    val(one, otchetError);
                }
            }
        } catch (IOException e){
        System.out.println("Файл не найден");
       }
    }
    public void val (String str , File file){
        try (FileOutputStream fileOutputStream = new FileOutputStream(file,true);PrintWriter pw = new PrintWriter(fileOutputStream)){
            pw.println(str);
        }catch (IOException e){
            System.out.println("Файл для записи не найден");
        }
    }



}
