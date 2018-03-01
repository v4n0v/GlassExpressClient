package ru.glassexpress.core.get_command;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetBodyTypeCommand extends IDTitleObsevedCommand{
    public GetBodyTypeCommand(BaseObject object) {
        super(object);
    }

    @Override
    void buildRequest() {
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_BODY_TYPE)
                .setBlankRequest()
                .build();
    }
}