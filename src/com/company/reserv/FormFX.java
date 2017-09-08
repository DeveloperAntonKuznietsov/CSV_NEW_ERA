//package com.company.reserv;
//
//import com.company.JSOUP;
//import com.company.ReadCSVWithScanner;
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
//import java.io.File;
//import java.io.FileWriter;

//
//import static java.lang.String.valueOf;
//
//
//public class FormFX extends Application{
//    Button buttonChooser;
//    FileChooser fileChooser;
//    DatePicker calendar;
//    TextField kurs;
//    TextField vvod;
//    TextField vuvod;
//    ChoiceBox choiceBoxValut;
//    ChoiceBox choiceBoxProdavzov;
//    Label labelkalendar;
//    Label labeltip;
//    Label labelkurs;
//    Label labelvvod;
//    Label labelvuvod;
//    Label labelDokument;
//    Label labelOvalute;
//    Label labelProdavzu;
//    Label textArea;
//    ScrollPane scrollPane;
//    Image imageOK;
//    Image imageNOT;
//    ReadCSVWithScanner readCSVWithScanner;
//    Button buttonSave;
//    Button buttonDeletValuta;
//    FileChooser fileChooserSave;
//    File formatter;
//    Image imgPlaneta;
//    ListView<String> scrollPaneProdavcov;
//    JSOUP jsoup;
//
//
//    public static void main(String[] args) {
//       launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("Конвидив");
//        GridPane gridPane = new GridPane();
//        gridPane.setHgap(15);
//        gridPane.setVgap(15);
//
//        calendar=new DatePicker();
//        calendar.setDisable(true);
//        kurs = new TextField();
//        kurs.setDisable(true);
//        vvod = new TextField("");
//        vuvod = new TextField("");
//        labelkalendar = new Label("Введите дату:");
//        labeltip = new Label("Выберете валюту:");
//        labelkurs = new Label("Курс в руб. за одну единицу:");
//        labelvvod = new Label("Введите сумму:");
//        labelvuvod = new Label("Вывод суммы:");
//        labelProdavzu = new Label("Список продавцов:");
//        labelDokument = new Label("Выберите документ:");
//        labelOvalute = new Label("Вся валюта:");
//        labelDokument.setFont(Font.font(13));
//        //изображение иконки ок
//        imageOK = new Image(getClass().getResourceAsStream("ok.png"));
//        //изображение иконки не ок
//        imageNOT = new Image(getClass().getResourceAsStream("not.png"));
//        //////
//        imgPlaneta = new Image(getClass().getResourceAsStream("tenor.gif"));
//        ImageView imageViewPlaneta=new ImageView(imgPlaneta);
//        ImageView imageViewNOTtoOK = new ImageView(imageNOT);
//        ImageView imageViewNOTtoOK2 = new ImageView(imageNOT);
//        imageViewNOTtoOK.setFitHeight(20);
//        imageViewNOTtoOK.setFitWidth(20);
//        imageViewNOTtoOK2.setFitHeight(20);
//        imageViewNOTtoOK2.setFitWidth(20);
//
//        imageViewPlaneta.setVisible(true);
//
//        buttonDeletValuta = new Button("Удалить валюту ");
//        buttonDeletValuta.setPrefSize(111,10);
//        buttonDeletValuta.setVisible(false);
//        buttonDeletValuta.setOnAction(e-> {textArea.setText("");
//        imageViewPlaneta.setVisible(true);});
//
//
//        fileChooser = new FileChooser();
//        fileChooser.setTitle("Выбрать файл");
//        FileChooser.ExtensionFilter fileVubora=
//                new FileChooser.ExtensionFilter(
//                        "CSV files", "*.csv");
//
//        fileChooser.getExtensionFilters().addAll(fileVubora);
//        buttonChooser = new Button("Выбрать файл");
//        //кнопка выбора файлов и смены иконок
//        buttonChooser.setOnAction(e-> {
//                File file = fileChooser.showOpenDialog(primaryStage);
//                try {
//                    readCSVWithScanner = new ReadCSVWithScanner(file);
//                } catch (IOException event) {
//                    event.printStackTrace();
//                }
//                if (file!=null){
//                    imageViewNOTtoOK.setImage(imageOK);
//                    imageViewNOTtoOK2.setImage(imageOK);
//                    calendar.setDisable(false);
//                }
//                //задаём параметры боксу из полученого CSV файла
//
//               // choiceBoxProdavzov = new ChoiceBox<String>(FXCollections.observableArrayList(readCSVWithScanner.listProdavzovArray));
//                 scrollPaneProdavcov = new ListView<>();
//                 scrollPaneProdavcov.setItems(FXCollections.observableArrayList(readCSVWithScanner.listProdavzovArray));
//                 scrollPaneProdavcov.setMaxSize(150,100);
//                 scrollPaneProdavcov.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//                gridPane.add(scrollPaneProdavcov,4,5,3,3);
//                gridPane.add(labelProdavzu,1,5,3,1);
//               // gridPane.add(scrollPaneProdavcov,1,6,3,1);
//                 //логика обработки событей календаря и курса
////                choiceBoxValut.setOnAction(new EventHandler<ActionEvent>() {
////                    @Override
////                    public void handle(ActionEvent event) {
////                        if(!calendar.equals(null)&&!choiceBoxValut.equals(null)) {
////                            String data = valueOf(calendar.getValue());
////                            JSOUP jsoup = null;
////                            try {
////                                jsoup = new JSOUP(data.replaceAll("-", "."));
////                            } catch (IOException e) {
////                                e.printStackTrace();
////                            }
////                            System.out.println(jsoup.mapKurs.get(choiceBoxValut.getValue()));
////                            //вывод в поле курс валют и подгон параметров
////
////                        if (choiceBoxValut.getValue().equals("JPY")){
////                          double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                          d/=100;
////                          kurs.setText(valueOf(d));
////                        }
////                        else if (choiceBoxValut.getValue().equals("AMD")){double d=jsoup.mapKurs.get(choiceBoxValut.getValue()); d/=100;
////                            kurs.setText(valueOf(d));
////                        }
////                        else if (choiceBoxValut.getValue().equals("HUF")){
////                            double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                            d/=100;
////                            kurs.setText(valueOf(d));
////                        }
////                        else if (choiceBoxValut.getValue().equals("KRW")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=1000;
////                                kurs.setText(valueOf(d));
////                        }
////                        else  if (choiceBoxValut.getValue().equals("HKD")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=10;
////                                kurs.setText(valueOf(d));
////                            }
////                        else  if (choiceBoxValut.getValue().equals("DKK")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=10;
////                                kurs.setText(valueOf(d));
////                            }
////                        else   if (choiceBoxValut.getValue().equals("INR")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=100;
////                                kurs.setText(valueOf(d));
////                            }
////                        else   if (choiceBoxValut.getValue().equals("KZT")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=100;
////                                kurs.setText(valueOf(d));
////                            }
////                        else   if (choiceBoxValut.getValue().equals("KGS")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=100;
////                                kurs.setText(valueOf(d));
////                            }
////                        else  if (choiceBoxValut.getValue().equals("CNY")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=10;
////                                kurs.setText(valueOf(d));
////                            }
////                        else   if (choiceBoxValut.getValue().equals("MDL")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=10;
////                                kurs.setText(valueOf(d));
////                            }
////                        else   if (choiceBoxValut.getValue().equals("NOK")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=10;
////                                kurs.setText(valueOf(d));
////                            }
////                        else   if (choiceBoxValut.getValue().equals("TJS")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=10;
////                                kurs.setText(valueOf(d));
////                            }
////                        else   if (choiceBoxValut.getValue().equals("UZS")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=1000;
////                                kurs.setText(valueOf(d));
////                            }
////                        else   if (choiceBoxValut.getValue().equals("UAH")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=10;
////                                kurs.setText(valueOf(d));
////                            }
////                        else  if (choiceBoxValut.getValue().equals("CZK")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=10;
////                                kurs.setText(valueOf(d));
////                            }
////                           else if (choiceBoxValut.getValue().equals("SEK")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=10;
////                                kurs.setText(valueOf(d));
////                            }
////                         else   if (choiceBoxValut.getValue().equals("ZAR")){
////                                double d=jsoup.mapKurs.get(choiceBoxValut.getValue());
////                                d/=10;
////                                kurs.setText(valueOf(d));
////                            }
////                         else kurs.setText(valueOf(jsoup.mapKurs.get(choiceBoxValut.getValue())));
////                        }
////                    }
////                });
//                buttonSave.setVisible(true);
//                buttonSave.setDisable(true);
//                buttonDeletValuta.setVisible(true);
//                buttonDeletValuta.setDisable(true);
//
//        });
//
//        textArea = new Label();
//        //логика обработки событей календаря и курса
//        calendar.setOnAction(e-> {
////                if(!calendar.equals(null)&&!choiceBoxValut.equals(null)){
//                    String data = valueOf(calendar.getValue());
//                    JSOUP jsoup = null;
//                    try {
//                        jsoup = new JSOUP(data.replaceAll("-","."));
//                        choiceBoxValut = new ChoiceBox<String>(FXCollections.observableArrayList(jsoup.arrayListValut));
//                        gridPane.add(choiceBoxValut,5,2);
//                    } catch (IOException event) {
//                        event.printStackTrace();
//                    }
//                    System.out.println(jsoup.mapKurs.get(choiceBoxValut.getValue()));
//                    //вывод в поле курс валют
//                     kurs.setText(valueOf(jsoup.mapKurs.get(choiceBoxValut.getValue())));
//                     imageViewPlaneta.setVisible(false);
//                    for (int i =0;i<jsoup.tablValutus.size();i++)
//                    {
//                        textArea.setText(textArea.getText()+String.valueOf(jsoup.tablValutus.get(i).codValutu+" "));
//                        textArea.setText(textArea.getText()+String.valueOf(jsoup.tablValutus.get(i).abreviaturaValutu+" "));
//                        textArea.setText(textArea.getText()+String.valueOf(jsoup.tablValutus.get(i).imjaValuta+" "));
//                        textArea.setText(textArea.getText()+String.valueOf(jsoup.tablValutus.get(i).koli4estvo+" "));
//                        textArea.setText(textArea.getText()+String.valueOf(jsoup.tablValutus.get(i).kurs+"\n"));
//                    }
////                }
//                buttonSave.setDisable(false);
//                buttonDeletValuta.setDisable(false);
//        });
//
//        buttonSave = new Button("Сохранить курсы");
//        buttonSave.setVisible(false);
//        fileChooserSave = new FileChooser();
//        FileChooser.ExtensionFilter fileSave =
//                new FileChooser.ExtensionFilter(
//                        "tex files", "*.txt");
//
//        fileChooserSave.getExtensionFilters().addAll(fileSave);
//
//
//        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//              formatter= fileChooserSave.showSaveDialog(primaryStage);
//              fileChooserSave.setTitle("Сохранить курс влют");
//
//                try{
//                    FileWriter fwr = new FileWriter(formatter);
//                    fwr.write(textArea.getText());
//                    fwr.flush();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//
//
//
//        //scrollPane = new ScrollPane(textArea);
//        gridPane.add(labelDokument,1,1);
//        gridPane.add(imageViewNOTtoOK,2,1);
//        gridPane.add(buttonChooser,1,2);
//        gridPane.add(labelkalendar, 1,3);
//        gridPane.add(calendar,1,4,2,1);
//        gridPane.add(labeltip, 5,1);
//        gridPane.add(imageViewNOTtoOK2,6,1);
//        gridPane.add(labelkurs, 5,3,2,1);
//        gridPane.add(kurs, 5,4,2,1);
//        gridPane.add(labelOvalute, 8,1,2,1);
//        gridPane.add(textArea,8,2,8,10);
//        gridPane.add(buttonSave,10,1,2,1);
//        gridPane.add(buttonDeletValuta,12,1,2,1);
//        gridPane.add(imageViewPlaneta,8,2,8,10);
//
//
//
//
//
//        Group root = new Group();
//        root.getChildren().add(gridPane);
//        Scene scene = new Scene(root,930,650);
//        scene.setFill(Color.ALICEBLUE);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//}
