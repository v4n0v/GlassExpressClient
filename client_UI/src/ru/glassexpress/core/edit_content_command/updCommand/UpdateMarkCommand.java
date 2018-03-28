package ru.glassexpress.core.edit_content_command.updCommand;

import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class UpdateMarkCommand extends UpdateElementCommand {
    public UpdateMarkCommand(IdTitleObj model, String key) {
        super(model, key);
    }

    @Override
    void prepareRequest() {

        IdTitleObj model = (IdTitleObj) object;

        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setTarget(Resources.TARGET_MARK)
                .setAction(Resources.ACTION_UPD)
                .setKey(key)

                .setRequest("id", String.valueOf(model.getId()))
                .setRequest("title", model.getTitle())

                .build();
    }
}