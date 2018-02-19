package ru.glassexpress;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.glassexpress.controllers.MainController;
import ru.glassexpress.controllers.MenuController;


import java.io.IOException;

public class GEClient extends Application {


    private MainController mainController;
    private MenuController menuController;


    public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;
    private BorderPane rootLayout;

// запуск приложения
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GlassExpress client");

        // мэнеджер клиента, в нем вся бизнеслогика


        initRootLayout();
        initCarInfoLayout();

    }
    public  void fillMarksList(){

    }

    // инициализация корневого
    public void initRootLayout() {

        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GEClient.class.getResource("fxml/root.fxml"));
            System.out.println("rootLayout.fxml подгружен");
            rootLayout = (BorderPane) loader.load();
            menuController = loader.getController();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initCarInfoLayout() {
        try {
            // Загружаем сведения об адресатах.
            Stage carsSelectStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GEClient.class.getResource("fxml/addCarLayout.fxml"));
            System.out.println("addCarLayout подгружен");
            VBox modelAdd = (VBox) loader.load();
            mainController = loader.getController();
            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(modelAdd);

          //  mainController.setClientController(clientController);
            mainController.setMainApp(this);
            mainController.setStage(carsSelectStage);
            //clientController.setCarsController(mainController);
            mainController.init();
            carsSelectStage.setOnCloseRequest((event) -> primaryStage.close());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }


}
