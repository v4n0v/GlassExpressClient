package ru.glassexpress.controllers.presenters;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.controllers.views.MainView;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.JsonController;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.edit_content_command.addCommand.AddOperator;
import ru.glassexpress.core.edit_content_command.deleteCommand.DeleteOperator;
import ru.glassexpress.core.edit_content_command.updCommand.UpdateOperator;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.Composite;
import ru.glassexpress.core.objects.GenerationObj;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.builders.GlassBuilder;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.library.Resources;

import java.util.ArrayList;
import java.util.List;

public class GlassTablePresenter {
    private MainView view;
    private ObservableList<GlassObject> glassObjects = FXCollections.observableArrayList();
    private DataMap dataMap;

    private BaseObjectAdapter adapter;
    private AddOperator addOperator;
    private GetListOperator getListOperator;
    private DeleteOperator deleteOperator;
    private UpdateOperator updateOperator;
    public GlassTablePresenter(MainView view) {
        this.view = view;
    }

    public void init() {
        dataMap = DataMap.getInstance();
        adapter = BaseObjectAdapter.getInsance();
        addOperator = new AddOperator(dataMap.getUser().getKey());
        deleteOperator  =new DeleteOperator(dataMap.getUser().getKey());
        updateOperator = new UpdateOperator(dataMap.getUser().getKey());
        getListOperator = dataMap.getGetListOperator();

        view.fillTable(glassObjects);
    }

    public void getGlassList() {
        List<GenerationObj> generationObjList = dataMap.getGenerationObjList();
        glassObjects.clear();
        if (generationObjList != null && generationObjList.size() > 0) {
            // получем поколение авто для выбора стекла
            GenerationObj car = dataMap.getGenerationObjList().get(view.getSelectedListElement(Resources.TARGET_GENERATION));
            dataMap.setGlassList(getListOperator.getTableGoods(car));

            // пустой оъект стекла клиента
            GlassObject glass = new GlassBuilder()
                    .setId(0)
                    .setCarTitle("")
                    .setDescription("Стекло клиента")
                    .setInsertPrice(Resources.DEFAULT_INSERT_PRICE + 500)
                    .build();

            dataMap.getGlassList().add(glass);

            // парсим список опций стекла, которые хранятся в json'ах
            for (int i = 0; i < dataMap.getGlassList().size() - 1; i++) {

                Composite optList = (Composite) JsonController.getInstance().convertJsonToObject(dataMap.getGlassList().get(i).getOptListString());
                List<IdTitleObj> list = adapter.baseObjToIdTitleObj(optList.getComponents());
                String params = "";
                for (int j = 0; j < list.size(); j++) {
                    params += list.get(j) + "\n";
                }
                dataMap.getGlassList().get(i).setOptList(list);
                dataMap.getGlassList().get(i).setParametrList(params);
                System.out.println("параметров: " + list.size());
            }
            glassObjects.addAll(dataMap.getGlassList());
        } else {
            view.showError("Укажите поколение авто");
        }
        System.out.println("показываю подходящий товар");
    }

    public void addNewGlass() {
        List<GenerationObj> generationObjList = dataMap.getGenerationObjList();
        if (generationObjList != null && generationObjList.size() > 0) {
            // открываем окгно
            if (dataMap.getGenerationObjList() != null) {
                view.openAddNewGlassView(dataMap.getGenerationObjList().get(view.getSelectedListElement(Resources.TARGET_GENERATION)).getId());
            } else
                System.out.println("выбиритее поколение");
        } else {
            view.showError("Укажите поколение авто");
        }
    }

    public void insertNewGlassToDatabase(GlassObject glass) {
        if (addOperator.addGlassIsComplete(glass)) {
            System.out.println("Добавлено в базу, все ок");
            view.showInfo("Добавлено в базу");
        } else {
            System.out.println("Фиаско! не добавлено");
        }
    }

    public void deleteGlass(){
        List<GlassObject> glasList = dataMap.getGlassList();
        List<Integer> idToDeleleList = null;

        int checked = 0;
        if (glasList != null && glasList.size() > 0) {
            for (GlassObject aGlasList : glasList) {
                BooleanProperty isSelected = aGlasList.isSelectedProperty();
                if (isSelected.get()) {
                    checked++;
                }
            }
        }

        if (checked > 0) {
            boolean isTrue = view.showConfirmationDialog("Удалить выбранные стекла из базы?");

            if (isTrue) {
                if (glasList != null && glasList.size() > 0) {
                    for (GlassObject aGlasList : glasList) {

                        BooleanProperty isSelected = aGlasList.isSelectedProperty();

                        if (isSelected.get()) {
                            if (aGlasList.getId() == 0) {
                                view.showError("Стекло клиента можно только установить. Ну или разбить :)");
                                return;
                            }

                            if (idToDeleleList == null) idToDeleleList = new ArrayList<>();
                            deleteOperator.deleteGlassIsComplete(aGlasList.getId());
                            idToDeleleList.add(aGlasList.getId());
                            //glassObjects.remove(i);
                        }
                    }
                    if (idToDeleleList != null && idToDeleleList.size() > 0) {
                        deleteFromGlassListByid(idToDeleleList);
                    }
                } else {
                   view.showError("Список товаров пуст");
                }
            }

        } else {
            view.showError("Выбирите стекло");
        }
    }

    public void editGlass(){
        List<GlassObject> glasList = dataMap.getGlassList();
        if (glasList != null && glasList.size() > 0) {

            int pos = view.getSelectedTableItem();
            GlassObject glass = glassObjects.get(pos);
            if (glass.getId() != 0) {

                int glassOptId = glass.getGlassOptionId();
                int glassTypeId = glass.getGlassTypeId();
                int glassFactoryID = glass.getGlassFactoryId();
                int glassBodyTypeId = glass.getBodyTypeId();

                String desc = glass.getDescription();
                String price = String.valueOf(glass.getPrice());
                String priceIns = String.valueOf(glass.getInsertPrice());
                String alert = String.valueOf(glass.getAlert());
                String pricein = String.valueOf(glass.getPriceIn());


                if (dataMap.getGenerationObjList() != null) {
                    int carId = dataMap.getGenerationObjList().get(view.getSelectedListElement(Resources.TARGET_GENERATION)).getId();
                    view.openEditGlassView(glassOptId, glassTypeId, glassFactoryID, glassBodyTypeId,
                            desc, price, priceIns, alert, pricein, glass.getId(), carId);

                } else
                    System.out.println("выбиритее поколение");


            } else {
                AlertWindow.errorMessage("Нельзя отредактирровать стекло клиента");
            }
//            addGlassController.show();
        } else {
            AlertWindow.errorMessage("Список товаров пуст");
        }
    }

    public void editGlassRequest(GlassObject glassPrepared){
        if (updateOperator.editGlassIsComplete(glassPrepared, dataMap.getUser().getKey())) {
            //fillGenerationsListView();
            view.showGoods();
            System.out.println("Запись обновлена базу, все ок");
            AlertWindow.infoMessage("Добавлено в базу");
            Log2File.writeLog("Запись обновлена id=" + glassPrepared.getId());
            //  dataMap.setGenerationObjList(getListOperator.getGenerations(car));
        } else {
            System.out.println("Фиаско! не добавлено");
            Log2File.writeLog("Запись не обновлена id=" + glassPrepared.getId());
        }
    }


    private void deleteFromGlassListByid(List<Integer> id) {
        for (int i = 0; i < glassObjects.size(); i++) {
            for (Integer anId : id) {
                if (glassObjects.get(i).getId() == anId) {
                    glassObjects.remove(i);
                }
            }
        }
    }

    public void createNewOrder(){
        List<GlassObject> glasList = dataMap.getGlassList();
        StringBuilder selected = new StringBuilder();
        if (glasList != null && glasList.size() > 0) {
            List<GlassObject> cart = new ArrayList<>();
            for (GlassObject glassObject : glasList) {
                BooleanProperty boo = glassObject.isSelectedProperty();

                if (boo.get()) {
                    selected.append(glassObject.getId()).append(" ");
                    cart.add(glassObject);
                }

                //  System.out.println(aaa);
            }
            if (!selected.toString().equals("")) {
                view.openNewOrderView(cart);
                //      AlertWindow.infoMessage("Выбраны N: " + selected);
            } else {
                AlertWindow.errorMessage("Выбирите товар");
            }
        } else {
            AlertWindow.errorMessage("Список товаров пуст");
        }

    }

}
