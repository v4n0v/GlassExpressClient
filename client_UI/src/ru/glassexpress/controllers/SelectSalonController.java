package ru.glassexpress.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import ru.glassexpress.core.JsonController;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.Composite;
import ru.glassexpress.core.objects.DateObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.core.utils.OpenDayManager;

import java.util.List;

public class SelectSalonController extends BaseController {
    public ComboBox<IdTitleObj> salonsComboBox;
    private ObservableList<IdTitleObj> salons;
    private List<IdTitleObj> totalSalons;
    @Override
    public void init() {
        salons = FXCollections.observableArrayList();
        dataMap = DataMap.getInstance();
        salonsComboBox.setCellFactory(p -> new ListCell<IdTitleObj>() {
            @Override
            protected void updateItem(IdTitleObj item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.getTitle());
                } else {
                    setText(null);
                }
            }
        });


        salons.addAll(dataMap.getSalonsList());

        for (int i = 0; i < salons.size(); i++) {

            if (salons.get(i).getTitle().equals("Все салоны")){
                salons.remove(i);
            }
        }
        salonsComboBox.setItems(salons);
    }

    public void setSalon(){

        if (salonsComboBox.getSelectionModel().getSelectedIndex() != -1) {
            int salon = salons.get(salonsComboBox.getSelectionModel().getSelectedIndex()).getId();
            dataMap.getUser().setSalonId(salon);

            OpenDayManager openDayManager = new OpenDayManager(mainController.getGetListOperator(), dataMap.getUser());
            if (!openDayManager.isDayAlreadyOpened()){
                mainApp.initGoodMorningLayout(dataMap.getUser());
            } else {
                DateObject day = openDayManager.getCurrentDay();
                Composite empComposite = (Composite) JsonController.getInstance().convertJsonToObject(day.getEmployeesJson());
                BaseObjectAdapter adapter=BaseObjectAdapter.getInsance();
                ObservableList<UserObject> empList = adapter.baseObjToUserObjList(empComposite.getComponents());
                dataMap.setCurrentEmployeesList(empList);
                mainController.initPermission();
            }


            close();
        }

    }
}
