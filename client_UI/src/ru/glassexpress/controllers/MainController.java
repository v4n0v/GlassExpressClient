package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import ru.glassexpress.JsonController;
import ru.glassexpress.ServerVocabulary;
import ru.glassexpress.URLConnection;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.objects.CarMark;
import ru.glassexpress.objects.Composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController extends BaseController {
    @FXML
    Button addGenerationButton;
    @FXML
    Button addModelButton;
    @FXML
    ListView genListView1;
    @FXML
    ListView modelListView;
    @FXML
    Button addMarkButton;

    @FXML
    ListView<String> markListView;
    private URLConnection urlConnection;
    private JsonController jsonController;

    private ObservableList<String> marksList = FXCollections.observableArrayList();
    ;
    private ObservableList<String> modelsList = FXCollections.observableArrayList();
    ;
    Map<Integer, String> marks;

    public Car getCar() {
        return car;
    }

    Car car;

    @Override
    public void init() {
        urlConnection = new URLConnection();
        jsonController = new JsonController();
        car = new Car();
        marks = new HashMap<>();
        markListView.setItems(marksList);
        modelListView.setItems(modelsList);
        showMarks();

    }

    void showMarks() {

        fillList(marksList, getMarks());
        markListView.getSelectionModel().selectFirst();
        showModels();

    }
    private void showModels() {
        String selectedItem = markListView.getSelectionModel().getSelectedItem();
        car.setMark(selectedItem);
        fillList(modelsList, getModels(selectedItem));
        // modelsList = getModels(selectedItem);
//
//        if (modelsList != null) {
//            modelListView.setItems(modelsList);
//            //получаем модели
//           // showModels();
//        } else modelListView.setItems(null);
        System.out.println("показать модели авто " + selectedItem);
    }
    public void fillList(ObservableList<String> list, List<String> source) {
        list.clear();
        if (source!=null)
            list.addAll(source);
        System.out.println("список марок обновлен");
    }

    void getMarkList() {

    }

    void getModelList() {

    }

    void getGenerations() {

    }

    void getPhoto(String carName) {

    }

    void showGoods(Car car) {

    }



    public ObservableList<String> getModels(String mark) {
        String jsonResponse = null;
        String body = "&mark=" + mark;
        try {
            jsonResponse = urlConnection.receiveData("GET", ServerVocabulary.ACTION_SELECT, ServerVocabulary.TARGET_MODEL, body);
            if (jsonResponse != null) {
                if (!jsonResponse.equals(ServerVocabulary.ERROR_RESPONSE)) {

                    // получаем список и переносим его во вью
                    Composite baseObject = (Composite) jsonController.convertJsonToObject(jsonResponse);
                    List<BaseObject> list = baseObject.getComponents();
                    ArrayList<String> carMarks = new ArrayList<>();

                    for (int i = 0; i < list.size(); i++) {
                        CarMark models = (CarMark) list.get(i);
                        carMarks.add(models.getTitle());
                    }

                    return FXCollections.observableArrayList(carMarks);
                } else {
                    modelsList.clear();
                }
            }
        } catch (Exception e) {
            System.out.println("Это фиаско, братан!");
            AlertWindow.errorMessage("Ошибка соединения с сервером :(");

        }
        return null;

    }

    public ObservableList<String> getMarks() {
        String jsonResponse = null;
        try {
            jsonResponse = urlConnection.receiveData("GET", ServerVocabulary.ACTION_SELECT, ServerVocabulary.TARGET_MARK, "");
            if (jsonResponse != null) {
                if (!jsonResponse.equals(ServerVocabulary.ERROR_RESPONSE)) {
                    // получаем список и переносим его во вью
                    Composite baseObject = (Composite) jsonController.convertJsonToObject(jsonResponse);
                    List<BaseObject> list = baseObject.getComponents();
                    ArrayList<String> carMarks = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        CarMark mark = (CarMark) list.get(i);
                        carMarks.add(mark.getTitle());
                    }

                    return FXCollections.observableArrayList(carMarks);
                } else {
                    marksList.clear();
                }
            }
        } catch (Exception e) {
            System.out.println("Это фиаско, братан!");
            AlertWindow.errorMessage("Ошибка соединения с сервером :(");

        }

        return null;
    }

    String connect(String method, String action, String target, String body) {

        try {
            return urlConnection.receiveData(method, action, target, body);
        } catch (Exception e) {
            System.out.println("Это фиаско, братан!");
            AlertWindow.errorMessage("Ошибка соединения с сервером :(");

        }
        return null;
    }


    public void showModels(MouseEvent mouseEvent) {
        showModels();
    }


    void addElement(String method, String target, String body) {

    }

    void addMark(String mark) {
        System.out.println("Добавляем марку");
        String body = "&mark=" + mark;
        String jsonResponse = urlConnection.receiveData("GET", ServerVocabulary.ACTION_INSERT, ServerVocabulary.TARGET_MARK, body);
        //
        if (jsonResponse.equals("ok")) {
            System.out.println(mark + ", новая марка добавлена в базу");

            showMarks();
        } else {
            System.out.println("Фиаско! не добавлено");
        }
    }


    void addModel(String model) {
        System.out.println("Добавляем модель");

        String body = "&mark=" + car.getMark() + "&model=" + model;
        String jsonResponse = urlConnection.receiveData("GET", ServerVocabulary.ACTION_INSERT, ServerVocabulary.TARGET_MODEL, body);

        if (jsonResponse.equals("ok")) {
            System.out.println(model + ", новая модель марки " + car.getMark() + " добавлена в базу");
            markListView.getSelectionModel().getSelectedItem();
            showModels();
        } else {
            System.out.println("Фиаско! не добавлено");
        }

    }


    // обработка нажатия кнопки (добавить модель)
    public void showAddTitleModal(ActionEvent keyEvent) {
        if ((Button) keyEvent.getSource() == addMarkButton) {

            String answer = AlertWindow.dialogWindow("Добавить новую марку авто", "Выыедите марку авто");
            if (answer != null) {
                addMark(answer);
//            mainApp.initAddTitleLayout("Добавить новую марку авто", ServerVocabulary.ADD_MARK);
                System.out.println("добавить марку авто " + answer);
            }

        } else if ((Button) keyEvent.getSource() == addModelButton) {
            String answer = AlertWindow.dialogWindow("Добавить новую модель авто", "Выыедите модель марки " + car.getMark());
            addModel(answer);
            System.out.println("добавить модель авто");

        } else {
            System.out.println("Такую кнопку не умею");
        }
        //    showModels();
    }


}
