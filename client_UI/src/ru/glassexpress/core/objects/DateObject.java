package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class DateObject extends BaseObject {

    public DateObject() {
        super("day");
        employees=new ArrayList<>();
    }


    int id;
    long date;
    List<Integer> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    int idAdmin;

    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objClass", objectClass);
        return obj1;
    }
}
