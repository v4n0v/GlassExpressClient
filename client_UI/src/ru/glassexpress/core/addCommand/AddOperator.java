package ru.glassexpress.core.addCommand;

import ru.glassexpress.objects.BaseObject;

public class AddOperator  implements AddCommands{
    AddElementCommand comand;


    @Override
    public boolean addModelIsComplete(String model, BaseObject baseObject) {
        comand=new AddModelCommand(model, baseObject);
        return comand.addElement(model);
    }

    @Override
    public boolean addMarkIsComplete(String mark) {
        comand=new AddMarkCommand(mark);
//        return comand.isOk(mark);
        return comand.addElement(mark);
    }

    @Override
    public boolean addGenerationIsComplete(String generation, BaseObject baseObject) {
        comand=new AddGeneratonCommand(generation, baseObject);
        return comand.addElement(generation);
    }
}
