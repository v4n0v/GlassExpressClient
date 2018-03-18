package ru.glassexpress.core.get_command;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetInsertClassCommand extends ObservedCommand {
    public    GetInsertClassCommand (BaseObject object) {
        super(object);
    }

    @Override
    public void buildRequest() {
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_INSERT_CLASS)
                .setBlankRequest()
                .build();
    }

//    @Override
//    void fillObject(int i) {
//
//    }
}