package ru.glassexpress.controllers.presenters;

import ru.glassexpress.controllers.views.MenuView;
import ru.glassexpress.core.data.DataMap;

public class MenuPresenter {
    private MenuView view;
    private DataMap dataMap;
    public MenuPresenter(MenuView view) {
        this.view = view;
    }

    public void init(){
        dataMap=DataMap.getInstance();
    }

    public int getPermission() {
        return dataMap.getUser().getPermission();
    }

    public void initPermission() {
        boolean visible;
        if ( dataMap.getUser().getPermission() == 1) {
            visible = true;
        } else {
            visible = false;
        }
        view.setPermission(visible);
    }
}
