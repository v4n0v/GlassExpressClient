package ru.glassexpress.core.edit_content_command.updCommand;

import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.GlassObject;

public class UpdateOperator {

    UpdateElementCommand comand;

    public boolean updAutoInsertClass(int idClass,int autoId) {
        comand=new UpdateInsertClassCommand(idClass, autoId);
        return comand.updateElement();
    }

    public boolean editGlassIsComplete(GlassObject glassPrepared) {
        comand=new UpdateGlassCommand(glassPrepared);
        return  comand.updateElement();
    }


}
