package ru.glassexpress.core.get_command;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.core.objects.*;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetGlassTableCommand extends ObservedCommand{
    public GetGlassTableCommand(BaseObject inputObject, String key) {
        super(inputObject, key);
    }



    @Override
    public void buildRequest() {
        GenerationObj car = (GenerationObj) inputObject;
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_TABLE_GOODS)
                .setKey(key)

                .setRequest("car", String.valueOf(car.getId()))
                .build();
    }

//    @Override
  //  void fillObject(int i) {
//        GlassObject tblRow = (GlassObject) components.get(i);
//    }
}
