package ru.glassexpress.objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class IdTitleObj extends BaseObject {
    public IdTitleObj() {
        super("IdTitleObj");
    }

    public int getId() {
        return id;
    }

    private int id;

    public String getTitle() {
        return title;
    }

    private String title;


    public IdTitleObj(int id, String title) {
        super("IdTitleObj");
        this.id = id;
        this.title = title;
    }



    public JsonElement toJSONObject() {

        obj1.addProperty("objClass", objectClass);
        obj1.addProperty("id", id);
        obj1.addProperty("title", title);


        return obj1;
    }

}
