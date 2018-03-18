package ru.glassexpress.core.data;

import ru.glassexpress.core.objects.*;

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
    private List<InsertClassElement> insertClassElementList;


    public List<InsertClassElement> getInsertClassElementList() {
        return insertClassElementList;
    }

    public void setInsertClassElementList(List<InsertClassElement> insertClassElementList) {
        this.insertClassElementList = insertClassElementList;
    }


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


    public void setPreparedGlass(GlassObject glassTableRow) {
        this.glassTableRow = glassTableRow;
    }

    public float getInsertClassPriceByGlassType(int inserClassId, int glassTypeId){
        for (int i = 0; i < insertClassList.size(); i++) {
            if (insertClassList.get(i).getId()==inserClassId){
                if (glassTypeId==1)
                    return insertClassList.get(i).getInsertFront();
                if (glassTypeId==2)
                    return insertClassList.get(i).getInsertRear();
                if (glassTypeId==3||glassTypeId==4||glassTypeId==5||glassTypeId==6)
                    return insertClassList.get(i).getInsertSide();
            }
        }

        return 0;
    }

    public int getPosById(List<IdTitleObj> list, int id){
        int c=0;
                for (IdTitleObj o: list){
                    if (o.getId()==id) {
                        return c;
                    }
                    c++;
                }
        return -1;
    }

    public int getIdByTitle(List<IdTitleObj> list, String title){
        for (IdTitleObj o: list){
            if (o.getTitle().equals(title)) return o.getId();
        }
        return -1;
    }

}
