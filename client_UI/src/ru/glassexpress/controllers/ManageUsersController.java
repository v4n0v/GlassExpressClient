package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.StringValidator;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.edit_content_command.deleteCommand.DeleteOperator;
import ru.glassexpress.core.edit_content_command.updCommand.UpdateOperator;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.core.utils.ObservableListAdapter;
import ru.glassexpress.library.AlertWindow;

import java.util.ArrayList;
import java.util.List;

public class ManageUsersController extends BaseController {


    public ComboBox<IdTitleObj> salonComboBox;
    public ListView<UserObject> empList;
    public CheckBox isFiredShow;
    private List<UserObject> fullEmpsList;
    private ObservableList<UserObject> emps;
    UpdateOperator updateOperator;

    @Override
    public void init() {
        Log2File.writeLog("Инициализация панели управления пользователями");
        GetListOperator operator = mainController.getGetListOperator();
        ObservableListAdapter adapter = new ObservableListAdapter();
        updateOperator=mainController.getUpdateOperator();
        fullEmpsList = operator.getAllEmloyees();

        emps = FXCollections.observableArrayList();
        emps.addAll(fullEmpsList);
        filterFired();

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
        salonComboBox.setItems(adapter.asObservableList(dataMap.getSalonsList()));

        empList.setItems(emps);
    }

    public void addUser(ActionEvent actionEvent) {
        mainApp.initAddAdminLayout();
        close();
    }

    private int getSelectedIndex() {
        return empList.getSelectionModel().getSelectedIndex();
    }

    public void delUser(ActionEvent actionEvent) {


        boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Уволить " + emps.get(getSelectedIndex()) + "?");
        if (isTrue) {
            isTrue = AlertWindow.confirmationWindow("Вы точно уверены?", emps.get(getSelectedIndex())+" , будет уволен, а его семья будет голодать!");
            if (isTrue) {
                DeleteOperator deleteOperator = mainController.getDeleteOperator();
                int id = emps.get(empList.getSelectionModel().getSelectedIndex()).getId();
                if (deleteOperator.deleteUserIsComplete(id)) {
                    AlertWindow.completeMessage();
//                    emps.remove(empList.getSelectionModel().getSelectedIndex());
                }
            }
        }
    }

    public void editLogin(ActionEvent actionEvent) {
        String login = AlertWindow.dialogWindow("Изменить логин " + emps.get(getSelectedIndex()).getLogin(), "Введите логин");
        if (login!=null && StringValidator.isPassCorrect(login) && login.length() > 2) {
            UserObject user = emps.get(empList.getSelectionModel().getSelectedIndex());
            user.setLogin(login);
            if (updateOperator.updateLoginComplete(user)) {
                AlertWindow.completeMessage();
            } else AlertWindow.notCompleteMessage();
        } else {
            AlertWindow.errorMessage("Допустимы только буквы и цифры английского алфавита.\nДлина не менее 3х символов");
        }
    }

    public void editPassword(ActionEvent actionEvent) {
        String pass1 = AlertWindow.dialogWindow("Изменить пароль пользователя " + emps.get(getSelectedIndex()).getLogin(), "Введите пароль");
        if (pass1 != null) {
            String pass2 = AlertWindow.dialogWindow("Изменить пароль пользователя" + emps.get(getSelectedIndex()).getLogin(), "Поторите пароль");
            if (pass2!=null && StringValidator.isPassCorrect(pass1) && StringValidator.isPassCorrect(pass2)
                    && pass1.length() > 2 && pass2.length() > 2) {
                if (pass1.equals(pass2)) {

                    UserObject user = emps.get(empList.getSelectionModel().getSelectedIndex());
                    System.out.println("Новый хеш пароля "+pass1.hashCode());
                    user.setPassHash(pass1.hashCode());
                    if (updateOperator.updatePassComplete(user)){
                        AlertWindow.completeMessage();
                    }

                  //  AlertWindow.infoMessage("Пока не работает, но скоро будет!");
                } else {
                    AlertWindow.errorMessage("Пароли не совпадают. Будте внимательны.");
                }
            }

        }
    }

    public void editUserInfo(ActionEvent actionEvent) {
        AlertWindow.infoMessage("Пока не работает, но скоро будет!");
    }

    public void filterUsersBySalon() {
        System.out.println("Фильтр сотрудников по салону");
        int id = dataMap.getSalonsList().get(salonComboBox.getSelectionModel().getSelectedIndex()).getId();
        if (id != -1) {
            emps.clear();
           // emps.addAll(fullEmpsList);

            for (int i = 0; i < fullEmpsList.size(); i++) {
                if (fullEmpsList.get(i).getSalonId() == id) {
                    emps.add(fullEmpsList.get(i));
                }
            }
        }
    }


    public void filterFired( ) {
        System.out.println("чек удаленных");
        for (int i = 0; i < emps.size(); i++) {
            if (emps.get(i).getPositionId()==5){
                if (isFiredShow.isSelected()) {
                    emps.remove(i);

                }
            }
        }
    }
}
