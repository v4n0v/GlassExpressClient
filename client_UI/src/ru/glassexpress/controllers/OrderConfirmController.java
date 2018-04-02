package ru.glassexpress.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.CartProductObject;
import ru.glassexpress.core.objects.CartServiceObject;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.core.objects.ServiceObject;
import ru.glassexpress.core.utils.ObservableListAdapter;
import ru.glassexpress.library.AlertWindow;

import java.util.List;

public class OrderConfirmController extends BaseController {
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
    private ObservableList<CartProductObject> cart;
    private ObservableList<CartServiceObject> cartService;
    public TitledPane goodsAccordion;
    @FXML
    Label totalLabel;
    ObservableListAdapter observableAdapter;
    public List<GlassObject> getSelectedGlass() {
        return selectedGlass;
    }

    public void setSelectedGlass(List<GlassObject> selectedGlass) {
        this.selectedGlass = selectedGlass;
    }
  private  float orderPrice;
    private ObservableList<String> discounts;
    private List<GlassObject> selectedGlass;
    private BaseObjectAdapter adapter;
    @Override
    public void init() {

        Log2File.writeLog("Иинициализация окна подтверждения заказа");
        adapter=BaseObjectAdapter.getInsance();
        initTable();
        observableAdapter=new ObservableListAdapter();
       // ObservableList<String> services = adapter.returnServicesStringList(dataMap.getServices());
//        servicesComboBox.setCellFactory(p -> new ListCell <ServiceObject> () {
//            @Override
//            protected void updateItem(ServiceObject item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item != null && !empty) {
//                    setText(item.getTitle());
//                } else {
//                    setText(null);
//                }
//            }
//        });

        servicesComboBox.setItems(observableAdapter.asObservableList(dataMap.getServices()));

        initPermission();
        ToggleGroup payment = new ToggleGroup();
        cashRB.setToggleGroup(payment);
        cardRB.setToggleGroup(payment);
        cashRB.setSelected(true);

        // goodsAccordion.setExpanded(true);
        discounts = FXCollections.observableArrayList();
        discounts.add("5");
        discounts.add("10");
        discounts.add("15");
        discounts.add("20");
        discountComboBox.setItems(discounts);
        calculateOrderPrice();
    }
    public Button addServiceButton;
    public Button dellServiceButton;
    private void initPermission() {
        boolean isVisible;
        if (dataMap.getUser().getPermission() == 1) {
//        if (mainController.getUser().getPermission() == 1) {
            isVisible = true;
        } else {
            isVisible = false;
        }
        dellServiceButton.setVisible(isVisible);
        addServiceButton.setVisible(isVisible);
    }

    public  void fillServicePrice(){
        int index = servicesComboBox.getSelectionModel().getSelectedIndex();
        if (index!=-1){
            servicesPriceTextField.setText(String.valueOf(dataMap.getServices().get(index).getPrice()));
        }
    }


    public  void addServicePriceInCart(){
        int index = servicesComboBox.getSelectionModel().getSelectedIndex();
        if (index!=-1){
            ServiceObject serviceObject = dataMap.getServices().get(index);
            cartService.add(new CartServiceObject(serviceObject, Float.parseFloat(servicesPriceTextField.getText())));
        }
        calculateOrderPrice();
    }

    private void initTable() {
        Log2File.writeLog("Иинициализация таблицы корзины");
        cart = FXCollections.observableArrayList();
        cartService=FXCollections.observableArrayList();
       // таблица товаров
        for (GlassObject selectedGlas : selectedGlass) {
            cart.add(new CartProductObject(selectedGlas));
        }
        tableCart.setEditable(true);
        tableCart.setItems(cart);
        isInsertCol.setCellValueFactory(cellData -> cellData.getValue().isInsertProperty());
        isInsertCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        glassCol.setCellValueFactory(cellData -> cellData.getValue().getGlassStringProperty());
        //typeCol.setCellValueFactory(cellData -> cellData.getValue().());
        countCol.setCellValueFactory(cellData -> cellData.getValue().getCountProperty().asObject());
        countCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceFloatProperty());

        // таблица услуг
        tableServiceCart.setEditable(true);
        tableServiceCart.setItems(cartService);
        serviceTitleCol.setCellValueFactory(cellData -> cellData.getValue().getTitleStringProperty());

        countServiceCol.setCellValueFactory(cellData -> cellData.getValue().getCountProperty().asObject());
        countServiceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        servicePriceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceFloatProperty());

    }

    public void confirm(ActionEvent actionEvent) {
        calculateOrderPrice();
        boolean isTrue = AlertWindow.confirmationWindow("Вы уверены", "Вы точно хотите подтвердить заказ?\nСумма зазаза = "+ orderPrice );
        if (isTrue) {
            close();
        }

    }

    public void calculateOrderPrice() {

        orderPrice = 0;

        // проверка, выбрана ли установка стекла
        for (CartProductObject o : cart) {
            float prcire = o.getPriceFloatProperty().get();
            float insPrice = o.getGlass().getInsertPrice();
            BooleanProperty isInsert = o.isInsertProperty();
            orderPrice += prcire;
            if (isInsert.get()) {
                orderPrice += insPrice;
            }
            orderPrice *= o.getCountValue();
        }

        // если добавлены услуги
        for (CartServiceObject o : cartService) {
            float priceService = o.getPriceFloatProperty().get();
            float insPrice = o.getService().getPrice();
            //BooleanProperty isInsert = o.isInsertProperty();
             priceService*= o.getCountValue();
//            if (isInsert.get()) {
//                orderPrice += insPrice;
//            }
            orderPrice+=priceService;
        }


        int indexDsc = discountComboBox.getSelectionModel().getSelectedIndex();
        float dsc = 0;
        if (indexDsc != -1) {
            dsc = Float.parseFloat(discounts.get(indexDsc));
            dsc /= 100;
        }
        orderPrice -= orderPrice * dsc;
        totalLabel.setText(String.valueOf(orderPrice));
    }


    public void addService(){
        System.out.println("Добвляем услугу");
    }

    public void dellService(){
        System.out.println("Удаляем услугу");
    }
}
