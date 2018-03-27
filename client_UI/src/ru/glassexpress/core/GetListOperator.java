package ru.glassexpress.core;

import javafx.collections.ObservableList;
import ru.glassexpress.core.get_command.*;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.get_command.lists.GetEmployeesListCommand;
import ru.glassexpress.core.get_command.lists.GetPermissionsListCommand;
import ru.glassexpress.core.get_command.lists.GetPositionsListCommand;
import ru.glassexpress.core.get_command.lists.GetSalonsCommand;
import ru.glassexpress.core.objects.*;
import ru.glassexpress.core.utils.ObservableListAdapter;

import java.util.Date;
import java.util.List;

public class GetListOperator implements GetListCommands {

    public void setKey(String key) {
        this.key = key;
    }

    private String key;
    public GetListOperator(String key) {

        this.key = key;
        objectAdapter=BaseObjectAdapter.getInsance();
    }

    private ObservedCommand command;
    //   Request request;

    private BaseObjectAdapter objectAdapter;// = BaseObjectAdapter.getInsance();//= new BaseObjectAdapter();


    public List<BaseObject> getComponents() {
        return command.getComponents();
    }

    public List<IdTitleObj> getGlassTypes() {
        command = new GetGlassTypeCommand(null, key);

        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getModels(BaseObject object) {

        command = new GetModelsCommand(object, key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
    }


    public List<IdTitleObj> getMarks() {
        command = new GetMarksCommand(null, key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
    }

    @Override
    public List<GenerationObj> getGenerations(BaseObject object) {

        command = new GetGenerationCommand(object, key);
        return objectAdapter.returnGenerationList(command.returnRecievedList());
    }

    public ObservableList<GlassObject> getTableGoods(BaseObject object) {
        command = new GetGlassTableCommand(object, key);
        return objectAdapter.returnTableGLassList(command.returnRecievedList());
    }


    public List<IdTitleObj> getBodyTypes() {
        command = new GetBodyTypeCommand(null, key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getPermissions() {
        command = new GetPermissionsListCommand(null, key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getSalons() {
        command = new GetSalonsCommand(null, key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getPositions() {
        command = new GetPositionsListCommand(null, key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getGlassOptions() {
        command = new GetGlassOptionList(null, key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getGlassFactory() {
        command = new GetGlassFactoryCommand(null, key);
        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<InsertClass> getInsertClass() {
        command = new GetInsertClassCommand(null, key);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnInsertClassList(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<ServiceObject> getServices() {
        command = new GetServicesCommand(key);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnServicesList(command.returnRecievedList());
    }

    public List<InsertClassElement> getInsertClassElements() {
        command = new GetInsertClassElementCommand(null, key);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnInsertClassElementList(command.returnRecievedList());
    }


    public UserObject getUserByKey() {
        command = new GetUserByKeyCommand(key);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnUser(command.returnRecievedList());
    }

    public IdTitleObj getUserByLoginPass(BaseObject user) {
        command = new GetUserByLoginPass(user);
//        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
        return objectAdapter.returnKey(command.returnRecievedList());
    }

    public boolean isUserFree(String login) {
        command  = new GetIsUserFreeCommand(new IdTitleObj(0, login), key);

        return command.returnRecievedList().size() <= 0;
    }

    public long getLastOpenedDay(IdElement id) {
        command  = new GetLastOpenedDayCommand(id, key);
        return objectAdapter.returnDay(command.returnRecievedList());
    }
//    ObservableListAdapter observableListAdapter;
    public List<UserObject> getEmloyees(UserObject administrator) {
        command = new GetEmployeesListCommand(administrator, key);
        return objectAdapter.returnUserList(command.returnRecievedList());
//        return observableListAdapter.asObservableListFromBase(command.returnRecievedList());
    }
}
