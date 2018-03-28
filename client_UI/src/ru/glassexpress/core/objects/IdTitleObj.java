package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;

public class IdTitleObj extends BaseObject {
    public IdTitleObj() {
        super("IdTitleObj");
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    boolean isChecked;

    public int getId() {
        return id;
    }

    private int id;

    public String getTitle() {
        return title;
    }

    private String title;


    public IdTitleObj(int id, String title) {
        super("IdTitleObj");
        this.id = id;
        this.title = title;
    }



    public JsonElement toJSONObject() {

        obj1.addProperty("objectClass", objectClass);
        obj1.addProperty("id", id);
        obj1.addProperty("title", title);

        return obj1;
    }

    @Override
    public String toString() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
