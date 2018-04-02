package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.*;
import ru.glassexpress.core.utils.ObservableListAdapter;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.library.Resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DayController extends BaseController {


    public Label adminLabel;
    public Label dataLabel;
    public Button skipButton;

    public ListView<UserObject> totalEmpListView;
    public ListView<UserObject> currentEmpListView;
    public Label salonLabel;

    private UserObject administrator;
    private Date date;
    DateObject today;

    DataMap dataMap;
    ObservableList<UserObject> totalEmployees;
    ObservableList<UserObject> currentEmployees;
    ObservableList<IdTitleObj> salons;
    GetListOperator operator;
    private ObservableListAdapter<UserObject> userObservableAdapter;

    @Override
    public void init() {
        Log2File.writeLog("Инициализация окна составления рабочего графика");
        initPermissions();
        userObservableAdapter = new ObservableListAdapter<>();
        currentEmployees = FXCollections.observableArrayList();
        salons = FXCollections.observableArrayList();
        adminLabel.setText(administrator.getName() + " " + administrator.getLastName());
        dataMap = DataMap.getInstance();
        // устанавливаем дауту в заголовок
        date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Resources.DATE_PATTERN);
        String title = dateFormat.format(date);
        dataLabel.setText(title);

        // устанавливаем список магазинов в ComboBox
        operator = new GetListOperator(administrator.getKey());


        if (currentEmployees!=null) {
            dataMap.setCurrentEmployeesList(currentEmployees);
        }

        int salon = administrator.getSalonId();
        administrator.setSalonId(salon);
        totalEmployees = userObservableAdapter.asObservableList(operator.getEmloyees(administrator));
        totalEmpListView.setItems(totalEmployees);
        currentEmpListView.setItems(currentEmployees);


        salonLabel.setText(dataMap.getTitleById(dataMap.getSalonsList(), salon));


    }



    public void beginMakingMoney() {

            if (currentEmployees != null && currentEmployees.size() > 0) {

                String empListJson = parseToJson(currentEmployees);

                today = new DateObject(0, date.getTime(), empListJson, administrator.getId(), administrator.getSalonId());
                if (mainController.getAddOperator().addNewDay(today)) {
                    dataMap.setCurrentEmployeesList(currentEmployees);
                    startApplication();
                    close();
                }

            } else {
                AlertWindow.errorMessage("Кто-то должен поработать!");
            }

    }


    private String parseToJson(List<UserObject> empList) {

        Composite composite = new Composite();
        for (int i = 0; i < empList.size(); i++) {
            composite.addComponent(empList.get(i));
//            composite.addComponent(empList.get(i));
        }
        return composite.toJSONObject().toString();

    }

    private void initPermissions() {
        boolean isVisible = false;
        if (administrator.getPermission() == 1) {
            isVisible = true;
        }
        skipButton.setVisible(isVisible);
    }


    public void setAdministrator(UserObject administrator) {
        this.administrator = administrator;
    }

    private void startApplication() {
        mainController.reconnect();
    }


    public void addEmployer(ActionEvent actionEvent) {

        int index = totalEmpListView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            currentEmployees.add(totalEmployees.get(index));
            totalEmployees.remove(index);
        }

    }

    public void removeEmployer(ActionEvent actionEvent) {
        int index = currentEmpListView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            totalEmployees.add(currentEmployees.get(index));
            currentEmployees.remove(index);
        }
    }
}
