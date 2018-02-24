package ru.glassexpress.core.addCommand;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_builder.RequestBuilder;

public class AddGeneratonCommand extends AddElementCommand {
    public AddGeneratonCommand(String model, BaseObject baseObject) {
        super(model, baseObject);
    }

    @Override
    void prepareRequest() {
        answer = answer.replace(" ", "");
        String[] gen = answer.split("-");
        Car car = (Car) baseObject;
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setTarget(Prefs.TARGET_GENERATION)
                .setAction(Prefs.ACTION_INSERT)
                //   .setRequest("mark", car.getMark())
                .setRequest("model", car.getModel())
                .setRequest("yearFrom", gen[0])
                .setRequest("yearTo", gen[1])
                .build();
    }
}
