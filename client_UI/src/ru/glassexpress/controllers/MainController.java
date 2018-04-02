package ru.glassexpress.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;
import ru.glassexpress.core.JsonController;
import ru.glassexpress.core.StringValidator;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.utils.ObservableListAdapter;
import ru.glassexpress.library.Resources;
import ru.glassexpress.core.URLConnection;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.edit_content_command.addCommand.AddOperator;
import ru.glassexpress.core.edit_content_command.deleteCommand.DeleteOperator;

import ru.glassexpress.core.edit_content_command.updCommand.UpdateOperator;
import ru.glassexpress.core.get_command.ObservedCommand;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.core.objects.*;
import ru.glassexpress.core.objects.builders.GlassBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class MainController extends BaseController {


    public Label nameLabel;
    public Label salonLabel;
    public Button createOrderButton;
    public ListView  employeesListView;
    public Button editGenerationButton;
    public Button editModelButton;
    public Button editMarkButton;
    public Button addGlassButton;
    @FXML
    ComboBox<String> bodyTypeListView;

    @FXML
    ComboBox<String> insertClassComboBox;

    @FXML
    Button addGenerationButton;
    @FXML
    Button addModelButton;

    @FXML
    ListView<String> modelListView;

    @FXML
    ListView<IdTitleObj> markListView;

    @FXML
    ListView<String> genListView;

    @FXML
    Button addMarkButton;

    @FXML
    ToggleGroup radioGroup;

    @FXML
    RadioButton isFrontRadio;
    @FXML
    RadioButton isRearRadio;

//    public UserObject getUser() {
//        return user;
//    }


    private UserObject user;

    public GetListOperator getGetListOperator() {
        return getListOperator;
    }

    private GetListOperator getListOperator;

    public AddOperator getAddOperator() {
        return addOperator;
    }

    private AddOperator addOperator;
    private  DeleteOperator deleteOperator;
    private  UpdateOperator updateOperator;
    private URLConnection urlConnection;
    private JsonController jsonController;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    MenuController menuController;
    ObservedCommand command;

    private ObservableList<String> marksList = FXCollections.observableArrayList();

    private ObservableList<String> modelsList = FXCollections.observableArrayList();
    private ObservableList<String> genList = FXCollections.observableArrayList();

    private ObservableList<String> classList = FXCollections.observableArrayList();
    private Stage loginStage;

    public ObservableList<String> getBodyTypeStringList() {
        return bodyTypeStringList;
    }

    private ObservableList<String> bodyTypeStringList = FXCollections.observableArrayList();

    private AddGlassController addGlassController;
    @FXML
    Button delGenerationButton;
    @FXML
    Button delModelButton;
    @FXML
    Button delMarkButton;
    //public Car getCar() {
//        return car;
//    }

    ObservableListAdapter observableListAdapter;
    // класс авто, заполняющийся после конструктора
    // Car car;
    BaseObjectAdapter adapter;


    // инициализация конроллера, вызывается при открытии приложения
    @Override
    public void init() {
        Log2File.writeLog("Инициализация главного окна");

        user = new UserObject();
        observableListAdapter=new ObservableListAdapter();
        urlConnection = URLConnection.getInstance();
        jsonController = JsonController.getInstance();
        // car = new Car();
        adapter =  BaseObjectAdapter.getInsance();

        radioGroup = new ToggleGroup();
        isFrontRadio.setToggleGroup(radioGroup);
        isRearRadio.setToggleGroup(radioGroup);

    }


    public void reconnect() {
        initTGTable();
        //getListOperator = new GetListOperator(user.getKey());
        deleteOperator = new DeleteOperator(user.getKey());
        addOperator = new AddOperator(user.getKey());
        updateOperator = new UpdateOperator(user.getKey());
        Log2File.writeLog("reconnect");

        insertClassComboBox.setItems(classList);


        fillMarksListView();
        // если получили список марок, то тянем остальные параметры и модели
        if (dataMap.getCarMarksList() != null) {
            dataMap.setGlassTypeList(getListOperator.getGlassTypes());
            if (dataMap.getGlassTypeList() != null) {
                dataMap.setBodyTypeList(getListOperator.getBodyTypes());
                dataMap.setGlassOptList(getListOperator.getGlassOptions());
                dataMap.setGlassFactoryList(getListOperator.getGlassFactory());
                dataMap.setSalonsList(getListOperator.getSalons());
                dataMap.setPositionsList(getListOperator.getPositions());
                dataMap.setPermissionsList(getListOperator.getPermissions());
                dataMap.setServices(getListOperator.getServices());
                bodyTypeListView.setItems(observableListAdapter.asObservableList(dataMap.getBodyTypeList()));

               // fillObservableList(bodyTypeStringList, adapter.idTitleObjToString(dataMap.getBodyTypeList()));
                nameLabel.setText(user.getName() + " " + user.getLastName());
                int salonPos = dataMap.getPosById(dataMap.getSalonsList(), user.getSalonId());
                salonLabel.setText(dataMap.getSalonsList().get(salonPos).getTitle());
            }
        }

        initPermission();
    }

    public Button editGlassButton;
    public Button deleteGlassButton;

    public void initPermission() {
        employeesListView.setItems(dataMap.getCurrentEmployeesList());
        boolean isDisabled;
        boolean isVisible;
        if (user.getPermission()!= 1) {
            isDisabled = true;
            isVisible=false;
        } else {
            isDisabled = false;
            isVisible=true;
        }
        if (user.getPermission()== 3) {
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

    // получаем список для конкретной выбранныей машины
    private void initInsertClass() {
        // узнаем id выбранной машины
        if (dataMap.getGenerationObjList() != null && dataMap.getGenerationObjList().size() > 0) {
            int id = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getIdInsert();
            // получаем список всех классов цен
            List<InsertClass> list = dataMap.getInsertClassList();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    if (dataMap.getInsertClassList().get(i).getId() == id) {
                        insertClassComboBox.getSelectionModel().select(i);
                    }
                }
            }
        }
    }

    private void fillInsertClassList(List<InsertClass> insertClassList) {
        classList.clear();
        for (int i = 0; i < insertClassList.size(); i++) {
            classList.addAll("Класс " + (i + 1) + "-" + insertClassList.get(i).getInsertFront());

        }
    }

    // получаем данные с сервера и заполняем ListView марок автомобилей
    private void fillMarksListView() {
        Log2File.writeLog("Запрос марок авто");
        dataMap.setCarMarksList(getListOperator.getMarks());

        markListView.setItems(observableListAdapter.asObservableList(dataMap.getCarMarksList()));

        // fillObservableList(marksList, adapter.idTitleObjToString(dataMap.getCarMarksList()));
        if (dataMap.getCarMarksList() != null && dataMap.getCarMarksList().size() > 0) {
            markListView.getSelectionModel().selectFirst();

            // формируется запрос на получение списка моделей
//            System.out.println(dataMap.getCarModelsList().size());
            Log2File.writeLog("Список марок получен. Кол-во = " + dataMap.getCarMarksList().size());
            fillModelsListView();
        } else {
            Log2File.writeLog("Список марок авто пуст");
        }
    }


    // получаем данные с сервера и заполняем ListView моделей автомобилей, выбранной марки
    public void fillModelsListView() {
        Log2File.writeLog("Запрос моделей авто");
        // car.setMark(markListView.getSelectionModel().getSelectedItem());
        markListView.getSelectionModel().getSelectedIndex();

        dataMap.setCarModelsList(getListOperator.getModels(dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex())));
        modelListView.setItems(observableListAdapter.asObservableList(dataMap.getCarModelsList()));

       // fillObservableList(modelsList, adapter.idTitleObjToString(dataMap.getCarModelsList()));
        if (dataMap.getCarModelsList() != null && dataMap.getCarModelsList().size() > 0) {
            modelListView.getSelectionModel().selectFirst();
            //   System.out.println("показать модели марки " + car.getMark());
            Log2File.writeLog("Список моделей получен. Кол-во = " + dataMap.getCarModelsList().size());
            fillGenerationsListView();
        } else {
            Log2File.writeLog("Список моделей авто пуст");
        }
    }

//    private List<BaseObject> currentModelGenerations = new ArrayList<>();

    public void fillGenerationsListView() {
        Log2File.writeLog("Запрос поколений авто");


        IdTitleObj model = dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex());
        dataMap.setGenerationObjList(getListOperator.getGenerations(model));

        genListView.setItems(observableListAdapter.asObservableList(dataMap.getGenerationObjList()));
//        fillObservableList(genList, adapter.generationObjToString(dataMap.getGenerationObjList()));
        if (dataMap.getGenerationObjList().size() > 0) {
            genListView.getSelectionModel().selectFirst();
            // получаем список классов цен
            //dataMap.setInsertClassList(getListOperator.getInsertClass());
            Log2File.writeLog("Список поколений получен. Кол-во = " + dataMap.getGenerationObjList().size());
//            // заполняем ObservableList
//            fillInsertClassList(dataMap.getInsertClassList());
//            // получаем список для конкретной выбранныей машины
//            initInsertClass();
//            // заполянем лейблы с конкретными ценами
//            initPriceClassLabels();
            showGoods();
        } else {
            Log2File.writeLog("Список поколений авто пуст");
        }
    }


    // обновляем данные ObservableList, новыми, прилетевшими с сервера
    public void fillObservableList(ObservableList<String> list, List<String> source) {
        list.clear();
        if (source != null)
            list.addAll(source);

    }


    void addMark(String mark) {
        Log2File.writeLog("Добавляем марку авто");

        if (addOperator.addMarkIsComplete(mark)) {
            fillMarksListView();
            Log2File.writeLog("Марка авто " + mark + " успешно добавлена");
        } else {

            Log2File.writeLog(Level.WARNING, "Фиаско!" + mark + " не добавлено");

        }

    }


    void addModel(String model) {
        Log2File.writeLog("Добавляем модель авто");

        if (addOperator.addModelIsComplete(model, getSelectedMarkObj())) {
            fillModelsListView();
            Log2File.writeLog("Модель авто " + getSelectedMarkObj().getTitle() + " успешно добавлена");
        } else {
            Log2File.writeLog(Level.WARNING, "Фиаско!" + getSelectedMarkObj().getTitle() + " " + model + " не добавлено");
        }


    }

    private void editMark(IdTitleObj model){
        Log2File.writeLog("Редактируем маркувто");

        if (updateOperator.updateMarkIsComplete(model)) {
            fillMarksListView();
            Log2File.writeLog("Марка авто " + getSelectedMarkObj().getTitle() + " успешно обновлена");
        } else {
            Log2File.writeLog(Level.WARNING, "Фиаско!" + getSelectedMarkObj().getTitle() +  " не обновлена");
        }
    }



    private void editModel(IdTitleObj model){
        Log2File.writeLog("Редактируем модель авто");

        if (updateOperator.updateModelIsComplete(model)) {
            fillModelsListView();
            Log2File.writeLog("Модель авто " + getSelectedMarkObj().getTitle() + " "+getSelectedModelTitle()+ " успешно обновлена");
        } else {
            Log2File.writeLog(Level.WARNING, "Фиаско!" + getSelectedMarkObj().getTitle() + " " + model + " не обновлена");
        }
    }

    private void addGeneration(String answer) {
        Log2File.writeLog("Добавляем поколение авто");
        if (addOperator.addGenerationIsComplete(answer, getSelectedModelObj())) {
            fillGenerationsListView();
            Log2File.writeLog("Поколение авто " + getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + answer + " успешно добавлена");

        } else {
            Log2File.writeLog(Level.WARNING, "Фиаско!" + getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + answer + " не добавлено");
        }
    }
    private void editGeneration(GenerationObj obj) {
        if (updateOperator.editGenerationIsComplete(obj)) {
            fillGenerationsListView();
        } else {
            System.out.println("Фиаско! Не отредактированно");
        }
    }

    private void deleteGeneration(int id) {
        if (deleteOperator.deleteGenerationIsComplete(id)) {
            fillGenerationsListView();
        } else {
            System.out.println("Фиаско! Не удалено");
        }
    }

    private void deleteModel(int id) {
        if (deleteOperator.deleteModelIsComplete(id)) {
            fillModelsListView();
        } else {
            System.out.println("Фиаско! Не удалено");
        }
    }

    private void deleteMark(int id) {
        if (deleteOperator.deleteMarkIsComplete(id)) {
            fillMarksListView();
        } else {
            System.out.println("Фиаско! Не удалено");
        }
    }

    // обработка нажатия кнопки (добавить модель)
    public void showAddTitleModal(ActionEvent keyEvent) {

        if ((Button) keyEvent.getSource() == addMarkButton) {

            String answer = AlertWindow.dialogWindow("Добавить новую марку авто", "Выыедите марку авто");
            if (answer != null && !answer.equals("")) {
                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить марку " + answer + " в базу?");
                if (isTrue) {
                    if (!answer.equals("")) {
                        addMark(answer);
                        System.out.println("добавить марку авто " + answer);
                    }
                }
            } else {
                AlertWindow.errorMessage("Полее ввода не заполнено!");
            }
        } else if ((Button) keyEvent.getSource() == editMarkButton) {

            String answer = AlertWindow.dialogWindow("Редактировать марку авто", "Введите марку " );
            if (answer != null && !answer.equals("")) {

                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Изменить марку на " +  answer);
                if (isTrue) {
                    if (!answer.equals("")) {
                        int index = markListView.getSelectionModel().getSelectedIndex();
                        IdTitleObj model = dataMap.getCarMarksList().get(index);
                        model.setTitle(answer);
                        editMark(model);

                    }
                }
            } else {
                AlertWindow.errorMessage("Полее ввода не заполнено!");
            }
        } else if ((Button) keyEvent.getSource() == addModelButton) {

            String answer = AlertWindow.dialogWindow("Добавить новую модель авто", "Введите модель марки " + getSelectedMarkTitle());
            if (answer != null && !answer.equals("")) {
                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить " + getSelectedMarkTitle() + " " + answer + " в базу?");
                if (isTrue) {
                    if (!answer.equals("")) {
                        addModel(answer);

                    }
                }
            } else {
                AlertWindow.errorMessage("Полее ввода не заполнено!");
            }
        } else if ((Button) keyEvent.getSource() == editModelButton) {

            String answer = AlertWindow.dialogWindow("Редактировать модель авто", "Введите модель марки " + getSelectedMarkTitle());
            if (answer != null && !answer.equals("")) {

                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Изменить модель на " + getSelectedMarkTitle() + " " + answer);
                if (isTrue) {
                    if (!answer.equals("")) {
                        int index = modelListView.getSelectionModel().getSelectedIndex();
                        IdTitleObj model = dataMap.getCarModelsList().get(index);
                        model.setTitle(answer);
                        editModel(model);
                        System.out.println("изменить модель авто " + getSelectedMarkTitle() + " ");
                    }
                }
            } else {
                AlertWindow.errorMessage("Полее ввода не заполнено!");
            }
        } else if ((Button) keyEvent.getSource() == addGenerationButton) {

            String answer = AlertWindow.dialogWindow("Добавить поколение авто " + getSelectedMarkTitle() + " " + getSelectedModelTitle(), "Введите введите годы в формате (ХХХХ-ХХХХ)\n" +
                    "Если авто все еще выпускается, введите только\nгод начала производства ХХХХ ");
            if (answer != null && !answer.equals("")) {
                boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Добавить поколение " +
                        getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + answer + " в базу?");
                if (isTrue) {
                    if (!answer.equals("")) {
                        if (StringValidator.isYearsInputCorrect(answer) || StringValidator.isSingleYearInputCorrect(answer)) {
                            if (StringValidator.isSingleYearInputCorrect(answer)) {
                                answer += "-9999";
                            }
                            addGeneration(answer);
                            System.out.println("добавить поколение авто " + getSelectedModelTitle() + " " + answer);
                        } else {
                            AlertWindow.errorMessage("Проверьте правильность написания!");
                        }
                    }
                }
            } else {
                AlertWindow.errorMessage("Полее ввода не заполнено!");
            }

        } else if ((Button) keyEvent.getSource() == editGenerationButton) {

            String answer = AlertWindow.dialogWindow("Изменить поколение авто " + getSelectedMarkTitle() + " "
                    + getSelectedModelTitle(), "Введите введите годы в формате (ХХХХ-ХХХХ)\n" +
                    "Если авто все еще выпускается, введите только\nгод начала производства ХХХХ ");
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Проверьте правильность ввода ("+answer+")");
            if (isTrue) {
                if (!answer.equals("")) {
                    if (StringValidator.isYearsInputCorrect(answer) || StringValidator.isSingleYearInputCorrect(answer)) {
                        if (StringValidator.isSingleYearInputCorrect(answer)) {
                            answer += "-9999";
                        }
                        // получаем поколение
                        GenerationObj gen = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex());
                        // парсим введенные годы
                        answer = answer.replace(" ", "");
                        String[] genYears = answer.split("-");
                        // применяем  введенные годы
                        gen.setYearFrom(Integer.parseInt(genYears[0]));
                        gen.setYearTo(Integer.parseInt(genYears[1]));
                        editGeneration(gen);
                        System.out.println("редактирование поколение авто " + getSelectedModelTitle());
                    }
                }
            }


        } else if ((Button) keyEvent.getSource() == delGenerationButton) {

            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить поколение " + getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + " из базы?");
            if (isTrue) {
                int id = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId();
                deleteGeneration(id);
                System.out.println("удалить поколение авто " + getSelectedModelTitle());
            }


        } else if ((Button) keyEvent.getSource() == delModelButton) {
            System.out.println("удаляем модель " + getSelectedModelTitle());
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить модель " + getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + " из базы?");
            if (isTrue) {
                int id = dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex()).getId();
                dataMap.getCarModelsList().clear();
                deleteModel(id);
                System.out.println("удалить модель авто " + getSelectedMarkTitle() + " " + getSelectedModelTitle());
            }
        } else if ((Button) keyEvent.getSource() == delMarkButton) {
            System.out.println("удаляем марку " + getSelectedMarkTitle());
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить марку " + getSelectedMarkTitle() + " " + " из базы?");
            if (isTrue) {
                int id = dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex()).getId();
                deleteMark(id);
                System.out.println("удалить модель авто " + getSelectedMarkTitle());
            }
        } else if ((Button) keyEvent.getSource() == editMarkButton) {
            System.out.println("Редактируем марку " + getSelectedModelTitle());
            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить модель " + getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + " из базы?");
            if (isTrue) {
                int id = dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex()).getId();
                dataMap.getCarModelsList().clear();
                deleteModel(id);
                System.out.println("удалить модель авто " + getSelectedMarkTitle() + " " + getSelectedModelTitle());
            }


        } else if ((Button) keyEvent.getSource() == editModelButton) {





        } else {
            System.out.println("Такую кнопку не умею");
        }
    }




    public void setGeneration() {
        //   car.setGen((GenerationObj) dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()));
    }


    // показываем список товаров
    public void showGoods() {

        List<GenerationObj> generationObjList = dataMap.getGenerationObjList();
        glassObjects.clear();
        if (generationObjList != null && generationObjList.size() > 0) {

            //   List<GlassObject> glasses=getListOperator.getTableGoods(getSelectedCarObj());
            dataMap.setGlassList(getListOperator.getTableGoods(getSelectedCarObj()));

            GlassObject glass = new GlassBuilder()
                    .setCarTitle("")
                    .setDescription("Стекло клиента")
                    .setInsertPrice(Resources.DEFAULT_INSERT_PRICE + 500)
                    .build();

            dataMap.getGlassList().add(glass);
            // заполняем поле свойства стекол


            // заполняем цену установки исходя из класса машины

//          for (int i = 0; i < dataMap.getGlassList().size(); i++) {
//                int inserClassId = getSelectedCarObj().getIdInsert();
//                int glassTypeId = dataMap.getGlassList().get(i).getGlassTypeId();
//                dataMap.getGlassList().get(i).setInsertPrice(dataMap.getInsertClassPriceByGlassType(inserClassId, glassTypeId));
//          }
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
            AlertWindow.errorMessage("Укажите поколение авто");
        }
        System.out.println("показываю подходящий товар");

        //initTGTable();

    }


    void showOptList() {
        String optList;
        // получаю список стекол
        List<GlassObject> list = dataMap.getGlassList();
        // список опций стекла
        List<IdTitleObj> optListIdTitle = dataMap.getGlassOptList();
        List<List<String>> insideList = new ArrayList<>();
        for (GlassObject aList : list) {
            // получаю строку с опциями для каждого стекла
            String s = aList.getOptListString();
            String[] sa = s.split("i");
            StringBuilder s1 = new StringBuilder();

            for (IdTitleObj anOptListIdTitle : optListIdTitle) {
                for (String aSa : sa) {
                    if (anOptListIdTitle.getId() == Integer.parseInt(aSa)) {
                        s1.append(anOptListIdTitle.getTitle()).append("\n");
                    }

                }
            }
            aList.setOptListString(s1.toString());
        }
    }


    // таблица стекол на складе
    private ObservableList<GlassObject> glassObjects = FXCollections.observableArrayList();
    ;
    @FXML
    private TableView<GlassObject> tblGoodsInStock;
    @FXML
    private TableColumn<GlassObject, Integer> colTGId;
    @FXML
    private TableColumn<GlassObject, Float> colTGPrice;
    @FXML
    private TableColumn<GlassObject, String> colTGDesc;
    @FXML
    private TableColumn<GlassObject, String> colTGFactory;
    @FXML
    private TableColumn<GlassObject, String> colTGOption;
    @FXML
    private TableColumn<GlassObject, String> colTGType;
    @FXML
    private TableColumn<GlassObject, Float> colTGInsertPrice;

    @FXML
    private TableColumn<GlassObject, Float> colTGPriceIn;
    @FXML
    private TableColumn<GlassObject, String> colTGBody;

    @FXML
    private TableColumn<GlassObject, Boolean> colTGSelect;
    // инициализация колонок таблицы


    private void initTGTable() {
        // tblGoodsInStock.setEditable(false);


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

        tblGoodsInStock.setItems(glassObjects);
        tblGoodsInStock.setEditable(true);
    }


    // открываем дилоговое окно добавления ного стекла в базу
    public void openAddGlassDialog() {
        // получаем список поколений текущей модели
        List<GenerationObj> generationObjList = dataMap.getGenerationObjList();
        if (generationObjList != null && generationObjList.size() > 0) {
            // открываем окгно
            mainApp.initAddGlassLayout(getSelectedCarInfo());
            addGlassController.setState(AddGlassController.State.ADD);
            //передаем в него параметры авто
            if (dataMap.getGenerationObjList() != null) {
                addGlassController.setCarId(dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId());
            } else
                System.out.println("выбиритее поколение");
        } else {
            AlertWindow.errorMessage("Укажите поколение авто");
        }
    }


    public void setAddGlassController(AddGlassController addGlassController) {
        this.addGlassController = addGlassController;
    }


    // втавляем новое стекло в базу
    public void insertGlass(GlassObject glassTableRow) {
        System.out.println("добавляю новое стекло в базу");
        if (addOperator.addGlassIsComplete(glassTableRow)) {
            //fillGenerationsListView();

            System.out.println("Добавлено в базу, все ок");
            AlertWindow.infoMessage("Добавлено в базу");
            //  dataMap.setGenerationObjList(getListOperator.getGenerations(car));
        } else {
            System.out.println("Фиаско! не добавлено");
        }
    }

    // получаем название марки авто из datamap
    private String getSelectedMarkTitle() {
        if (dataMap.getCarMarksList() != null && dataMap.getCarMarksList().size() > 0) {

            return dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex()).getTitle();
        } else return null;
    }

    // получаем название модели авто из datamap
    private String getSelectedModelTitle() {
        if (dataMap.getCarModelsList() != null && dataMap.getCarModelsList().size() > 0) {
            return dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex()).getTitle();
        } else return null; //getSelectedMarkTitle() + " " +
    }

    // получаем полное название авто из datamap
    private String getSelectedCarInfo() {
        if (dataMap.getGenerationObjList() != null && dataMap.getGenerationObjList().size() > 0) {
            int from = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getYearFrom();
            int to = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getYearTo();

            return getSelectedModelTitle() + " " + from + "-" + to;
        } else {
            return null;
        }
    }

    // получаем id марки авто из datamap
    public Integer getSelectedMarkId() {
        Integer id = null;
        if (dataMap.getCarMarksList() != null && dataMap.getCarMarksList().size() > 0) {
            id = dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex()).getId();

        }

        return id;
    }

    // получаем id модели авто из datamap
    public Integer getSelectedModelId() {
        Integer id = null;
        if (dataMap.getCarModelsList() != null && dataMap.getCarModelsList().size() > 0) {
            id = dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex()).getId();
            //return getSelectedMarkTitle() + " " + model;
        }
        return id;
    }

    // получаем id машины авто из datamap
    public Integer getSelectedCarId() {
        Integer id = null;
        if (dataMap.getGenerationObjList() != null && dataMap.getGenerationObjList().size() > 0) {
            id = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId();

        }
        return id;
    }

    // получаем объект марки авто из datamap
    public IdTitleObj getSelectedMarkObj() {
        return dataMap.getCarMarksList().get(markListView.getSelectionModel().getSelectedIndex());
    }

    // получаем объект модели авто из datamap
    public IdTitleObj getSelectedModelObj() {
        return dataMap.getCarModelsList().get(modelListView.getSelectionModel().getSelectedIndex());
    }

    // получаем объект авто из datamap
    public GenerationObj getSelectedCarObj() {
        return dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex());
    }


    // получаю номер класса установки и вношу его в текущий автомобиль
//    public void saveInsertClass() {
//        // получаю ид класса установки
//        int id = dataMap.getInsertClassList().get(insertClassComboBox.getSelectionModel().getSelectedIndex()).getId();
//        // id авто
//        int carID = getSelectedCarId();
//        boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?",
//                "Изменить класс установки " + getSelectedMarkTitle() + " " + getSelectedModelTitle() + "?\n"
//                        + insertClassComboBox.getSelectionModel().getSelectedItem());
//        if (isTrue) {
//            // если нажата ok, обновляем
//            if (updateOperator.updAutoInsertClass(id, carID, user.getKey())) {
//                // обновил класс установки в объекте в dataMap
//                getSelectedCarObj().setIdInsert(id);
//                // если уже открыты  доступные товары, то предлогает обновить список
//                if (dataMap.getGlassList() != null && dataMap.getGlassList().size() > 0) {
//                    isTrue = AlertWindow.confirmationWindow("Класс установки изменен",
//                            "Обновить данные таблицы товаров?");
//                    if (isTrue) {
//                        showGoods();
//                    }
//                }
//                System.out.println("Класс установки обновлен");
//            } else {
//                System.out.println("Фиаско! не обновлено");
//            }
//        }
//
//
//    }

    @FXML
    Label frontInsertPriceLabel;
    @FXML
    Label rearInsertPriceLabel;
    @FXML
    Label sideInsertPriceLabel;

    //    @FXML
//    GridPane insertPriceGrid;
//
    //
    public void initPriceClassLabels() {
        // получам интекс текущего класс установки
//        int index = insertClassComboBox.getSelectionModel().getSelectedIndex();
////        if (!insertPriceGrid.isVisible()){
////            insertPriceGrid.setVisible(true);
////        }
//        // устанавливаем цены в ячейки
//        frontInsertPriceLabel.setText(String.valueOf(dataMap.getInsertClassList().get(index).getInsertFront()));
//        rearInsertPriceLabel.setText(String.valueOf(dataMap.getInsertClassList().get(index).getInsertRear()));
//        sideInsertPriceLabel.setText(String.valueOf(dataMap.getInsertClassList().get(index).getInsertSide()));
    }


    // создать новый заказ
    public void openNewOrder(ActionEvent actionEvent) {
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
                mainApp.initOrderConfirmLayout(cart);
          //      AlertWindow.infoMessage("Выбраны N: " + selected);
            } else {
                AlertWindow.errorMessage("Выбирите товар");
            }
        } else {
            AlertWindow.errorMessage("Список товаров пуст");
        }


    }

    public void deleteSelectedGlass(ActionEvent actionEvent) {
        List<GlassObject> glasList = dataMap.getGlassList();
        List<Integer> idToDeleleList = null;
        String selected = "";


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


            boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить выбранные стекла из базы?");
            if (isTrue) {
                if (glasList != null && glasList.size() > 0) {
                    for (GlassObject aGlasList : glasList) {

                        BooleanProperty isSelected = aGlasList.isSelectedProperty();

                        if (isSelected.get()) {
                            if (aGlasList.getId() == 0) {
                                AlertWindow.errorMessage("Стекло клиента можно только установить. Ну или разбить :)");
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
                    AlertWindow.errorMessage("Список товаров пуст");
                }
            }

        } else {
            AlertWindow.errorMessage("Выбирите стекло");
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


    public void delGen(ActionEvent actionEvent) {
        boolean isTrue = AlertWindow.confirmationWindow("Вы уверены?", "Удалить поколение " + getSelectedMarkTitle() + " " + getSelectedModelTitle() + " " + " из базы?");
        if (isTrue) {
            int id = dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId();
            deleteGeneration(id);
            System.out.println("удалить поколение авто " + getSelectedModelTitle());
        }
    }

    public void editGlass(ActionEvent actionEvent) {
        List<GlassObject> glasList = dataMap.getGlassList();
        if (glasList != null && glasList.size() > 0) {

            int pos = tblGoodsInStock.getSelectionModel().getFocusedIndex();
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

                mainApp.initAddGlassLayout(getSelectedCarInfo());
                addGlassController.setState(AddGlassController.State.EDIT);
                if (dataMap.getGenerationObjList() != null) {
                    addGlassController.setCarId(dataMap.getGenerationObjList().get(genListView.getSelectionModel().getSelectedIndex()).getId());
                } else
                    System.out.println("выбиритее поколение");

                addGlassController.updGlass(glassOptId, glassTypeId, glassFactoryID, glassBodyTypeId,
                        desc, price, priceIns, alert, pricein, glass.getId());
            } else {
                AlertWindow.errorMessage("Нельзя отредактирровать стекло клиента");
            }
//            addGlassController.show();
        } else {
            AlertWindow.errorMessage("Список товаров пуст");
        }

    }

    public void updGlass(GlassObject glassPrepared) {
        System.out.println("добавляю новое стекло в базу");
        if (updateOperator.editGlassIsComplete(glassPrepared,user.getKey())) {
            //fillGenerationsListView();
            showGoods();
            System.out.println("Запись обновлена базу, все ок");
            AlertWindow.infoMessage("Добавлено в базу");
            Log2File.writeLog("Запись обновлена id=" + glassPrepared.getId());
            //  dataMap.setGenerationObjList(getListOperator.getGenerations(car));
        } else {
            System.out.println("Фиаско! не добавлено");
            Log2File.writeLog("Запись не обновлена id=" + glassPrepared.getId());
        }
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    public void loginHide() {
        loginStage.close();
    }

    public void setUser(UserObject user) {
        this.user = user;
    }

    public void setOperator(GetListOperator operator) {
        this.getListOperator = operator;
    }

    public UpdateOperator getUpdateOperator() {
        return updateOperator;
    }

    public DeleteOperator getDeleteOperator() {
        return deleteOperator;
    }
}
