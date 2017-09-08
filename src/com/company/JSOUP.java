package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.*;


public class JSOUP{
    Map<String,Double> mapKurs;
    ArrayList<TablValutu>tablValutus = new ArrayList<>();
    ArrayList<String>arrayListValut;

    public JSOUP(String data) throws IOException {
        Document doc = Jsoup.connect("http://www.cbr.ru/currency_base/daily.aspx?date_req="+data).get();

        Elements tdElements = doc.select("td");
//        в мапе будем хранить Абривиатура(USD)=курс
        mapKurs =new HashMap<>();
        //Определяем размер массива
        int y = 0;
        for (Element element:tdElements)y++;
        String[]mass = new String[y];
        //заносим данные из HTML страницы в массив
        int t=0;

        for(Element element:tdElements){
            String str=element.text();
            mass[t]=str;
            t++;
        }
        //заносим в мапу значения
        //

        int o=0;
        arrayListValut = new ArrayList<>();
        while (o<y){
            int codValutu=Integer.parseInt(mass[o]);
            o++;
            String abreviaturaValutu =mass[o];
            o++;
            int koli4estvo =Integer.parseInt(mass[o]);
            o++;
            String imjaValuta=mass[o];
            o++;
            double kurs=Double.parseDouble(mass[o].replaceAll(",","."));
            o++;
            mapKurs.put(abreviaturaValutu,kurs);
            arrayListValut.add(abreviaturaValutu);
            tablValutus.add(new TablValutu(codValutu,abreviaturaValutu,koli4estvo,imjaValuta,kurs));
        }
        mapKurs.put("RUB",  1.0);
    }
}
