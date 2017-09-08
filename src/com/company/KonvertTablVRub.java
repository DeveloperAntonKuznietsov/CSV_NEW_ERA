package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import static java.lang.String.valueOf;

public class KonvertTablVRub {
        JSOUP jsoup;
        Double d;
        ReadCSVWithScanner scanner ;
        String[][] newMass;
        String[][] newProdavecSumma;

    public KonvertTablVRub(String data, File file) throws IOException {
        jsoup = new JSOUP(data);
        Double d;
        scanner = new ReadCSVWithScanner(file);
        newMass = scanner.mass;
//конверт массива в рубли
        for (int q = 1; q <scanner.cells-1; q++) {
            newMass[1][q] = newMass[1][q].replaceAll("In, ","").replaceAll("Out, ","");

            d = (Double)jsoup.mapKurs.get(newMass[1][q]);
            for (int i = 2; i <scanner.row; i++) {
                newMass[i][q+1]=newMass[i][q+1].replaceAll(" ","").replaceAll(",",".");
                newMass[i][q+1]=String.valueOf(Double.parseDouble(newMass[i][q+1])*d);
            }

        }
//        for (int i=0; i<scanner.row;i++){
//            for (int j = 0; j <scanner.cells ; j++) {
//                System.out.print(newMass[i][j]+" ||| ");
//            }
//            System.out.println();
//        }
        //сумма всех значений храниться в первом числовом столбце
        double dd=0;
        for (int i=2; i<scanner.row;i++){
            for (int j = 2; j <scanner.cells ; j++) {
                dd = Double.parseDouble(newMass[i][j])+dd;
            }
          newMass[i][2]=String.valueOf(dd);
            dd=0;
        }
//        for (int i=0; i<scanner.row;i++){
//            for (int j = 0; j <scanner.cells ; j++) {
//            System.out.print(newMass[i][j]+" ||| ");
//        }
//        System.out.println();
//    }

        //новый массив для хранения значения продавец=сумма
        newProdavecSumma = new String[scanner.row][2];
        for (int i=0; i<scanner.row;i++){
           newProdavecSumma[i][0]=newMass[i][0];
        }
        for (int i=0; i<scanner.row;i++){
           newProdavecSumma[i][1]=newMass[i][2];
        }
//        for (int i=0; i<scanner.row;i++){
//            for (int j = 0; j <2 ; j++) {
//            System.out.print(newProdavecSumma[i][j]+" ||| ");
//        }
//        System.out.println();
//    }




        }
    }



