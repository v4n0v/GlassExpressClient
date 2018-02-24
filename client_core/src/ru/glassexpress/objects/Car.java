package ru.glassexpress.objects;

import com.google.gson.JsonElement;

public class Car extends BaseObject{

    public Car() {
        super("car");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodytype() {
        return bodytype;
    }

    public void setBodytype(String bodytype) {
        this.bodytype = bodytype;
    }

    public int getYearFrom() {
        return gen.getYearFrom();
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearTo() {
        return gen.getYearTo();
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    private int id;
    private String mark;
    private String model;
    private String bodytype;
    private int yearFrom;
    private int yearTo;

    public GenerationObj getGen() {
        return gen;
    }

    public void setGen(GenerationObj gen) {
        this.gen = gen;
    }

    GenerationObj gen;


    @Override
    public String toString() {
        return mark+" "+model+" "+gen.yearFrom+" "+gen.yearTo;
    }

    @Override
    public JsonElement toJSONObject() {
        return null;
    }
}
