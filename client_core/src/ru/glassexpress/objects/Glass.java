package ru.glassexpress.objects;

import com.google.gson.JsonElement;

public class Glass extends BaseObject{



    public Glass(String objectClass) {
        super(objectClass);
    }

    @Override
    public JsonElement toJSONObject() {
        return null;
    }
}
