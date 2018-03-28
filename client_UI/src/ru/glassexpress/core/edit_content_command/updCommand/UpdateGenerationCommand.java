package ru.glassexpress.core.edit_content_command.updCommand;

import ru.glassexpress.core.objects.GenerationObj;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class UpdateGenerationCommand extends UpdateElementCommand {
    public UpdateGenerationCommand(GenerationObj generationObj, String key) {
        super(generationObj, key);
    }

    @Override
    void prepareRequest() {

        GenerationObj gen = (GenerationObj) object;

        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setTarget(Resources.TARGET_GENERATION)
                .setAction(Resources.ACTION_UPD)
                .setKey(key)
                //   .setRequest("mark", car.getMark())
                .setRequest("id", String.valueOf(gen.getId()))
                .setRequest("yearFrom", String.valueOf(gen.getYearFrom()))
                .setRequest("yearTo", String.valueOf(gen.getYearTo()))
                .build();
    }
}
