package ru.glassexpress.core.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.List;

public class Composite extends BaseObject {
    public List<BaseObject> getComponents() {
        return components;
    }

    private List<BaseObject> components = new ArrayList<>();

    public Composite( ) {
        super("composite");

    }


    String getComponentClass(){
        if (components.size()>0){
            return components.get(0).getClass().getSimpleName() ;
        }
        return null;
    }

    public void addComponent(BaseObject component){
        components.add(component);

    }
    public void removeComponent(BaseObject component){
        components.remove(component);
    }



    @Override
    public JsonElement toJSONObject() {
        JsonObject object = new JsonObject();
        object.addProperty("objectClass", objectClass);
        //object.addProperty("objClass", objectClass);
        JsonArray arr = new JsonArray();
        for (BaseObject component: components){
            arr.add( component.toJSONObject());
        }

        object.add("array", arr);
        return object;
    }
}
