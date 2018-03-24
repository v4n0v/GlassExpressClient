package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.request_builder.RequestBuilder;

public class DeleteModelCommand extends DeleteElementCommand{
    public DeleteModelCommand(int id,String key) {
        super(id, key);
    }

    @Override
    void prepareRequest() {
        request = new RequestBuilder()
                .setMethod(Prefs.METHOD_POST)
                .setTarget(Prefs.TARGET_MODEL)
                .setAction(Prefs.ACTION_DELETE)
                .setKey(key)
                .setRequest("id", String.valueOf(id))
                .build();
    }
}
