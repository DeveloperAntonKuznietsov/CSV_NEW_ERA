package com.company;

public class TablValutu {
    int codValutu;
    String abreviaturaValutu;
    int koli4estvo;
    String imjaValuta;
    double kurs;

    public TablValutu(int codValutu, String abreviaturaValutu, int koli4estvo,String imjaValuta,double kurs){
        this.codValutu = codValutu;
        this.abreviaturaValutu =abreviaturaValutu;
        this.koli4estvo =koli4estvo;
        this.imjaValuta =imjaValuta;
        this.kurs =kurs;
//        System.out.println("Код валюты: "+codValutu+" Абревиатура: "+abreviaturaValutu+
//                " Количество: "+koli4estvo+" Имя: "+ imjaValuta+" Курс: "+kurs+"\n");
    }

}
