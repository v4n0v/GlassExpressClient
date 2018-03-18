package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.request_builder.RequestBuilder;

public class DeleteGenerationCommand extends DeleteElementCommand {

    public DeleteGenerationCommand(int id) {
        super(id);
    }

    @Override
    void prepareRequest() {
        request = new RequestBuilder()
                .setMethod(Prefs.METHOD_POST)
                .setTarget(Prefs.TARGET_GENERATION)
                .setAction(Prefs.ACTION_DELETE)
                .setRequest("id", String.valueOf(id))
                .build();

    }
}
