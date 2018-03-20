package ru.glassexpress.core;

import javafx.collections.ObservableList;
import ru.glassexpress.core.get_command.*;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.*;

import java.util.List;

public class GetListOperator implements GetListCommands {



    private ObservedCommand command;
 //   Request request;

    BaseObjectAdapter objectAdapter = new BaseObjectAdapter();


    public List<BaseObject> getComponents() {
        return command.getComponents();
    }

    public List<IdTitleObj> getGlassTypes( ) {
        command = new GetGlassTypeCommand(null);

        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

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
    public List<InsertClass> getInsertClass() {
        command = new GetInsertClassCommand(null);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnInsertClassList(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }


    public List<InsertClassElement> getInsertClassElements() {
        command = new GetInsertClassElementCommand(null);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnInsertClassElementList(command.returnRecievedList());
    }


    public UserObject getUserByKey(UserObject user) {
        command = new GetUserByKeyCommand(user);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnUser(command.returnRecievedList());
    }
}
