package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.glassexpress.controllers.presenters.ManageUsersPresenter;
import ru.glassexpress.controllers.views.ManageUsersView;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.library.AlertWindow;

public class ManageUsersController extends BaseController implements ManageUsersView {


    public ComboBox<IdTitleObj> salonComboBox;
    public ListView<UserObject> empList;
    public CheckBox isFiredShow;

    ManageUsersPresenter presenter;
    @Override
    public void init() {
        Log2File.writeLog("Инициализация панели управления пользователями");
        presenter = new ManageUsersPresenter(this);
        presenter.init();
        salonComboBox.setCellFactory(p -> new ListCell<IdTitleObj>() {
            @Override
            protected void updateItem(IdTitleObj item, boolean empty) {
                super.updateItem(item, empty) ;
                if (item != null && !empty){
                    setText(item.getTitle());
                } else {
                    setText(null);
                }
            }
        });
    }

    public void addUser(ActionEvent actionEvent) {
        mainApp.initAddAdminLayout();
        close();
    }

    @Override
    public int getSelectedIndex() {
        return empList.getSelectionModel().getSelectedIndex();
    }


    public void delUser(ActionEvent actionEvent) {
        boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Уволить сотруддника?");
        if (isTrue) {
            isTrue = AlertWindow.confirmationWindow("Вы точно уверены?", "Сотрудник , будет уволен, а его семья будет голодать!");
            if (isTrue) {
                presenter.deleteUser(getSelectedIndex());

            }
        }
    }

    public void editLogin(ActionEvent actionEvent) {
        String login = AlertWindow.dialogWindow("Изменить логин", "Введите логин");
        presenter.editLogin(login, getSelectedIndex());

    }

    public void editPassword(ActionEvent actionEvent) {
        String pass1 = AlertWindow.dialogWindow("Изменить пароль пользователя", "Введите пароль");
        if (pass1 != null) {
            String pass2 = AlertWindow.dialogWindow("Изменить пароль пользователя", "Поторите пароль");
            presenter.editPassWord(pass1, pass2, getSelectedIndex());


        }
    }

    public void editUserInfo(ActionEvent actionEvent) {
        AlertWindow.infoMessage("Пока не работает, но скоро будет!");
    }

    public void filterUsersBySalon() {
        System.out.println("Фильтр сотрудников по салону");
        presenter.filterUsersBySalon(salonComboBox.getSelectionModel().getSelectedIndex());

    }


    public void filterFired( ) {
        System.out.println("чек удаленных");
        presenter.filterFired();

    }

    @Override
    public void fillComboBox(ObservableList list) {
        salonComboBox.setItems(list);
    }

    @Override
    public void fillEmpList(ObservableList<UserObject> emps) {
        empList.setItems(emps);
    }

    @Override
    public boolean isFiredShow() {
        return  isFiredShow.isSelected();
    }

    @Override
    public void showComplete() {
        AlertWindow.completeMessage();
    }

    @Override
    public void showNotComplete() {
        AlertWindow.notCompleteMessage();
    }

    @Override
    public void showError(String s) {
        AlertWindow.errorMessage(s);
    }
}
