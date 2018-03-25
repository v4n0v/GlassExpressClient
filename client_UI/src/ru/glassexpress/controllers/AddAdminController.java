package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.StringValidator;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.edit_content_command.addCommand.AddOperator;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.core.security.Keygen;
//import ru.glassexpress.core.security.SQLConnectionManager;
import ru.glassexpress.library.AlertWindow;

import java.security.NoSuchAlgorithmException;

public class AddAdminController extends BaseController {
    public ComboBox salonsComboBox;
    private String login;
    private String pass1;
    private String pass2;
    private String key;
    DataMap dataMap;
    public javafx.scene.control.TextField fieldLogin;
    AddOperator addOperator;
//    SQLConnectionManager sqlConnectionManager;
    @FXML
    PasswordField fieldPass1;

    @FXML
    PasswordField fieldPass2;


    @Override
    public void init() {
        Log2File.writeLog("Иинициализация окна добавления администратора");
    }

    public void addAdmin(ActionEvent actionEvent) {

        login = fieldLogin.getText();
        login = login.replace(" ", "");

        pass1 = fieldPass1.getText();
        pass1 = pass1.replace(" ", "");

        pass2 = fieldPass2.getText();
        pass2 = pass2.replace(" ", "");

        if (StringValidator.isLoginCorrect(login) && StringValidator.isPassCorrect(pass1) && StringValidator.isPassCorrect(pass2)
                && login.length() > 2 && pass1.length() > 2 && pass2.length() > 2) {
            // проверяем равенство паролей
            if (fieldPass1.getText().equals(fieldPass2.getText())) {
                // проверяем занят ли логин
                GetListOperator getListOperator = new GetListOperator(mainController.getUser().getKey());
                if (getListOperator.isUserFree(login)) {
                    Log2File.writeLog("Полизователь свободен");
                    try {
                        key = Keygen.generate();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        AlertWindow.errorMessage("Ошибка генерации ключа");
                    }
                    mainApp.initAddEmpLayout(login, pass1, key);
                    close();
                } else {
                    AlertWindow.errorMessage("Пользователь с таким логим уже зарегистрирован");
                }

//                sqlConnectionManager = SQLConnectionManager.getInstance();
//                if (!sqlConnectionManager.isLoginBusy(login)) {
//
//                    // генерируем ключ
//                    try {
//                        key = Keygen.generate();
//                    } catch (NoSuchAlgorithmException e) {
//                        e.printStackTrace();
//                    }
//                    //
//                    mainApp.initAddEmpLayout(login, pass1, key );
//                    close();
//                } else {
//                    AlertWindow.errorMessage("Пользователь с таким логим уже зарегистрирован");
//                }
            } else {
                AlertWindow.errorMessage("Вы ввели разные пароли");
            }
        } else {
            AlertWindow.errorMessage("Допустимы только буквы и цифры английского алфавита.\nДлина не менее 3х символов");
        }

    }

}
