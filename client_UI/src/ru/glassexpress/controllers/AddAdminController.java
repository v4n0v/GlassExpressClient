package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import ru.glassexpress.controllers.presenters.AddAdminPresenter;
import ru.glassexpress.controllers.presenters.AddAdminView;
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

public class AddAdminController extends BaseController implements AddAdminView {
    public ComboBox salonsComboBox;
    private String login;
    private String pass1;
    private String pass2;

    public javafx.scene.control.TextField fieldLogin;

    @FXML
    PasswordField fieldPass1;

    @FXML
    PasswordField fieldPass2;
    AddAdminPresenter presenter;

    @Override
    public void init() {
        Log2File.writeLog("Иинициализация окна добавления администратора");
        presenter=new AddAdminPresenter(this);
    }

    @Override
    public void onAddButtonClick() {
        login = fieldLogin.getText();
        pass1 = fieldPass1.getText();
        pass2 = fieldPass2.getText();
        presenter.addUser(login, pass1, pass2);
    }


    @Override
    public void initAddEmpLayout(String login, String pass1, String key) {
        mainApp.initAddEmpLayout(login, pass1, key);
    }

    @Override
    public void closeView() {
        close();
    }

    @Override
    public void showError(String msg) {
        AlertWindow.errorMessage(msg);
    }

}
