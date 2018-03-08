package ru.glassexpress.core;

import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.GenerationObj;
import ru.glassexpress.objects.IdTitleObj;

import java.util.List;

public interface GetListCommands {

    List<IdTitleObj> getMarks();

    List<IdTitleObj> getModels(BaseObject object);

    List<GenerationObj> getGenerations(BaseObject object);
   // ObservableList<IdTitleObj> getMarksIdTitle(BaseObject object);

}
