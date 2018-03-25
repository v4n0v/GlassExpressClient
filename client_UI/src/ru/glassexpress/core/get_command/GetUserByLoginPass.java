package ru.glassexpress.core.get_command;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetUserByLoginPass extends ObservedCommand {
    public GetUserByLoginPass(BaseObject userObject) {
        super(userObject, null);
    }

    @Override
    public void buildRequest() {

        IdTitleObj usr = (IdTitleObj) inputObject;
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_LOGIN)
                .setTarget(Prefs.TARGET_USER)
                // .setKey(key)
                .setRequest("login", usr.getTitle())
                .setRequest("pass", String.valueOf(usr.getId()))
                .build();


    }
}
