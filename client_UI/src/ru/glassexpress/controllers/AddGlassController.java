package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.library.Resources;

import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.core.objects.Composite;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.builders.GlassBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddGlassController extends BaseController {

    enum State {ADD, EDIT}

    public void setState(State state) {
        this.state = state;
    }

    State state = State.ADD;

    public SplitMenuButton splitMenu;
    @FXML
    RadioButton glueRB;
    @FXML
    RadioButton rubberRB;
    @FXML
    TextField descriptionTextField;
    @FXML
    TextField priceInTextField;
    @FXML
    TextField priceTextField;
    @FXML
    TextField insertPriceTextField;
    @FXML
    TextField alertTextField;
    @FXML
    ComboBox<String> glassOptComboBox;
    @FXML
    ComboBox<String> glassFactoryComboBox;
    @FXML
    ComboBox<String> glassTypeComboBox;
    @FXML
    ComboBox<String> glassBodyComboBox;
    @FXML
    Button applyBtn;

    private BaseObjectAdapter adapter;
    private ObservableList<String> glassOptList;
    private ObservableList<String> glassFactoryList;
    private ObservableList<String> glassTypeList;
    private int carId;
    private int carIndex;
    private int glassId;
    private GlassObject glassPrepared;

    public AddGlassController() {
    }

    ObservableList<CheckMenuItem> optMultipleList;
    @FXML
    private MenuButton menuBTN;

    @Override
    public void init() {

        initButton();
        Log2File.writeLog("Инициализация окна добавления новго стекла");
        adapter = BaseObjectAdapter.getInsance();
        glassOptList = adapter.idTitleObjToString(dataMap.getGlassOptList());
        glassFactoryList = adapter.idTitleObjToString(dataMap.getGlassFactoryList());
        glassTypeList = adapter.idTitleObjToString(dataMap.getGlassTypeList());
        glassOptComboBox.setItems(glassOptList);
        glassOptComboBox.getSelectionModel().select(0);
        glassFactoryComboBox.setItems(glassFactoryList);
        glassFactoryComboBox.getSelectionModel().select(0);
        glassTypeComboBox.setItems(glassTypeList);
        glassTypeComboBox.getSelectionModel().select(0);
        glassBodyComboBox.setItems(adapter.idTitleObjToString(dataMap.getBodyTypeList()));
        glassBodyComboBox.getSelectionModel().select(0);


//        float defInsertPrice = dataMap.getInsertClassList().get(3).getInsertFront();
//        insertPriceTextField.setText(String.valueOf(defInsertPrice));
        insertPriceTextField.setText(String.valueOf(Resources.DEFAULT_INSERT_PRICE));


        optMultipleList = FXCollections.observableArrayList();
        for (int i = 0; i < glassOptList.size(); i++) {
            optMultipleList.add(new CheckMenuItem(glassOptList.get(i)));
        }
        menuBTN.getItems().addAll(optMultipleList);

    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    void initButton() {
        if (state == State.ADD) {
            applyBtn.setText("Добавить");
        } else if (state == State.EDIT) {
            applyBtn.setText("Редактировать");
        }
    }


    public void updGlass(int glassOptId, int glassTypeId, int glassFactoryId, int glassBodyId
            , String decription, String price, String insPrice, String alert, String priceIn, int glassId) {
        initButton();
        this.glassId=glassId;

        System.out.println("bodyType="+dataMap.getPosById(dataMap.getBodyTypeList(), glassBodyId));
        glassBodyComboBox.getSelectionModel().select(dataMap.getPosById(dataMap.getBodyTypeList(), glassBodyId));
        glassFactoryComboBox.getSelectionModel().select(dataMap.getPosById(dataMap.getGlassFactoryList(), glassFactoryId));
        glassTypeComboBox.getSelectionModel().select(dataMap.getPosById(dataMap.getGlassTypeList(), glassTypeId));
        glassOptComboBox.getSelectionModel().select(dataMap.getPosById(dataMap.getGlassOptList(), glassOptId));

        descriptionTextField.setText(decription);

        priceTextField.setText(price);
        priceInTextField.setText(insPrice);
        alertTextField.setText(alert);
        priceInTextField.setText(priceIn);

    }

    public void addGlass() {

        initButton();
        //получаем данные с полей вводв
        Log2File.writeLog("Начало добавления нового элемента стекла");
        String description = descriptionTextField.getText();
        String priceIn = priceInTextField.getText();
        String price = priceTextField.getText();
        String alert = alertTextField.getText();
        String insertPrice = insertPriceTextField.getText();
        int glassOptIndex = glassOptComboBox.getSelectionModel().getSelectedIndex();
        int glassFactoryIndex = glassFactoryComboBox.getSelectionModel().getSelectedIndex();
        int glassTypeIndex = glassTypeComboBox.getSelectionModel().getSelectedIndex();
        int bodyTypeIndex = glassBodyComboBox.getSelectionModel().getSelectedIndex();


        String optionsJson="";
        Composite composite = new Composite();
        int checked = 0;
        for (int i = 0; i < optMultipleList.size(); i++) {
            if (optMultipleList.get(i).isSelected()) {
                composite.addComponent(new IdTitleObj(i, glassOptList.get(i)));
                checked++;
            }
        }
        if (checked>0) {
            optionsJson = composite.toJSONObject().toString();
        }

        if (!priceIn.equals("") && !price.equals("") &&
                !alert.equals("") && !insertPrice.equals("") &&
                //   glassOptIndex != -1 && glassFactoryIndex != -1 && glassTypeIndex != -1 &&
                isNumeric(price) && isNumeric(priceIn)) {


            if (!optionsJson.equals("")) {
                if (description.equals("")) description = " ";

                int insertMethod = 2;
                if (glueRB.isSelected()) {
                    insertMethod = 2;
                } else if (rubberRB.isSelected()) {
                    insertMethod = 3;
                } else {
                    AlertWindow.errorMessage("Выбирете метод установки");
                }

                int glassOptId = dataMap.getGlassOptList().get(glassOptIndex).getId();
                int glassFactoryId = dataMap.getGlassFactoryList().get(glassFactoryIndex).getId();
                int glassTypeId = dataMap.getGlassTypeList().get(glassTypeIndex).getId();
                int bodyTypeId = dataMap.getBodyTypeList().get(bodyTypeIndex).getId();

                glassPrepared = new GlassBuilder()
                        .setCarId(carId)
                        .setDescription(description)
                        .setPriceIn(Float.parseFloat(priceIn))
                        .setPrice(Float.parseFloat(price))
                        .setOptListString(optionsJson)
                        .setGlassOptionId(glassOptId)
                        .setGlassFactoryId(glassFactoryId)
                        .setInsertMethodId(insertMethod)
                        .setGlassTypeId(glassTypeId)
                        .setAlert(Integer.parseInt(alert))
                        .setInsertPrice(Float.parseFloat(insertPrice))
                        .setBodyType(bodyTypeId)
                        .build();

                dataMap.setPreparedGlass(glassPrepared);

                if (state == State.ADD) {
                    mainController.insertGlass(glassPrepared);
                } else if (state == State.EDIT) {
                    glassPrepared.setId(glassId);
                    System.out.println(glassId);
                    mainController.updGlass(glassPrepared);
                }
                Log2File.writeLog("Новое стекло добавлено");

                System.out.println(optionsJson);
                close();
            } else {
                AlertWindow.errorMessage("Выбирите хотябы 1 параметр стекла");
            }

        } else {
            AlertWindow.errorMessage("Корректно заполните все формы");
        }
    }


    public void closeModal() {
        close();
    }

    public static boolean isNumeric(String x) {
        Pattern p = Pattern.compile("^\\d+(?:\\.\\d+)?$");
        Matcher m = p.matcher(x);
        return m.matches();
    }


    public void addNewBodyType() {
        String answer = AlertWindow.dialogWindow("Добавить тип кузова", "Введите название кузова авто ");
        if (answer != null && !answer.equals("")) {
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить новый тип " + answer + " в базу?");
            if (isTrue) {
                if (!answer.equals("")) {

                }
            }
        } else {
            AlertWindow.errorMessage("Полее ввода не заполнено!");
        }
    }

    public void addNewFactory() {
        String answer = AlertWindow.dialogWindow("Добавить поставщика", "Введите название завода");
        if (answer != null && !answer.equals("")) {
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить новый завод " + answer + " в базу?");
            if (isTrue) {
                if (!answer.equals("")) {

                }
            }
        } else {
            AlertWindow.errorMessage("Полее ввода не заполнено!");
        }
    }

    public void addNewGlassType() {
        String answer = AlertWindow.dialogWindow("Добавить тип стекла", "Введите название типа стекла");
        if (answer != null && !answer.equals("")) {
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить новый тип стекла " + answer + " в базу?");
            if (isTrue) {
                if (!answer.equals("")) {

                }
            }
        } else {
            AlertWindow.errorMessage("Полее ввода не заполнено!");
        }
    }

    public void addNewGlassOption() {
        String answer = AlertWindow.dialogWindow("Добавить свойство стекла", "Введите название свойства стекла");
        if (answer != null && !answer.equals("")) {
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить новое свойство стекла " + answer + " в базу?");
            if (isTrue) {
                if (!answer.equals("")) {

                }
            }
        } else {
            AlertWindow.errorMessage("Полее ввода не заполнено!");
        }
    }
}
