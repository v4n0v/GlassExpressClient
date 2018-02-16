package ru.glassexpress;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.glassexpress.controllers.CarsController;

import java.io.IOException;

public class GEClient extends Application {

    ClientManager clientManager;
    CarsController carsController;

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


        initRootLayout();
        showCarInfo();

    }
// инициализация корневого
    public void initRootLayout() {
        clientManager=new ClientManager();
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GEClient.class.getResource("fxml/root.fxml"));
            System.out.println("rootLayout.fxml подгружен");
            rootLayout = (BorderPane) loader.load();
            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCarInfo() {
        try {
            // Загружаем сведения об адресатах.
            Stage carsSelectStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GEClient.class.getResource("fxml/addCarLayout.fxml"));
            System.out.println("addCarLayout подгружен");
            VBox modelAdd = (VBox) loader.load();
            carsController = loader.getController();
            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(modelAdd);

            carsController.setClientManger(clientManager);
            carsController.setMainApp(this);
            carsController.setStage(carsSelectStage);
            carsController.init();
            carsSelectStage.setOnCloseRequest((event) -> primaryStage.close());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
