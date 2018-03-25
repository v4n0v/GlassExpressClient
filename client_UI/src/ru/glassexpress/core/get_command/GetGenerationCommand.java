package ru.glassexpress.core.get_command;

import ru.glassexpress.library.Resources;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetGenerationCommand extends ObservedCommand {
    public GetGenerationCommand(BaseObject inputObject, String key) {
        super(inputObject, key);
    }
    @Override
    public void buildRequest() {
        if (inputObject!=null) {
            IdTitleObj car = (IdTitleObj) inputObject;
            request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                    .setAction(Resources.ACTION_SELECT)
                    .setTarget(Resources.TARGET_GENERATION)
                    .setKey(key)
                    // .setRequest("mark", markListView.getSelectionModel().getSelectedItem())
                    .setRequest("model", String.valueOf(car.getId()))
                    .build();
        } else {
            System.out.println("GetGenerationCommand inputObject = null!");
        }
    }

  //  @Override
//    void fillObject(int i) {
//        GenerationObj cars = (GenerationObj) components.get(i);
//        //currentModelGenerations.add(cars);
//        resultString.add((i+1)+". "+cars.toString());
    //}
}
