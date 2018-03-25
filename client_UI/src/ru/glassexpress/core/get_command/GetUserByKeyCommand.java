package ru.glassexpress.core.get_command;

import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetUserByKeyCommand extends ObservedCommand {
    public GetUserByKeyCommand(String key) {
        super(null, key);
    }

    @Override
    public void buildRequest() {

          //  UserObject usr = (UserObject) inputObject;
            request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                    .setAction(Resources.ACTION_SELECT)
                    .setTarget(Resources.TARGET_USER)
                    .setKey(key)

                    .build();


    }
}
