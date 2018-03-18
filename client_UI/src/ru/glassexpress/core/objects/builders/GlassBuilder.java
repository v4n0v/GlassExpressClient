package ru.glassexpress.core.objects.builders;

import ru.glassexpress.core.objects.GlassObject;

public class GlassBuilder {

    public GlassBuilder setId(int id) {
        this.id = id;
        return this;
    }

//    public GlassBuilder setCarTitle(int carId) {
//        this.carTitle = carTitle;
//        return this;
//    }

    public GlassBuilder setGlassTypeId(int glassType) {
        this.glassType = glassType;
        return this;
    }

    public GlassBuilder setGlassOptionId(int glassOption) {
        this.glassOption = glassOption;
        return this;
    }

    public GlassBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public GlassBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public GlassBuilder setPriceIn(float priceIn) {
        this.priceIn = priceIn;
        return this;
    }

    public GlassBuilder setInsertPrice(float insertPrice) {
        this.insertPrice = insertPrice;
        return this;
    }

    public GlassBuilder setCountWh1(int countWh1) {
        this.countWh1 = countWh1;
        return this;
    }

    public GlassBuilder setCountWh2(int countWh2) {
        this.countWh2 = countWh2;
        return this;
    }

    public GlassBuilder setCountRemainder(int countRemainder) {
        this.countRemainder = countRemainder;
        return this;
    }
    public GlassBuilder setGlassFactoryId(int glassFactory) {
        this.glassFactory = glassFactory;
        return this;
    }
    public GlassBuilder setCarId(int carId) {
        this.carId = carId;
        return this;
    }
    public GlassBuilder setInsertMethodId(int insertMethod) {
        this.insertMethod = insertMethod;
        return this;
    }
    public GlassBuilder setAlert(int alert) {
        this.alert = alert;
        return this;
    }

    public GlassBuilder setInsertMethodTitle(String insertMethodTitle) {
        this.insertMethodTitle = insertMethodTitle;
        return this;
    }
    public GlassBuilder setInsertGlassFactoryTitle(String glassFactoryTitle) {
        this.glassFactoryTitle = glassFactoryTitle;
        return this;
    }
    public GlassBuilder setInsertGlassTypeTitle(String glassTypeTitle) {
        this.glassTypeTitle = glassTypeTitle;
        return this;
    }
    public GlassBuilder setInsertGlassOptTitle(String glassOptTitle) {
        this.glassOptTitle = glassOptTitle;
        return this;
    }
    public GlassBuilder setCarTitle(String carTitle) {
        this.carTitle = carTitle;
        return this;
    }
    public GlassBuilder setBodyTypeTitle(String bodyTypeTitle) {
        this.bodyTypeTitle = bodyTypeTitle;
        return this;
    }
    public GlassBuilder setBodyType(int bodyType) {
        this.bodyType=bodyType;
        return this;
    }

    public GlassBuilder setOptListString(String optListString) {
        this.optListString=optListString;
        return this;
    }
    private int id;
    private int carId;
    private int glassType;
    private int glassOption;
    private String description;
    private float price;
    private float priceIn;
    private float insertPrice;
    private int countWh1;
    private int countWh2;
    private int countRemainder;
    private  int glassFactory;
    private  int insertMethod;
    private int alert;
    private int bodyType;
    private String carTitle;
String optListString;
    private String insertMethodTitle;
    private String glassFactoryTitle;
    private String glassTypeTitle;
    private String glassOptTitle;
    private String bodyTypeTitle;


    public GlassObject build() {
        GlassObject table = new GlassObject();

        //table.setSelect(new Checkbox());
        table.setId(id);
        table.setCarId(carId);
        table.setCarTitle(carTitle);

        table.setGlassTypeId(glassType);
        table.setGlassTypeTitle(glassTypeTitle);

        table.setGlassOptTitle(glassOptTitle);
        table.setGlassOptionId(glassOption);
        table.setOptListString(optListString);

        table.setDescription(description);
        table.setPrice(price);
        table.setPriceIn(priceIn);
        table.setInsertPrice(insertPrice);
        table.setCountRemainder(countRemainder);
        table.setCountWh1(countWh1);
        table.setCountWh2(countWh2);

        table.setGlassFactoryId(glassFactory);
        table.setGlassFactoryTitle(glassFactoryTitle);

        table.setInsertMethod(insertMethod);
        table.setInsertMethodTitle(insertMethodTitle);
        table.setAlert(alert);
        table.setBodyTypeId(bodyType);
        table.setBodyTypeTitle(bodyTypeTitle);




        return table;
    }


}
