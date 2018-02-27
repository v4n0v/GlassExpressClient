package ru.glassexpress.core.get_command;

import javafx.collections.ObservableList;
import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_builder.RequestBuilder;

import java.util.List;

public class GetListOperator implements GetListCommands {

    ObservedCommand command;
    Request request;

    public List<BaseObject> getComponents() {
        return command.getComponents();
    }

    @Override
    public ObservableList<String> getMarks(BaseObject object) {


        command = new GetMarksCommand(object);
        return command.returnList();
    }

    @Override
    public ObservableList<String> getModels(BaseObject object) {

        command = new GetModelsCommand(object);
        return command.returnList();
    }

    @Override
    public ObservableList<String> getGenerations(BaseObject object) {

        command = new GenerationObservedCommand(object);
        return command.returnList();
    }

//    public ObservableList<String> getTableGoods(BaseObject object) {
//
//        command = new GenerationObservedCommand(object);
//        return command.returnList();
//    }




}
