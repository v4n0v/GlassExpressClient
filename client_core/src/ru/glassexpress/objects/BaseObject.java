package ru.glassexpress.objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

abstract public class BaseObject {
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    String objectClass;

    public BaseObject(String objectClass) {
        this.objectClass = objectClass;
    }

    // серверынй метод, возвращает JSON
    abstract public String toJSON();
    // клиентский метод, возвращает запрос
    abstract public String toGET();
    abstract public JsonElement toJSONObject();
}

