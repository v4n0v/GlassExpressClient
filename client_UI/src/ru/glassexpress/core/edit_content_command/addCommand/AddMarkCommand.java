package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class AddMarkCommand extends AddElementCommand{
    public AddMarkCommand(String answer, String key) {
        super(answer, null, key);
    }

    @Override
    protected void prepareRequest() {
        request = new RequestBuilder().setMethod(Resources.METHOD_GET)
                .setTarget(Resources.TARGET_MARK)
                .setAction(Resources.ACTION_INSERT)
                .setKey(key)
                .setRequest("mark", answer)
                .build();
    }
}
