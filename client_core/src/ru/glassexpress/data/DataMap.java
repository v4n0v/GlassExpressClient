package ru.glassexpress.data;

import ru.glassexpress.objects.GenerationObj;
import ru.glassexpress.objects.IdTitleObj;
import ru.glassexpress.objects.GlassObject;
import ru.glassexpress.objects.InsertClass;

import java.util.List;

// класс, в котором хранятся данные в "чистом виде", как пришли с сервера
public class DataMap {
    private GlassObject glassTableRow;
    // список видов стекол
    private List<IdTitleObj> glassTypeList;
    // список типов кузовов
    private List<IdTitleObj> bodyTypeList;
    // список свойств стекла
    private List<IdTitleObj> glassOptList;
    // список заводов производителей
    private List<IdTitleObj> glassFactoryList;
    // список поколений(автомобилей марки)
    private List<GenerationObj> generationObjList;
    // список марок
    private List<IdTitleObj> carMarksList;
    // список стекол
    private List<GlassObject> glassList;
// список классов вклейки\установик
private List<InsertClass> insertClassList;

    public List<InsertClass> getInsertClassList() {
        return insertClassList;
    }

    public void setInsertClassList(List<InsertClass> insertClassList) {
        this.insertClassList = insertClassList;
    }

    public List<GlassObject> getGlassList() {
        return glassList;
    }

    public void setGlassList(List<GlassObject> glassList) {
        this.glassList = glassList;
    }


    public List<IdTitleObj> getCarMarksList() {
        return carMarksList;
    }

    public void setCarMarksList(List<IdTitleObj> carMarksList) {
        this.carMarksList = carMarksList;
    }

    public List<IdTitleObj> getCarModelsList() {
        return carModelsList;
    }

    public void setCarModelsList(List<IdTitleObj> carModelsList) {
        this.carModelsList = carModelsList;
    }

    private List<IdTitleObj> carModelsList;

    public List<GenerationObj> getGenerationObjList() {
        return generationObjList;
    }

    public void setGenerationObjList(List<GenerationObj> generationObjList) {
        this.generationObjList = generationObjList;
    }

    public List<IdTitleObj> getGlassTypeList() {
        return glassTypeList;
    }

    public void setGlassTypeList(List<IdTitleObj> glassTypeList) {
        this.glassTypeList = glassTypeList;
    }

    public List<IdTitleObj> getBodyTypeList() {
        return bodyTypeList;
    }

    public void setBodyTypeList(List<IdTitleObj> bodyTypeList) {
        this.bodyTypeList = bodyTypeList;
    }


    public List<IdTitleObj> getGlassOptList() {
        return glassOptList;
    }

    public void setGlassOptList(List<IdTitleObj> glassOptList) {
        this.glassOptList = glassOptList;
    }



    public List<IdTitleObj> getGlassFactoryList() {
        return glassFactoryList;
    }

    public void setGlassFactoryList(List<IdTitleObj> glassFactoryList) {
        this.glassFactoryList = glassFactoryList;
    }


    public void setGlassTableRow(GlassObject glassTableRow) {
        this.glassTableRow = glassTableRow;
    }


}
