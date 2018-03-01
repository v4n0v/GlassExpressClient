package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.Prefs;
import ru.glassexpress.core.edit_content_command.addCommand.AddElementCommand;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.request_builder.RequestBuilder;

import java.util.zip.Deflater;

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
