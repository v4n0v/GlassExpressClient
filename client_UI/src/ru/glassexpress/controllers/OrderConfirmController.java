package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import ru.glassexpress.controllers.presenters.OrderConfirmPresenter;
import ru.glassexpress.controllers.views.OrderConfirmView;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.CartProductObject;
import ru.glassexpress.core.objects.CartServiceObject;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.core.objects.ServiceObject;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.library.Resources;

import java.util.List;

public class OrderConfirmController extends BaseController implements OrderConfirmView {
    public RadioButton cashRB;
    public RadioButton cardRB;

    public TableView<CartProductObject> tableCart;
    public TableColumn<CartProductObject, Boolean> isInsertCol;
    //public TableColumn<CartProductObject, Boolean> isToneCol;
    public TableColumn<CartProductObject, String> glassCol;
    public TableColumn<CartProductObject, String> typeCol;

    public TableColumn<CartProductObject, Integer> countCol;
    public TableColumn<CartProductObject, Float> priceCol;
    //    public ComboBox<String> servicesComboBox;
    public ComboBox<ServiceObject> servicesComboBox;
    public TextField servicesPriceTextField;

    public TableView<CartServiceObject> tableServiceCart;
    public TableColumn<CartServiceObject, Integer> serviceIdCol;
    public TableColumn<CartServiceObject, String> serviceTitleCol;
    public TableColumn<CartServiceObject, Float> servicePriceCol;
    public TableColumn<CartServiceObject, Integer> countServiceCol;

    @FXML
    private ComboBox<String> discountComboBox;


    public TitledPane goodsAccordion;
    @FXML
    Label totalLabel;
    OrderConfirmPresenter presenter;




    public Button addServiceButton;
    public Button dellServiceButton;


    private BaseObjectAdapter adapter;

    @Override
    public void init() {

        Log2File.writeLog("Иинициализация окна подтверждения заказа");
        presenter = new OrderConfirmPresenter(this);
        adapter = BaseObjectAdapter.getInsance();
        initTable();
        ToggleGroup payment = new ToggleGroup();
        cashRB.setToggleGroup(payment);
        cardRB.setToggleGroup(payment);
        cashRB.setSelected(true);
    }
    public void setSelectedGlass(List<GlassObject> selectedGlass) {
        presenter.setSelectedGlass(selectedGlass);
        presenter.init();

    }

    public void fillServicePrice() {
        presenter.handleServicePrice(servicesComboBox.getSelectionModel().getSelectedIndex());


    }


    public void addServicePriceInCart() {
        int index = servicesComboBox.getSelectionModel().getSelectedIndex();
        presenter.addServicePriceInCart(index);


    }

    private void initTable() {
        Log2File.writeLog("Иинициализация таблицы корзины");

        // таблица товаров

        tableCart.setEditable(true);

        isInsertCol.setCellValueFactory(cellData -> cellData.getValue().isInsertProperty());
        isInsertCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        glassCol.setCellValueFactory(cellData -> cellData.getValue().getGlassStringProperty());
        //typeCol.setCellValueFactory(cellData -> cellData.getValue().());
        countCol.setCellValueFactory(cellData -> cellData.getValue().getCountProperty().asObject());
        countCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceFloatProperty());

        // таблица услуг
        tableServiceCart.setEditable(true);

        serviceTitleCol.setCellValueFactory(cellData -> cellData.getValue().getTitleStringProperty());

        countServiceCol.setCellValueFactory(cellData -> cellData.getValue().getCountProperty().asObject());
        countServiceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        servicePriceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceFloatProperty());

    }

    public void confirm(ActionEvent actionEvent) {
        presenter.confirmOrder();
        calculateOrderPrice();
    }

    public void calculateOrderPrice() {
        presenter.calculate();

    }


    public void addService() {
        System.out.println("Добвляем услугу");
    }

    public void dellService() {
        System.out.println("Удаляем услугу");
    }

    @Override
    public void setComboBox(String target, ObservableList list) {
        ComboBox comboBox = initComboBox(target);
        comboBox.setItems(list);
    }

    @Override
    public void setPermission(boolean isVisible) {
        dellServiceButton.setVisible(isVisible);
        addServiceButton.setVisible(isVisible);
    }

    @Override
    public void setTotalPrice(String s) {
        totalLabel.setText(s);
    }

    @Override
    public int getDiscountIndex() {
        return discountComboBox.getSelectionModel().getSelectedIndex();

    }

    @Override
    public void setTextField(String s) {
        servicesPriceTextField.setText(s);
    }

    @Override
    public void setServiceTable(String service, ObservableList<CartServiceObject> cartService) {
        tableServiceCart.setItems(cartService);
    }

    @Override
    public float getServicesPrice() {
        return Float.parseFloat(servicesPriceTextField.getText());
    }

    @Override
    public boolean confirm(String s) {
        return AlertWindow.confirmationWindow("Вы уверены", s);

    }

    @Override
    public void closeView() {
        close();
    }

    @Override
    public void setGlassTable(String cartName, ObservableList<CartProductObject> cart) {
        tableCart.setItems(cart);
    }

    private ComboBox initComboBox(String target) {
        switch (target) {
            case Resources.TARGET_SERVICE:
                return servicesComboBox;
            case "discount":
                return discountComboBox;
        }
        return null;
    }

}
