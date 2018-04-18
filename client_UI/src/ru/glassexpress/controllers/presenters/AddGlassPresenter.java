package ru.glassexpress.controllers.presenters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckMenuItem;
import ru.glassexpress.controllers.AddGlassController;
import ru.glassexpress.controllers.views.AddGlassView;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.Composite;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.builders.GlassBuilder;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.library.Resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddGlassPresenter {
    private AddGlassView view;
    private int carId;

    public AddGlassPresenter(AddGlassView view) {
        this.view = view;
    }


    private BaseObjectAdapter adapter;
//    private ObservableListAdapter adapter;
    private ObservableList<String> glassOptList;
    private ObservableList<String> glassFactoryList;
    private ObservableList<String> glassTypeList;
    private ObservableList<CheckMenuItem> optMultipleList;
    private  DataMap dataMap;
    public void init() {
        // получаем данные из модели
        adapter = BaseObjectAdapter.getInsance();
        dataMap=DataMap.getInstance();
        glassOptList = adapter.idTitleObjToString(dataMap.getGlassOptList());
        glassFactoryList = adapter.idTitleObjToString(dataMap.getGlassFactoryList());
        glassTypeList = adapter.idTitleObjToString(dataMap.getGlassTypeList());

        // получили данные из модели, передаем их во вью
        view.fillGlassOptComboBox(glassOptList);
        view.fillGlassFactoryComboBox(glassFactoryList);
        view.fillGlassTypeComboBox(glassTypeList);
        view.fillGlassBodyComboBox(adapter.idTitleObjToString(dataMap.getBodyTypeList()));
        view.setDefaultPriceComboBox(String.valueOf(Resources.DEFAULT_INSERT_PRICE));

        optMultipleList = FXCollections.observableArrayList();
        for (int i = 0; i < glassOptList.size(); i++) {
            optMultipleList.add(new CheckMenuItem(glassOptList.get(i)));
        }
        view.fillMultipleOptionsList(optMultipleList);

    }



    public int getBodyTypePosition(int glassBodyId){
       return dataMap.getPosById(dataMap.getBodyTypeList(), glassBodyId);
    }
    public int getFactoryPosition(int glassFactoryId){
        return dataMap.getPosById(dataMap.getGlassFactoryList(), glassFactoryId);
    }
    public int getTypePosition(int glassTypeId){
        return dataMap.getPosById(dataMap.getGlassTypeList(), glassTypeId);
    }
    public int getOptPosition(int glassOptId){
        return dataMap.getPosById(dataMap.getGlassOptList(), glassOptId);
    }


    public void addGlass(){
        Log2File.writeLog("Начало добавления нового элемента стекла");

        String description = view.getDescription();
        String priceIn = view.getPriceIn();
        String price = view.getPrice();
        String alert = view.getAlert();
        String insertPrice = view.getInsertPrice();
        int glassOptIndex = view.getGlassOptIndex();
        int glassFactoryIndex = view.getGlassFactoryIndex();
        int glassTypeIndex = view.getGlassTypeIndex();
        int bodyTypeIndex = view.getBodyTypeIndex();

        // собираем json доп. опций
        String optionsJson="";
        Composite composite = new Composite();
        int checked = 0;
        for (int i = 0; i < optMultipleList.size(); i++) {
            if (optMultipleList.get(i).isSelected()) {
                composite.addComponent(new IdTitleObj(i, glassOptList.get(i)));
                checked++;
            }
        }
        if (checked>0) {
            optionsJson = composite.toJSONObject().toString();
        }
        // если все выбрано верно
        GlassObject glassPrepared;
        if (!priceIn.equals("") && !price.equals("") &&
                !alert.equals("") && !insertPrice.equals("") &&
                //   glassOptIndex != -1 && glassFactoryIndex != -1 && glassTypeIndex != -1 &&
                isNumeric(price) && isNumeric(priceIn)) {

            if (!optionsJson.equals("")) {
                if (description.equals("")) description = " ";

                int insertMethod = 2;
                if (view.isGlueRadioButtonSelected()) {
                    insertMethod = 2;
                } else if (view.isRubberRadioButtonSelected()) {
                    insertMethod = 3;
                } else {
                    AlertWindow.errorMessage("Выбирете метод установки");
                }
                // создаем стекло
                int glassOptId = dataMap.getGlassOptList().get(glassOptIndex).getId();
                int glassFactoryId = dataMap.getGlassFactoryList().get(glassFactoryIndex).getId();
                int glassTypeId = dataMap.getGlassTypeList().get(glassTypeIndex).getId();
                int bodyTypeId = dataMap.getBodyTypeList().get(bodyTypeIndex).getId();

                glassPrepared = new GlassBuilder()
                        .setCarId(carId)
                        .setDescription(description)
                        .setPriceIn(Float.parseFloat(priceIn))
                        .setPrice(Float.parseFloat(price))
                        .setOptListString(optionsJson)
                        .setGlassOptionId(glassOptId)
                        .setGlassFactoryId(glassFactoryId)
                        .setInsertMethodId(insertMethod)
                        .setGlassTypeId(glassTypeId)
                        .setAlert(Integer.parseInt(alert))
                        .setInsertPrice(Float.parseFloat(insertPrice))
                        .setBodyType(bodyTypeId)
                        .build();

               // dataMap.setPreparedGlass(glassPrepared);

                if (view.getState() == AddGlassController.State.ADD) {
                    view.insertGlass(glassPrepared);

                } else if (view.getState()  == AddGlassController.State.EDIT) {
                    glassPrepared.setId(view.getGlassId());
                    view.editGlass(glassPrepared);

                }
                Log2File.writeLog("Новое стекло добавлено");

                System.out.println(optionsJson);
             view.closeView();
            } else {
                view.showError("Выбирите хотябы 1 параметр стекла");
            }

        } else {
            view.showError("Корректно заполните все формы");
        }
    }
    private static boolean isNumeric(String x) {
        Pattern p = Pattern.compile("^\\d+(?:\\.\\d+)?$");
        Matcher m = p.matcher(x);
        return m.matches();
    }

    public void addNewBodyType(String answer) {

    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
