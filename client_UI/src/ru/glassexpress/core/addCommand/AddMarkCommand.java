package ru.glassexpress.core.addCommand;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_builder.RequestBuilder;

public class AddMarkCommand extends AddElementCommand{
    public AddMarkCommand(String answer) {
        super(answer, null
        );
    }

    @Override
    void prepareRequest() {
        request = new RequestBuilder().setMethod(Prefs.METHOD_GET)
                .setTarget(Prefs.TARGET_MARK)
                .setAction(Prefs.ACTION_INSERT)
                .setRequest("mark", answer)
                .build();
    }
}
