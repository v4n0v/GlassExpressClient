package ru.glassexpress.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ru.glassexpress.controllers.presenters.LoginPresenter;
import ru.glassexpress.controllers.views.LoginView;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.objects.*;
//import ru.glassexpress.core.security.ClientSecurityManager;
import ru.glassexpress.library.AlertWindow;


public class LoginController extends BaseController implements LoginView{


    @FXML
    TextField fieldLogin;

    @FXML
    PasswordField fieldPass;

    @FXML
    VBox rootElement;


    private LoginPresenter presenter;

    @Override
    public void init() {
        Log2File.writeLog("Иинициализация окна аутентификации");
        presenter= new LoginPresenter(this);
    }


    @Override
    public void onLoginButtonClick() {
        String login = fieldLogin.getText();
        String pass = fieldPass.getText();
        System.out.println(login + " " + pass);
        presenter.login(login, pass, mainController);
    }


    @Override
    public void closeView() {
        close();
    }

    @Override
    public void showSelectSalonLayout() {
        mainApp.showSelectSalonLayout();
    }

    @Override
    public void initGoodMorningLayout(UserObject user) {
        mainApp.initGoodMorningLayout(user);
    }

    @Override
    public void showError(String msg) {
        AlertWindow.errorMessage(msg);
    }
}
