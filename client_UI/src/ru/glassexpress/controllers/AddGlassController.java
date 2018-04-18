package ru.glassexpress.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.glassexpress.controllers.presenters.AddGlassPresenter;
import ru.glassexpress.controllers.views.AddGlassView;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.library.AlertWindow;

public class AddGlassController extends BaseController implements AddGlassView {

    public enum State {ADD, EDIT}

    public void setState(State state) {
        this.state = state;
    }

    State state = State.ADD;

    @FXML
    RadioButton glueRB;
    @FXML
    RadioButton rubberRB;
    @FXML
    TextField descriptionTextField;
    @FXML
    TextField priceInTextField;
    @FXML
    TextField priceTextField;
    @FXML
    TextField insertPriceTextField;
    @FXML
    TextField alertTextField;
    @FXML
    ComboBox<String> glassOptComboBox;
    @FXML
    ComboBox<String> glassFactoryComboBox;
    @FXML
    ComboBox<String> glassTypeComboBox;
    @FXML
    ComboBox<String> glassBodyComboBox;
    @FXML
    Button applyBtn;

    private int carId;
    private int carIndex;
    private int glassId;


    public AddGlassController() {
    }


    @FXML
    private MenuButton menuBTN;

    private AddGlassPresenter presenter;

    @Override
    public void init() {

        initButton();
        Log2File.writeLog("Инициализация окна добавления новго стекла");
        presenter = new AddGlassPresenter(this);
        presenter.init();

    }

    public void setCarId(int carId) {
        presenter.setCarId(carId);
    }

    void initButton() {
        if (state == State.ADD) {
            applyBtn.setText("Добавить");
        } else if (state == State.EDIT) {
            applyBtn.setText("Редактировать");
        }
    }


    public void updGlass(int glassOptId, int glassTypeId, int glassFactoryId, int glassBodyId
            , String decription, String price, String insPrice, String alert, String priceIn, int glassId) {
        initButton();
        this.glassId = glassId;

        glassBodyComboBox.getSelectionModel().select(presenter.getBodyTypePosition(glassBodyId));
        glassFactoryComboBox.getSelectionModel().select(presenter.getFactoryPosition(glassFactoryId));
        glassTypeComboBox.getSelectionModel().select(presenter.getTypePosition(glassTypeId));
        glassOptComboBox.getSelectionModel().select(presenter.getOptPosition(glassOptId));

        descriptionTextField.setText(decription);

        priceTextField.setText(price);
        priceInTextField.setText(insPrice);
        alertTextField.setText(alert);
        priceInTextField.setText(priceIn);

    }

    public void addGlass() {
        initButton();
        presenter.addGlass();
    }

    public void closeModal() {
        close();
    }
    // добавление опций (+)
    public void addNewBodyType() {
        String answer = AlertWindow.dialogWindow("Добавить тип кузова", "Введите название кузова авто ");
        if (answer != null && !answer.equals("")) {
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить новый тип " + answer + " в базу?");
            if (isTrue) {
                if (!answer.equals("")) {
                    presenter.addNewBodyType(answer);
                }
            }
        } else {
            showError("Полее ввода не заполнено!");
        }
    }

    public void addNewFactory() {
        String answer = AlertWindow.dialogWindow("Добавить поставщика", "Введите название завода");
        if (answer != null && !answer.equals("")) {
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить новый завод " + answer + " в базу?");
            if (isTrue) {
                if (!answer.equals("")) {

                }
            }
        } else {
            showError("Полее ввода не заполнено!");
        }
    }

    public void addNewGlassType() {
        String answer = AlertWindow.dialogWindow("Добавить тип стекла", "Введите название типа стекла");
        if (answer != null && !answer.equals("")) {
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить новый тип стекла " + answer + " в базу?");
            if (isTrue) {
                if (!answer.equals("")) {

                }
            }
        } else {
            showError("Полее ввода не заполнено!");
        }
    }

    public void addNewGlassOption() {
        String answer = AlertWindow.dialogWindow("Добавить свойство стекла", "Введите название свойства стекла");
        if (answer != null && !answer.equals("")) {
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить новое свойство стекла " + answer + " в базу?");
            if (isTrue) {
                if (!answer.equals("")) {

                }
            }
        } else {
            showError("Полее ввода не заполнено!");
        }
    }

    // заполнение
    @Override
    public void fillGlassOptComboBox(ObservableList listt) {
        glassOptComboBox.setItems(listt);
        glassOptComboBox.getSelectionModel().select(0);
    }

    @Override
    public void fillGlassFactoryComboBox(ObservableList listt) {
        glassFactoryComboBox.setItems(listt);
        glassFactoryComboBox.getSelectionModel().select(0);
    }

    @Override
    public void fillGlassTypeComboBox(ObservableList listt) {
        glassTypeComboBox.setItems(listt);
        glassTypeComboBox.getSelectionModel().select(0);
    }

    @Override
    public void fillGlassBodyComboBox(ObservableList listt) {
        glassBodyComboBox.setItems(listt);
        glassBodyComboBox.getSelectionModel().select(0);
    }

    @Override
    public void setDefaultPriceComboBox(String price) {
        insertPriceTextField.setText(price);
    }

    @Override
    public void fillMultipleOptionsList(ObservableList<CheckMenuItem> optMultipleList) {
        menuBTN.getItems().addAll(optMultipleList);
    }

    @Override
    public void showError(String msg) {
        AlertWindow.errorMessage(msg);
    }

    @Override
    public void closeView() {
        close();
    }

    @Override
    public String getDescription() {
        return descriptionTextField.getText();
    }

    @Override
    public String getPriceIn() {
        return priceInTextField.getText();
    }

    @Override
    public String getPrice() {
        return priceTextField.getText();
    }

    @Override
    public String getAlert() {
        return alertTextField.getText();
    }

    @Override
    public String getInsertPrice() {
        return insertPriceTextField.getText();
    }

    @Override
    public int getGlassOptIndex() {
        return glassOptComboBox.getSelectionModel().getSelectedIndex();
    }

    @Override
    public int getGlassFactoryIndex() {
        return glassFactoryComboBox.getSelectionModel().getSelectedIndex();
    }

    @Override
    public int getGlassTypeIndex() {
        return glassTypeComboBox.getSelectionModel().getSelectedIndex();
    }

    @Override
    public int getBodyTypeIndex() {
        return glassBodyComboBox.getSelectionModel().getSelectedIndex();
    }

    @Override
    public boolean isGlueRadioButtonSelected() {
        return glueRB.isSelected();
    }

    @Override
    public boolean isRubberRadioButtonSelected() {
        return rubberRB.isSelected();
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void insertGlass(GlassObject glassPrepared) {
        mainController.insertGlass(glassPrepared);
    }

    @Override
    public void editGlass(GlassObject glassPrepared) {
        mainController.updGlass(glassPrepared);
    }

    @Override
    public int getGlassId() {
        return glassId;
    }

}
