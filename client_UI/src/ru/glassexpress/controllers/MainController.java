package ru.glassexpress.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import ru.glassexpress.JsonController;
import ru.glassexpress.URLConnection;
import ru.glassexpress.core.edit_content_command.addCommand.AddOperator;
import ru.glassexpress.core.edit_content_command.deleteCommand.DeleteOperator;
import ru.glassexpress.core.edit_content_command.updCommand.UpdateOperator;
import ru.glassexpress.core.get_command.ObservedCommand;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.*;


import java.util.List;

public class MainController extends BaseController {


    @FXML
    ComboBox<String> bodyTypeListView;

    @FXML
    ComboBox<String> insertClassComboBox;

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
    UpdateOperator updateOperator;
    private URLConnection urlConnection;
    private JsonController jsonController;

    ObservedCommand command;

    private ObservableList<String> marksList = FXCollections.observableArrayList();

    private ObservableList<String> modelsList = FXCollections.observableArrayList();
    private ObservableList<String> genList = FXCollections.observableArrayList();

    //TODO организовать заполнение
    private ObservableList<String> classList = FXCollections.observableArrayList();

    public ObservableList<String> getBodyTypeStringList() {
        return bodyTypeStringList;
    }

    private ObservableList<String> bodyTypeStringList = FXCollections.observableArrayList();

    private AddGlassController aggGlassController;
    @FXML
    Button delGenerationButton;
    @FXML
    Button delModelButton;
    @FXML
    Button delMarkButton;
    //public Car getCar() {
//        return car;
//    }

    // класс авто, заполняющийся после конструктора
    // Car car;
    BaseObjectAdapter adapter;


    // инициализация конроллера, вызывается при открытии приложения
    @Override
    public void init() {
        initTGTable();
        urlConnection = URLConnection.getInstance();
        jsonController = JsonController.getInstance();
        // car = new Car();
        adapter = new BaseObjectAdapter();
        getListOperator = new GetListOperator();
        deleteOperator = new DeleteOperator();
        addOperator = new AddOperator();
        updateOperator=new UpdateOperator();
        // glassTypeList = new ArrayList<>();


        radioGroup = new ToggleGroup();
        isFrontRadio.setToggleGroup(radioGroup);
        isRearRadio.setToggleGroup(radioGroup);

        reconnect();
        // получаем список типов стекол, кузовов

        initTGTable();


        //ystem.out.println(dataMap.getBodyTypeList());
    }



    public void reconnect() {
        markListView.setItems(marksList);
        modelListView.setItems(modelsList);
        genListView.setItems(genList);
        bodyTypeListView.setItems(bodyTypeStringList);


        insertClassComboBox.setItems(classList);


        fillMarksListView();
// если получили список марок, то тянем остальные параметры и модели
        if (dataMap.getCarMarksList() != null) {
            dataMap.setGlassTypeList(getListOperator.getGlassTypes());
            if (dataMap.getGlassTypeList() != null) {
                dataMap.setBodyTypeList(getListOperator.getBodyTypes());
                dataMap.setGlassOptList(getListOperator.getGlassOptions());
                dataMap.setGlassFactoryList(getListOperator.getGlassFactory());

                fillObservableList(bodyTypeStringList, adapter.idTitleObjToString(dataMap.getBodyTypeList()));
            }
        }
    }

    // получаем список для конкретной выбранныей машины
    private void initInsertClass() {
        // узнаем id выбранной машины
        if (dataMap.getGenerationObjList()!=null&& dataMap.getGenerationObjList().size()>0) {
            int id = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getIdInsert();
            // получаем список всех классов цен
            List<InsertClass> list = dataMap.getInsertClassList();
            if (list!=null) {
                for (int i = 0; i < list.size(); i++) {
                    if (dataMap.getInsertClassList().get(i).getId() == id) {
                        insertClassComboBox.getSelectionModel().select(i);
                    }
                }
            }
        }
    }

    private void fillInsertClassList(List<InsertClass> insertClassList) {
        classList.clear();
        for (int i = 0; i < insertClassList.size(); i++) {
            classList.addAll("Класс "+(i+1)+"-"+insertClassList.get(i).getInsertFront());

        }
    }

    // получаем данные с сервера и заполняем ListView марок автомобилей
    private void fillMarksListView() {

        dataMap.setCarMarksList(getListOperator.getMarks());
        fillObservableList(marksList, adapter.idTitleObjToString(dataMap.getCarMarksList()));
        if (dataMap.getCarMarksList() != null && dataMap.getCarMarksList().size() > 0) {
            markListView.getSelectionModel().selectFirst();

            // формируется запрос на получение списка моделей

            fillModelsListView();
        }
    }

    // получаем данные с сервера и заполняем ListView моделей автомобилей, выбранной марки
    public void fillModelsListView() {

        // car.setMark(markListView.getSelectionModel().getSelectedItem());
        markListView.getSelectionModel().getSelectedIndex();

        dataMap.setCarModelsList(getListOperator.getModels(dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex())));
        fillObservableList(modelsList, adapter.idTitleObjToString(dataMap.getCarModelsList()));
        if (dataMap.getCarModelsList() != null && dataMap.getCarModelsList().size() > 0) {
            modelListView.getSelectionModel().selectFirst();
            //   System.out.println("показать модели марки " + car.getMark());

            fillGenerationsListView();
        }
    }

//    private List<BaseObject> currentModelGenerations = new ArrayList<>();

    public void fillGenerationsListView() {

        System.out.println("Запрос поколений авто");

        //car.setModel(modelListView.getSelectionModel().getSelectedItem());
        IdTitleObj model = dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex());
        dataMap.setGenerationObjList(getListOperator.getGenerations(model));
        fillObservableList(genList, adapter.generationObjToString(dataMap.getGenerationObjList()));
        if (dataMap.getGenerationObjList().size() > 0) {
            genListView.getSelectionModel().selectFirst();
            //currentModelGenerations = getListOperator.getComponents();
            // получаем список классов цен
            dataMap.setInsertClassList(getListOperator.getInsertClass());
            // заполняем ObservableList
            fillInsertClassList(dataMap.getInsertClassList());
            // получаем список для конкретной выбранныей машины
            initInsertClass();
            // заполянем лейблы с конкретными ценами
            initPriceClassLabels();
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
        if (addOperator.addModelIsComplete(model, getSelectedMarkObj())) {
            fillModelsListView();
        } else {
            System.out.println("Фиаско!" + getSelectedMarkObj().getTitle() + " " + model + " не добавлено");
        }


    }

    private void addGeneration(String answer) {

        if (addOperator.addGenerationIsComplete(answer, getSelectedModelObj())) {
            fillGenerationsListView();
            //  dataMap.setGenerationObjList(getListOperator.getGenerations(car));
        } else {
            System.out.println("Фиаско!" + getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + answer + " не добавлено");
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

    private void deleteMark(int id) {
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
                    if (!answer.equals("")) {
                        addMark(answer);
                        System.out.println("добавить марку авто " + answer);
                    }
                }
            }

        } else if ((Button) keyEvent.getSource() == addModelButton) {

            String answer = AlertWindow.dialogWindow("Добавить новую модель авто", "Введите модель марки " + getSelectedMarkTitle());
            if (answer != null) {
                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить " + getSelectedMarkTitle() + " " + answer + " в базу?");
                if (isTrue) {
                    if (!answer.equals("")) {
                        addModel(answer);
                        System.out.println("добавить модель авто " + getSelectedMarkTitle() + " ");
                    }
                }
            }

        } else if ((Button) keyEvent.getSource() == addGenerationButton) {

            String answer = AlertWindow.dialogWindow("Добавить поколение авто " + getSelectedMarkTitle() + " " + getSelectedModelTitle(), "Введите введите годы в формате (ХХХХ-ХХХХ) ");
            if (answer != null) {
                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить поколение " +
                        getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + answer + " в базу?");
                if (isTrue) {
                    if (!answer.equals("")) {
                        addGeneration(answer);
                        System.out.println("добавить поколение авто " + getSelectedModelTitle() + " " + answer);
                    }
                }
            }

        } else if ((Button) keyEvent.getSource() == delGenerationButton) {

            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить поколение " + getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + " из базы?");
            if (isTrue) {
                int id = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId();
                deleteGeneration(id);
                System.out.println("удалить поколение авто " + getSelectedModelTitle());
            }


        } else if ((Button) keyEvent.getSource() == delModelButton) {
            System.out.println("удаляем модель " + getSelectedModelTitle());
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить модель " + getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + " из базы?");
            if (isTrue) {
                int id = dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex()).getId();
                deleteModel(id);
                System.out.println("удалить модель авто " + getSelectedMarkTitle() + " " + getSelectedModelTitle());
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
        //   car.setGen((GenerationObj) dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()));
    }


    // показываем список товаров
    public void showGoods() {
        List<GenerationObj> generationObjList = dataMap.getGenerationObjList();
        glassObjects.clear();
        if (generationObjList != null && generationObjList.size() > 0) {
            dataMap.setGlassList(getListOperator.getTableGoods(getSelectedCarObj()));
            glassObjects.addAll(dataMap.getGlassList());
        } else {
            AlertWindow.errorMessage("Укажите поколение авто");
        }
        System.out.println("показываю подходящий товар");

        //initTGTable();

    }

    // таблица стекол на складе
    private ObservableList<GlassObject> glassObjects = FXCollections.observableArrayList();
    ;
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
    private TableColumn<GlassObject, Float> colTGPriceIn;
    @FXML
    private TableColumn<GlassObject, String> colTGBody;

    @FXML
    private TableColumn<GlassObject, String>colTGSelect;
    // инициализация колонок таблицы
    private void initTGTable() {
        tblGoodsInStock.setEditable(false);

        colTGPriceIn.setVisible(false);
        colTGId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        colTGDesc.setCellValueFactory(cellData -> cellData.getValue().getDescProperty());
        colTGOption.setCellValueFactory(cellData -> cellData.getValue().getOptTitleProperty());
        colTGType.setCellValueFactory(cellData -> cellData.getValue().getTypeTitleProperty());
        colTGPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        colTGInsertPrice.setCellValueFactory(cellData -> cellData.getValue().getInsertPriceProperty());
        colTGFactory.setCellValueFactory(cellData -> cellData.getValue().getFactoryTitle());
        colTGBody.setCellValueFactory(cellData -> cellData.getValue().getBodyTypeTitle());


        tblGoodsInStock.setItems(glassObjects);
    }

//    public void buildCar() {
//        System.out.println("заполняю объект Car");
//        car.setGen((GenerationObj) dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()));
//        car.setId(car.getGen().getModelID());
//        System.out.println(car);
//
//    }


    // открываем дилоговое окно добавления ного стекла в базу
    public void openAddGlassDialog() {
        // получаем список поколений текущей модели
        List<GenerationObj> generationObjList = dataMap.getGenerationObjList();
        if (generationObjList != null && generationObjList.size() > 0) {
            // открываем окгно
            mainApp.initAddGlassLayout(getSelectedCarInfo());
            //передаем в него параметры авто
            if (dataMap.getGenerationObjList() != null) {
                aggGlassController.setCarId(dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId());
            } else
                System.out.println("выбиритее поколение");
        } else {
            AlertWindow.errorMessage("Укажите поколение авто");
        }
    }


    public void setAggGlassController(AddGlassController aggGlassController) {
        this.aggGlassController = aggGlassController;
    }

    // втавляем новое стекло в базу
    public void insertGlass(GlassObject glassTableRow) {
        System.out.println("добавляю новое стекло в базу");
        if (addOperator.addGlassIsComplete(glassTableRow)) {
            //fillGenerationsListView();

            System.out.println("Добавлено в базу, все ок");
            AlertWindow.infoMessage("Добавлено в базу");
            //  dataMap.setGenerationObjList(getListOperator.getGenerations(car));
        } else {
            System.out.println("Фиаско! не добавлено");
        }
    }

    // получаем название марки авто из datamap
    public String getSelectedMarkTitle() {
        if (dataMap.getCarMarksList() != null) {

            return dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex()).getTitle();
        } else return null;
    }
    // получаем название модели авто из datamap
    public String getSelectedModelTitle() {
        if (dataMap.getCarModelsList() != null) {
            return getSelectedMarkTitle() + " " + dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex()).getTitle();
        } else return null;
    }

    // получаем полное название авто из datamap
    public String getSelectedCarInfo() {
        if (dataMap.getGenerationObjList() != null) {
            int from = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getYearFrom();
            int to = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getYearTo();

            return getSelectedModelTitle() + " " + from + "-" + to;
        } else {
            return null;
        }
    }

    // получаем id марки авто из datamap
    public Integer getSelectedMarkId() {
        Integer id = null;
        if (dataMap.getCarMarksList() != null) {
            id = dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex()).getId();

        }

        return id;
    }
    // получаем id модели авто из datamap
    public Integer getSelectedModelId() {
        Integer id = null;
        if (dataMap.getCarModelsList() != null) {
            id = dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex()).getId();
            //return getSelectedMarkTitle() + " " + model;
        }
        return id;
    }
    // получаем id машины авто из datamap
    public Integer getSelectedCarId() {
        Integer id = null;
        if (dataMap.getGenerationObjList() != null) {
            id = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId();

        }
        return id;
    }

    // получаем объект марки авто из datamap
    public IdTitleObj getSelectedMarkObj() {
        return dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex());
    }
    // получаем объект модели авто из datamap
    public IdTitleObj getSelectedModelObj() {
        return dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex());
    }
    // получаем объект авто из datamap
    public GenerationObj getSelectedCarObj() {
        return dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex());
    }


    // получаю номер класса установки и вношу его в текущий автомобиль
    public void saveInsertClass() {
        // получаю ид класса установки
        int id= dataMap.getInsertClassList().get(insertClassComboBox.getSelectionModel().getSelectedIndex()).getId();
        // id авто
        int carID = getSelectedCarId();
        boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?",
                "Изменить класс установки " + getSelectedMarkTitle() + " " + getSelectedModelTitle() + "?\n"
        +insertClassComboBox.getSelectionModel().getSelectedItem());
        if (isTrue) {

            // если нажата ok, обновляем
            if (updateOperator.updAutoInsertClass(id, carID)) {
                System.out.println("Класс установки обновлен");
            } else {
                System.out.println("Фиаско! не обновлено");
            }
        }



    }

    @FXML
    Label frontInsertPriceLabel;
    @FXML
    Label rearInsertPriceLabel;
    @FXML
    Label sideInsertPriceLabel;
//    @FXML
//    GridPane insertPriceGrid;
//
    //
    public void initPriceClassLabels() {
        // получам интекс текущего класс установки
        int index = insertClassComboBox.getSelectionModel().getSelectedIndex();
//        if (!insertPriceGrid.isVisible()){
//            insertPriceGrid.setVisible(true);
//        }
        // устанавливаем цены в ячейки
        frontInsertPriceLabel.setText(String.valueOf(dataMap.getInsertClassList().get(index).getInsertFront()));
        rearInsertPriceLabel.setText(String.valueOf(dataMap.getInsertClassList().get(index).getInsertRear()));
        sideInsertPriceLabel.setText(String.valueOf(dataMap.getInsertClassList().get(index).getInsertSide()));
    }
}
