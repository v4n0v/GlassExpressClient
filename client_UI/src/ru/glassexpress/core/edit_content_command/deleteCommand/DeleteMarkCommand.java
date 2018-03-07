package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.Prefs;
import ru.glassexpress.request_builder.RequestBuilder;

public class DeleteMarkCommand extends DeleteElementCommand {

    public DeleteMarkCommand(int id) {
        super(id);
    }

    @Override
    void prepareRequest() {
        request = new RequestBuilder()
                .setMethod(Prefs.METHOD_POST)
                .setTarget(Prefs.TARGET_MARK)
                .setAction(Prefs.ACTION_DELETE)
                .setRequest("id", String.valueOf(id))
                .build();
    }
}

