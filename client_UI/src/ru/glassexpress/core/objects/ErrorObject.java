package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;

public class ErrorObject extends BaseObject{
    public ErrorObject() {
        super("error");
    }

    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objClass", objectClass);
        return obj1;
    }
}
