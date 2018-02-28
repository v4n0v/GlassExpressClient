package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.glassexpress.JsonController;
import ru.glassexpress.URLConnection;
import ru.glassexpress.core.addCommand.AddOperator;
import ru.glassexpress.core.get_command.ObservedCommand;
import ru.glassexpress.core.get_command.GetListOperator;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.*;


import java.util.ArrayList;
import java.util.List;

public class MainController extends BaseController {
    @FXML
    ComboBox bodyTypeListView;
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

    @FXML
    ToggleGroup radioGroup;

    @FXML
    RadioButton isFrontRadio;
    @FXML
    RadioButton isRearRadio;


    GetListOperator getListOperator;
    AddOperator addOperator;


    private URLConnection urlConnection;
    private JsonController jsonController;

    ObservedCommand command;

    private ObservableList<String> marksList = FXCollections.observableArrayList();

    private ObservableList<String> modelsList = FXCollections.observableArrayList();
    private ObservableList<String> genList = FXCollections.observableArrayList();
    private ObservableList<String> bodyTypeStringList = FXCollections.observableArrayList();
    private List<IdTitleObj> glassOptList;
    private AddGlassController aggGlassController;

    public Car getCar() {
        return car;
    }

    // класс авто, заполняющийся после конструктора
    Car car;
    BaseObjectAdapter adapter;
    List<String> glassTypeList;
    List<IdTitleObj> bodyTypeList;

    public List<IdTitleObj> getGlassFactoryList() {
        return glassFactoryList;
    }

    List<IdTitleObj> glassFactoryList;
    // инициализация конроллера, вызывается при открытии приложения
    @Override
    public void init() {
        urlConnection = URLConnection.getInstance();
        jsonController = JsonController.getInstance();
        car = new Car();
        adapter = new BaseObjectAdapter();
        getListOperator = new GetListOperator();
        addOperator = new AddOperator();
        glassTypeList = new ArrayList<>();

        markListView.setItems(marksList);
        modelListView.setItems(modelsList);
        genListView.setItems(genList);
        bodyTypeListView.setItems(bodyTypeStringList);
        radioGroup = new ToggleGroup();
        isFrontRadio.setToggleGroup(radioGroup);
        isRearRadio.setToggleGroup(radioGroup);

        reconnect();
        // получаем список типов стекол, кузовов
        glassTypeList = getListOperator.getGlassTypes();
        bodyTypeList = getListOperator.getBodyTypes();
        glassOptList = getListOperator.getGlassOptions();
        glassFactoryList=getListOperator.getGlassFactory();

        fillObservableList(bodyTypeStringList, adapter.IdTitleObjToString(bodyTypeList));

        System.out.println(glassTypeList);
    }

    public void reconnect() {
        fillMarksListView();
    }

    // получаем данные с сервера и заполняем ListView марок автомобилей
    private void fillMarksListView() {


            fillObservableList(marksList, getListOperator.getMarks(null));
        if (marksList.size() > 0) {
            markListView.getSelectionModel().selectFirst();

            // формируется запрос на получение списка моделей

            fillModelsListView();
        }
    }

    // получаем данные с сервера и заполняем ListView моделей автомобилей, выбранной марки
    public void fillModelsListView() {

            car.setMark(markListView.getSelectionModel().getSelectedItem());

            fillObservableList(modelsList, getListOperator.getModels(car));
        if (modelsList.size() > 0) {
            modelListView.getSelectionModel().selectFirst();
            System.out.println("показать модели марки " + car.getMark());

            fillGenerationsListView();
        }
    }

    List<BaseObject> currentModelGenerations = new ArrayList<>();

    public void fillGenerationsListView() {

            System.out.println("Запрос поколений авто");

            car.setModel(modelListView.getSelectionModel().getSelectedItem());
            fillObservableList(genList, getListOperator.getGenerations(car));
        if (genList.size() > 0) {
            currentModelGenerations = getListOperator.getComponents();
        }
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
            currentModelGenerations = getListOperator.getComponents();
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
        System.out.println("показываю подходящий товар");
        initTGTable();

    }

    // таблица стекол на складе
    ObservableList<TableGoodsInStockRow> tableGoodsInStockRows;
    @FXML
    private TableView<TableGoodsInStockRow> tblGoodsInStock;
    @FXML
    private TableColumn<TableGoodsInStockRow, Integer> colTGId;
    @FXML
    private TableColumn<TableGoodsInStockRow, Float> colTGPrice;
    @FXML
    private TableColumn<TableGoodsInStockRow, String> colTGDesc;

    private void initTGTable() {
        tableGoodsInStockRows = getListOperator.getTableGoods(car);


        tblGoodsInStock.setEditable(false);
        colTGId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        colTGDesc.setCellValueFactory(cellData -> cellData.getValue().getDescProperty());
        colTGPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        tblGoodsInStock.setItems(tableGoodsInStockRows);
    }

    public void buildCar() {
        System.out.println("заполняю объект Car");
        car.setGen((GenerationObj) currentModelGenerations.get(genListView.getSelectionModel().getSelectedIndex()));
        car.setId(car.getGen().getModelID());
        System.out.println(car);

    }

    public void addGlass() {
        mainApp.initAddGlassLayout();
        aggGlassController.setCarId(car.getId());
    }

    public List<IdTitleObj> getGlassOptList() {
        return glassOptList;
    }

    public void setAggGlassController(AddGlassController aggGlassController) {
        this.aggGlassController = aggGlassController;
    }
}
