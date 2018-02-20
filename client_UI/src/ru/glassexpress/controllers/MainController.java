package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import ru.glassexpress.JsonController;
import ru.glassexpress.Prefs;
import ru.glassexpress.URLConnection;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.objects.IdTitleObj;
import ru.glassexpress.objects.Composite;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_builder.RequestBuilder;
import ru.glassexpress.request_chain.RequestController;


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
    ListView<String> modelListView;

    @FXML
    ListView<String> markListView;

    @FXML
    ListView<String> genListView;

    @FXML
    Button addMarkButton;




    private URLConnection urlConnection;
    private JsonController jsonController;

    private ObservableList<String> marksList = FXCollections.observableArrayList();

    private ObservableList<String> modelsList = FXCollections.observableArrayList();
    private ObservableList<String> genList = FXCollections.observableArrayList();

    public Car getCar() {
        return car;
    }

    // класс авто, заполняющийся после конструктора
    Car car;


    // инициализация конроллера, вызывается при открытии приложения
    @Override
    public void init() {
        urlConnection = URLConnection.getInstance();
        jsonController = JsonController.getInstance();
        car = new Car();

        markListView.setItems(marksList);
        modelListView.setItems(modelsList);
        genListView.setItems(genList);
        reconnect();

    }

    public void reconnect() {
        fillMarksListView();
    }

    // получаем данные с сервера и заполняем ListView марок автомобилей
    private void fillMarksListView() {
//        if (!isBusy()) {
//            busy();
        // формируется запрос на получение списка марок
        Request requestMark = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_MARK)
                .setBlankRequest()
                .build();

        fillObservableList(marksList, getList(requestMark));
        markListView.getSelectionModel().selectFirst();

        // формируется запрос на получение списка моделей
        if (marksList.size() > 0)
            fillModelsListView();
//            free();
//        }

    }

    // получаем данные с сервера и заполняем ListView моделей автомобилей, выбранной марки
    public void fillModelsListView() {
//        if (!isBusy()) {
//            busy();
        Request requestModel = new RequestBuilder().setMethod(Prefs.METHOD_GET)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_MODEL)
                .setRequest("mark", markListView.getSelectionModel().getSelectedItem())
                .build();
        String selectedItem = markListView.getSelectionModel().getSelectedItem();
        car.setModel(selectedItem);

        fillObservableList(modelsList, getList(requestModel));
        System.out.println("показать модели авто " + selectedItem);
        if (modelsList.size() > 0)
            fillGenerationsListView();
//            free();
//        }

//
//            free();
//        }
    }
    public void fillGenerationsListView() {
        System.out.println("Запрос поколений авто");

        Request requestGeneration = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_GENERATION)
                .setRequest("mark", markListView.getSelectionModel().getSelectedItem())
                .setRequest("model", modelListView.getSelectionModel().getSelectedItem())
                .build();
        fillObservableList(genList, getList(requestGeneration));
        //genListView.getSelectionModel().selectFirst();


        // формируется запрос на получение списка моделей
//        if (genList.size()>0)
//            fillModelsListView( );
////            free();
////        }

    }


    // обновляем данные ObservableList, новыми, прилетевшими с сервера
    public void fillObservableList(ObservableList<String> list, List<String> source) {
        list.clear();
        if (source != null)
            list.addAll(source);
        System.out.println("список марок обновлен");
    }

    void getMarkList() {

    }

    void getModelList() {

    }

    void fillGenerations() {


    }


    void getPhoto(Car car) {

    }

    void showGoods(Car car) {

    }

    // получаем список типа ID=TITLE
    public ObservableList<String> getList(Request request) {

        Composite baseObject = (Composite) RequestController.responseToObject(RequestController.recieveResponse(request));
        if (baseObject != null) {
            List<BaseObject> list = baseObject.getComponents();
            ArrayList<String> carMarks = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                IdTitleObj mark = (IdTitleObj) list.get(i);
                carMarks.add(mark.getTitle());
            }
            return FXCollections.observableArrayList(carMarks);
        }
        return null;
    }


    boolean insertElement(Request request) {
//        if (!isBusy()) {
//            busy();
        if (RequestController.isRequestAccepted(RequestController.recieveResponse(request)))
            return true;
//            free();
//        }
        return false;
    }

    void addMark(String mark) {
        System.out.println("Добавляем марку");

        Request request = new RequestBuilder().setMethod(Prefs.METHOD_GET)
                .setTarget(Prefs.TARGET_MARK)
                .setAction(Prefs.ACTION_INSERT)
                .setRequest("mark", mark)
                .build();

        if (insertElement(request)) {
            fillMarksListView();
        } else {
            System.out.println("Фиаско!" + mark + " не добавлено");
        }
    }


    void addModel(String model) {
        System.out.println("Добавляем модель");

        Request request = new RequestBuilder().setMethod(Prefs.METHOD_GET)
                .setTarget(Prefs.TARGET_MODEL)
                .setAction(Prefs.ACTION_INSERT)
                .setRequest("mark", car.getMark())
                .setRequest("model", model)
                .build();

        String body = "&mark=" + car.getMark() + "&model=" + model;
        if (insertElement(request)) {

            fillModelsListView();
        } else {
            System.out.println("Фиаско!" + car.getMark() + " " + model + " не добавлено");
        }

    }

    private void addGeneration(String answer) {
        System.out.println("Добавляем поколение");
        answer=answer.replace(" ", "");
        String[] gen = answer.split("-");
        Request request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setTarget(Prefs.TARGET_GENERATION)
                .setAction(Prefs.ACTION_INSERT)
                .setRequest("mark", car.getMark())
                .setRequest("model", car.getModel())
                .setRequest("yearFrom", gen[0])
                .setRequest("yearTo", gen[1])
                .build();
        if (insertElement(request)) {

            fillModelsListView();
        } else {
            System.out.println("Фиаско!" + car.getMark() + " " + car.getMark() +" " + answer+" не добавлено");
        }
    }

    // обработка нажатия кнопки (добавить модель)
    public void showAddTitleModal(ActionEvent keyEvent) {

        if ((Button) keyEvent.getSource() == addMarkButton) {

            String answer = AlertWindow.dialogWindow("Добавить новую марку авто", "Выыедите марку авто");

            if (answer != null) {
                boolean isShure = AlertWindow.confirmationWindow("Вы уверены?", "Добавить марку " + answer + " в базу?");
                if (isShure) {
                    addMark(answer);
                    System.out.println("добавить марку авто " + answer);
                }

            }

        } else if ((Button) keyEvent.getSource() == addModelButton) {

            String answer = AlertWindow.dialogWindow("Добавить новую модель авто", "Введите модель марки " + car.getMark());
            if (answer != null) {
                boolean isShure = AlertWindow.confirmationWindow("Вы уверены?", "Добавить " + car.getMark() + " " + answer + " в базу?");
                if (isShure) {
                    addModel(answer);
                    System.out.println("добавить модель авто " + answer);
                }
            }

        } else if ((Button) keyEvent.getSource() == addGenerationButton) {

            String answer = AlertWindow.dialogWindow("Добавить поколение авто", "Введите введите годы в формате (ХХХХ-ХХХХ) " + car.getMark());
            if (answer != null) {
                boolean isShure = AlertWindow.confirmationWindow("Вы уверены?", "Добавить поколение" + car.getMark() + " " + answer + " в базу?");
                if (isShure) {
                    addGeneration(answer);
                    System.out.println("добавить модель авто " + answer);
                }
            }

        } else {
            System.out.println("Такую кнопку не умею");
        }
    }


}

//enum State {BUSY, FREE}

;

//    State state = State.FREE;
//
//    void free() {
//        state = State.FREE;
//    }
//
//    void busy() {
//        state = State.BUSY;
//    }
//
//    boolean isBusy() {
//        if (state == State.BUSY) {
//            System.out.println("BUSY");
//            return true;
//        }
//        return false;
//    }
