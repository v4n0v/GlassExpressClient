package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class DeleteModelCommand extends DeleteElementCommand{
    public DeleteModelCommand(int id,String key) {
        super(id, key);
    }

    @Override
    void prepareRequest() {
        request = new RequestBuilder()
                .setMethod(Resources.METHOD_POST)
                .setTarget(Resources.TARGET_MODEL)
                .setAction(Resources.ACTION_DELETE)
                .setKey(key)
                .setRequest("id", String.valueOf(id))
                .build();
    }
}
