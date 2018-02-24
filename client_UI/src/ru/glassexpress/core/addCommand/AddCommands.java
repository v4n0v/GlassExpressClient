package ru.glassexpress.core.addCommand;

import ru.glassexpress.objects.BaseObject;

public interface AddCommands {
    boolean addModelIsComplete(String model, BaseObject baseObject);
    boolean addMarkIsComplete(String mark);
    boolean addGenerationIsComplete(String generation, BaseObject baseObject);
}
