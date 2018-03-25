package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class DeleteMarkCommand extends DeleteElementCommand {

    public DeleteMarkCommand(int id, String key) {
        super(id, key);
    }

    @Override
    void prepareRequest() {
        request = new RequestBuilder()
                .setMethod(Resources.METHOD_POST)
                .setTarget(Resources.TARGET_MARK)
                .setAction(Resources.ACTION_DELETE)
                .setKey(key)
                .setRequest("id", String.valueOf(id))
                .build();
    }
}

