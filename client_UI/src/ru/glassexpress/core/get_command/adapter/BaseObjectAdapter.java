package ru.glassexpress.core.get_command.adapter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.core.get_command.ObservedCommand;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.GenerationObj;
import ru.glassexpress.objects.IdTitleObj;
import ru.glassexpress.objects.TableGoodsInStockRow;

import java.util.ArrayList;
import java.util.List;

public class BaseObjectAdapter {

    public BaseObjectAdapter() {
        this.inputList = inputList;
    }

    ObservableList<BaseObject> inputList;


    public ObservableList<String> returnStringList(ObservableList<BaseObject> inputList) {

        if (inputList != null) {
            List<String> resultList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                IdTitleObj obj = (IdTitleObj) inputList.get(i);
                resultList.add(obj.getTitle());

            }
            return FXCollections.observableArrayList(resultList);

        }
        return null;
    }

    public List<IdTitleObj> baseObjToIdTitleObj(List<BaseObject> inputList){
        if (inputList != null) {
            List<IdTitleObj> resultList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                IdTitleObj obj = (IdTitleObj) inputList.get(i);

                resultList.add(obj);
            }
            return resultList;
        }
        return null;
    }
    public ObservableList<String> IdTitleObjToString(List<IdTitleObj> inputList){
        if (inputList != null) {
            List<String> resultList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                IdTitleObj obj = (IdTitleObj) inputList.get(i);

                resultList.add(obj.getTitle());
            }
            return FXCollections.observableArrayList(resultList);
        }
        return null;
    }

    public ObservableList<String> generationObjToString(List<GenerationObj> inputList){
        if (inputList != null) {
            List<String> resultList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                GenerationObj obj = inputList.get(i);
                resultList.add((i+1)+". "+obj.getYearFrom()+"-"+obj.getYearTo());
            }
            return FXCollections.observableArrayList(resultList);
        }
        return null;
    }


//    public ObservableList<String> returnGenerationList(ObservableList<BaseObject> inputList) {
//
//        if (inputList != null) {
//            List<String> resultList = new ArrayList<>();
//            for (int i = 0; i < inputList.size(); i++) {
//                GenerationObj cars = (GenerationObj) inputList.get(i);
//                //currentModelGenerations.add(cars);
//                resultList.add((i + 1) + ". " + cars.toString());
//
//            }
//            return FXCollections.observableArrayList(resultList);
//        }
//        return null;
//    }

    public List<GenerationObj> returnGenerationList(ObservableList<BaseObject> inputList) {

        if (inputList != null) {
            List<GenerationObj> resultList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                GenerationObj car = (GenerationObj) inputList.get(i);
                //currentModelGenerations.add(cars);
                resultList.add(car);

            }
            return resultList;
        }
        return null;
    }
    public ObservableList<TableGoodsInStockRow> returnTableGoodsList(ObservableList<BaseObject> inputList) {

        if (inputList != null) {
            List<TableGoodsInStockRow> resultList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                TableGoodsInStockRow row = (TableGoodsInStockRow) inputList.get(i);
                //currentModelGenerations.add(cars);
                resultList.add(row);

            }
            return FXCollections.observableArrayList(resultList);

        }
        return null;

    }
}
