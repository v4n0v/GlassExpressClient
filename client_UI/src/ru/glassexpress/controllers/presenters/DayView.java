package ru.glassexpress.controllers.presenters;

import javafx.collections.ObservableList;
import ru.glassexpress.core.objects.DateObject;
import ru.glassexpress.core.objects.UserObject;

public interface DayView {
    void setAdminLabel(String admin);

    void setDataLabel(String date);

    void fillTotalEmpList(ObservableList<UserObject> totalEmployees);

    void fillCurrentEmpList(ObservableList<UserObject> currentEmployees);

    void updateSalonLabel(String salon);

    void showError(String msg);

    boolean addNewDay(DateObject today);

    void closeView();

    void startApplication();
}
