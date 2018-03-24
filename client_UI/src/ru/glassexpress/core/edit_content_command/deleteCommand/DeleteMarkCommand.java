package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.request_builder.RequestBuilder;

public class DeleteMarkCommand extends DeleteElementCommand {

    public DeleteMarkCommand(int id, String key) {
        super(id, key);
    }

    @Override
    void prepareRequest() {
        request = new RequestBuilder()
                .setMethod(Prefs.METHOD_POST)
                .setTarget(Prefs.TARGET_MARK)
                .setAction(Prefs.ACTION_DELETE)
                .setKey(key)
                .setRequest("id", String.valueOf(id))
                .build();
    }
}

