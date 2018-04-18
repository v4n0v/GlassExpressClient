package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;
import javafx.beans.property.*;

public class CartProductObject extends BaseObject {
    public CartProductObject(GlassObject glass) {
        super("cartProduct");
        this.glass=glass;
        this.count=new SimpleIntegerProperty();
        this.isInsert = new SimpleBooleanProperty();
        this.count.setValue(1);

    }
    private final BooleanProperty isInsert;


    private GlassObject glass;

    public int getCountValue() {
        return count.get();
    }

    public IntegerProperty getCountProperty() {
        return count;
    }

    private final IntegerProperty count;

    public GlassObject getGlass() {
        return glass;
    }

    public void setGlass(GlassObject glass) {
        this.glass = glass;
    }



    public boolean isIsInsert() {
        return isInsert.get();
    }

    public BooleanProperty isInsertProperty() {
        return isInsert;
    }




    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objClass", objectClass);
        return obj1;
    }

    public StringProperty getGlassStringProperty() {
        return new SimpleStringProperty(glass.toString());
    }

    public SimpleObjectProperty<Float> getPriceFloatProperty() {
        return new SimpleObjectProperty<Float>(glass.getPrice());
    }

}
