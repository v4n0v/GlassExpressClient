package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.request_builder.RequestBuilder;

public class DeleteGlassCommand extends DeleteElementCommand {
    public DeleteGlassCommand(int id) {
        super(id);
    }

    @Override
    void prepareRequest() {
        request = new RequestBuilder()
                .setMethod(Prefs.METHOD_POST)
                .setTarget(Prefs.TARGET_GLASS)
                .setAction(Prefs.ACTION_DELETE)
                .setRequest("id", String.valueOf(id))
                .build();
    }
}
