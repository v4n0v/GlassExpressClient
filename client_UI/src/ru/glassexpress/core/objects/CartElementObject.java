package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;
import javafx.beans.property.*;

public class CartElementObject extends BaseObject {
    public CartElementObject(GlassObject glass) {
        super("cart");
        this.glass=glass;
    }


    public GlassObject getGlass() {
        return glass;
    }

    public void setGlass(GlassObject glass) {
        this.glass = glass;
    }

    private final BooleanProperty isTone = new SimpleBooleanProperty();

    public boolean isIsTone() {
        return isTone.get();
    }

    public BooleanProperty isToneProperty() {
        return isTone;
    }

    public boolean isIsInsert() {
        return isInsert.get();
    }

    public BooleanProperty isInsertProperty() {
        return isInsert;
    }

    private final BooleanProperty isInsert = new SimpleBooleanProperty();

    GlassObject glass;


    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objectClass", objectClass);
        return obj1;
    }

    public StringProperty getGlassStringProperty() {

        return new SimpleStringProperty(glass.toString());
    }

    public SimpleObjectProperty<Float> getPriceFloatProperty() {

        return new SimpleObjectProperty<Float>(glass.getPrice());
    }


}
