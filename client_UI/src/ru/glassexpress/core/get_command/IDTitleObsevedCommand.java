package ru.glassexpress.core.get_command;

import ru.glassexpress.core.objects.BaseObject;

public abstract class IDTitleObsevedCommand extends ObservedCommand{
    public IDTitleObsevedCommand(BaseObject object, String key) {
        super(object, key);
    }

//    @Override
//    void fillObject(int i) {
////        IdTitleObj mark = (IdTitleObj) components.get(i);
////        resultString.add(mark.getTitle());
//    }
}
