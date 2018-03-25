package ru.glassexpress.core.get_command;

import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class GetServicesCommand extends ObservedCommand{
    public GetServicesCommand(  String key) {
        super(null, key);
    }

    @Override
    public void buildRequest() {
        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setAction(Resources.ACTION_SELECT)
                .setTarget(Resources.TARGET_SERVICE)
                .setKey(key)
                .setBlankRequest()
                .build();
    }
}
