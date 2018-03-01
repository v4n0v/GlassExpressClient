package ru.glassexpress.data;

import ru.glassexpress.objects.GenerationObj;
import ru.glassexpress.objects.IdTitleObj;
import ru.glassexpress.objects.TableGoodsInStockRow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// класс, в котором хранятся данные в "чистом виде", как пришли с сервера
public class DataMap {
    private TableGoodsInStockRow glassTableRow;
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


    public void setGlassTableRow(TableGoodsInStockRow glassTableRow) {
        this.glassTableRow = glassTableRow;
    }
}
