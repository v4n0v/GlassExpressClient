package ru.glassexpress.controllers.presenters;

import javafx.collections.ObservableList;
import javafx.scene.control.CheckMenuItem;
import ru.glassexpress.controllers.AddGlassController;
import ru.glassexpress.core.objects.GlassObject;

public interface AddGlassView {
    void fillGlassOptComboBox(ObservableList listt);
    void fillGlassFactoryComboBox(ObservableList listt);
    void fillGlassTypeComboBox(ObservableList listt);
    void fillGlassBodyComboBox(ObservableList listt);
    void setDefaultPriceComboBox(String price);
    void fillMultipleOptionsList(ObservableList<CheckMenuItem> optMultipleList);

    void showError(String msg);
    void closeView();

    String getDescription();
    String getPriceIn();
    String getPrice();
    String getAlert();
    String getInsertPrice();
    int getGlassOptIndex();
    int getGlassFactoryIndex();
    int getGlassTypeIndex();
    int getBodyTypeIndex();

    boolean isGlueRadioButtonSelected();
    boolean isRubberRadioButtonSelected();

   AddGlassController.State getState();

    void insertGlass(GlassObject glassPrepared);

    void editGlass(GlassObject glassPrepared);

    int getGlassId();
}
