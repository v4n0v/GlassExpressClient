package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.edit_content_command.addCommand.AddOperator;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.UserObject;

import ru.glassexpress.library.AlertWindow;

public class AddEmpController extends BaseController {

    public TextField fieldName;
    public ComboBox<String> salonsComboBox;
    public TextField fieldLastName;
    public ComboBox<String> permisComboBox;
    public ComboBox<String> posComboBox;

    private BaseObjectAdapter adapter;
    private ObservableList<String> salonsList;
    private ObservableList<String> permisList;
    private ObservableList<String> posList;

    private String login;
    private String pass;
    private String key;

    @Override
    public void init() {
        Log2File.writeLog("Иинициализация окна добавления сотрудника");
        // инициализация формы
        dataMap = DataMap.getInstance();
        adapter = BaseObjectAdapter.getInsance();
        salonsList = adapter.idTitleObjToString(dataMap.getSalonsList());
        permisList = adapter.idTitleObjToString(dataMap.getPermissionsList());
        posList = adapter.idTitleObjToString(dataMap.getPositionsList());
        salonsComboBox.setItems(salonsList);
        // salonsComboBox.getSelectionModel().select(0);
        posComboBox.setItems(posList);
        //salonsComboBox.getSelectionModel().select(0);
        permisComboBox.setItems(permisList);
        //  salonsComboBox.getSelectionModel().select(0);
    }

    public void setParams(String login, String pass, String key) {
        this.login = login;
        this.pass = pass;
        this.key = key;
    }

    public void addEmp(ActionEvent actionEvent) {

        AddOperator addOperator = new AddOperator(dataMap.getUser().getKey());
        UserObject newUser = new UserObject();
        // проверяем правильность заполнения полей
        if (!fieldName.getText().equals("") && !fieldLastName.getText().equals("")
                && fieldName.getText()!=null && fieldLastName.getText()!=null
                && salonsComboBox.getSelectionModel().getSelectedIndex() != -1
                && permisComboBox.getSelectionModel().getSelectedIndex() != -1
                && posComboBox.getSelectionModel().getSelectedIndex() != -1) {

            // настраиваем пльзователя
                    newUser.setName(fieldName.getText());
                    newUser.setLastName(fieldLastName.getText());
                    newUser.setSalonId(dataMap.getSalonsList().get(salonsComboBox.getSelectionModel().getSelectedIndex()).getId());
                    newUser.setPermission(dataMap.getPermissionsList().get(permisComboBox.getSelectionModel().getSelectedIndex()).getId());
                    newUser.setPositionId(dataMap.getPositionsList().get(posComboBox.getSelectionModel().getSelectedIndex()).getId());
                    newUser.setLogin(login);

                    newUser.setPassHash(pass.hashCode());
                    newUser.setKey(key);
                    // добавляем пользователя на сервер
                    if (addOperator.addUserIsComplete(newUser)) {
                        // если добавлен, тогда добавляем в локальную базу логин\пароль\ключ
                        System.out.println("Все ок");
//                        SQLConnectionManager sqlConnectionManager = SQLConnectionManager.getInstance();
//                        sqlConnectionManager.addNewUser(login, pass, key);
                        close();
                        AlertWindow.infoMessage("Пользователь '"+login+"' успешно добавлен на сервер");
                    } else {
                        System.out.println("Пользователь не добавлен на сервер");
                        AlertWindow.errorMessage("Пользователь не добавлен на сервер");
                    }


        } else {
            AlertWindow.errorMessage("Заполниет все поля и формы");
        }

        System.out.println(newUser);
    }
}
