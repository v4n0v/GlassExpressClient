package ru.glassexpress.controllers;

import javafx.stage.Stage;
import ru.glassexpress.ClientManager;
import ru.glassexpress.GEClient;


abstract class BaseController {
        protected GEClient mainApp;
        Stage stage;
        protected ClientManager clientManager;

        public void setClientManger(ClientManager clientManager) {
            this.clientManager = clientManager;
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
