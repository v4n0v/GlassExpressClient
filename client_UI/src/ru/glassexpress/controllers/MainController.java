package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import ru.glassexpress.controllers.presenters.GlassTablePresenter;
import ru.glassexpress.controllers.views.MainView;
import ru.glassexpress.controllers.presenters.SelectCarPresenter;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.library.Resources;

import java.util.List;

public class MainController extends BaseController implements MainView {
    public Label nameLabel;
    public Label salonLabel;
    public Button createOrderButton;
    public ListView employeesListView;
    public Button editGenerationButton;
    public Button editModelButton;
    public Button editMarkButton;
    public Button addGlassButton;
    public ComboBox<String> bodyTypeListView;
    public Button addGenerationButton;
    public Button addModelButton;
    public ListView<String> modelListView;
    public ListView<IdTitleObj> markListView;
    public ListView<String> genListView;
    public Button addMarkButton;
    public RadioButton isFrontRadio;
    public RadioButton isRearRadio;
    public Button delModelButton;
    public Button delMarkButton;
    public Button delGenerationButton;

    private SelectCarPresenter selectCarPresenter;
    private GlassTablePresenter glassTablePresenter;


    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    private MenuController menuController;
    private AddGlassController addGlassController;


    // инициализация конроллера, вызывается при открытии приложения
    @Override
    public void init() {
        Log2File.writeLog("Инициализация главного окна");
        selectCarPresenter = new SelectCarPresenter(this);
        glassTablePresenter = new GlassTablePresenter(this);
        ToggleGroup radioGroup = new ToggleGroup();
        isFrontRadio.setToggleGroup(radioGroup);
        isRearRadio.setToggleGroup(radioGroup);
    }

    public void update() {
        initTGTable();
        Log2File.writeLog("update");
        selectCarPresenter.init();
        selectCarPresenter.update();
        selectCarPresenter.getMarks();
        selectCarPresenter.getLists();
        initPermission();
    }

    public Button editGlassButton;
    public Button deleteGlassButton;

    public void initPermission() {
        selectCarPresenter.initEmployees();
        boolean isDisabled;
        boolean isVisible;
        if (selectCarPresenter.getPermission() != 1) {
            isDisabled = true;
            isVisible = false;
        } else {
            isDisabled = false;
            isVisible = true;
        }
        if (selectCarPresenter.getPermission() == 3) {
            createOrderButton.setDisable(true);
        }
        addGlassButton.setDisable(isDisabled);
        editGenerationButton.setDisable(isDisabled);
        editMarkButton.setDisable(isDisabled);
        editModelButton.setDisable(isDisabled);
        deleteGlassButton.setDisable(isDisabled);
        colTGPriceIn.setVisible(isVisible);
        delMarkButton.setDisable(isDisabled);
        delGenerationButton.setDisable(isDisabled);
        delModelButton.setDisable(isDisabled);
        editGlassButton.setDisable(isDisabled);
        menuController.initPermission();
    }

    // диалоговые окна добавления\ужаления\редактирования элементов авто
    public void startAddDialog(ActionEvent keyEvent) {
        String answer = AlertWindow.dialogWindow("Добавление элемент", "Выберите марку авто");
        if (answer != null && !answer.equals("")) {
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить " + answer + " в базу?");
            if (isTrue) {
                if (!answer.equals("")) {
                    Button pressedButton = (Button) keyEvent.getSource();
                    if (pressedButton == addMarkButton) {
                        selectCarPresenter.addMark(answer);
                    } else if (pressedButton == addModelButton) {
                        selectCarPresenter.addModel(answer);
                    } else if (pressedButton == addGenerationButton) {
                        selectCarPresenter.addGeneration(answer);
                    }
                }
            } else {
                showError("Полее ввода не заполнено!");
            }
        }
    }

    public void startEditDialog(ActionEvent keyEvent) {
        String answer = AlertWindow.dialogWindow("Редактирование элемента", "Выберите марку авто");
        if (answer != null && !answer.equals("")) {
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Изменить значение на " + answer);
            if (isTrue) {
                if (!answer.equals("")) {
                    Button pressedButton = (Button) keyEvent.getSource();
                    if (pressedButton == editMarkButton) {
                        selectCarPresenter.editMark(answer);
                    } else if (pressedButton == editModelButton) {
                        selectCarPresenter.editModel(answer);
                    } else if (pressedButton == editGenerationButton) {
                        selectCarPresenter.editGeneration(answer);
                    }
                }
            } else {
                showError("Полее ввода не заполнено!");
            }
        }
    }

    public void startDeleteDialog(ActionEvent keyEvent) {
        boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить безвозвратно выбранныей элемент из базы?");
        if (isTrue) {
            Button pressedButton = (Button) keyEvent.getSource();
            if (pressedButton == delMarkButton) {
                selectCarPresenter.deleteMark();
            } else if (pressedButton == delModelButton) {
                selectCarPresenter.deleteModel();
            } else if (pressedButton == delGenerationButton) {
                selectCarPresenter.deleteGeneration();
            }
        }
    }

    // показываем список товаров
    @Override
    public void showGoods() {
        glassTablePresenter.getGlassList();
    }


    // таблица стекол на складе

    public TableView<GlassObject> tblGoodsInStock;
    public TableColumn<GlassObject, Integer> colTGId;
    public TableColumn<GlassObject, Float> colTGPrice;
    public TableColumn<GlassObject, String> colTGDesc;
    public TableColumn<GlassObject, String> colTGFactory;
    public TableColumn<GlassObject, String> colTGOption;
    public TableColumn<GlassObject, String> colTGType;
    public TableColumn<GlassObject, Float> colTGInsertPrice;
    public TableColumn<GlassObject, Float> colTGPriceIn;
    public TableColumn<GlassObject, String> colTGBody;
    public TableColumn<GlassObject, Boolean> colTGSelect;
    // инициализация колонок таблицы


    private void initTGTable() {
        colTGId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        colTGDesc.setCellValueFactory(cellData -> cellData.getValue().getDescProperty());
        colTGOption.setCellValueFactory(cellData -> cellData.getValue().getParameterListTitle());
        colTGType.setCellValueFactory(cellData -> cellData.getValue().getTypeTitleProperty());
        colTGPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        colTGPriceIn.setCellValueFactory(cellData -> cellData.getValue().getPriceInProperty());
        colTGInsertPrice.setCellValueFactory(cellData -> cellData.getValue().getInsertPriceProperty());
        colTGFactory.setCellValueFactory(cellData -> cellData.getValue().getFactoryTitle());
        colTGBody.setCellValueFactory(cellData -> cellData.getValue().getBodyTypeTitle());
        colTGSelect.setCellValueFactory(cellData -> cellData.getValue().isSelectedProperty());
        colTGSelect.setCellFactory(tc -> new CheckBoxTableCell<>());
        glassTablePresenter.init();

        tblGoodsInStock.setEditable(true);
    }


    // открываем дилоговое окно добавления ного стекла в базу
    public void openAddGlassDialog() {
        glassTablePresenter.addNewGlass();
    }


    public void setAddGlassController(AddGlassController addGlassController) {
        this.addGlassController = addGlassController;
    }


    // втавляем новое стекло в базу
    public void insertGlass(GlassObject glass) {
        glassTablePresenter.insertNewGlassToDatabase(glass);
        System.out.println("добавляю новое стекло в базу");

    }


    @FXML
    Label frontInsertPriceLabel;
    @FXML
    Label rearInsertPriceLabel;
    @FXML
    Label sideInsertPriceLabel;


    // создать новый заказ
    public void openNewOrder(ActionEvent actionEvent) {
        glassTablePresenter.createNewOrder();
    }

    public void deleteSelectedGlass(ActionEvent actionEvent) {
        glassTablePresenter.deleteGlass();
    }


    public void editGlass(ActionEvent actionEvent) {
        glassTablePresenter.editGlass();

    }

    public void updGlass(GlassObject glassPrepared) {
        glassTablePresenter.editGlassRequest(glassPrepared);
        System.out.println("добавляю новое стекло в базу");

    }


    @Override
    public void fillListView(String view, ObservableList list) {
        ListView listView = initListView(view);
        listView.setItems(list);

    }

    @Override
    public void selectFirstElement(String view) {
        ListView listView = initListView(view);
        listView.getSelectionModel().selectFirst();
    }

    @Override
    public int getSelectedListElement(String view) {
        ListView listView = initListView(view);
        return listView.getSelectionModel().getSelectedIndex();
    }

    @Override
    public void setLabel(String label, String text) {
        Label labelView = initLabel(label);
        labelView.setText(text);
    }


    @Override
    public void fillTable(ObservableList<GlassObject> glassObjects) {
        tblGoodsInStock.setItems(glassObjects);
    }

    @Override
    public void openAddNewGlassView(int id) {
        String title = selectCarPresenter.getSelectedCarInfo();
        mainApp.initAddGlassLayout(title);
        addGlassController.setState(AddGlassController.State.ADD);
        addGlassController.setCarId(id);
    }


    @Override
    public void openNewOrderView(List<GlassObject> cart) {
        mainApp.initOrderConfirmLayout(cart);
    }

    private Label initLabel(String labelName) {
        switch (labelName) {
            case Resources.TARGET_USER:
                return nameLabel;
            case Resources.TARGET_SALONS:
                return salonLabel;
        }
        return null;
    }

    private ListView initListView(String view) {
        switch (view) {
            case Resources.TARGET_MARK:
                return markListView;
            case Resources.TARGET_MODEL:
                return modelListView;
            case Resources.TARGET_GENERATION:
                return genListView;
            case Resources.TARGET_USER:
                return employeesListView;
        }
        return null;
    }

    public void fillModelsListView(MouseEvent mouseEvent) {
        selectCarPresenter.getModels();
    }

    public void fillGenerationsListView(MouseEvent mouseEvent) {
        selectCarPresenter.getGenerations();
    }

    @Override
    public void showError(String msg) {
        AlertWindow.errorMessage(msg);
    }

    @Override
    public void showInfo(String msg) {
        AlertWindow.infoMessage(msg);
    }

    @Override
    public int getSelectedTableItem() {
        return tblGoodsInStock.getSelectionModel().getFocusedIndex();
    }

    @Override
    public void openEditGlassView(int glassOptId, int glassTypeId, int glassFactoryID, int glassBodyTypeId, String desc, String price, String priceIns, String alert, String pricein, int id, int carId) {
        mainApp.initAddGlassLayout("Редактирование парметров стекла");
        addGlassController.setState(AddGlassController.State.EDIT);
        addGlassController.setCarId(carId);
        addGlassController.updGlass(glassOptId, glassTypeId, glassFactoryID, glassBodyTypeId,
                desc, price, priceIns, alert, pricein, id);
    }

    @Override
    public boolean showConfirmationDialog(String s) {
        return AlertWindow.confirmationWindow("Вы уверены?", s);

    }

}
