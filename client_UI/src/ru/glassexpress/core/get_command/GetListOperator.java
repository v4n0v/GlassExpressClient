package ru.glassexpress.core.get_command;

import javafx.collections.ObservableList;
import ru.glassexpress.controllers.MainController;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.data.DataMap;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.GenerationObj;
import ru.glassexpress.objects.IdTitleObj;
import ru.glassexpress.objects.GlassObject;
import ru.glassexpress.request_builder.Request;

import java.util.List;

public class GetListOperator implements GetListCommands {

    private ObservedCommand command;
    Request request;
    MainController mainController;
    BaseObjectAdapter objectAdapter = new BaseObjectAdapter();


    public List<BaseObject> getComponents() {
        return command.getComponents();
    }

//    @Override
//    public ObservableList<String> getMarks(BaseObject object) {
//        command = new GetMarksCommand(object);
//
//        //return command.returnRecievedList();
//        return objectAdapter.returnStringList(command.returnRecievedList());
//    }

    public List<IdTitleObj> getGlassTypes( ) {
        command = new GetGlassTypeCommand(null);

        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

//    @Override
//    public ObservableList<String> getModels(BaseObject object) {
//
//        command = new GetModelsCommand(object);
//        return objectAdapter.returnStringList(command.returnRecievedList());
//    }
    public List<IdTitleObj> getModels(BaseObject object) {

        command = new GetModelsCommand(object);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
    }


    public List<IdTitleObj> getMarks() {
        command = new GetMarksCommand(null);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
    }
    @Override
    public List<GenerationObj> getGenerations(BaseObject object) {

        command = new GetGenerationCommand(object);
        return  objectAdapter.returnGenerationList(command.returnRecievedList());
    }

    public ObservableList<GlassObject> getTableGoods(BaseObject object) {
        command = new GetGlassTableCommand(object);
        return  objectAdapter.returnTableGLassList(command.returnRecievedList());
    }


    public List<IdTitleObj> getBodyTypes() {
        command = new GetBodyTypeCommand(null);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getGlassOptions() {
        command = new GetGlassOptionList(null);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getGlassFactory() {
        command = new GetGlassFactoryCommand(null);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }
}
