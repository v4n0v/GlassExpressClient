package ru.glassexpress.core.get_command.adapter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.core.objects.*;

import java.util.ArrayList;
import java.util.List;

public class BaseObjectAdapter {

    public static BaseObjectAdapter getInsance() {
        if (adapter==null) return new BaseObjectAdapter();
        return adapter;
    }

    static BaseObjectAdapter adapter;

    private BaseObjectAdapter() {

    }


    public static ObservableList<String> returnStringList(ObservableList<BaseObject> inputList) {

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

    public List<IdTitleObj> baseObjToIdTitleObj(List<BaseObject> inputList) {
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

    public ObservableList<String> idTitleObjToString(List<IdTitleObj> inputList) {
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

    public ObservableList<String> generationObjToString(List<GenerationObj> inputList) {
        if (inputList != null) {
            List<String> resultList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                GenerationObj obj = inputList.get(i);
                resultList.add((i + 1) + ". " + obj);
            }
            return FXCollections.observableArrayList(resultList);
        }
        return null;
    }


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
                // row.setGlassFactoryTitle(getTitleById(mainController.getDataMap().getGlassFactoryList(), row.getGlassFactoryId()));

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
                // row.setGlassFactoryTitle(getTitleById(mainController.getDataMap().getGlassFactoryList(), row.getGlassFactoryId()));

                //currentModelGenerations.add(cars);
                resultList.add(row);

            }
            return FXCollections.observableArrayList(resultList);

        }
        return null;

    }


    String getTitleById(List<IdTitleObj> objs, int id) {
        for (int i = 0; i < objs.size(); i++) {
            if (objs.get(i).getId() == id) {
                return objs.get(i).getTitle();
            }
        }
        return null;
    }

    public List<InsertClassElement> returnInsertClassElementList(ObservableList<BaseObject> inputList) {
        if (inputList != null) {
            List<InsertClassElement> resultList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                InsertClassElement row = (InsertClassElement) inputList.get(i);
                resultList.add(row);
            }
            return FXCollections.observableArrayList(resultList);
        }
        return null;
    }

    public UserObject returnUser(ObservableList<BaseObject> inputList) {

        if (inputList != null) {
            System.out.println("UserObject input size = "+inputList.size());
            UserObject result = (UserObject)inputList.get(0);
//            for (int i = 0; i < inputList.size(); i++) {
//                InsertClassElement row = (InsertClassElement) inputList.get(i);
//                resultList.add(row);
//            }
            return result;
        }
        return null;
    }

    public  List<ServiceObject> returnServicesList(List<BaseObject> services) {
        if (services != null) {
            List<ServiceObject> resultList = new ArrayList<>();
            for (BaseObject service : services) {
                ServiceObject obj= (ServiceObject) service;
                resultList.add(obj);
            }
            return FXCollections.observableArrayList(resultList);
        }
        return null;
    }


    public ObservableList<String> returnServicesStringList(List<ServiceObject> services) {
        if (services != null) {
            List<String> resultList = new ArrayList<>();
            for (ServiceObject service : services) {

                resultList.add(service.getTitle());
            }
            return FXCollections.observableArrayList(resultList);
        }
        return null;
    }
}
