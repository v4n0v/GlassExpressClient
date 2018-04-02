package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ru.glassexpress.controllers.presenters.DayPresenter;
import ru.glassexpress.controllers.presenters.DayView;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.objects.DateObject;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.library.AlertWindow;


public class DayController extends BaseController implements DayView {

    public Label adminLabel;
    public Label dataLabel;
    public Button skipButton;
    public ListView<UserObject> totalEmpListView;
    public ListView<UserObject> currentEmpListView;
    public Label salonLabel;
    private DayPresenter presenter;

    @Override
    public void init() {
        Log2File.writeLog("Инициализация окна составления рабочего графика");
        presenter = new DayPresenter(this);
        presenter.init();
        initPermissions();
    }
    private void initPermissions() {
        skipButton.setVisible(presenter.initVisibility());
    }
    public void onAcceptButtonClick() {
        presenter.accept();
    }



    @Override
    public void startApplication() {
        mainController.reconnect();
    }

    public void addEmployer(ActionEvent actionEvent) {
        int index = totalEmpListView.getSelectionModel().getSelectedIndex();
        presenter.addEmployer(index);
    }

    public void removeEmployer(ActionEvent actionEvent) {
        int index = currentEmpListView.getSelectionModel().getSelectedIndex();
        presenter.removeEmployer(index);
    }

    @Override
    public void setAdminLabel(String admin) {
        adminLabel.setText(admin);
    }

    @Override
    public void setDataLabel(String date) {
        dataLabel.setText(date);
    }

    @Override
    public void fillTotalEmpList(ObservableList<UserObject> totalEmployees) {
        totalEmpListView.setItems(totalEmployees);
    }

    @Override
    public void fillCurrentEmpList(ObservableList<UserObject> currentEmployees) {
        currentEmpListView.setItems(currentEmployees);
    }

    @Override
    public void updateSalonLabel(String salon) {
        salonLabel.setText(salon);
    }

    @Override
    public void showError(String msg) {
        AlertWindow.errorMessage(msg);
    }

    @Override
    public boolean addNewDay(DateObject today) {
        return mainController.getAddOperator().addNewDay(today);
    }

    @Override
    public void closeView() {
        close();
    }
}
