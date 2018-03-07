package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.Prefs;
import ru.glassexpress.request_builder.RequestBuilder;

public class DeleteModelCommand extends DeleteElementCommand{
    public DeleteModelCommand(int id) {
        super(id);
    }

    @Override
    void prepareRequest() {
        request = new RequestBuilder()
                .setMethod(Prefs.METHOD_POST)
                .setTarget(Prefs.TARGET_MODEL)
                .setAction(Prefs.ACTION_DELETE)
                .setRequest("id", String.valueOf(id))
                .build();
    }
}
