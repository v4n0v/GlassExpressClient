package ru.glassexpress.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.library.AlertWindow;

public class ManageUsersController extends BaseController {


    public ComboBox<IdTitleObj> salonComboBox;
    public ListView<UserObject> empList;

    @Override
    public void init() {
        Log2File.writeLog("Инициализация панели управления пользователями");
    }

    public void addUser(ActionEvent actionEvent) {
        mainApp.initAddAdminLayout();
        close();
    }

    public void delUser(ActionEvent actionEvent) {

    }

    public void editLogin(ActionEvent actionEvent) {

    }

    public void editPassword(ActionEvent actionEvent) {

    }

    public void editUserInfo(ActionEvent actionEvent) {

    }
}
