package ru.glassexpress.controllers;

import javafx.stage.Stage;
import ru.glassexpress.GEClient;


abstract class BaseController {
    GEClient mainApp;
    Stage stage;

    MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainApp(GEClient mainApp) {
        this.mainApp = mainApp;
    }

    public void close() {
        stage.close();
    }

    abstract public void init();

}
