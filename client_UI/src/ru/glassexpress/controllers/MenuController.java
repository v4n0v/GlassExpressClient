package ru.glassexpress.controllers;

import javafx.event.ActionEvent;

public class MenuController extends BaseController {

    @Override
    public void init() {

    }

    public void refresh(ActionEvent actionEvent) {

        mainController.reconnect();
        System.out.println(mainController);
    }

    public void exit(ActionEvent actionEvent) {

    }


}
