package ru.glassexpress.controllers.views;

import javafx.collections.ObservableList;
import ru.glassexpress.core.objects.CartProductObject;
import ru.glassexpress.core.objects.CartServiceObject;

public interface OrderConfirmView {
    void setComboBox(String target, ObservableList list);

    void setPermission(boolean isVisible);

    void setTotalPrice(String s);

    int getDiscountIndex();

    void setTextField(String s);

    void setGlassTable(String cart, ObservableList<CartProductObject> cart1);

    void setServiceTable(String service, ObservableList<CartServiceObject> cartService);

    float getServicesPrice();

    boolean confirm(String s);

    void closeView();

}
