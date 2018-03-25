package ru.glassexpress.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
//import ru.glassexpress.core.security.ClientSecurityManager;
import ru.glassexpress.core.StringValidator;
import ru.glassexpress.library.AlertWindow;


public class LoginController extends BaseController {


    @FXML
    TextField fieldLogin;

    @FXML
    PasswordField fieldPass;

    @FXML
    VBox rootElement;
   // ClientSecurityManager securityManager;


    public void login() {
     //   securityManager = new ClientSecurityManager(mainController.getUser());
        String login = fieldLogin.getText();
        String pass = fieldPass.getText();
        System.out.println(login + " " + pass);

        if (!login.isEmpty() || !pass.isEmpty()) {
            if (StringValidator.isLoginCorrect(login) && StringValidator.isPassCorrect(pass)) {
//                securityManager.setLogin(login);
//                securityManager.setPassword(pass);
//
//                // проверяем наличие пользователя
//                // if (securityManager.isUserValid(login, pass)){
//                // получаем ключ
//                String key = securityManager.getKey(login, pass);
//                if (key != null) {
//                    // устанавливаем соединение, закрываем окно
//                    mainController.getUser().setKey(key);
                    GetListOperator operator = new GetListOperator(null);

                    IdTitleObj keyObj = operator.getUserByLoginPass(new IdTitleObj(pass.hashCode(), login));

                    if (keyObj!=null&&keyObj.getTitle()!=null&&!keyObj.getTitle().equals("")) {
                       operator.setKey(keyObj.getTitle());
                       UserObject user = operator.getUserByKey();
                        // UserObject user  = operator.getUserByLoginPass(new IdTitleObj(pass.hashCode(), login));
                        if (user != null) {
                            user.setKey(keyObj.getTitle());
                            mainController.setUser(user);
                            //  mainController.initPermission();
                            mainController.reconnect();
                            close();
                        } else {
                            AlertWindow.errorMessage("Пользователя не получены. Сервер не отвечает.");

                        }


                } else {
                    AlertWindow.errorMessage("Неверный логин\\пароль");
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
        Log2File.writeLog("Иинициализация окна аутентификации");
    }

}
