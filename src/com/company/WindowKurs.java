package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

public class WindowKurs{
    Button buttonSave;
    Button btnDeletValuta;
    FileChooser fileChooserSave;
    File formatter;
    Label textArea;
    JSOUP jsoup;
    Hyperlink link;

    public void show(ActionEvent event,String s) {
        Group root= new Group();
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        buttonSave = new Button("Сохранить курсы");
        fileChooserSave = new FileChooser();
        FileChooser.ExtensionFilter fileSave =
                new FileChooser.ExtensionFilter(
                        "tex files", "*.txt");

        fileChooserSave.getExtensionFilters().addAll(fileSave);
        //кнопка сохранить валюту
        textArea = new Label("");
        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                formatter= fileChooserSave.showSaveDialog(stage);
                fileChooserSave.setTitle("Сохранить курс влют");

                try{
                    FileWriter fwr = new FileWriter(formatter);
                    fwr.write(textArea.getText());
                    fwr.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
       // btnDeletValuta = new Button("Удалить");
//        btnDeletValuta.setOnAction(e->textArea.setText(""));

        String oldS = "";
        if(!oldS.equals(s)){
        try {
          jsoup = new JSOUP(s);
        for (int i =0;i<jsoup.tablValutus.size();i++)
        {
            textArea.setText(textArea.getText()+String.valueOf(jsoup.tablValutus.get(i).codValutu+" "));
            textArea.setText(textArea.getText()+String.valueOf(jsoup.tablValutus.get(i).abreviaturaValutu+" "));
            textArea.setText(textArea.getText()+String.valueOf(jsoup.tablValutus.get(i).imjaValuta+" "));
            textArea.setText(textArea.getText()+String.valueOf(jsoup.tablValutus.get(i).koli4estvo+" "));
            textArea.setText(textArea.getText()+String.valueOf(jsoup.tablValutus.get(i).kurs+"\n"));
        }oldS=s;}catch (Exception e){e.printStackTrace();}}


        buttonSave.setDisable(false);

       link = new Hyperlink("Перейти на сайт с курсами");
       link.setOnAction(event1 -> {
           try {
               if (Desktop.isDesktopSupported()){
                   Desktop desktop = Desktop.getDesktop();
                   if(desktop.isSupported(Desktop.Action.BROWSE)){
                       desktop.browse(new URI("http://www.cbr.ru/currency_base/daily.aspx?date_req="+s));
                   }
               }
           }catch (Exception ec){
               ec.printStackTrace();
           }
       });


        pane.add(buttonSave,1,1,2,1);
        pane.add(textArea,1,2,10,1);
//        pane.add(btnDeletValuta,3,1,2,1);
        pane.add(link,1,4,2,1);

        root.getChildren().addAll(pane);
        Scene scene = new Scene(root,400,670);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.setTitle("Kurs");
      //  stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
