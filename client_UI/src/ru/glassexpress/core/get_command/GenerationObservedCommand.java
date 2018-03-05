package ru.glassexpress.core.get_command;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.objects.GenerationObj;
import ru.glassexpress.objects.IdTitleObj;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_builder.RequestBuilder;

public class GenerationObservedCommand extends ObservedCommand {
    public GenerationObservedCommand(BaseObject object) {
        super(object);
    }

    @Override
    void buildRequest() {
        if (inputObject!=null) {
            IdTitleObj car = (IdTitleObj) inputObject;
            request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                    .setAction(Prefs.ACTION_SELECT)
                    .setTarget(Prefs.TARGET_GENERATION)
                    // .setRequest("mark", markListView.getSelectionModel().getSelectedItem())
                    .setRequest("model", String.valueOf(car.getId()))
                    .build();
        } else {
            System.out.println("GenerationObservedCommand inputObject = null!");
        }
    }

    @Override
    void fillObject(int i) {
//        GenerationObj cars = (GenerationObj) components.get(i);
//        //currentModelGenerations.add(cars);
//        resultString.add((i+1)+". "+cars.toString());
    }
}
