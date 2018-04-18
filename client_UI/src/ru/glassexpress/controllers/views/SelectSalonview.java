package ru.glassexpress.controllers.views;

import javafx.collections.ObservableList;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;

public interface SelectSalonview {
    void fillComboBox(ObservableList<IdTitleObj> salons);

    void openGoodMorningView(UserObject user);

    void initPermission();

}
