package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.Arrays;

public class CarsController extends BaseController {
    @FXML
    ListView genListView1;
    @FXML
    ListView modelListView;
//    @FXML
//    ComboBox markComboBox;
//
//    @FXML
//    ComboBox modelComboBox;

    @FXML
    ListView<String> markListView;

    @Override
    public void init() {
        ObservableList<String> modelList = clientManager.getCarModel();
        // markComboBox.setItems(modelList);

        markListView.setItems(modelList);
    }
}
