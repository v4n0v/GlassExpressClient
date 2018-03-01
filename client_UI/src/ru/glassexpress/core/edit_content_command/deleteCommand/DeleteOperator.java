package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.core.edit_content_command.addCommand.AddGeneratonCommand;
import ru.glassexpress.objects.BaseObject;

public class DeleteOperator {
    DeleteElementCommand comand;

    public boolean deleteGenerationIsComplete(int id) {
        comand=new DeleteGenerationCommand(id);
        return comand.deleteElement();
    }

}
