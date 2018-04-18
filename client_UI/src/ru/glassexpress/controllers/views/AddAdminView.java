package ru.glassexpress.controllers.views;

import ru.glassexpress.core.GetListOperator;

public interface AddAdminView {
    void onAddButtonClick();



    void initAddEmpLayout(String login, String pass1, String key);

    void closeView();
    void showError(String msg);
}
