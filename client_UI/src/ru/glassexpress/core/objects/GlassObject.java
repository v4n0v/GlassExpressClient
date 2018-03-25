package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;
import javafx.beans.property.*;

import java.util.List;

public class GlassObject extends BaseObject {

    private int id;

    private int carId;
    private int glassTypeId;
    private int glassOptionId;
    private String description;
    private float priceIn;
    private float price;
    private int alert;
    private float insertPrice;
    private int countWh1;
    private int countWh2;
    private int bodyTypeId;

    private String insertMethodTitle;
    private String glassFactoryTitle;
    private String glassTypeTitle;
    private String glassOptTitle;
    private String carTitle;
    private String bodyTypeTitle;

    private List<IdTitleObj> optList;

    public String getParametrList() {
        return parametrList;
    }

    public void setParametrList(String parametrList) {
        this.parametrList = parametrList;
    }

    private String parametrList;

    public List<IdTitleObj> getOptList() {
        return optList;
    }

    private String optListString;

    public void setOptList(List<IdTitleObj> optList) {
        this.optList = optList;
    }


    public int getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(int bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }


    public void setBodyTypeTitle(String bodyTypeTitle) {
        this.bodyTypeTitle = bodyTypeTitle;
    }


    public String getInsertMethodTitle() {
        return insertMethodTitle;
    }

    public void setInsertMethodTitle(String insertMethodTitle) {
        this.insertMethodTitle = insertMethodTitle;
    }

    public String getGlassFactoryTitle() {
        return glassFactoryTitle;
    }

    public void setGlassFactoryTitle(String glassFactoryTitle) {
        this.glassFactoryTitle = glassFactoryTitle;
    }

    public String getGlassTypeTitle() {
        return glassTypeTitle;
    }

    public void setGlassTypeTitle(String glassTypeTitle) {
        this.glassTypeTitle = glassTypeTitle;
    }

    public String getGlassOptTitle() {
        return glassOptTitle;
    }

    public void setGlassOptTitle(String glassOptTitle) {
        this.glassOptTitle = glassOptTitle;
    }

    public String getCarTitle() {
        return carTitle;
    }

    public void setCarTitle(String carTitle) {
        this.carTitle = carTitle;
    }


    public GlassObject() {
        super("tab_goods_in_stock");
        //   select=new Checkbox();
    }


    public String getOptListString() {
        return optListString;
    }

    public void setOptListString(String optListString) {
        this.optListString = optListString;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGlassTypeId() {
        return glassTypeId;
    }

    public void setGlassTypeId(int glassTypeId) {
        this.glassTypeId = glassTypeId;
    }

    public int getGlassOptionId() {
        return glassOptionId;
    }

    public void setGlassOptionId(int glassOptionId) {
        this.glassOptionId = glassOptionId;
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


    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }


    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }


    public int getInsertMethod() {
        return insertMethod;
    }

    public void setInsertMethod(int insertMethod) {
        this.insertMethod = insertMethod;
    }

    private int insertMethod;

    public int getGlassFactoryId() {
        return glassFactoryId;
    }

    public void setGlassFactoryId(int glassFactoryId) {
        this.glassFactoryId = glassFactoryId;
    }

    private int glassFactoryId;
    private int countRemainder;


    private final BooleanProperty isSelected = new SimpleBooleanProperty();

    public BooleanProperty isSelectedProperty() {
        return isSelected;
    }

    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objClass", objectClass);
        obj1.addProperty("id", id);
        obj1.addProperty("carId", carId);
        obj1.addProperty("glassTypeId", glassTypeId);
        obj1.addProperty("glassTypeTitle", glassTypeTitle);
        obj1.addProperty("glassOptionId", glassOptionId);
        obj1.addProperty("glassOptTitle", glassOptTitle);
        obj1.addProperty("description", description);
        obj1.addProperty("alert", alert);
        obj1.addProperty("optList", optListString);

        obj1.addProperty("insertPrice", insertPrice);
        obj1.addProperty("price", price);
        obj1.addProperty("priceIn", priceIn);
        obj1.addProperty("countWh1", countWh1);
        obj1.addProperty("countWh2", countWh2);
        obj1.addProperty("glassFactoryId", glassFactoryId);
        obj1.addProperty("glassFactoryTitle", glassFactoryTitle);
        obj1.addProperty("insertMethod", insertMethod);
        obj1.addProperty("optListString", optListString);
        obj1.addProperty("insertMethodTitle", insertMethodTitle);
        obj1.addProperty("insertBodyType", bodyTypeId);
        obj1.addProperty("insertBodyTypeTitle", bodyTypeTitle);
        return obj1;
    }


    public SimpleObjectProperty<Integer> getIdProperty() {
        return new SimpleObjectProperty<Integer>(id);
    }

    public SimpleObjectProperty<Integer> getGlassTypeProperty() {
        return new SimpleObjectProperty<Integer>(glassTypeId);
    }

    public SimpleObjectProperty<Integer> getGlassOptProperty() {
        return new SimpleObjectProperty<Integer>(glassOptionId);
    }

    public SimpleObjectProperty<Float> getPriceProperty() {
        return new SimpleObjectProperty<Float>(price);
    }

    public SimpleObjectProperty<Float> getInsertPriceProperty() {
        return new SimpleObjectProperty<Float>(insertPrice);
    }

    public SimpleObjectProperty<Float> getPriceInProperty() {
        return new SimpleObjectProperty<Float>(priceIn);
    }

    public SimpleObjectProperty<Integer> getCountRemainderProperty() {
        return new SimpleObjectProperty<Integer>(countRemainder);
    }


//    public ObservableValue<Checkbox> getSelectProperty() {
//        return new SimpleObjectProperty<Checkbox>(select);
//    }

    public StringProperty getDescProperty() {

        return new SimpleStringProperty(carTitle + " " + description);
    }

    public StringProperty getOptTitleProperty() {

        return new SimpleStringProperty(glassOptTitle);
    }

    public StringProperty getTypeTitleProperty() {

        return new SimpleStringProperty(glassTypeTitle);
    }

    public StringProperty getFactoryTitle() {

        return new SimpleStringProperty(glassFactoryTitle);
    }

    public StringProperty getBodyTypeTitle() {

        return new SimpleStringProperty(bodyTypeTitle);
    }


    public StringProperty getParameterListTitle() {


        return new SimpleStringProperty(parametrList);
    }

    @Override
    public String toString() {
        if (bodyTypeTitle != null && carTitle != null)
            return carTitle + " " + bodyTypeTitle + " " + glassFactoryTitle+ " "+glassTypeTitle;
        else
            return "Cтекло клиента";
    }
    //    public void setIsSelected(Boolean isSelected) {
//        this.isSelected = isSelected;
//    }
//    public BooleanProperty getIsSelectedProperty() {
//
//        return new SimpleBooleanProperty(isSelected);
//    }

//    public BooleanProperty getIsSelected() {
//        return isSelected;
//    }
}
