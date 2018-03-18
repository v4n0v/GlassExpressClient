package ru.glassexpress.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ru.glassexpress.core.security.ClientSecurityManager;
import ru.glassexpress.core.StringValidator;
import ru.glassexpress.library.AlertWindow;


public class LoginController extends BaseController {


    @FXML
    TextField fieldLogin;

    @FXML
    PasswordField fieldPass;

    @FXML
    VBox rootElement;
    ClientSecurityManager securityManager;

    public void login() {
        securityManager = new ClientSecurityManager();
        String login = fieldLogin.getText();
        String pass = fieldPass.getText();
        System.out.println(login + " " + pass);

        if (!login.isEmpty() || !pass.isEmpty()) {
            if (StringValidator.isLoginCorrect(login) && StringValidator.isPassCorrect(pass)) {
                securityManager.setLogin(login);
                securityManager.setPassword(pass);
                if (securityManager.isUserValid(login, pass)){
                    mainController.reconnect();
                    close();
                }

            } else {
                AlertWindow.errorMessage("Допустимы только буквы и цифры английского алфавита");
            }
        } else {
            AlertWindow.errorMessage("Заполните поля");
        }
    }


    @Override
    public void init() {

    }
}
