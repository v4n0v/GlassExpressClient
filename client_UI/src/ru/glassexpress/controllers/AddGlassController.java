package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.TableGoodsInStockRow;
import ru.glassexpress.objects.builders.TableGoodsBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddGlassController extends BaseController {
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
    ComboBox<String> glassOptComboBox;
    @FXML
    ComboBox<String> glassFactoryComboBox;
    @FXML
    ComboBox<String> glassTypeComboBox;

    private BaseObjectAdapter adapter = new BaseObjectAdapter();
    private ObservableList<String> glassOptList;
    private ObservableList<String> glassFactoryList;
    private ObservableList<String> glassTypeList;
    private int carId;
    private int carIndex;

    private TableGoodsInStockRow glassTableRow;

    public AddGlassController() {
    }


    @Override
    public void init() {
//        toggleGroup = new ToggleGroup();
//        glueRB.setToggleGroup(toggleGroup);
//        rubberRB.setToggleGroup(toggleGroup);

        glassOptList = adapter.IdTitleObjToString(dataMap.getGlassOptList());
        glassFactoryList = adapter.IdTitleObjToString(dataMap.getGlassFactoryList());
        glassTypeList = adapter.IdTitleObjToString(dataMap.getGlassTypeList());
        glassOptComboBox.setItems(glassOptList);
        glassFactoryComboBox.setItems(glassFactoryList);
        glassTypeComboBox.setItems(glassTypeList);
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void addGlass() {
        //получаем данные с полей вводв
        String description = descriptionTextField.getText();
        String priceIn = priceInTextField.getText();
        String price = priceTextField.getText();

        int glassOptIndex = glassOptComboBox.getSelectionModel().getSelectedIndex();
        int glassFactoryIndex = glassFactoryComboBox.getSelectionModel().getSelectedIndex();
        int glassTypeIndex = glassTypeComboBox.getSelectionModel().getSelectedIndex();

        if (!description.equals("") && !priceIn.equals("") && !price.equals("") &&
                glassOptIndex != -1 && glassFactoryIndex != -1 && glassTypeIndex != -1 &&
                isNumeric(price) && isNumeric(priceIn)) {

            int insertMethod = 0;
            if (glueRB.isSelected()) insertMethod = 0;
            else if (rubberRB.isSelected()) insertMethod = 1;
            else AlertWindow.errorMessage("Выбирете метод установки");

            int glassOptId = dataMap.getGlassOptList().get(glassOptIndex).getId();
            int glassFactoryId = dataMap.getGlassFactoryList().get(glassFactoryIndex).getId();
            int glassTypeId = dataMap.getGlassTypeList().get(glassTypeIndex).getId();
            glassTableRow = new TableGoodsBuilder()
                    .setCarId(carId)
                    .setDescription(description)
                    .setPriceIn(Float.parseFloat(priceIn))
                    .setPrice(Float.parseFloat(price))
                    .setGlassOption(glassOptId)
                    .setGlassFactory(glassFactoryId)
                    .setInsertMethod(insertMethod)
                    .setGlassType(glassTypeId)
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
