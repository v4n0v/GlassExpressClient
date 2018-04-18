package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;

public class ClientObj extends BaseObject{
    public ClientObj(String objectClass) {
        super("client");
    }

    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objectClass", objectClass);

        return obj1;
    }
}
