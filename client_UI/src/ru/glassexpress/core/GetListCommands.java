package ru.glassexpress.core;

import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.GenerationObj;
import ru.glassexpress.core.objects.IdTitleObj;

import java.util.List;

public interface GetListCommands {

    List<IdTitleObj> getMarks();

    List<IdTitleObj> getModels(BaseObject object);

    List<GenerationObj> getGenerations(BaseObject object);
   // ObservableList<IdTitleObj> getMarksIdTitle(BaseObject object);

}
