package ru.glassexpress;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.glassexpress.controllers.*;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.core.objects.UserObject;


import java.io.IOException;
import java.util.List;

public class GEClient extends Application {


    private MainController mainController;
    private MenuController menuController;
    private AddGlassController addGlassController;
    DataMap dataMap;
    public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;
    private AnchorPane rootLayout;



// запуск приложения
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GlassExpress client");

        dataMap=new DataMap();
        // мэнеджер клиента, в нем вся бизнеслогика


        initRootLayout();
        initMainLayout();
        showClientLoginLayout();

    }


    // инициализация корневого
    public void initRootLayout() {

        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GEClient.class.getResource("fxml/root.fxml"));
            Log2File.writeLog("rootLayout.fxml подгружен");

            rootLayout = (AnchorPane) loader.load();
            menuController = loader.getController();
            menuController.setMainApp(this);
            menuController.setStage(primaryStage);
            menuController.init();
            rootLayout.setMinWidth(1140d);

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showClientLoginLayout() {
        try {
            // новое окно логина
            Stage loginStage = new Stage();
            FXMLLoader loaderLog = new FXMLLoader();
            loaderLog.setLocation(GEClient.class.getResource("fxml/login_modal.fxml"));
            VBox loginRootElement = (VBox) loaderLog.load();
            Scene sceneLog = new Scene(loginRootElement);

            // получаем ссылку у контроллера окна
            // controller.

           // loginStage.getIcons().add(new Image(logoPath));
            loginStage.setTitle("Login");
            loginStage.setOnCloseRequest((event) -> primaryStage.close());
         //   setStyleToStage(currentStyleCSS, sceneLog);
            loginStage.setResizable(false);
            loginStage.setScene(sceneLog);
            loginStage.initModality(Modality.WINDOW_MODAL);
            loginStage.initOwner(primaryStage);
            LoginController controller = loaderLog.getController();
            controller.setMainController(mainController);
            controller.setMainApp(this);
            mainController.setLoginStage(loginStage);
            controller.setStage(loginStage);
            controller.init();
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    AddAdminController addAdminController;
    public void initAddAdminLayout() {
        try {
            // новое окно логина
            Stage addAdminStage = new Stage();
            FXMLLoader loaderLog = new FXMLLoader();
            loaderLog.setLocation(GEClient.class.getResource("fxml/addUserLogin.fxml"));
            VBox loginRootElement = (VBox) loaderLog.load();
            Scene sceneLog = new Scene(loginRootElement);

            // получаем ссылку у контроллера окна
            // controller.

            // loginStage.getIcons().add(new Image(logoPath));
            addAdminStage.setTitle("Добавить администратора");
         //   addAdminStage.setOnCloseRequest((event) -> primaryStage.close());
            //   setStyleToStage(currentStyleCSS, sceneLog);
            addAdminStage.setResizable(false);
            addAdminStage.setScene(sceneLog);
            addAdminStage.initModality(Modality.WINDOW_MODAL);
            addAdminStage.initOwner(primaryStage);
            addAdminController = loaderLog.getController();
            addAdminController.setMainController(mainController);
            addAdminController.setMainApp(this);
           // mainController.setLoginStage(addEmpStage);
            addAdminController.setStage(addAdminStage);
            addAdminController.init();
            addAdminStage.show();
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
            Log2File.writeLog("addCarLayout подгружен");

            AnchorPane modelAdd = (AnchorPane) loader.load();
            mainController = loader.getController();
            // Помещаем сведения об адресатах в центр корневого макета.
            menuController.setMainController(mainController);

            ObservableList<Node>childrens = rootLayout.getChildren();


            AnchorPane.setLeftAnchor(modelAdd, 0d);
            AnchorPane.setRightAnchor(modelAdd, 0d);

            AnchorPane.setTopAnchor(modelAdd, 15d);
            AnchorPane.setBottomAnchor(modelAdd, 0d);
            childrens.add(modelAdd);
//            AnchorPane container = (AnchorPane) childrens.get(1);
//            container.modelAdd;


          //  mainController.setMainController(clientController);
            mainController.setMainApp(this);
            mainController.setStage(carsSelectStage);
            mainController.setDataMap(dataMap);
            mainController.setMenuController(menuController);
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

           Log2File.writeLog("addCarLayout подгружен");
            AnchorPane modelAdd = (AnchorPane) loader.load();

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
            //  mainController.setMainController(clientController);
            mainController.setMainApp(this);

            addGlassStage.setScene(new Scene(modelAdd));
            mainController.setStage(addGlassStage);
            mainController.setAddGlassController(addGlassController);

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


    public void initAddEmpLayout(String login, String pass, String key) {
        try {
            // новое окно логина
            Stage addEmpStage = new Stage();
            FXMLLoader loaderLog = new FXMLLoader();
            loaderLog.setLocation(GEClient.class.getResource("fxml/addUserEmp.fxml"));
            VBox loginRootElement = (VBox) loaderLog.load();
            Scene sceneLog = new Scene(loginRootElement);

            addEmpStage.setTitle("Добавить cотрудника");
          //  addEmpStage.setOnCloseRequest((event) -> primaryStage.close());
            //   setStyleToStage(currentStyleCSS, sceneLog);
            addEmpStage.setResizable(false);
            addEmpStage.setScene(sceneLog);
            addEmpStage.initModality(Modality.WINDOW_MODAL);
            addEmpStage.initOwner(primaryStage);


            AddEmpController controller = loaderLog.getController();
            controller.setMainController(mainController);
            controller.setParams(login, pass, key);
            controller.setMainApp(this);
            controller.setStage(addEmpStage);
            controller.init();
            addEmpStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void initGoodMorningLayout(UserObject user) {
        try {
            // новое окно логина
            Stage goodMorningStage = new Stage();
            FXMLLoader loaderLog = new FXMLLoader();
            loaderLog.setLocation(GEClient.class.getResource("fxml/goodMorningLayout.fxml"));
            AnchorPane dayRootElement = (AnchorPane) loaderLog.load();
            Scene sceneLog = new Scene(dayRootElement);

            goodMorningStage.setTitle("C добрыйм утром! Сегодня нас ждет прекрасный день!");
            goodMorningStage.setOnCloseRequest((event) -> primaryStage.close());
            //   setStyleToStage(currentStyleCSS, sceneLog);
            goodMorningStage.setResizable(false);
            goodMorningStage.setScene(sceneLog);
            goodMorningStage.initModality(Modality.WINDOW_MODAL);
            goodMorningStage.initOwner(primaryStage);


            DayController controller = loaderLog.getController();
            controller.setMainController(mainController);
            controller.setMainApp(this);
            controller.setStage(goodMorningStage);
            controller.setAdministrator(user);
            controller.init();

            goodMorningStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void initOrderConfirmLayout(List<GlassObject> cart) {
        try {
            // новое окно логина
            Stage orderConfirmStage = new Stage();
            FXMLLoader loaderLog = new FXMLLoader();
            loaderLog.setLocation(GEClient.class.getResource("fxml/orderConfirmLayout.fxml"));
            AnchorPane orderRootElement = (AnchorPane) loaderLog.load();
            Scene sceneLog = new Scene(orderRootElement);

            orderConfirmStage.setTitle("Оформить заказ");
            //  addEmpStage.setOnCloseRequest((event) -> primaryStage.close());
            //   setStyleToStage(currentStyleCSS, sceneLog);
            orderConfirmStage.setResizable(false);
            orderConfirmStage.setScene(sceneLog);
            orderConfirmStage.initModality(Modality.WINDOW_MODAL);
            orderConfirmStage.initOwner(primaryStage);


            OrderConfirmController controller = loaderLog.getController();
            controller.setMainController(mainController);
            controller.setSelectedGlass(cart);
            controller.setMainApp(this);
            controller.setDataMap(dataMap);
            controller.setStage(orderConfirmStage);
            controller.init();
            orderConfirmStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showSelectSalonLayout() {
        try {
            // новое окно логина
            Stage selectStage = new Stage();
            FXMLLoader loaderLog = new FXMLLoader();
            loaderLog.setLocation(GEClient.class.getResource("fxml/selectSalon.fxml"));
            AnchorPane selectRootElement = (AnchorPane) loaderLog.load();
            Scene sceneLog = new Scene(selectRootElement);

            // получаем ссылку у контроллера окна
            // controller.

            // loginStage.getIcons().add(new Image(logoPath));
            selectStage.setTitle("Выбирите салон");
            selectStage.setOnCloseRequest(Event::consume);
            //   setStyleToStage(currentStyleCSS, sceneLog);
            selectStage.setResizable(false);
            selectStage.setScene(sceneLog);
            selectStage.initModality(Modality.WINDOW_MODAL);
            selectStage.initOwner(primaryStage);
            SelectSalonController controller = loaderLog.getController();
            controller.setMainController(mainController);
            controller.setMainApp(this);
           // mainController.setLoginStage(selectStage);
            controller.setStage(selectStage);
            controller.init();
            selectStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void showManageUsersLayout() {
        try {
            // новое окно логина
            Stage manageUsersStage = new Stage();
            FXMLLoader loaderLog = new FXMLLoader();
            loaderLog.setLocation(GEClient.class.getResource("fxml/manageUsersLayout.fxml"));
            AnchorPane manageRootElement = (AnchorPane) loaderLog.load();
            Scene sceneLog = new Scene(manageRootElement);

            // получаем ссылку у контроллера окна
            // controller.

            // loginStage.getIcons().add(new Image(logoPath));
            manageUsersStage.setTitle("Управление пользователями");
//            manageUsersStage.setOnCloseRequest((event) ->
//                    primaryStage.close());
            //   setStyleToStage(currentStyleCSS, sceneLog);
            manageUsersStage.setResizable(false);
            manageUsersStage.setScene(sceneLog);
            manageUsersStage.initModality(Modality.WINDOW_MODAL);
            manageUsersStage.initOwner(primaryStage);
            ManageUsersController controller = loaderLog.getController();
            controller.setMainController(mainController);
            controller.setMainApp(this);
            // mainController.setLoginStage(selectStage);
            controller.setStage(manageUsersStage);
            controller.init();
            manageUsersStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
