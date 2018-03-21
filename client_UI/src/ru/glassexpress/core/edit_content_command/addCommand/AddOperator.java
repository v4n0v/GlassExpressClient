package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.core.edit_content_command.updCommand.UpdateGlassCommand;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.GlassObject;

public class AddOperator  implements AddCommands{
    AddElementCommand comand;
    String key;
    public AddOperator(String key){
        this.key=key;
    }

    @Override
    public boolean addModelIsComplete(String model, BaseObject baseObject) {
        comand=new AddModelCommand(model, baseObject, key);
        return comand.addElement(model);
    }

    @Override
    public boolean addMarkIsComplete(String mark) {
        comand=new AddMarkCommand(mark, key);
//        return comand.isOk(mark);
        return comand.addElement(mark);
    }

    @Override
    public boolean addGenerationIsComplete(String generation, BaseObject baseObject) {
        comand=new AddGeneratonCommand(generation, baseObject, key);
        return comand.addElement(generation);
    }

    public boolean addGlassIsComplete(BaseObject baseObject) {
        comand=new AddGlassCommand(baseObject,key);
        return comand.addElement(null);
    }
    public boolean addUserIsComplete(BaseObject baseObject) {
        comand=new AddUserCommand(baseObject,key);
        return comand.addElement(null);
    }

}
