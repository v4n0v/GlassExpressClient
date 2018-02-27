package ru.glassexpress.objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

abstract public class BaseObject {
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public String getObjectClass() {
        return objectClass;
    }

    String objectClass;

    public BaseObject(String objectClass) {
        this.objectClass = objectClass;
        obj1 = new JsonObject();
        // серверынй метод, возвращает JSON
    }
    protected JsonObject obj1;
    // серверынй метод, возвращает JSON

    // клиентский метод, возвращает запрос

    abstract public JsonElement toJSONObject();
}

