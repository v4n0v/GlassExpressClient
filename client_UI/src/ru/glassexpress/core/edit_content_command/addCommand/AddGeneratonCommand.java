package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.library.Resources;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.request_builder.RequestBuilder;

public class AddGeneratonCommand extends AddElementCommand {
    public AddGeneratonCommand(String generation, BaseObject baseObject, String key) {
        super(generation, baseObject, key);
    }

    @Override
    protected void prepareRequest() {
        answer = answer.replace(" ", "");
        String[] gen = answer.split("-");
        // получаем модель, к которой добавляем поколение
        IdTitleObj car = (IdTitleObj) baseObject;
        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setTarget(Resources.TARGET_GENERATION)
                .setAction(Resources.ACTION_INSERT)
                .setKey(key)
                //   .setRequest("mark", car.getMark())
                .setRequest("model", String.valueOf(car.getId()))
                .setRequest("yearFrom", gen[0])
                .setRequest("yearTo", gen[1])
                .build();
    }
}
