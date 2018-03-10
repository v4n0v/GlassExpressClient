package ru.glassexpress.objects;

import com.google.gson.JsonElement;

public class InsertClassElement extends BaseObject{

    int id; int idGlassType; float price;
    String idGlassTypeTitle;

    public InsertClassElement(int id, int idGlassType, float price, String idGlassTypeTitle) {
        super("insert_class_");
        this.id = id;
        this.idGlassType = idGlassType;
        this.price = price;
        this.idGlassTypeTitle = idGlassTypeTitle;
    }

    public JsonElement toJSONObject() {
        obj1.addProperty("objClass", objectClass);
        obj1.addProperty("id", id);
        obj1.addProperty("idGlassType", idGlassType);
        obj1.addProperty("price", price);
        obj1.addProperty("idGlassTypeTitle", idGlassTypeTitle);

        return obj1;
    }
}