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
import ru.glassexpress.core.addCommand.AddOperator;
import ru.glassexpress.core.get_command.ObservedCommand;
import ru.glassexpress.core.get_command.GetListOperator;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.*;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_builder.RequestBuilder;
import ru.glassexpress.request_chain.RequestController;


import java.util.ArrayList;
import java.util.List;

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


    GetListOperator getListOperator;
    AddOperator addOperator;


    private URLConnection urlConnection;
    private JsonController jsonController;

    ObservedCommand command;

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

        getListOperator = new GetListOperator();
        addOperator = new AddOperator();

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


        fillObservableList(marksList, getListOperator.getMarks(null));
        markListView.getSelectionModel().selectFirst();

        // формируется запрос на получение списка моделей
        if (marksList.size() > 0)
            fillModelsListView();

    }

    // получаем данные с сервера и заполняем ListView моделей автомобилей, выбранной марки
    public void fillModelsListView() {

        car.setMark(markListView.getSelectionModel().getSelectedItem());

        fillObservableList(modelsList, getListOperator.getModels(car));
        modelListView.getSelectionModel().selectFirst();
        System.out.println("показать модели марки " + car.getMark());
        if (modelsList.size() > 0)
            fillGenerationsListView();

    }

    List<BaseObject> currentModelGenerations = new ArrayList<>();

    public void fillGenerationsListView() {
        System.out.println("Запрос поколений авто");

        car.setModel(modelListView.getSelectionModel().getSelectedItem());
        fillObservableList(genList, getListOperator.getGenerations(car));
        currentModelGenerations = getListOperator.getComponents();
    }


    // обновляем данные ObservableList, новыми, прилетевшими с сервера
    public void fillObservableList(ObservableList<String> list, List<String> source) {
        list.clear();
        if (source != null)
            list.addAll(source);
        System.out.println("список марок обновлен");
    }


    void addMark(String mark) {
        System.out.println("Добавляем марку");

        if (addOperator.addMarkIsComplete(mark))
            fillMarksListView();
        else {
            System.out.println("Фиаско!" + mark + " не добавлено");
        }

    }


    void addModel(String model) {
        System.out.println("Добавляем модель");
        if (addOperator.addModelIsComplete(model, car)) {
            fillModelsListView();
        } else {
            System.out.println("Фиаско!" + car.getMark() + " " + model + " не добавлено");
        }


    }

    private void addGeneration(String answer) {

        if (addOperator.addGenerationIsComplete(answer, car)) {
            fillGenerationsListView();
        } else {
            System.out.println("Фиаско!" + car.getMark() + " " + car.getMark() + " " + answer + " не добавлено");
        }
    }


    // обработка нажатия кнопки (добавить модель)
    public void showAddTitleModal(ActionEvent keyEvent) {

        if ((Button) keyEvent.getSource() == addMarkButton) {

            String answer = AlertWindow.dialogWindow("Добавить новую марку авто", "Выыедите марку авто");

            if (answer != null) {
                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить марку " + answer + " в базу?");
                if (isTrue) {
                    addMark(answer);
                    System.out.println("добавить марку авто " + answer);
                }

            }

        } else if ((Button) keyEvent.getSource() == addModelButton) {

            String answer = AlertWindow.dialogWindow("Добавить новую модель авто", "Введите модель марки " + car.getMark());
            if (answer != null) {
                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить " + car.getMark() + " " + answer + " в базу?");
                if (isTrue) {
                    addModel(answer);
                    System.out.println("добавить модель авто " + car.getMark() + " " + car.getModel() + " ");
                }
            }

        } else if ((Button) keyEvent.getSource() == addGenerationButton) {

            String answer = AlertWindow.dialogWindow("Добавить поколение авто " + car.getMark() + " " + car.getModel(), "Введите введите годы в формате (ХХХХ-ХХХХ) ");
            if (answer != null) {
                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить поколение" + car.getMark() + " " + car.getModel() + " " + answer + " в базу?");
                if (isTrue) {
                    addGeneration(answer);
                    System.out.println("добавить поколение авто " + car.getMark() + " " + car.getModel() + " " + answer);
                }
            }

        } else {
            System.out.println("Такую кнопку не умею");
        }
    }


    public void setGeneration() {
        car.setGen((GenerationObj) currentModelGenerations.get(genListView.getSelectionModel().getSelectedIndex()));
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

    public void showGoods() {


    }
}
