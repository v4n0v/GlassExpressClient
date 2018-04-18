package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import ru.glassexpress.controllers.presenters.SelectSalonPresenter;
import ru.glassexpress.controllers.views.SelectSalonview;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;

public class SelectSalonController extends BaseController implements SelectSalonview{
    public ComboBox<IdTitleObj> salonsComboBox;

    SelectSalonPresenter presenter;

    @Override
    public void init() {
        presenter = new SelectSalonPresenter(this);
        presenter.init();
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
    }

    public void setSalon(){

        if (salonsComboBox.getSelectionModel().getSelectedIndex() != -1) {
            presenter.applySalon(salonsComboBox.getSelectionModel().getSelectedIndex());

            close();
        }

    }

    @Override
    public void fillComboBox(ObservableList<IdTitleObj> salons) {
        salonsComboBox.setItems(salons);
    }

    @Override
    public void openGoodMorningView(UserObject user) {
        mainApp.initGoodMorningLayout(user  );
    }

    @Override
    public void initPermission() {
        mainController.initPermission();
    }
}
