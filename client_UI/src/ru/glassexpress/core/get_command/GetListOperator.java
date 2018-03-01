package ru.glassexpress.core.get_command;

import javafx.collections.ObservableList;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.GenerationObj;
import ru.glassexpress.objects.IdTitleObj;
import ru.glassexpress.objects.TableGoodsInStockRow;
import ru.glassexpress.request_builder.Request;

import java.util.List;

public class GetListOperator implements GetListCommands {

    ObservedCommand command;
    Request request;
BaseObjectAdapter objectAdapter = new BaseObjectAdapter();


    public List<BaseObject> getComponents() {
        return command.getComponents();
    }

    @Override
    public ObservableList<String> getMarks(BaseObject object) {
        command = new GetMarksCommand(object);

        //return command.returnRecievedList();
        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    public List<IdTitleObj> getGlassTypes( ) {
        command = new GetGlassTypeCommand(null);

        return objectAdapter.baseObjToIdTitleObj(command.returnRecievedList());
//        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    @Override
    public ObservableList<String> getModels(BaseObject object) {

        command = new GetModelsCommand(object);
        return objectAdapter.returnStringList(command.returnRecievedList());
    }

    @Override
    public List<GenerationObj> getGenerations(BaseObject object) {

        command = new GenerationObservedCommand(object);
        return  objectAdapter.returnGenerationList(command.returnRecievedList());
    }

    public ObservableList<TableGoodsInStockRow> getTableGoods(BaseObject object) {
        command = new GetTableGoodsCommand(object);
        return  objectAdapter.returnTableGoodsList(command.returnRecievedList());
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
