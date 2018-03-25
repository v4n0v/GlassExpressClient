package ru.glassexpress.core.get_command;

import ru.glassexpress.library.Resources;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetInsertClassCommand extends ObservedCommand {
    public    GetInsertClassCommand (BaseObject inputObject, String key) {
        super(inputObject, key);
    }
    @Override
    public void buildRequest() {
        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setAction(Resources.ACTION_SELECT)
                .setTarget(Resources.TARGET_INSERT_CLASS)
                .setKey(key)
                .setBlankRequest()
                .build();
    }

//    @Override
//    void fillObject(int i) {
//
//    }
}