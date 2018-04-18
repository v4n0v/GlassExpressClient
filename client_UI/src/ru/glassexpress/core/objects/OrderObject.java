package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;
import ru.glassexpress.core.data.DataMap;

import java.util.List;
public class OrderObject extends BaseObject{


    private final String servicesJson;

    public OrderObject(List<CartProductObject> products, List<CartServiceObject> services , float total, DateObject date) {
        super("order");
        this.products = products;
        this.productsJson = DataMap.getInstance().convertListToJson(products);
        this.services = services;
        this.servicesJson = DataMap.getInstance().convertListToJson(services);
        this.total = total;
        this.date = date;
    }
    String productsJson;
    private List<CartProductObject> products;
    private List<CartServiceObject> services;
    private final float total;
    private DateObject date;
    private ClientObj client;
    boolean isCardPayment;

    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objectClass", objectClass);

        return obj1;
    }

    public DateObject getDate() {
        return date;
    }

    @Override
    public String toString() {
        return productsJson+"\n"+servicesJson+"\n"+"total = "+total+"\n"+date;
    }
}
