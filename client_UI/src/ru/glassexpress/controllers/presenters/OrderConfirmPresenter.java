package ru.glassexpress.controllers.presenters;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.controllers.views.OrderConfirmView;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.objects.CartProductObject;
import ru.glassexpress.core.objects.CartServiceObject;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.core.objects.ServiceObject;
import ru.glassexpress.core.utils.ObservableListAdapter;
import ru.glassexpress.library.Resources;

import java.util.List;

public class OrderConfirmPresenter {
    private OrderConfirmView view;
    private ObservableList<String> discounts;
    private ObservableListAdapter observableAdapter;
    private DataMap dataMap;
    private ObservableList<CartProductObject> cart;
    private ObservableList<CartServiceObject> cartService;

    public void setSelectedGlass(List<GlassObject> selectedGlass) {
        this.selectedGlass = selectedGlass;
    }

    private List<GlassObject> selectedGlass;
    public OrderConfirmPresenter(OrderConfirmView view) {
        this.view = view;
    }
    private  float orderPrice;
    public void init() {
        dataMap=DataMap.getInstance();
        observableAdapter=new ObservableListAdapter();
        cart = FXCollections.observableArrayList();
        cartService=FXCollections.observableArrayList();

        for (GlassObject selectedGlas : selectedGlass) {
            cart.add(new CartProductObject(selectedGlas));
        }

        view.setGlassTable("cart",cart);
        view.setServiceTable("service", cartService);
        view.setComboBox(Resources.TARGET_SERVICE, observableAdapter.asObservableList(dataMap.getServices()));


        initPermission();


        // goodsAccordion.setExpanded(true);
        discounts = FXCollections.observableArrayList();
        discounts.add("5");
        discounts.add("10");
        discounts.add("15");
        discounts.add("20");
        view.setComboBox("discount", discounts);

        calculate();
    }

    private void initPermission() {
        boolean isVisible;

        if (dataMap.getUser().getPermission() == 1) {
//        if (mainController.getUser().getPermission() == 1) {
            isVisible = true;
        } else {
            isVisible = false;
        }

        view.setPermission(isVisible);

    }

    public void calculate() {
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


        int indexDsc = view.getDiscountIndex();

        float dsc = 0;
        if (indexDsc != -1) {
            dsc = Float.parseFloat(discounts.get(indexDsc));
            dsc /= 100;
        }
        orderPrice -= orderPrice * dsc;
        view.setTotalPrice(String.valueOf(orderPrice));

    }

    public void handleServicePrice(int index) {
        if (index!=-1){
            view.setTextField(String.valueOf(dataMap.getServices().get(index).getPrice()));

        }
    }

    public void addServicePriceInCart(int index) {
        if (index!=-1){
            ServiceObject serviceObject = dataMap.getServices().get(index);

            cartService.add(new CartServiceObject(serviceObject, view.getServicesPrice()));
        }
        calculate();
    }

    public void confirmOrder() {
        calculate();
        if(view.confirm("Вы точно хотите подтвердить заказ?\nСумма зазаза = "+ orderPrice )) {
            // логика оформления заказа
            view.closeView();
        }
    }
}
