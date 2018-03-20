package ru.glassexpress.core.objects.builders;

import ru.glassexpress.core.objects.UserObject;

public class UserBuilder {

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public UserBuilder setPositionId(int positionId) {
        this.positionId = positionId;
        return this;
    }

    public UserBuilder setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
        return this;
    }
    public UserBuilder setSalonId(int salonId) {
        this.salonId = salonId;
        return this;
    }

    public UserBuilder setSalonTitle(String salonTitle) {
        this.salonTitle = salonTitle;
        return this;
    }

    public UserBuilder setPermission(int permission) {
        this.permission = permission;
        return this;
    }

    public UserBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public UserObject build(){
        UserObject object = new UserObject();
        object.setId(id);
        object.setName(name);
        object.setLastName(lastName);
        object.setPositionId(positionId);
        object.setPositionTitle(positionTitle);
        object.setSalonId(salonId);
        object.setSalonTitle(salonTitle);
        object.setPermission(permission);

        return object;
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

}
