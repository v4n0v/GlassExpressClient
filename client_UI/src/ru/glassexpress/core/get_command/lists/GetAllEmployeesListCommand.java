package ru.glassexpress.core.get_command.lists;

import ru.glassexpress.core.get_command.ObservedCommand;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetAllEmployeesListCommand extends ObservedCommand {

    public GetAllEmployeesListCommand(String key) {
        super(null, key);
    }

    @Override
    public void buildRequest() {

        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setAction(Resources.ACTION_SELECT)
                .setTarget(Resources.TARGET_ALL_EMPLOYEES)
                .setKey(key)
                .build();
    }
}
