package ru.glassexpress.objects;

import com.google.gson.JsonElement;
import ru.glassexpress.Prefs;

public class InsertClass extends BaseObject{
    public InsertClass(String objectClass) {
        super(Prefs.TARGET_INSERT_CLASS);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getInsertFront() {
        return insertFront;
    }

    public void setInsertFront(float insertFront) {
        this.insertFront = insertFront;
    }

    public float getInsertRear() {
        return insertRear;
    }

    public void setInsertRear(float insertRear) {
        this.insertRear = insertRear;
    }

    public float getInsertSide() {
        return insertSide;
    }

    public void setInsertSide(float insertSide) {
        this.insertSide = insertSide;
    }

    public float getToneFront() {
        return toneFront;
    }

    public void setToneFront(float toneFront) {
        this.toneFront = toneFront;
    }

   private  int id;
    private float insertFront;
    private float insertRear;
    private  float insertSide;
    private  float toneFront;



    @Override
    public JsonElement toJSONObject() {
        obj1.addProperty("objClass", objectClass);
        obj1.addProperty("insertFront", insertFront);
        obj1.addProperty("insertRear", insertRear);
        obj1.addProperty("insertSide", insertSide);
        obj1.addProperty("toneFront", toneFront);
        return obj1;
    }
}
