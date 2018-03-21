package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.core.data.Prefs;
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
        request=new RequestBuilder().setMethod(Prefs.METHOD_GET)
                .setTarget(Prefs.TARGET_MODEL)
                .setAction(Prefs.ACTION_INSERT)
                .setKey(key)
                .setRequest("mark", car.getTitle())
                .setRequest("model", answer)
                .build();
    }
}
