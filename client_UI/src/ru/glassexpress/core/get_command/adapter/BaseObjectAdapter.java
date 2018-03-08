package ru.glassexpress.core.get_command.adapter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.controllers.MainController;
import ru.glassexpress.data.DataMap;
import ru.glassexpress.objects.*;

import java.util.ArrayList;
import java.util.List;

public class BaseObjectAdapter {


    public BaseObjectAdapter() {

    }


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
    public ObservableList<String> idTitleObjToString(List<IdTitleObj> inputList){
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
    public ObservableList<GlassObject> returnTableGLassList(ObservableList<BaseObject> inputList) {

        if (inputList != null) {
            List<GlassObject> resultList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                GlassObject row = (GlassObject) inputList.get(i);
               // row.setGlassFactoryTitle(getTitleById(mainController.getDataMap().getGlassFactoryList(), row.getGlassFactory()));

                //currentModelGenerations.add(cars);
                resultList.add(row);

            }
            return FXCollections.observableArrayList(resultList);

        }
        return null;

    }

    public List<InsertClass> returnInsertClassList(List<BaseObject> inputList) {

        if (inputList != null) {
            List<InsertClass> resultList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                InsertClass row = (InsertClass) inputList.get(i);
                // row.setGlassFactoryTitle(getTitleById(mainController.getDataMap().getGlassFactoryList(), row.getGlassFactory()));

                //currentModelGenerations.add(cars);
                resultList.add(row);

            }
            return FXCollections.observableArrayList(resultList);

        }
        return null;

    }




    String getTitleById(List<IdTitleObj> objs, int id){
        for (int i = 0; i < objs.size(); i++) {
            if (objs.get(i).getId()==id){
                return objs.get(i).getTitle();
            }
        }
        return null;
    }

}
