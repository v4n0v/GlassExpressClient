package ru.glassexpress.core.get_command.lists;

import ru.glassexpress.core.get_command.ObservedCommand;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetEmployeesListCommand extends ObservedCommand {
    public GetEmployeesListCommand(BaseObject inputObject, String key) {
        super(inputObject, key);
    }

    @Override
    public void buildRequest() {
        UserObject usr = (UserObject) inputObject;
        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setAction(Resources.ACTION_SELECT)
                .setTarget(Resources.TARGET_EMPLOYEES)
                .setKey(key)
                .setRequest("id_salon", String.valueOf(usr.getSalonId()))
                .build();
    }
}
