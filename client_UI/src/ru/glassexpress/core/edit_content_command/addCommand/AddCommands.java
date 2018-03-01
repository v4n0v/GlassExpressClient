package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.objects.BaseObject;

public interface AddCommands {
    boolean addModelIsComplete(String model, BaseObject baseObject);
    boolean addMarkIsComplete(String mark);
    boolean addGenerationIsComplete(String generation, BaseObject baseObject);
}
