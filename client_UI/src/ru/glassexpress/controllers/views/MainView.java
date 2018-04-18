package ru.glassexpress.controllers.views;

import javafx.collections.ObservableList;
import ru.glassexpress.core.objects.GlassObject;

import java.util.List;


public interface MainView {

    void fillListView(String view, ObservableList list);
    void selectFirstElement(String view);
    int getSelectedListElement(String listView);
    void setLabel(String label, String text);

    void fillTable(ObservableList<GlassObject> glassObjects);
    void openAddNewGlassView(int id);
    void openNewOrderView(List<GlassObject> cart);

    boolean showConfirmationDialog(String s);
    void showError(String msg);
    void showInfo(String msg);

    int getSelectedTableItem();

    void openEditGlassView(int glassOptId, int glassTypeId, int glassFactoryID, int glassBodyTypeId, String desc, String price, String priceIns, String alert, String pricein, int id, int carId);

    void showGoods();
}
