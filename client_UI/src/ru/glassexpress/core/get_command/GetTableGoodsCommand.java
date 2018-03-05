package ru.glassexpress.core.get_command;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.objects.GlassObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetTableGoodsCommand extends ObservedCommand{
    public GetTableGoodsCommand(BaseObject inputObject) {
        super(inputObject);
    }




    @Override
    void buildRequest() {
        Car car = (Car) inputObject;
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_TABLE_GOODS)
                .setRequest("car", String.valueOf(car.getId()))
                .build();
    }

    @Override
    void fillObject(int i) {
        GlassObject tblRow = (GlassObject) components.get(i);
    }
}
