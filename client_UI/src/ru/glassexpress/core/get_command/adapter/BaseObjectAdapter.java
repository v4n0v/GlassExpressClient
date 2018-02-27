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
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            IdTitleObj obj = (IdTitleObj) inputList.get(i);
            resultList.add(obj.getTitle());

        }
        return FXCollections.observableArrayList(resultList);
    }


    public ObservableList<String> returnGenerationList(ObservableList<BaseObject> inputList) {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            GenerationObj cars = (GenerationObj) inputList.get(i);
            //currentModelGenerations.add(cars);
            resultList.add((i+1)+". "+cars.toString());

        }
        return FXCollections.observableArrayList(resultList);
    }

    public ObservableList<TableGoodsInStockRow> returnTableGoodsList(ObservableList<BaseObject> inputList) {
        List<TableGoodsInStockRow> resultList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            TableGoodsInStockRow row = (TableGoodsInStockRow) inputList.get(i);
            //currentModelGenerations.add(cars);
            resultList.add(row);

        }
        return FXCollections.observableArrayList(resultList);
    }
}
