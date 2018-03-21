package ru.glassexpress.core.get_command.lists;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.core.get_command.IDTitleObsevedCommand;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetPositionsListCommand extends IDTitleObsevedCommand {
    public GetPositionsListCommand(BaseObject inputObject, String key) {
        super(inputObject, key);
    }

    @Override
    public void buildRequest() {
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_SELECT)
                .setTarget(Prefs.TARGET_POSITIONS)
                .setKey(key)
                .setBlankRequest()
                .build();
    }
}

