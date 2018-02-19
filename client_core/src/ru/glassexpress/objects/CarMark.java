package ru.glassexpress.objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CarMark extends BaseObject {
    public CarMark() {
        super("CarMark");
    }

    public int getId() {
        return id;
    }

    private int id;

    public String getTitle() {
        return title;
    }

    private String title;


    public CarMark(int id, String title) {
        super("CarMark");
        this.id = id;
        this.title = title;
    }

    @Override
    public String toJSON() {
        return GSON.toJson(this);
    }

    public JsonElement toJSONObject() {
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("objClass", objectClass);
        obj1.addProperty("id", id);
        obj1.addProperty("title", title);

        CarMark dd = GSON.fromJson(obj1, CarMark.class);

        return obj1;
    }


    @Override
    public String toGET() {
        return null;
    }
}
