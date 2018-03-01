package ru.glassexpress.objects;

import com.google.gson.JsonElement;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class TableGoodsInStockRow extends BaseObject {

    public TableGoodsInStockRow() {
        super("tab_goods_in_stock");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGlassType() {
        return glassType;
    }

    public void setGlassType(int glassType) {
        this.glassType = glassType;
    }

    public int getGlassOption() {
        return glassOption;
    }

    public void setGlassOption(int glassOption) {
        this.glassOption = glassOption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(float priceIn) {
        this.priceIn = priceIn;
    }

    public float getInsertPrice() {
        return insertPrice;
    }

    public void setInsertPrice(float insertPrice) {
        this.insertPrice = insertPrice;
    }

    public int getCountWh1() {
        return countWh1;
    }

    public void setCountWh1(int countWh1) {
        this.countWh1 = countWh1;
    }

    public int getCountWh2() {
        return countWh2;
    }

    public void setCountWh2(int countWh2) {
        this.countWh2 = countWh2;
    }

    public int getCountRemainder() {
        return countRemainder;
    }

    public void setCountRemainder(int countRemainder) {
        this.countRemainder = countRemainder;
    }

    private int id;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    private int carId;

    private int glassType;
    private int glassOption;
    private String description;
    private float price;
    private float priceIn;
    private float insertPrice;
    private int countWh1;
    private int countWh2;

    public int getInsertMethod() {
        return insertMethod;
    }

    public void setInsertMethod(int insertMethod) {
        this.insertMethod = insertMethod;
    }

    private int insertMethod;

    public int getGlassFactory() {
        return glassFactory;
    }

    public void setGlassFactory(int glassFactory) {
        this.glassFactory = glassFactory;
    }

    private int glassFactory;
    private int countRemainder;


    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objClass", objectClass);
        obj1.addProperty("id", id);
        obj1.addProperty("glassType", glassType);
        obj1.addProperty("glassOption", glassOption);
        obj1.addProperty("description", description);
        obj1.addProperty("insertPrice", insertPrice);
        obj1.addProperty("price", price);
        obj1.addProperty("priceIn", priceIn);
        obj1.addProperty("countWh1", countWh1);
        obj1.addProperty("countWh2", countWh2);
        obj1.addProperty("countRemainder", countRemainder);
        return obj1;
    }


    public SimpleObjectProperty<Integer> getIdProperty() {
        return new SimpleObjectProperty<Integer>(id);
    }

    public SimpleObjectProperty<Integer> getGlassTypeProperty() {
        return new SimpleObjectProperty<Integer>(glassType);
    }
    public SimpleObjectProperty<Integer> getGlassOptProperty() {
        return new SimpleObjectProperty<Integer>(glassOption);
    }
    public SimpleObjectProperty<Float> getPriceProperty() {
        return new SimpleObjectProperty<Float>(price);
    }
    public SimpleObjectProperty<Float> getPriceInProperty() {
        return new SimpleObjectProperty<Float>(priceIn);
    }
    public SimpleObjectProperty<Integer> getCountRemainderProperty() {
        return new SimpleObjectProperty<Integer>(countRemainder);
    }
    public StringProperty getDescProperty() {

        return new SimpleStringProperty(description);
    }
}
