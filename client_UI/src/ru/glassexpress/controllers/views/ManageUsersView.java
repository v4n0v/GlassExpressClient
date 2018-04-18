package ru.glassexpress.controllers.views;

import javafx.collections.ObservableList;
import ru.glassexpress.core.objects.UserObject;

public interface ManageUsersView {
    void fillComboBox(ObservableList list);

    void fillEmpList(ObservableList<UserObject> emps);

    boolean isFiredShow();
    int getSelectedIndex();

    void showComplete();

    void showNotComplete();

    void showError(String s);

}
