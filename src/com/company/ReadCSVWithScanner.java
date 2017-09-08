package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadCSVWithScanner {
    Set<String> listProdavzov;
     Set<String> listValut;
    List<String>listValutArray;
    List<String>listProdavzovArray;
    String[][]mass;
    int row;
    int cells;
    public ReadCSVWithScanner(File s) throws IOException {
        // открываем файл
        BufferedReader reader = new BufferedReader(new FileReader(s));


         row=0;
         cells=0;
        int celsRes=0;
        String line = null;
        Scanner scanner = null;
//узнаём размер массива
        while ((line = reader.readLine()) != null) {// считываем построчно

            scanner = new Scanner(line);
            scanner.useDelimiter(";");

            while (scanner.hasNext()) {
                scanner.next();
                celsRes++;
            }
            if (cells<celsRes){cells=celsRes;}

            row++;
            celsRes=0;
        }
        //закрываем наш ридер
        reader.close();
        mass= new String[row][cells];
        // заполнить масив считаными данными
        BufferedReader reader2 = new BufferedReader(new FileReader(s));
        int i=0;
        int j=0;
        Scanner scanner2 = null;
        while ((line = reader2.readLine()) != null&&i<row) {

            scanner2 = new Scanner(line);
            scanner2.useDelimiter(";");

            while (scanner2.hasNext()&&j<cells) {
                String data = scanner2.next();
                mass[i][j]=data.replaceAll("\"","");
                j++;
            }
            i++;
            j=0;
        }
        reader2.close();
/////////////создаём список продавцов
        listProdavzov=new HashSet<String>();
        for (int l =0;l<row;l++){
            if((mass[l][0].equals(""))||mass[l][0].equals("Total")||mass[l][0].equals("Продавец") )continue;
            listProdavzov.add(mass[l][0]);
        }
        listProdavzovArray = new ArrayList<>();
        listProdavzovArray.addAll(listProdavzov);

/////////////создаём список валют
        listValut=new HashSet<>();
        for (int n =0;n<cells-2;n++){
            if(mass[1][n].equals(""))continue;
            listValut.add(mass[1][n].replaceAll("In, ","").replaceAll("Out, ",""));
        }
        listValutArray=new ArrayList<>(listValut);


//        for (int q =0;q<row;q++){
//            for (int k = 0; k <cells ; k++) {
//                mass[i][k]
//            }
//        }



    }
}