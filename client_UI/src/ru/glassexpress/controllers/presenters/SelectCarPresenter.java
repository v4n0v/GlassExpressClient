package ru.glassexpress.controllers.presenters;

import ru.glassexpress.controllers.views.MainView;
import ru.glassexpress.core.GetListOperator;
import ru.glassexpress.core.JsonController;
import ru.glassexpress.core.StringValidator;
import ru.glassexpress.core.URLConnection;
import ru.glassexpress.core.data.DataMap;
import ru.glassexpress.core.data.Log2File;
import ru.glassexpress.core.edit_content_command.addCommand.AddOperator;
import ru.glassexpress.core.edit_content_command.deleteCommand.DeleteOperator;
import ru.glassexpress.core.edit_content_command.updCommand.UpdateOperator;
import ru.glassexpress.core.get_command.adapter.BaseObjectAdapter;
import ru.glassexpress.core.objects.GenerationObj;
import ru.glassexpress.core.objects.IdTitleObj;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.core.utils.ObservableListAdapter;
import ru.glassexpress.library.Resources;

import java.util.logging.Level;

public class SelectCarPresenter {
    private MainView view;
    private URLConnection urlConnection;
    private JsonController jsonController;
    private ObservableListAdapter observableListAdapter;
    // класс авто, заполняющийся после конструктора
    // Car car;
    private BaseObjectAdapter adapter;
    private DataMap dataMap;
    private GetListOperator getListOperator;
    private UserObject user;
    private AddOperator addOperator;
    private DeleteOperator deleteOperator;
    private UpdateOperator updateOperator;

    public SelectCarPresenter(MainView view) {
        this.view = view;
    }

    public void init() {
        dataMap = DataMap.getInstance();
        observableListAdapter = new ObservableListAdapter();
        urlConnection = URLConnection.getInstance();
        jsonController = JsonController.getInstance();
        adapter = BaseObjectAdapter.getInsance();

        user = dataMap.getUser();

    }


    public void update(){
        getListOperator = dataMap.getGetListOperator();
        deleteOperator = new DeleteOperator(user.getKey());
        addOperator = new AddOperator(user.getKey());
        updateOperator = new UpdateOperator(user.getKey());
        view.fillListView(Resources.TARGET_USER, dataMap.getCurrentEmployeesList());

    }

    public void getMarks() {
        Log2File.writeLog("Запрос марок авто");
        dataMap.setCarMarksList(getListOperator.getMarks());
        view.fillListView(Resources.TARGET_MARK, observableListAdapter.asObservableList(dataMap.getCarMarksList()));
        if (dataMap.getCarMarksList() != null && dataMap.getCarMarksList().size() > 0) {
            view.selectFirstElement(Resources.TARGET_MARK);
            // формируется запрос на получение списка моделей
            Log2File.writeLog("Список марок получен. Кол-во = " + dataMap.getCarMarksList().size());
            getModels();
        } else {
            Log2File.writeLog("Список марок авто пуст");
        }
    }

    public void getModels() {
        Log2File.writeLog("Запрос моделей авто");
        int index = view.getSelectedListElement(Resources.TARGET_MARK);
        dataMap.setCarModelsList(getListOperator.getModels(dataMap.getCarMarksList().get(index)));
        view.fillListView(Resources.TARGET_MODEL, observableListAdapter.asObservableList(dataMap.getCarModelsList()));
        if (dataMap.getCarModelsList() != null && dataMap.getCarModelsList().size() > 0) {
            view.selectFirstElement(Resources.TARGET_MODEL);
            //   System.out.println("показать модели марки " + car.getMark());
            Log2File.writeLog("Список моделей получен. Кол-во = " + dataMap.getCarModelsList().size());
            getGenerations();
        } else {
            Log2File.writeLog("Список моделей авто пуст");
        }
    }

    public void getGenerations() {
        Log2File.writeLog("Запрос поколений авто");
        int index = view.getSelectedListElement(Resources.TARGET_MODEL);
        IdTitleObj model = dataMap.getCarModelsList().get(index);
        dataMap.setGenerationObjList(getListOperator.getGenerations(model));
        view.fillListView(Resources.TARGET_GENERATION, observableListAdapter.asObservableList(dataMap.getGenerationObjList()));
        if (dataMap.getGenerationObjList().size() > 0) {
            view.selectFirstElement(Resources.TARGET_GENERATION);
            Log2File.writeLog("Список поколений получен. Кол-во = " + dataMap.getGenerationObjList().size());
        } else {
            Log2File.writeLog("Список поколений авто пуст");
        }
    }

    public void addMark(String mark) {
        Log2File.writeLog("Добавляем марку авто");

        if (addOperator.addMarkIsComplete(mark)) {
            getMarks();
            Log2File.writeLog("Марка авто " + mark + " успешно добавлена");
        } else {
            Log2File.writeLog(Level.WARNING, "Фиаско!" + mark + " не добавлено");
        }
    }

    public void addModel(String model) {
        Log2File.writeLog("Добавляем модель авто");
        IdTitleObj mark = dataMap.getCarMarksList().get(view.getSelectedListElement(Resources.TARGET_MARK));
        if (addOperator.addModelIsComplete(model, mark)) {
            getModels();
            Log2File.writeLog("Модель авто " + mark.getTitle() + " успешно добавлена");
        } else {
            Log2File.writeLog(Level.WARNING, "Фиаско!" + mark.getTitle() + " " + model + " не добавлено");
        }
    }

    public void editMark(String newMark) {
        Log2File.writeLog("Редактируем маркувто");
        int index = view.getSelectedListElement(Resources.TARGET_MARK);
        IdTitleObj mark = dataMap.getCarMarksList().get(index);
        mark.setTitle(newMark);

        if (updateOperator.updateMarkIsComplete(mark)) {
            getMarks();
            Log2File.writeLog("Марка авто " + mark.getTitle() + " успешно обновлена");
        } else {
            Log2File.writeLog(Level.WARNING, "Фиаско!" + mark.getTitle() + " не обновлена");
        }
    }


    public void editModel(String newModel) {
        Log2File.writeLog("Редактируем модель авто");
        IdTitleObj model = dataMap.getCarModelsList().get(view.getSelectedListElement(Resources.TARGET_MODEL));
        model.setTitle(newModel);
        if (updateOperator.updateModelIsComplete(model)) {
            getModels();
            Log2File.writeLog("Модель авто " + model.getTitle() + " " + model + " успешно обновлена");
        } else {
            Log2File.writeLog(Level.WARNING, "Фиаско!" + model.getTitle() + " " + model + " не обновлена");
        }
    }


    public void addGeneration(String answer) {
        Log2File.writeLog("Добавляем поколение авто");
        IdTitleObj model = dataMap.getCarModelsList().get(view.getSelectedListElement(Resources.TARGET_MODEL));
        if (addOperator.addGenerationIsComplete(answer, model)) {
            getGenerations();
            Log2File.writeLog("Поколение авто " + answer + " успешно добавлена");

        } else {
            Log2File.writeLog(Level.WARNING, "Фиаско! Поколение " + answer + " не добавлено");
        }
    }

    public void editGeneration(String newGeneration) {
        if (StringValidator.isYearsInputCorrect(newGeneration) || StringValidator.isSingleYearInputCorrect(newGeneration)) {
            if (StringValidator.isSingleYearInputCorrect(newGeneration)) {
                newGeneration += "-9999";
            }
            // получаем поколение
            GenerationObj gen = dataMap.getGenerationObjList().get(view.getSelectedListElement(Resources.TARGET_GENERATION));
            // парсим введенные годы
            newGeneration = newGeneration.replace(" ", "");
            String[] genYears = newGeneration.split("-");
            // применяем  введенные годы
            gen.setYearFrom(Integer.parseInt(genYears[0]));
            gen.setYearTo(Integer.parseInt(genYears[1]));
            if (updateOperator.editGenerationIsComplete(gen)) {
                getGenerations();
            } else {
                System.out.println("Фиаско! Не отредактированно");
            }
        }
    }

    public void deleteGeneration() {
        int id = dataMap.getGenerationObjList().get(view.getSelectedListElement(Resources.TARGET_GENERATION)).getId();
        if (deleteOperator.deleteGenerationIsComplete(id)) {
            getGenerations();
        } else {
            System.out.println("Фиаско! Не удалено");
        }
    }

    public void deleteModel() {
        int id = dataMap.getCarModelsList().get(view.getSelectedListElement(Resources.TARGET_MODEL)).getId();
        if (deleteOperator.deleteModelIsComplete(id)) {
            getModels();
        } else {
            System.out.println("Фиаско! Не удалено");
        }
    }

    public void deleteMark() {
        int id = dataMap.getCarMarksList().get(view.getSelectedListElement(Resources.TARGET_MARK)).getId();
        if (deleteOperator.deleteMarkIsComplete(id)) {
            getMarks();
        } else {
            System.out.println("Фиаско! Не удалено");
        }
    }

    public void getLists() {
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
                view.setLabel(Resources.TARGET_USER, user.getName() + " " + user.getLastName());
                int salonPos = dataMap.getPosById(dataMap.getSalonsList(), user.getSalonId());
                view.setLabel(Resources.TARGET_SALONS, dataMap.getSalonsList().get(salonPos).getTitle());
            }
        }
    }


    public int getPermission() {
        return user.getPermission();
    }

    public String getSelectedCarInfo() {
        String mark = dataMap.getCarMarksList().get(view.getSelectedListElement(Resources.TARGET_MARK)).getTitle();
        String model = dataMap.getCarMarksList().get(view.getSelectedListElement(Resources.TARGET_MODEL)).getTitle();
        String gen = dataMap.getGenerationObjList().get(view.getSelectedListElement(Resources.TARGET_GENERATION)).toString();
        return mark+" "+model+" "+gen;
    }

    public void initEmployees() {
        view.fillListView(Resources.TARGET_USER, dataMap.getCurrentEmployeesList());
    }
}

