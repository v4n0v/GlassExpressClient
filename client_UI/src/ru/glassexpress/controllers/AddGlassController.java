package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.GlassObject;
import ru.glassexpress.objects.builders.GlassBuilder;

import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddGlassController extends BaseController  {
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



    private BaseObjectAdapter adapter;
    private ObservableList<String> glassOptList;
    private ObservableList<String> glassFactoryList;
    private ObservableList<String> glassTypeList;
    private int carId;
    private int carIndex;

    private GlassObject glassTableRow;

    public AddGlassController() {
    }


    @Override
    public void init() {
//        toggleGroup = new ToggleGroup();
//        glueRB.setToggleGroup(toggleGroup);
//        rubberRB.setToggleGroup(toggleGroup);
        adapter = new BaseObjectAdapter();
        glassOptList = adapter.idTitleObjToString(dataMap.getGlassOptList());
        glassFactoryList = adapter.idTitleObjToString(dataMap.getGlassFactoryList());
        glassTypeList = adapter.idTitleObjToString(dataMap.getGlassTypeList());
        glassOptComboBox.setItems(glassOptList);
        glassOptComboBox.getSelectionModel().select(0);
        glassFactoryComboBox.setItems(glassFactoryList);
        glassFactoryComboBox.getSelectionModel().select(0);
        glassTypeComboBox.setItems(glassTypeList);
        glassTypeComboBox.getSelectionModel().select(0);
        glassBodyComboBox.setItems(mainController.getBodyTypeStringList());
        glassBodyComboBox.getSelectionModel().select(0);
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void addGlass() {
        //получаем данные с полей вводв
        String description = descriptionTextField.getText();
        String priceIn = priceInTextField.getText();
        String price = priceTextField.getText();
        String alert = alertTextField.getText();
        String insertPrice = insertPriceTextField.getText();
        int glassOptIndex = glassOptComboBox.getSelectionModel().getSelectedIndex();
        int glassFactoryIndex = glassFactoryComboBox.getSelectionModel().getSelectedIndex();
        int glassTypeIndex = glassTypeComboBox.getSelectionModel().getSelectedIndex();
        int bodyTypeIndex = glassBodyComboBox.getSelectionModel().getSelectedIndex();
        if (!description.equals("") && !priceIn.equals("") && !price.equals("") &&
                !alert.equals("") & !insertPrice.equals("") &&
             //   glassOptIndex != -1 && glassFactoryIndex != -1 && glassTypeIndex != -1 &&
                isNumeric(price) && isNumeric(priceIn)) {

            int insertMethod = 2;
            if (glueRB.isSelected()) insertMethod = 2;
            else if (rubberRB.isSelected()) insertMethod = 3;
            else AlertWindow.errorMessage("Выбирете метод установки");

            int glassOptId = dataMap.getGlassOptList().get(glassOptIndex).getId();
            int glassFactoryId = dataMap.getGlassFactoryList().get(glassFactoryIndex).getId();
            int glassTypeId = dataMap.getGlassTypeList().get(glassTypeIndex).getId();
            int bodyTypeId = dataMap.getBodyTypeList().get(bodyTypeIndex).getId();
            glassTableRow = new GlassBuilder()
                    .setCarId(carId)
                    .setDescription(description)
                    .setPriceIn(Float.parseFloat(priceIn))
                    .setPrice(Float.parseFloat(price))
                    .setGlassOptionId(glassOptId)
                    .setGlassFactoryId(glassFactoryId)
                    .setInsertMethodId(insertMethod)
                    .setGlassTypeId(glassTypeId)
                    .setAlert(Integer.parseInt(alert))
                    .setInsertPrice(Float.parseFloat(insertPrice))
                    .setBodyType(bodyTypeId)
                    .build();
            dataMap.setGlassTableRow(glassTableRow);
            mainController.insertGlass(glassTableRow);
            close();
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


}
