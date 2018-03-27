package ru.glassexpress.core.get_command;

import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.IdElement;

import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetLastOpenedDayCommand extends ObservedCommand{
    public GetLastOpenedDayCommand(BaseObject inputObject, String key) {
        super(inputObject, key);
    }

    @Override
    public void buildRequest() {

        IdElement id  = (IdElement) inputObject;
        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setAction(Resources.ACTION_SELECT)
                .setTarget(Resources.TARGET_LAST_DAY)
                .setKey(key)
//                .setRequest("id_admin", String.valueOf(usr.getId()))
                .setRequest("id_salon", String.valueOf(id.getId()))
                .build();

    }
}
