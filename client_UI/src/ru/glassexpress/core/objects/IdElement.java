package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;

public class IdElement extends BaseObject {
    public IdElement(int id) {
        super("idObj");
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    @Override
    public JsonElement toJSONObject() {
       obj1.addProperty("objectClass", objectClass);
        obj1.addProperty("id", id);
        return  obj1;
    }
}
