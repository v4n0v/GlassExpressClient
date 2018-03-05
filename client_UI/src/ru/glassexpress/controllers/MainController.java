package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.glassexpress.JsonController;
import ru.glassexpress.URLConnection;
import ru.glassexpress.core.edit_content_command.addCommand.AddOperator;
import ru.glassexpress.core.edit_content_command.deleteCommand.DeleteOperator;
import ru.glassexpress.core.get_command.ObservedCommand;
import ru.glassexpress.core.get_command.GetListOperator;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.*;


import java.util.List;

public class MainController extends BaseController {


    @FXML
    ComboBox<String> bodyTypeListView;
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
    DeleteOperator deleteOperator;

    private URLConnection urlConnection;
    private JsonController jsonController;

    ObservedCommand command;

    private ObservableList<String> marksList = FXCollections.observableArrayList();

    private ObservableList<String> modelsList = FXCollections.observableArrayList();
    private ObservableList<String> genList = FXCollections.observableArrayList();
    private ObservableList<String> bodyTypeStringList = FXCollections.observableArrayList();

    private AddGlassController aggGlassController;
    @FXML
    Button delGenerationButton;
    @FXML
    Button delModelButton;
    @FXML
    Button delMarkButton;
    public Car getCar() {
        return car;
    }

    // класс авто, заполняющийся после конструктора
    Car car;
    BaseObjectAdapter adapter;


    // инициализация конроллера, вызывается при открытии приложения
    @Override
    public void init() {
        initTGTable();
        urlConnection = URLConnection.getInstance();
        jsonController = JsonController.getInstance();
        car = new Car();
        adapter = new BaseObjectAdapter();
        getListOperator = new GetListOperator();
        deleteOperator = new DeleteOperator();
        addOperator = new AddOperator();
        // glassTypeList = new ArrayList<>();


        radioGroup = new ToggleGroup();
        isFrontRadio.setToggleGroup(radioGroup);
        isRearRadio.setToggleGroup(radioGroup);

        reconnect();
        // получаем список типов стекол, кузовов
        dataMap.setGlassTypeList(getListOperator.getGlassTypes());
        dataMap.setBodyTypeList(getListOperator.getBodyTypes());
        dataMap.setGlassOptList(getListOperator.getGlassOptions());
        dataMap.setGlassFactoryList(getListOperator.getGlassFactory());

        fillObservableList(bodyTypeStringList, adapter.idTitleObjToString(dataMap.getBodyTypeList()));

        System.out.println(dataMap.getBodyTypeList());
    }

    public void reconnect() {
        markListView.setItems(marksList);
        modelListView.setItems(modelsList);
        genListView.setItems(genList);
        bodyTypeListView.setItems(bodyTypeStringList);
        fillMarksListView();
    }

    // получаем данные с сервера и заполняем ListView марок автомобилей
    private void fillMarksListView() {

        dataMap.setCarMarksList(getListOperator.getMarks());
        fillObservableList(marksList, adapter.idTitleObjToString(dataMap.getCarMarksList()));
        if (marksList.size() > 0) {
            markListView.getSelectionModel().selectFirst();

            // формируется запрос на получение списка моделей

            fillModelsListView();
        }
    }

    // получаем данные с сервера и заполняем ListView моделей автомобилей, выбранной марки
    public void fillModelsListView() {

        car.setMark(markListView.getSelectionModel().getSelectedItem());
        dataMap.setCarModelsList(getListOperator.getModels(car));
        fillObservableList(modelsList, adapter.idTitleObjToString(dataMap.getCarModelsList()));
        if (modelsList.size() > 0) {
            modelListView.getSelectionModel().selectFirst();
            System.out.println("показать модели марки " + car.getMark());

            fillGenerationsListView();
        }
    }

//    private List<BaseObject> currentModelGenerations = new ArrayList<>();

    public void fillGenerationsListView() {

        System.out.println("Запрос поколений авто");

        car.setModel(modelListView.getSelectionModel().getSelectedItem());
        IdTitleObj model = dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex());
        dataMap.setGenerationObjList(getListOperator.getGenerations(model));
        fillObservableList(genList, adapter.generationObjToString(dataMap.getGenerationObjList()));
        if (genList.size() > 0) {
            genListView.getSelectionModel().selectFirst();
            //currentModelGenerations = getListOperator.getComponents();
        } else {
            System.out.println("пустой список поколений");
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
            //  dataMap.setGenerationObjList(getListOperator.getGenerations(car));
        } else {
            System.out.println("Фиаско!" + car.getMark() + " " + car.getMark() + " " + answer + " не добавлено");
        }
    }


    private void deleteGeneration(int id) {
        if (deleteOperator.deleteGenerationIsComplete(id)) {
            fillGenerationsListView();
        } else {
            System.out.println("Фиаско! Не удалено");
        }
    }

    private void deleteModel(int id) {
        if (deleteOperator.deleteModelIsComplete(id)) {
            fillModelsListView();
        } else {
            System.out.println("Фиаско! Не удалено");
        }
    }
    private void  deleteMark(int id) {
        if (deleteOperator.deleteMarkIsComplete(id)) {
            fillMarksListView();
        } else {
            System.out.println("Фиаско! Не удалено");
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
                    System.out.println("добавить модель авто " + getSelectedMarkTitle() + " ");
                }
            }

        } else if ((Button) keyEvent.getSource() == addGenerationButton) {

            String answer = AlertWindow.dialogWindow("Добавить поколение авто " + car.getMark() + " " + car.getModel(), "Введите введите годы в формате (ХХХХ-ХХХХ) ");
            if (answer != null) {
                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить поколение " + car.getMark() + " " + car.getModel() + " " + answer + " в базу?");
                if (isTrue) {
                    addGeneration(answer);
                    System.out.println("добавить поколение авто " + getSelectedModelTitle() + " " + answer);
                }
            }

        } else if ((Button) keyEvent.getSource() == delGenerationButton) {

            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить поколение " + car.getMark() + " " + car.getModel() + " " + " из базы?");
            if (isTrue) {
                int id = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId();
                deleteGeneration(id);
                System.out.println("удалить поколение авто " + getSelectedModelTitle());
            }


        } else if ((Button) keyEvent.getSource() == delModelButton) {
            System.out.println("удаляем модель " + getSelectedModelTitle());
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить модель " + car.getMark() + " " + car.getModel() + " " + " из базы?");
            if (isTrue) {
                int id = dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex()).getId();
                deleteModel(id);
                System.out.println("удалить модель авто " + car.getMark() + " " + car.getModel());
            }
        } else if ((Button) keyEvent.getSource() == delMarkButton) {
            System.out.println("удаляем марку " + getSelectedMarkTitle());
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить марку " + getSelectedMarkTitle() + " " + " из базы?");
            if (isTrue) {
                int id = dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex()).getId();
                deleteMark(id);
                System.out.println("удалить модель авто " + getSelectedMarkTitle());
            }
        } else {
            System.out.println("Такую кнопку не умею");
        }
    }


    public void setGeneration() {
        car.setGen((GenerationObj) dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()));
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
        List<GenerationObj> generationObjList= dataMap.getGenerationObjList();
        if (generationObjList!=null&&generationObjList.size()>0){
            glassObjects = getListOperator.getTableGoods(car);
        } else {
            AlertWindow.errorMessage("Укажите поколение авто");
        }
        System.out.println("показываю подходящий товар");

        //initTGTable();

    }

    // таблица стекол на складе
    ObservableList<GlassObject> glassObjects;
    @FXML
    private TableView<GlassObject> tblGoodsInStock;
    @FXML
    private TableColumn<GlassObject, Integer> colTGId;
    @FXML
    private TableColumn<GlassObject, Float> colTGPrice;
    @FXML
    private TableColumn<GlassObject, String> colTGDesc;
    @FXML
    private TableColumn<GlassObject, String> colTGFactory;
    @FXML
    private TableColumn<GlassObject, String> colTGOption;
    @FXML
    private TableColumn<GlassObject, String> colTGType;
    @FXML
    private TableColumn<GlassObject, Float> colTGInsertPrice;
    @FXML
    private TableColumn<GlassObject, Float> colTGPrice1;
    @FXML
    private TableColumn<GlassObject, Float> colTGPriceIn;
    private void initTGTable() {



        tblGoodsInStock.setEditable(false);

        colTGPriceIn.setVisible(false);
        colTGId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        colTGDesc.setCellValueFactory(cellData -> cellData.getValue().getDescProperty());
        colTGOption.setCellValueFactory(cellData -> cellData.getValue().getOptTitleProperty());
        colTGType.setCellValueFactory(cellData -> cellData.getValue().getTypeTilteProperty());
        colTGPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        colTGInsertPrice.setCellValueFactory(cellData -> cellData.getValue().getInsertPriceProperty());
        colTGFactory.setCellValueFactory(cellData -> cellData.getValue().getFactoryTitle());

        tblGoodsInStock.setItems(glassObjects);
    }

    public void buildCar() {
        System.out.println("заполняю объект Car");
        car.setGen((GenerationObj) dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()));
        car.setId(car.getGen().getModelID());
        System.out.println(car);

    }

    public void addGlass() {
       List<GenerationObj> generationObjList= dataMap.getGenerationObjList();
        if (generationObjList!=null&&generationObjList.size()>0){
            mainApp.initAddGlassLayout(getSelectedCarInfo());

//        int d= dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId();
            if (dataMap.getGenerationObjList() != null) {
                aggGlassController.setCarId(dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId());
            } else
                System.out.println("выбире поколение");
        } else {
            AlertWindow.errorMessage("Укажите поколение авто");
        }
    }


    public void setAggGlassController(AddGlassController aggGlassController) {
        this.aggGlassController = aggGlassController;
    }

    public void insertGlass(GlassObject glassTableRow) {
        System.out.println("добавляю новое стекло в базу");
        if (addOperator.addGlassIsComplete(glassTableRow)) {
            //fillGenerationsListView();
            System.out.println("все ок");
            //  dataMap.setGenerationObjList(getListOperator.getGenerations(car));
        } else {
            System.out.println("Фиаско! не добавлено");
        }
    }




    public String getSelectedMarkTitle() {
        if(dataMap.getCarMarksList()!=null) {
            String mark = dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex()).getTitle();
            return mark;
        } else return null;
    }

    public String getSelectedModelTitle() {
        if (dataMap.getCarModelsList()!=null) {
            String model = dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex()).getTitle();
            return getSelectedMarkTitle() + " " + model;
        }
        else return null;
    }

    public String getSelectedCarInfo () {
        if (dataMap.getGenerationObjList()!=null) {
            int from = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getYearFrom();
            int to = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getYearTo();

            return getSelectedModelTitle() + " " + from + "-" + to;
        } else {
            return null;
        }
    }
}
