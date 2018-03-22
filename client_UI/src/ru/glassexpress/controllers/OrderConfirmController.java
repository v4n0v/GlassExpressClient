package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.objects.CartElementObject;
import ru.glassexpress.core.objects.GlassObject;

import java.util.List;

public class OrderConfirmController extends BaseController {
    public RadioButton cashRB;
    public RadioButton cardRB;

    public TableView<CartElementObject> tableCart;
    public TableColumn<CartElementObject, Boolean> isInsertCol;
    public TableColumn<CartElementObject, Boolean> isToneCol;
    public TableColumn<CartElementObject, String> glassCol;
    public TableColumn<CartElementObject, Float> priceCol;
    @FXML
    private ComboBox<String> discountComboBox;
    private ObservableList<CartElementObject> cart;


    public List<GlassObject> getSelectedGlass() {
        return selectedGlass;
    }

    public void setSelectedGlass(List<GlassObject> selectedGlass) {
        this.selectedGlass = selectedGlass;
    }

   private List<GlassObject> selectedGlass;
    @Override
    public void init() {
        Log2File.writeLog("Иинициализация окна подтверждения заказа");
        initTable();
        ToggleGroup payment = new ToggleGroup();
        cashRB.setToggleGroup(payment);
        cardRB.setToggleGroup(payment);
        cashRB.setSelected(true);



        ObservableList<String> discounts = FXCollections.observableArrayList();
        discounts.add("5");
        discounts.add("10");
        discounts.add("15");
        discounts.add("20");
        discountComboBox.setItems(discounts);

    }


   private void initTable(){
        Log2File.writeLog("Иинициализация таблицы корзины");
        cart = FXCollections.observableArrayList();
        for (GlassObject selectedGlas : selectedGlass) {
            cart.add(new CartElementObject(selectedGlas));
        }
        tableCart.setEditable(true);
        tableCart.setItems(cart);
        isInsertCol.setCellValueFactory(cellData -> cellData.getValue().isInsertProperty());
        isInsertCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        isToneCol.setCellValueFactory(cellData -> cellData.getValue().isToneProperty());
        isToneCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        glassCol.setCellValueFactory(cellData -> cellData.getValue().getGlassStringProperty());
       priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceFloatProperty());
    }

    public void confirm(ActionEvent actionEvent) {
        close();
    }
}
