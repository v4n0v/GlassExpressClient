package ru.glassexpress.core.edit_content_command.updCommand;

import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.GlassObject;

public class UpdateOperator {

    private final String key;
    UpdateElementCommand comand;

    public UpdateOperator(String key) {
        this.key=key;
    }

    public boolean updAutoInsertClass(int idClass,int autoId, String key) {
        comand=new UpdateInsertClassCommand(idClass, autoId, key);
        return comand.updateElement();
    }

    public boolean editGlassIsComplete(GlassObject glassPrepared, String key) {
        comand=new UpdateGlassCommand(glassPrepared, key);
        return  comand.updateElement();
    }


}
