package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import ru.glassexpress.JsonController;
import ru.glassexpress.ServerVocabulary;
import ru.glassexpress.URLConnection;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.objects.CarMark;
import ru.glassexpress.objects.Composite;
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
    ListView genListView1;
    @FXML
    ListView modelListView;
    @FXML
    Button addMarkButton;

    enum State {BUSY, FREE}

    ;

    State state = State.FREE;

    void free() {
        state = State.FREE;
    }

    void busy() {
        state = State.BUSY;
    }

    boolean isBusy() {
        if (state == State.BUSY) {
            System.out.println("BUSY");
            return true;
        }
        return false;
    }

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

    // класс авто, заполняющийся после конструктора
    Car car;


    // инициализация конроллера, вызывается при открытии приложения
    @Override
    public void init() {
        urlConnection = URLConnection.getInstance();
        jsonController = JsonController.getInstance();
        car = new Car();
        marks = new HashMap<>();
        markListView.setItems(marksList);
        modelListView.setItems(modelsList);
        fillMarksListView();

    }

    // получаем данные с сервера и заполняем ListView марок автомобилей
    private void fillMarksListView() {
        if (!isBusy()) {
            busy();
            fillList(marksList, getList(ServerVocabulary.TARGET_MARK, ""));
            markListView.getSelectionModel().selectFirst();
            fillModelsListView();
            free();
        }

    }

    // получаем данные с сервера и заполняем ListView моделей автомобилей, выбранной марки
    public void fillModelsListView() {
        if (!isBusy()) {
            busy();

            String selectedItem = markListView.getSelectionModel().getSelectedItem();
            car.setMark(selectedItem);
            String body = "&mark=" + selectedItem;
            fillList(modelsList, getList(ServerVocabulary.TARGET_MODEL, body));
            System.out.println("показать модели авто " + selectedItem);

            free();
        }
    }

    // обновляем данные ObservableList, новыми, прилетевшими с сервера
    public void fillList(ObservableList<String> list, List<String> source) {
        list.clear();
        if (source != null)
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

    // получаем список типа ID=TITLE
    public ObservableList<String> getList(String target, String body) {

        Composite baseObject = (Composite) RequestController.responseToObject(RequestController.recieveResponse("GET", ServerVocabulary.ACTION_SELECT, target, body));
        if (baseObject != null) {
            List<BaseObject> list = baseObject.getComponents();
            ArrayList<String> carMarks = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                CarMark mark = (CarMark) list.get(i);
                carMarks.add(mark.getTitle());
            }
            return FXCollections.observableArrayList(carMarks);
        }
        return null;
    }


    void addMark(String mark) {
        System.out.println("Добавляем марку");
        String body = "&mark=" + mark;

        if (insertElement(ServerVocabulary.TARGET_MARK, body)) {
            fillMarksListView();
        } else {
            System.out.println("Фиаско! не добавлено");
        }
    }

    boolean insertElement(String target, String body) {
        if (!isBusy()) {
            busy();
            if (RequestController.isRequestAccepted(RequestController.recieveResponse("GET", ServerVocabulary.ACTION_INSERT, target, body)))
                return true;
            free();
        }
        return false;
    }

    void addModel(String model) {
        System.out.println("Добавляем модель");

        String body = "&mark=" + car.getMark() + "&model=" + model;
        if (insertElement(ServerVocabulary.TARGET_MODEL, body)) {
            fillModelsListView();
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
                System.out.println("добавить марку авто " + answer);
            }

        } else if ((Button) keyEvent.getSource() == addModelButton) {

            String answer = AlertWindow.dialogWindow("Добавить новую модель авто", "Выыедите модель марки " + car.getMark());
            addModel(answer);
            System.out.println("добавить модель авто");

        } else {
            System.out.println("Такую кнопку не умею");
        }
    }
}

