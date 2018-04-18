package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;

import java.util.List;
public class OrderObject extends BaseObject{

    public OrderObject( List<CartProductObject> products,  List<CartServiceObject> services , float total, DateObject date) {
        super("order");
        this.products = products;
        this.services = services;
        this.total = total;
        this.date = date;
    }

    private List<CartProductObject> products;
    private List<CartServiceObject> services;
    private final float total;
    private DateObject date;
    private ClientObj client;
    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objectClass", objectClass);

        return obj1;
    }
}
