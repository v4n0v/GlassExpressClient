package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.request_builder.RequestBuilder;

public class AddMarkCommand extends AddElementCommand{
    public AddMarkCommand(String answer, String key) {
        super(answer, null, key);
    }

    @Override
    protected void prepareRequest() {
        request = new RequestBuilder().setMethod(Prefs.METHOD_GET)
                .setTarget(Prefs.TARGET_MARK)
                .setAction(Prefs.ACTION_INSERT)
                .setKey(key)
                .setRequest("mark", answer)
                .build();
    }
}
