package ru.glassexpress.controllers.presenters;

import javafx.collections.ObservableList;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.edit_content_command.addCommand.AddOperator;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.core.utils.ObservableListAdapter;

public class AddEmployerPresenter {
    private AddEmployerView view;
    private  DataMap dataMap;

    public AddEmployerPresenter(AddEmployerView view) {
        this.view = view;
    }

    public void init() {
        dataMap = DataMap.getInstance();
        //BaseObjectAdapter adapter = BaseObjectAdapter.getInsance();
//        ObservableList<String> salonsList = adapter.idTitleObjToString(dataMap.getSalonsList());
//        ObservableList<String> permisList = adapter.idTitleObjToString(dataMap.getPermissionsList());
//        ObservableList<String> posList = adapter.idTitleObjToString(dataMap.getPositionsList());


        ObservableListAdapter adapter = new ObservableListAdapter();
        ObservableList<String> salonsList = adapter.asObservableList(dataMap.getSalonsList());
        ObservableList<String> permisList = adapter.asObservableList(dataMap.getPermissionsList());
        ObservableList<String> posList = adapter.asObservableList(dataMap.getPositionsList());

        view.fillPermisComboBox(permisList);
        view.fillPositionsComboBox(posList);
        view.fillSalonsComboBox(salonsList);
    }
    public void addUser(String firstName, String lastName, int salonIndex, int permisIndex, int posIndex) {
        if (firstName != null && lastName != null && !firstName.equals("") && !lastName.equals("")
                && salonIndex != -1 && permisIndex != -1 && posIndex != -1) {

            AddOperator addOperator = new AddOperator(dataMap.getUser().getKey());
            UserObject newUser = dataMap.getNewUser();

            newUser.setName(firstName);
            newUser.setLastName(lastName);
            newUser.setSalonId(dataMap.getSalonsList().get(salonIndex).getId());
            newUser.setPermission(dataMap.getPermissionsList().get(permisIndex).getId());
            newUser.setPositionId(dataMap.getPositionsList().get(posIndex).getId());

            // добавляем пользователя на сервер
            if (addOperator.addUserIsComplete(newUser)) {
                System.out.println("Все ок");
                view.closeView();
            } else {
                System.out.println("Пользователь не добавлен на сервер");
                view.showError("Пользователь не добавлен на сервер");
            }
        } else {
            view.showError("Заполниет все поля и формы");
        }


    }
}
