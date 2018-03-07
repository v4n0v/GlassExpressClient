package ru.glassexpress.core.get_command;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.objects.IdTitleObj;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetModelsCommand extends IDTitleObsevedCommand{
    public GetModelsCommand(BaseObject object) {
        super(object);
    }

    @Override
    void buildRequest() {
        IdTitleObj car = (IdTitleObj) inputObject;
        request = new RequestBuilder().setMethod(Prefs.METHOD_GET)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_MODEL)
                .setRequest("mark", String.valueOf(car.getId()))
                .build();
    }
}
