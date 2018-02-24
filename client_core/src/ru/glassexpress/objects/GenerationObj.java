package ru.glassexpress.objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GenerationObj extends BaseObject{
    public GenerationObj(int yearFrom) {
        super("generation");
        this.yearFrom = yearFrom;
    }

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    int yearFrom;
    int yearTo;
    int modelID;

    public GenerationObj(int yearFrom, int yearTo, int modelID) {
        super("generation");
        this.yearFrom = yearFrom;
        this.yearTo = yearTo;
        this.modelID = modelID;
    }

    public GenerationObj(int yearFrom, int yearTo) {
        super("generation");
        this.yearFrom = yearFrom;
        this.yearTo = yearTo;
    }



    @Override
    public JsonElement toJSONObject() {

        obj1.addProperty("objClass", objectClass);
        obj1.addProperty("yearFrom", yearFrom);
        obj1.addProperty("yearTo", yearTo);
        obj1.addProperty("modelID", modelID);

        IdTitleObj dd = GSON.fromJson(obj1, IdTitleObj.class);

        return obj1;
    }

    @Override
    public String toString() {
        return yearFrom+" - "+yearTo;
    }
}
