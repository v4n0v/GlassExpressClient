package ru.glassexpress.core.get_command.lists;

import ru.glassexpress.library.Resources;
import ru.glassexpress.core.get_command.IDTitleObsevedCommand;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetPermissionsListCommand extends IDTitleObsevedCommand {
    public GetPermissionsListCommand(BaseObject inputObject, String key) {
        super(inputObject, key);
    }

    @Override
    public void buildRequest() {
        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setAction(Resources.ACTION_SELECT)
                .setTarget(Resources.TARGET_PERMISSIONS)
                .setKey(key)
                .setBlankRequest()
                .build();
    }
}