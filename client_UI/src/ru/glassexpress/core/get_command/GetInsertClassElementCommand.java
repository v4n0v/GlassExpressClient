package ru.glassexpress.core.get_command;

import ru.glassexpress.library.Resources;

import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetInsertClassElementCommand extends ObservedCommand {


    public GetInsertClassElementCommand(BaseObject inputObject, String key) {
        super(inputObject, key);
    }
    @Override
    public void buildRequest() {


            request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                    .setAction(Resources.ACTION_SELECT)
                    .setTarget(Resources.TARGET_INSERT_CLASS_ELEMENT)
                    .setKey(key)

                    .setBlankRequest()
                    .build();
        }

//    @Override
//    void fillObject(int i) {
//
//    }

}
