package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.TableGoodsInStockRow;
import ru.glassexpress.objects.builders.TableGoodsBuilder;

public class AddGlassController extends BaseController {
    public RadioButton glueRB;
    public RadioButton rubberRB;
    public TextField descriptionTextField;
    public TextField priceInTextField;
    public TextField priceTextField;
    public TextField insertPriceTextField;
    @FXML

    ComboBox glassOptComboBox;
    @FXML
    ComboBox glassFactoryComboBox;

    private BaseObjectAdapter adapter = new BaseObjectAdapter();
    private ObservableList<String> glassOptList;
    private ObservableList<String> glassFactoryList;
    private int carId;

    ToggleGroup toggleGroup;
    TableGoodsInStockRow glassTableRow;

    public AddGlassController() {
    }


    @Override
    public void init() {
        toggleGroup = new ToggleGroup();
        glueRB.setToggleGroup(toggleGroup);
        rubberRB.setToggleGroup(toggleGroup);

        glassOptList = adapter.IdTitleObjToString(mainController.getGlassOptList());
        glassFactoryList = adapter.IdTitleObjToString(mainController.getGlassFactoryList());
        glassOptComboBox.setItems(glassOptList);
        glassFactoryComboBox.setItems(glassFactoryList);
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void addGlass() {
        String description = descriptionTextField.getText();
        String priceIn = priceInTextField.getText();
        String price = priceTextField.getText();

        int glassOptIndex = glassOptComboBox.getSelectionModel().getSelectedIndex();
        int glassFactoryIndex = glassFactoryComboBox.getSelectionModel().getSelectedIndex();


        if (!description.equals("") && !priceIn.equals("") && !price.equals("") &&
                glassOptIndex != -1 && glassFactoryIndex != -1) {
 
            int glassOptId = mainController.getGlassOptList().get(glassOptIndex).getId();
            int glassFactoryId = mainController.getGlassFactoryList().get(glassFactoryIndex).getId();
            glassTableRow = new TableGoodsBuilder().setCarId(carId)
                    .setDescription(description)
                    .setPriceIn(Float.parseFloat(priceIn))
                    .setPrice(Float.parseFloat(price))
                    .setGlassOption(glassOptId)
                    .setGlassFactory(glassFactoryId)
                    .build();

        } else {
            AlertWindow.errorMessage("Заполните все формы");
        }
        System.out.println("Добавить стекло");
    }


    public void closeModal() {
        close();
    }

}
