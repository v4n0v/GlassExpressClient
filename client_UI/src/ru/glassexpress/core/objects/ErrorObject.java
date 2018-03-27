package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;
import javafx.util.StringConverter;

public class ErrorObject extends BaseObject{
    private String message;

    public ErrorObject(String message) {
        super("error");
        this.message=message;
    }

    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objectClass", objectClass);
        obj1.addProperty("message", message);
        return obj1;
    }

    public String getMessage() {
        return message;
    }
}
