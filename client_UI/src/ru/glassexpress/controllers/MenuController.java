package ru.glassexpress.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import ru.glassexpress.controllers.presenters.MenuPresenter;
import ru.glassexpress.controllers.views.MenuView;
import ru.glassexpress.core.data.Log2File;

public class MenuController extends BaseController implements MenuView {

    public Menu menuUsers;
    private MenuPresenter presenter;

    @Override
    public void init() {
        Log2File.writeLog("Иинициализация меню приложения");
        presenter = new MenuPresenter(this);
        presenter.init();
    }

    public void refresh(ActionEvent actionEvent) {

        mainController.update();
        System.out.println(mainController);
    }

    public void exit(ActionEvent actionEvent) {
        close();
    }


    public void addAdmin(ActionEvent actionEvent) {
        mainApp.initAddAdminLayout();
        System.out.println("Добавляем админа");
    }

    public void editEmp(ActionEvent actionEvent) {
        mainApp.showManageUsersLayout();
        System.out.println("Редактируем сотрудников");
    }

    public void initPermission() {
        presenter.initPermission();
    }

    public void logoOut(ActionEvent actionEvent) {
        mainApp.showClientLoginLayout();
    }

    @Override
    public void setPermission(boolean visible) {
        menuUsers.setVisible(visible);
    }
}
