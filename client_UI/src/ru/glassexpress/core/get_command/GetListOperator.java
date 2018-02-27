package ru.glassexpress.core.get_command;

import javafx.collections.ObservableList;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.TableGoodsInStockRow;
import ru.glassexpress.request_builder.Request;

import java.util.List;

public class GetListOperator implements GetListCommands {

    ObservedCommand command;
    Request request;
BaseObjectAdapter titleStringAdapter = new BaseObjectAdapter();


    public List<BaseObject> getComponents() {
        return command.getComponents();
    }

    @Override
    public ObservableList<String> getMarks(BaseObject object) {
        command = new GetMarksCommand(object);

        return titleStringAdapter.returnStringList(command.returnList());
    }

    @Override
    public ObservableList<String> getModels(BaseObject object) {

        command = new GetModelsCommand(object);
        return titleStringAdapter.returnStringList(command.returnList());
    }

    @Override
    public ObservableList<String> getGenerations(BaseObject object) {

        command = new GenerationObservedCommand(object);
        return titleStringAdapter.returnGenerationList(command.returnList());
    }

    public ObservableList<TableGoodsInStockRow> getTableGoods(BaseObject object) {
        command = new GetTableGoodsCommand(object);
        return  titleStringAdapter.returnTableGoodsList(command.returnList());
    }




}
