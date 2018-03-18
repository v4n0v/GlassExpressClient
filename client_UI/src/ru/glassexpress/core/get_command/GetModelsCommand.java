package ru.glassexpress.core.get_command;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetModelsCommand extends IDTitleObsevedCommand{
    public GetModelsCommand(BaseObject object) {
        super(object);
    }

    @Override
    public void buildRequest() {
        IdTitleObj car = (IdTitleObj) inputObject;
        request = new RequestBuilder().setMethod(Prefs.METHOD_GET)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_MODEL)
                .setRequest("mark", String.valueOf(car.getId()))
                .build();
    }
}
