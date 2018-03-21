package ru.glassexpress.core.get_command;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetGlassOptionList extends IDTitleObsevedCommand{
    public GetGlassOptionList(BaseObject inputObject, String key) {
        super(inputObject, key);
    }
    @Override
    public void buildRequest() {
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_GLASS_OPTION)
                .setKey(key)
                .setBlankRequest()
                .build();
    }
}
