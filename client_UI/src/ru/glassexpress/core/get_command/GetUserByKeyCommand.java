package ru.glassexpress.core.get_command;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetUserByKeyCommand extends ObservedCommand {
    public GetUserByKeyCommand(BaseObject inputObject) {
        super(inputObject);
    }

    @Override
    public void buildRequest() {
        if (inputObject!=null) {
            UserObject usr = (UserObject) inputObject;
            request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                    .setAction(Prefs.ACTION_SELECT)
                    .setTarget(Prefs.TARGET_USER)
                    .setRequest("key", usr.getKey())
                    .build();

        }
    }
}
