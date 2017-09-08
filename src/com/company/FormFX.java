package com.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static java.lang.String.*;


public class FormFX extends Application{
    Button buttonChooser;
    FileChooser fileChooser;
    DatePicker calendar;
    TextField kurs;
    TextField vvod;
    TextField vuvod;
    ChoiceBox choiceBoxValut;
    ChoiceBox choiceBoxProdavzov;
    Label labelkalendar;
    Label labeltip;
    Label labelkurs;
    Label labelvvod;
    Label labelvuvod;
    Label labelDokument;
    Label labelOvalute;
    Label labelProdavzu;
    ScrollPane scrollPane;
    Image imageOK;
    Image imageNOT;
    ReadCSVWithScanner readCSVWithScanner;
    Button btnShowKurs;
    Image imgPlaneta;
    ListView<String> scrollPaneProdavcov;
    JSOUP jsoup;
    File file;
    Button btnDeleteFile;
    Button btnObrabotka;
    HTMLEditor editor;
    String data;



    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Конвидив");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        calendar=new DatePicker();
        calendar.setDisable(true);
        kurs = new TextField();
        kurs.setDisable(true);

        choiceBoxValut = new ChoiceBox<String>();
        choiceBoxValut.setVisible(false);

        vvod = new TextField("");
        vuvod = new TextField("");
        labelkalendar = new Label("Введите дату:");
        labeltip = new Label("Выберете валюту:");
        labeltip.setVisible(false);
        labelkurs = new Label("Курс в руб. за одну единицу:");
        labelvvod = new Label("Введите сумму:");
        labelvuvod = new Label("Вывод суммы:");
        labelProdavzu = new Label("Список продавцов:");
        labelDokument = new Label("Выберите документ:");
        labelOvalute = new Label("Вся валюта:");
        labelOvalute.setVisible(false);
        labelDokument.setFont(Font.font(13));
        //изображение иконки ок
        imageOK = new Image(getClass().getResourceAsStream("img\\ok.png"));
        //изображение иконки не ок
        imageNOT = new Image(getClass().getResourceAsStream("img\\not.png"));
        //////
        imgPlaneta = new Image(getClass().getResourceAsStream("img\\tenor.gif"));
        ImageView imageViewPlaneta=new ImageView(imgPlaneta);
        ImageView imageViewNOTtoOK = new ImageView(imageNOT);
        ImageView imageViewNOTtoOK2 = new ImageView(imageNOT);
        imageViewNOTtoOK.setFitHeight(20);
        imageViewNOTtoOK.setFitWidth(20);
        imageViewNOTtoOK2.setFitHeight(20);
        imageViewNOTtoOK2.setFitWidth(20);

        imageViewPlaneta.setVisible(true);
        imageViewPlaneta.setFitWidth(600);
        imageViewPlaneta.setFitHeight(550);

        btnShowKurs = new Button("Просмотр");
        btnShowKurs.setVisible(false);
        btnObrabotka = new Button("Обработать");

        editor = new HTMLEditor();
        editor.setVisible(false);
        editor.setPrefSize(300,150);



        fileChooser = new FileChooser();
        fileChooser.setTitle("Выбрать файл");
        FileChooser.ExtensionFilter fileVubora = new FileChooser.ExtensionFilter("CSV files","*.csv");

        fileChooser.getExtensionFilters().addAll(fileVubora);
        buttonChooser = new Button("Выбрать файл");

        scrollPaneProdavcov = new ListView<>();
        scrollPaneProdavcov.setMaxSize(100,25);
        //кнопка выбора файлов и смены иконок
        buttonChooser.setOnAction(e-> {
                file = fileChooser.showOpenDialog(primaryStage);
                try {
                    readCSVWithScanner = new ReadCSVWithScanner(file);
                } catch (IOException event) {
                    event.printStackTrace();
                }
                if (file!=null){
                    imageViewNOTtoOK.setImage(imageOK);

                    calendar.setDisable(false);
                }

                 scrollPaneProdavcov.setItems(FXCollections.observableArrayList(readCSVWithScanner.listProdavzovArray));
                 scrollPaneProdavcov.setMaxSize(150,75);
                 scrollPaneProdavcov.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


                if(file!=null){
                    buttonChooser.setDisable(true);
                }
            btnDeleteFile.setDisable(false);
        });

        //логика обработки событей календаря и курса
        calendar.setOnAction(e-> {
                   data = valueOf(calendar.getValue());
                    jsoup = null;
                    try {
                        jsoup = new JSOUP(data.replaceAll("-","."));
                    } catch (IOException event) {
                        event.printStackTrace();
                    }
                    imageViewNOTtoOK2.setImage(imageOK);
                    choiceBoxValut.setItems(FXCollections.observableArrayList(jsoup.arrayListValut));
                    choiceBoxValut.setVisible(true);
                    labeltip.setVisible(true);

                    //вывод в поле курс валют
//                     kurs.setText(String.valueOf(choiceBoxValut.getValue()));
            btnShowKurs.setVisible(true);
            labelOvalute.setVisible(true);
            btnShowKurs.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    WindowKurs windowKurs = new WindowKurs();
                    windowKurs.show(event,data);

                }
            });

        });
        choiceBoxValut.setOnAction(event->{
           String s = (String)choiceBoxValut.getValue();
            kurs.setText(String.valueOf(kurs(s)));

            System.out.println(s);

        });
        btnDeleteFile = new Button("Удалить CSV");
        btnDeleteFile.setDisable(true);

//кнопка обработки событей
        btnObrabotka.setOnAction(event -> {
            imageViewPlaneta.setVisible(false);
            editor.setVisible(true);
            KonvertTablVRub konvertTablVRub = null;
            try {konvertTablVRub = new KonvertTablVRub(data,file);}
            catch (IOException e) {e.printStackTrace(); }
                    for (int i=0; i<readCSVWithScanner.row;i++){
                        for (int j = 0; j <2 ; j++) {
                        editor.setHtmlText(editor.getHtmlText()+konvertTablVRub.newProdavecSumma[i][j]+" ||| ");
                            }
                        editor.setHtmlText(editor.getHtmlText()+"\n");
    }

        });

        //scrollPane = new ScrollPane(textArea);
        gridPane.add(labelDokument,1,1);
        gridPane.add(imageViewNOTtoOK,2,1);
        gridPane.add(buttonChooser,1,2);
        gridPane.add(btnDeleteFile,8,4);
        gridPane.add(labeltip, 7,2);
        gridPane.add(calendar,5,2,2,1);
        gridPane.add(labelkalendar, 5,1);
        gridPane.add(imageViewNOTtoOK2,6,1);
        gridPane.add(labelkurs, 5,3,2,1);
        gridPane.add(kurs, 5,4,2,1);
        gridPane.add(btnShowKurs,8,1,2,1);
        gridPane.add(imageViewPlaneta,1,6,8,1);
        gridPane.add(choiceBoxValut,8,2);
        gridPane.add(labelOvalute, 7,1,2,1);
        gridPane.add(labelProdavzu,1,3);
        gridPane.add(scrollPaneProdavcov,1,3,3,3);
        gridPane.add(btnObrabotka,7,4,2,1);
        gridPane.add(editor,1,6,8,2);

        Group root = new Group();
        root.getChildren().add(gridPane);
        Scene scene = new Scene(root,630,750);
        scene.setFill(Color.ALICEBLUE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    ////метод кориктировки данных предоставленном в не удобном формате (не один к одному)
public double kurs(String s) {
    s = (String) choiceBoxValut.getValue();
    Double  d = jsoup.mapKurs.get(s);
    if (s.equals("AMD")) {d=d/100;}
    if (s.equals("HUF")) {d=d/100;}
    if (s.equals("KRW")) {d=d/1000;}
    if (s.equals("HKD")) {d=d/10;}
    if (s.equals("DKK")) {d=d/10;}
    if (s.equals("INR")) {d=d/100;}
    if (s.equals("KZT")) {d=d/100;}
    if (s.equals("KGS")) {d=d/100;}
    if (s.equals("CNY")) {d=d/10;}
    if (s.equals("MDL")) {d=d/10;}
    if (s.equals("NOK")) {d=d/10;}
    if (s.equals("TJS")) {d=d/10;}
    if (s.equals("UZS")) {d=d/1000;}
    if (s.equals("UAH")) {d=d/10;}
    if (s.equals("CZK")) {d=d/10;}
    if (s.equals("SEK")) {d=d/10;}
    if (s.equals("ZAR")) {d=d/10;}
    if (s.equals("JPY")) {d=d/100;}
    else {d = d;}
    return d;
}

}
