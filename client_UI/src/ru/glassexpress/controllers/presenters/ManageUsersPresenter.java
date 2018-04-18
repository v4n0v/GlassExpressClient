package ru.glassexpress.controllers.presenters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.controllers.views.ManageUsersView;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.StringValidator;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.edit_content_command.deleteCommand.DeleteOperator;
import ru.glassexpress.core.edit_content_command.updCommand.UpdateOperator;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.core.utils.ObservableListAdapter;
import ru.glassexpress.library.AlertWindow;

import java.util.List;

public class ManageUsersPresenter {
    private ManageUsersView view;
    private List<UserObject> fullEmpsList;
    private ObservableList<UserObject> emps;
    private UpdateOperator updateOperator;
    private DataMap dataMap;
    private GetListOperator operator;

    public ManageUsersPresenter(ManageUsersView view) {
        this.view = view;
    }

    public void init() {
        dataMap = DataMap.getInstance();

        operator = dataMap.getGetListOperator();
        ObservableListAdapter adapter = new ObservableListAdapter();
        updateOperator = new UpdateOperator(dataMap.getUser().getKey());
        fullEmpsList = operator.getAllEmloyees();

        emps = FXCollections.observableArrayList();
        emps.addAll(fullEmpsList);
        filterFired();

        view.fillComboBox(adapter.asObservableList(dataMap.getSalonsList()));
        view.fillEmpList(emps);
    }

    public void filterFired() {
        System.out.println("чек удаленных");
        for (int i = 0; i < emps.size(); i++) {
            if (emps.get(i).getPositionId() == 5) {
                if (view.isFiredShow()) {
                    emps.remove(i);
                }
            }
        }
    }

    public void deleteUser(int selectedIndex) {
        DeleteOperator deleteOperator = new DeleteOperator(dataMap.getUser().getKey());
        int id = emps.get(selectedIndex).getId();
        if (deleteOperator.deleteUserIsComplete(id)) {
            view.showComplete();
        }
    }

    public void editLogin(String login, int index) {
        if (login != null && StringValidator.isPassCorrect(login) && login.length() > 2) {
            UserObject user = emps.get(index);
            user.setLogin(login);
            if (updateOperator.updateLoginComplete(user)) {
                view.showComplete();
            } else view.showNotComplete();
        } else {
            AlertWindow.errorMessage("Допустимы только буквы и цифры английского алфавита.\nДлина не менее 3х символов");
        }
    }

    public void editPassWord(String pass1, String pass2, int index) {
        if (pass2 != null && StringValidator.isPassCorrect(pass1) && StringValidator.isPassCorrect(pass2)
                && pass1.length() > 2 && pass2.length() > 2) {
            if (pass1.equals(pass2)) {
                UserObject user = emps.get(index);
                System.out.println("Новый хеш пароля " + pass1.hashCode());
                user.setPassHash(pass1.hashCode());
                if (updateOperator.updatePassComplete(user)) {
                    view.showComplete();
                }
            } else {
                view.showError("Пароли не совпадают. Будте внимательны.");
            }
        }
    }

    public void filterUsersBySalon(int index) {
        int id = dataMap.getSalonsList().get(index).getId();
        if (id != -1) {
            emps.clear();

            for (int i = 0; i < fullEmpsList.size(); i++) {
                if (fullEmpsList.get(i).getSalonId() == id) {
                    emps.add(fullEmpsList.get(i));
                }
            }
        }
    }
}
