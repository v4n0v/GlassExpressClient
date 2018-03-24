package ru.glassexpress.core.edit_content_command.updCommand;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.request_builder.RequestBuilder;

public class UpdateInsertClassCommand extends UpdateElementCommand {

    private int autoId;


    public UpdateInsertClassCommand(int id, int autoId,String key) {
       super(id, key);
        this.autoId = autoId;
    }


    @Override
    void prepareRequest() {
        request = new RequestBuilder()
                .setMethod(Prefs.METHOD_POST)
                .setTarget(Prefs.TARGET_INSERT_CLASS)
                .setAction(Prefs.ACTION_UPD)
                .setRequest("id", String.valueOf(id))
                .setRequest("autoId", String.valueOf(autoId))
                .build();
        System.out.println(request);
    }
}
