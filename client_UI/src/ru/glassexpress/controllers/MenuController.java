package ru.glassexpress.controllers;

import javafx.event.ActionEvent;
import ru.glassexpress.core.data.Log2File;

public class MenuController extends BaseController {

    @Override
    public void init() {
        Log2File.writeLog("Иинициализация меню приложения");
    }

    public void refresh(ActionEvent actionEvent) {

        mainController.reconnect();
        System.out.println(mainController);
    }

    public void exit(ActionEvent actionEvent) {
       close();
    }


    public void addAdmin(ActionEvent actionEvent) {
        mainApp.initAddAdminLayout();
        System.out.println("Добавляем админа");
    }

    public void addEmp(ActionEvent actionEvent) {
        System.out.println("Добавляем сотрудника");
    }
}
