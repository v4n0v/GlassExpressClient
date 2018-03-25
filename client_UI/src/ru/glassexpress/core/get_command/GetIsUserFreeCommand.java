package ru.glassexpress.core.get_command;

import ru.glassexpress.library.Resources;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetIsUserFreeCommand extends ObservedCommand {
    public GetIsUserFreeCommand(BaseObject userObject, String key) {
        super(userObject, key);
    }

    @Override
    public void buildRequest() {

        IdTitleObj usr = (IdTitleObj) inputObject;
        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setAction(Resources.ACTION_CHECK)
                .setTarget(Resources.TARGET_USER)
                 .setKey(key)
                .setRequest("login", usr.getTitle())

                .build();


    }
}
