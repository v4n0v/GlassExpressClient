package ru.glassexpress;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.glassexpress.controllers.AddGlassController;
import ru.glassexpress.controllers.MainController;
import ru.glassexpress.controllers.MenuController;
import ru.glassexpress.data.DataMap;


import java.io.IOException;

public class GEClient extends Application {


    private MainController mainController;
    private MenuController menuController;
    private AddGlassController addGlassController;
    DataMap dataMap;
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

        dataMap=new DataMap();
        // мэнеджер клиента, в нем вся бизнеслогика


        initRootLayout();
        initMainLayout();

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

    public void initMainLayout() {
        try {
            // Загружаем сведения об адресатах.
            Stage carsSelectStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GEClient.class.getResource("fxml/baseScreen.fxml"));
            System.out.println("addCarLayout подгружен");
            AnchorPane modelAdd = (AnchorPane) loader.load();
            mainController = loader.getController();
            // Помещаем сведения об адресатах в центр корневого макета.
            menuController.setMainController(mainController);
            rootLayout.setCenter(modelAdd);

          //  mainController.setClientController(clientController);
            mainController.setMainApp(this);
            mainController.setStage(carsSelectStage);
            mainController.setDataMap(dataMap);
            //clientController.setCarsController(mainController);
            mainController.init();
            carsSelectStage.setOnCloseRequest((event) -> primaryStage.close());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initAddGlassLayout(String selectedCar) {
        try {
            // Загружаем сведения об адресатах.
            Stage addGlassStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GEClient.class.getResource("fxml/addGlassLayout.fxml"));
            System.out.println("addGlassLayout подгружен");
            AnchorPane modelAdd = (AnchorPane) loader.load();
            Scene scene = new Scene(modelAdd);
            addGlassStage.setScene(scene);
            addGlassController = loader.getController();
            addGlassStage.setTitle("Добавить стекло "+selectedCar);
            // Помещаем сведения об адресатах в центр корневого макета.
            addGlassController.setMainController(mainController);
            addGlassController.setStage(addGlassStage);
            addGlassController.setDataMap(dataMap);
           // rootLayout.setCenter(modelAdd);
            addGlassStage.initModality(Modality.WINDOW_MODAL);
            addGlassStage.initOwner(primaryStage);
            addGlassStage.setResizable(false);
            //  mainController.setClientController(clientController);
            mainController.setMainApp(this);

            mainController.setStage(addGlassStage);
            mainController.setAggGlassController(addGlassController);
            //clientController.setCarsController(mainController);
            addGlassController.init();
            //addGlassStage.setOnCloseRequest((event) -> primaryStage.close());
            addGlassStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


}
