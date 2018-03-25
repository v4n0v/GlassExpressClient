package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class DeleteGlassCommand extends DeleteElementCommand {
    public DeleteGlassCommand(int id, String key) {
        super(id, key);
    }

    @Override
    void prepareRequest() {
        request = new RequestBuilder()
                .setMethod(Resources.METHOD_POST)
                .setTarget(Resources.TARGET_GLASS)
                .setAction(Resources.ACTION_DELETE)
                .setRequest("id", String.valueOf(id))
                .build();
    }
}
