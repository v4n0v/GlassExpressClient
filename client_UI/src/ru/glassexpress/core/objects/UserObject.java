package ru.glassexpress.core.objects;

import com.google.gson.JsonElement;

public class UserObject extends BaseObject{

    public UserObject () {
        super("user");

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public int getSalonId() {
        return salonId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    public String getSalonTitle() {
        return salonTitle;
    }

    public void setSalonTitle(String salonTitle) {
        this.salonTitle = salonTitle;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    private int id;
    private String name;
    private String lastName;
    private int positionId;
    private String positionTitle;
    private int salonId;
    private String salonTitle;
    private int permission;
    private String key;

    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("id", id);
        obj1.addProperty("name", name);
        obj1.addProperty("lastName",lastName);
        obj1.addProperty("positionId", positionId);
        obj1.addProperty("positionTitle", positionTitle);
        obj1.addProperty("salonId", salonId);
        obj1.addProperty("salonTitle", salonTitle);
        obj1.addProperty("permission", permission);
        return obj1;
    }
}
