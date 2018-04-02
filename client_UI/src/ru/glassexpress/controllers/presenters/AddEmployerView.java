package ru.glassexpress.controllers.presenters;

import javafx.collections.ObservableList;

public interface AddEmployerView {
    void fillSalonsComboBox(ObservableList list);
    void fillPositionsComboBox(ObservableList list);
    void fillPermisComboBox(ObservableList list);

    void closeView();
    void onAddEmployerClick();

    void showError(String msg);
}
