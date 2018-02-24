package ru.glassexpress.core.get_command;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.objects.GenerationObj;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_builder.RequestBuilder;

public class GenerationObservedCommand extends ObservedCommand {
    public GenerationObservedCommand(BaseObject object) {
        super(object);
    }

    @Override
    void buildRequest() {
        Car car = (Car) inputObject;
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_GENERATION)
                // .setRequest("mark", markListView.getSelectionModel().getSelectedItem())
                .setRequest("model", car.getModel())
                .build();
    }

    @Override
    void fillObject(int i) {
        GenerationObj cars = (GenerationObj) components.get(i);
        //currentModelGenerations.add(cars);
        resultString.add((i+1)+". "+cars.toString());
    }
}
