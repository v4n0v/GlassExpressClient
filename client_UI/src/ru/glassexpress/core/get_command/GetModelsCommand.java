package ru.glassexpress.core.get_command;

import ru.glassexpress.library.Resources;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetModelsCommand extends IDTitleObsevedCommand{
    public GetModelsCommand(BaseObject inputObject, String key) {
        super(inputObject, key);
    }
    @Override
    public void buildRequest() {
        IdTitleObj car = (IdTitleObj) inputObject;
        request = new RequestBuilder().setMethod(Resources.METHOD_GET)
                .setAction(Resources.ACTION_SELECT)
                .setTarget(Resources.TARGET_MODEL)
                .setKey(key)
                .setRequest("mark", String.valueOf(car.getId()))
                .build();
    }
}
