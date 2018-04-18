package ru.glassexpress.controllers.model;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.objects.*;

import java.util.List;

public class OrderModel {
    private DataMap dataMap;
    private ObservableList<CartProductObject> cart;
    private ObservableList<CartServiceObject> cartService;

    public ObservableList<String> getDiscounts() {
        return discounts;
    }

    private ObservableList<String> discounts;
    private float orderPrice;

    public boolean isCardPayment() {
        return isCardPayment;
    }

    public void setPaymentType(boolean cardPayment) {
        isCardPayment = cardPayment;
    }

    private boolean isCardPayment;


    public OrderModel() {
        dataMap = DataMap.getInstance();
        cart = FXCollections.observableArrayList();
        cartService = FXCollections.observableArrayList();
        discounts = FXCollections.observableArrayList();
        discounts.add("5");
        discounts.add("10");
        discounts.add("15");
        discounts.add("20");
    }


    public void addProductsToCart(List<GlassObject> selectedGlassList) {
        for (GlassObject glass : selectedGlassList) {
            cart.add(new CartProductObject(glass));
        }
    }

    public float getTotalPrice(int discountIndex) {
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
            priceService *= o.getCountValue();
//            if (isInsert.get()) {
//                orderPrice += insPrice;
//            }
            orderPrice += priceService;
        }

        float dsc = 0;
        if (discountIndex != -1) {
            dsc = Float.parseFloat(discounts.get(discountIndex));
            dsc /= 100;
        }
        return orderPrice -= orderPrice * dsc;
    }

    public ObservableList<CartProductObject> getCart() {
        return cart;
    }

    public ObservableList<CartServiceObject> getCartService() {
        return cartService;
    }

    public List<ServiceObject> getServices() {
        return dataMap.getServices();
    }

    public int getUserPermission() {
        return dataMap.getUser().getPermission();
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void createOrder() {
        OrderObject order = new OrderObject(cart, cartService, orderPrice,  dataMap.getDate());
        System.out.println(order);

    }
}
