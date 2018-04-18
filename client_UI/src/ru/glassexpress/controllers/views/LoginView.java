package ru.glassexpress.controllers.views;

import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.objects.UserObject;

public interface LoginView {

    void onLoginButtonClick();
    void closeView();
    void showSelectSalonLayout();
    void initGoodMorningLayout(UserObject user);
    void showError(String msg);
}
