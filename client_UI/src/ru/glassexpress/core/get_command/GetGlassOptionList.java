package ru.glassexpress.core.get_command;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetGlassOptionList extends IDTitleObsevedCommand{
    public GetGlassOptionList(BaseObject object) {
        super(object);
    }

    @Override
    public void buildRequest() {
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_GLASS_OPTION)
                .setBlankRequest()
                .build();
    }
}
