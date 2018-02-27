package ru.glassexpress.core.get_command.adapter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.core.get_command.ObservedCommand;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.IdTitleObj;

import java.util.ArrayList;
import java.util.List;

public class IdTitleSringAdapter {

    public IdTitleSringAdapter(ObservableList<BaseObject> inputList) {
        this.inputList = inputList;
    }

    ObservableList<BaseObject> inputList;


    public ObservableList<String> returnList() {

        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            IdTitleObj obj = (IdTitleObj) inputList.get(i);
            resultList.add(obj.getTitle());

        }

        return FXCollections.observableArrayList(resultList);
    }
}
