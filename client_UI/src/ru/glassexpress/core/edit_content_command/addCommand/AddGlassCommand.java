package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.request_builder.RequestBuilder;

public class AddGlassCommand extends AddElementCommand{
    public AddGlassCommand(String answer, BaseObject baseObject) {
        super(answer, baseObject);
    }

    @Override
    protected void prepareRequest() {

        Car car = (Car) baseObject;
        request=new RequestBuilder().setMethod(Prefs.METHOD_GET)
                .setTarget(Prefs.TARGET_MODEL)
                .setAction(Prefs.ACTION_INSERT)
                .setRequest("carId", String.valueOf(car.getId()))
                .setRequest("model", answer)
                .build();
    }
}
