package ru.glassexpress.core;

import javafx.collections.ObservableList;
import ru.glassexpress.core.get_command.*;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.get_command.lists.GetPermissionsListCommand;
import ru.glassexpress.core.get_command.lists.GetPositionsListCommand;
import ru.glassexpress.core.get_command.lists.GetSalonsCommand;
import ru.glassexpress.core.objects.*;

import java.util.List;

public class GetListOperator implements GetListCommands {
String key;

    public GetListOperator(String key) {
        this.key = key;
    }

    private ObservedCommand command;
 //   Request request;

    BaseObjectAdapter objectAdapter = new BaseObjectAdapter();


    public List<BaseObject> getComponents() {
        return command.getComponents();
    }

    public List<IdTitleObj> getGlassTypes( ) {
        command = new GetGlassTypeCommand(null, key);

        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getModels(BaseObject object) {

        command = new GetModelsCommand(object,key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
    }


    public List<IdTitleObj> getMarks() {
        command = new GetMarksCommand(null, key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
    }
    @Override
    public List<GenerationObj> getGenerations(BaseObject object) {

        command = new GetGenerationCommand(object,key);
        return  objectAdapter.returnGenerationList(command.returnRecievedList());
    }

    public ObservableList<GlassObject> getTableGoods(BaseObject object) {
        command = new GetGlassTableCommand(object,key);
        return  objectAdapter.returnTableGLassList(command.returnRecievedList());
    }


    public List<IdTitleObj> getBodyTypes() {
        command = new GetBodyTypeCommand(null,key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }
    public List<IdTitleObj> getPermissions() {
        command = new GetPermissionsListCommand(null,key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }
    public List<IdTitleObj> getSalons() {
        command = new GetSalonsCommand(null,key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }
    public List<IdTitleObj> getPositions() {
        command = new GetPositionsListCommand(null,key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }
    public List<IdTitleObj> getGlassOptions() {
        command = new GetGlassOptionList(null,key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getGlassFactory() {
        command = new GetGlassFactoryCommand(null,key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }
    public List<InsertClass> getInsertClass() {
        command = new GetInsertClassCommand(null,key);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnInsertClassList(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }


    public List<InsertClassElement> getInsertClassElements() {
        command = new GetInsertClassElementCommand(null,key);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnInsertClassElementList(command.returnRecievedList());
    }


    public UserObject getUserByKey() {
        command = new GetUserByKeyCommand(key);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnUser(command.returnRecievedList());
    }
}
