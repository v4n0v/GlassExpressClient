package ru.glassexpress.core.get_command;

import javafx.collections.ObservableList;
import ru.glassexpress.core.data.Prefs;
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
        request = new RequestBuilder().setMethod(Prefs.METHOD_POST)
                .setAction(Prefs.ACTION_CHECK)
                .setTarget(Prefs.TARGET_USER)
                 .setKey(key)
                .setRequest("login", usr.getTitle())

                .build();


    }
}
