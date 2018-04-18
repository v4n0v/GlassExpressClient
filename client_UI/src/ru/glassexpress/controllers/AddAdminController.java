package ru.glassexpress.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import ru.glassexpress.controllers.presenters.AddAdminPresenter;
import ru.glassexpress.controllers.views.AddAdminView;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.library.AlertWindow;


public class AddAdminController extends BaseController implements AddAdminView {


    public javafx.scene.control.TextField fieldLogin;

    @FXML
    PasswordField fieldPass1;

    @FXML
    PasswordField fieldPass2;
    private AddAdminPresenter presenter;

    @Override
    public void init() {
        Log2File.writeLog("Иинициализация окна добавления администратора");
        presenter = new AddAdminPresenter(this);
    }

    @Override
    public void onAddButtonClick() {
        presenter.addUser(fieldLogin.getText(), fieldPass1.getText(), fieldPass2.getText());
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
