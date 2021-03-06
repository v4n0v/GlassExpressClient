package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.glassexpress.controllers.presenters.AddEmployerPresenter;
import ru.glassexpress.controllers.views.AddEmployerView;
import ru.glassexpress.core.data.Log2File;

import ru.glassexpress.library.AlertWindow;

public class AddEmpController extends BaseController implements AddEmployerView {

    public TextField fieldName;
    public ComboBox<String> salonsComboBox;
    public TextField fieldLastName;
    public ComboBox<String> permisComboBox;
    public ComboBox<String> posComboBox;


    private String login;
    public TextField fieldEmail;
    private String pass;
    private String key;
    private AddEmployerPresenter presenter;

    @Override
    public void init() {
        Log2File.writeLog("Иинициализация окна добавления сотрудника");
        presenter = new AddEmployerPresenter(this);
        presenter.init();


    }

    public void setParams(String login, String pass, String key) {
        this.login = login;
        this.pass = pass;
        this.key = key;
    }


    @Override
    public void fillSalonsComboBox(ObservableList list) {
        salonsComboBox.setItems(list);
    }

    @Override
    public void fillPositionsComboBox(ObservableList list) {
        posComboBox.setItems(list);
    }

    @Override
    public void fillPermisComboBox(ObservableList list) {
        permisComboBox.setItems(list);
    }

    @Override
    public void closeView() {
        close();
    }

    @Override
    public void onAddEmployerClick() {
        presenter.addUser(fieldName.getText(),fieldLastName.getText(), fieldEmail.getText(),
                salonsComboBox.getSelectionModel().getSelectedIndex(),
                permisComboBox.getSelectionModel().getSelectedIndex(),
                posComboBox.getSelectionModel().getSelectedIndex());
    }

    @Override
    public void showError(String msg) {
        AlertWindow.errorMessage(msg);
    }
}
