package ru.glassexpress.controllers.presenters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.controllers.views.SelectSalonview;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.JsonController;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.Composite;
import ru.glassexpress.core.objects.DateObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.core.OpenDayManager;

import java.util.List;

public class SelectSalonPresenter {
    public SelectSalonPresenter(SelectSalonview view) {
        this.view = view;
    }

    private ObservableList<IdTitleObj> salons;
    private List<IdTitleObj> totalSalons;
    private SelectSalonview view;
    private DataMap dataMap;
    private   GetListOperator getListOperator;

    public void init() {
        salons = FXCollections.observableArrayList();
        dataMap = DataMap.getInstance();
        salons.addAll(dataMap.getSalonsList());
        getListOperator= dataMap.getGetListOperator();
        for (int i = 0; i < salons.size(); i++) {

            if (salons.get(i).getTitle().equals("Все салоны")){
                salons.remove(i);
            }
        }

        view.fillComboBox(salons);


    }

    public void applySalon(int salonPos) {
        int salon = salons.get(salonPos).getId();
        dataMap.getUser().setSalonId(salon);

        OpenDayManager openDayManager = new OpenDayManager(getListOperator, dataMap.getUser());
        if (!openDayManager.isDayAlreadyOpened()){
            view.openGoodMorningView(dataMap.getUser());

        } else {
            DateObject day = openDayManager.getCurrentDay();
            Composite empComposite = (Composite) JsonController.getInstance().convertJsonToObject(day.getEmployeesJson());
            BaseObjectAdapter adapter=BaseObjectAdapter.getInsance();
            ObservableList<UserObject> empList = adapter.baseObjToUserObjList(empComposite.getComponents());
            dataMap.setCurrentEmployeesList(empList);
            view.initPermission();

        }

    }
}
