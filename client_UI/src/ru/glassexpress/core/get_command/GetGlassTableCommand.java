package ru.glassexpress.core.get_command;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.*;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetGlassTableCommand extends ObservedCommand{
    public GetGlassTableCommand(BaseObject inputObject) {
        super(inputObject);
    }




    @Override
    public void buildRequest() {
        GenerationObj car = (GenerationObj) inputObject;
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_TABLE_GOODS)
                .setRequest("car", String.valueOf(car.getId()))
                .build();
    }

//    @Override
  //  void fillObject(int i) {
//        GlassObject tblRow = (GlassObject) components.get(i);
//    }
}
