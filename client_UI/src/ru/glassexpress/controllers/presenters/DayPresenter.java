package ru.glassexpress.controllers.presenters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.controllers.views.DayView;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.edit_content_command.addCommand.AddOperator;
import ru.glassexpress.core.objects.*;
import ru.glassexpress.core.utils.ObservableListAdapter;
import ru.glassexpress.library.Resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DayPresenter {
    DayView view;
    private UserObject administrator;
    private Date date;
    private DataMap dataMap;

    public DayPresenter(DayView view) {
        this.view = view;
    }
    private AddOperator addOperator;
    private ObservableList<UserObject> totalEmployees;
    private ObservableList<UserObject> currentEmployees;

    public void init() {
        dataMap = DataMap.getInstance();

        administrator = dataMap.getUser();
        ObservableListAdapter<UserObject> userObservableAdapter = new ObservableListAdapter<>();
        currentEmployees = FXCollections.observableArrayList();
        //ObservableList<IdTitleObj> salons = FXCollections.observableArrayList();
        view.setAdminLabel(administrator.getName() + " " + administrator.getLastName());
        addOperator = new AddOperator(administrator.getKey());
        // устанавливаем дауту в заголовок
        date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Resources.DATE_PATTERN);
        String date = dateFormat.format(this.date);
        view.setDataLabel(date);

        // устанавливаем список магазинов в ComboBox
        GetListOperator operator = new GetListOperator(administrator.getKey());
        if (currentEmployees != null) {
            dataMap.setCurrentEmployeesList(currentEmployees);
        }

        int salon = administrator.getSalonId();
        administrator.setSalonId(salon);
        totalEmployees = userObservableAdapter.asObservableList(operator.getEmloyees(administrator));
        view.fillTotalEmpList(totalEmployees);
        view.fillCurrentEmpList(currentEmployees);
        view.updateSalonLabel(dataMap.getTitleById(dataMap.getSalonsList(), salon));
    }

    public void accept() {
        if (currentEmployees != null && currentEmployees.size() > 0) {
            String empListJson = parseToJson(currentEmployees);
//            String empListJson = dataMap.convertListToJson(currentEmployees);
            DateObject today = new DateObject(0, date.getTime(), empListJson, administrator.getId(), administrator.getSalonId());
            if (addOperator.addNewDay(today)) {
                dataMap.setCurrentEmployeesList(currentEmployees);
                view.startApplication();
                view.closeView();
            }
        } else {
            view.showError("Кто-то должен поработать!");
        }
    }


    public boolean initVisibility() {
        return administrator.getPermission() == 1;
    }

    public void addEmployer(int index) {
        if (index != -1) {
            currentEmployees.add(totalEmployees.get(index));
            totalEmployees.remove(index);
        }

    }
    public String parseToJson(List<UserObject> list) {

        Composite composite = new Composite();
        for (int i = 0; i < list.size(); i++) {
            composite.addComponent(list.get(i));
        }
        return composite.toJSONObject().toString();

    }
    public void removeEmployer(int index) {
        if (index != -1) {
            totalEmployees.add(currentEmployees.get(index));
            currentEmployees.remove(index);
        }
    }
}
