package ru.glassexpress.controllers.presenters;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.controllers.model.OrderModel;
import ru.glassexpress.controllers.views.OrderConfirmView;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.objects.*;
import ru.glassexpress.core.utils.ObservableListAdapter;
import ru.glassexpress.library.Resources;

import java.util.List;

public class OrderConfirmPresenter {
    private OrderConfirmView view;
    private ObservableListAdapter observableAdapter;

    OrderModel model;

    public void setSelectedGlass(List<GlassObject> selectedGlass) {
        this.selectedGlass = selectedGlass;
    }

    private List<GlassObject> selectedGlass;
    public OrderConfirmPresenter(OrderConfirmView view) {
        this.view = view;
    }
    private  float orderPrice;

    // инициализация презентера
    public void init() {
        // получаем экземпляр списков
        model = new OrderModel();
        observableAdapter=new ObservableListAdapter();

        // добавляем вкорзину список выбранных стекол
        model.addProductsToCart(selectedGlass);

        // заполняем таблицу выбранных товаров
        view.setGlassTable("cart",model.getCart());
        view.setServiceTable("service", model.getCartService());
        view.setComboBox(Resources.TARGET_SERVICE, observableAdapter.asObservableList(model.getServices()));

        initPermission();
        // заполняем комбо со скидосами
        view.setComboBox("discount", model.getDiscounts());

        calculate();

    }

    // опередляем права юзера на видиние кнопок добавить\удалить
    private void initPermission() {
        boolean isVisible;

        if (model.getUserPermission() == 1) {
            isVisible = true;
        } else {
            isVisible = false;
        }

        view.setPermission(isVisible);
        view.setTotalPrice(String.valueOf(orderPrice));
    }

    // подсчет конечной цены
    public void calculate() {
        view.setTotalPrice(String.valueOf(model.getTotalPrice(view.getDiscountIndex())));
    }

    //
    public void handleServicePrice(int index) {
        if (index!=-1){
            view.setTextField(String.valueOf(model.getServices().get(index).getPrice()));
            }
    }

    public void addServicePriceInCart(int index) {
        if (index!=-1){
            model.getCartService().add(new CartServiceObject(model.getServices().get(index), view.getServicesPrice()));
        }
        calculate();
    }

    public void confirmOrder(boolean selected) {
        model.setPaymentType(selected);
        calculate();
        String payment=", оплата ";

        payment+= (selected) ?  "картой" : "наличныйми";
        if(view.confirm("Вы точно хотите подтвердить заказ?\nСумма зазаза = "+ model.getOrderPrice() +payment)) {
            // логика оформления заказа
          model.createOrder();
            view.closeView();
        }
    }

    public void addNewService(String answer, String price) {
        view.showInfo(answer+" за "+price+"р успешно добавлено");
    }

    public void delService(int selectedIndex) {
        if(view.confirm("Вы точно хотите удалить услугу '"+ model.getServices().get(selectedIndex)+"'")) {
            // логика удаления услуги
            view.showInfo("Услуга успешно удалена");
        }
    }
}
