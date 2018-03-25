package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.library.Resources;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.request_builder.RequestBuilder;

public class AddModelCommand extends AddElementCommand{

    public AddModelCommand(String answer, BaseObject baseObject, String key) {
        super(answer, baseObject, key);


    }
    @Override
    protected void prepareRequest(){
       // Car car = (Car) baseObject;
        IdTitleObj car = (IdTitleObj) baseObject;
        request=new RequestBuilder().setMethod(Resources.METHOD_GET)
                .setTarget(Resources.TARGET_MODEL)
                .setAction(Resources.ACTION_INSERT)
                .setKey(key)
                .setRequest("mark", car.getTitle())
                .setRequest("model", answer)
                .build();
    }
}
