package ru.glassexpress.core.get_command;

import javafx.collections.ObservableList;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.GenerationObj;
import ru.glassexpress.objects.IdTitleObj;
import ru.glassexpress.request_builder.Request;

import java.util.List;

public interface GetListCommands {

    ObservableList<String> getMarks(BaseObject object);

    ObservableList<String> getModels(BaseObject object);

    List<GenerationObj> getGenerations(BaseObject object);
   // ObservableList<IdTitleObj> getMarksIdTitle(BaseObject object);

}
