package ru.glassexpress.core.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.ServiceObject;

import java.util.ArrayList;
import java.util.List;

public class ObservableListAdapter<T> {

    public ObservableList<T> asObservableList(List<T> inputList){

        if (inputList != null) {
            List<T> resultList = new ArrayList<>(inputList);
            return FXCollections.observableArrayList(resultList);
        }
        return null;

    }

    public ObservableList<T> asObservableListFromBase(List<BaseObject> inputList){

        if (inputList != null) {
            List<T> resultList = new ArrayList<>();
            for (BaseObject element: inputList){
                T obj = (T) element;
                resultList.add(obj);
            }
            return FXCollections.observableArrayList(resultList);
        }
        return null;

    }
    public ObservableList<T> convertObservableList(List<BaseObject> inputList, List<T> outputList){

        if (inputList != null) {
           // List<T> resultList = new ArrayList<>();
            for (BaseObject element: inputList){
                T obj = (T) element;
                outputList.add(obj);
            }
            return FXCollections.observableArrayList(outputList);
        }
        return null;

    }
    public  List<T> convertList(List<BaseObject> inputList, List<T> outputList){

        if (inputList != null) {
            // List<T> resultList = new ArrayList<>();
            for (BaseObject element: inputList){
                T obj = (T) element;
                outputList.add(obj);
            }
            return outputList;
        }
        return null;

    }


}
