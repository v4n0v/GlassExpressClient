package ru.glassexpress.core.get_command;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.IdTitleObj;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetGlassFactoryCommand extends IDTitleObsevedCommand{
    public GetGlassFactoryCommand(BaseObject object) {
        super(object);
    }

    @Override
    public void buildRequest() {
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_GLASS_FACTORY)
                .setBlankRequest()
                .build();
    }
}
