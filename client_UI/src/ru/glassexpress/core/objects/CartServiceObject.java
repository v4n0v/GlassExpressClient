package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;
import javafx.beans.property.*;

public class CartServiceObject extends BaseObject {
    public CartServiceObject(ServiceObject service, float price) {
        super("cartService");
        this.price=price;
        this.service=service;
        this.count=new SimpleIntegerProperty();
        count.setValue(1);
    }

//    private final BooleanProperty isSelected;
    private  ServiceObject service;

    public float getPrice() {
        return price;
    }

    private  float price;
    private final IntegerProperty count;
    public IntegerProperty getCountProperty() {
        return count;
    }

    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objectClass", objectClass);
        return obj1;
    }

    public StringProperty getTitleStringProperty() {
        return new SimpleStringProperty(service.toString());
    }

    public SimpleObjectProperty<Float> getPriceFloatProperty() {
        return new SimpleObjectProperty<Float>(price);
    }

    public ServiceObject getService() {
        return service;
    }

    public float getCountValue() {
        return count.getValue();
    }
}
