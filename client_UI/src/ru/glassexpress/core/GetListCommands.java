package ru.glassexpress.core;

import javafx.collections.ObservableList;
import ru.glassexpress.core.objects.*;

import java.util.List;

public interface GetListCommands {

    //-----------------------
    //АВТО
    //-----------------------
    // получаем список марок
    List<IdTitleObj> getMarks();
    // получаем список моделей
    List<IdTitleObj> getModels(BaseObject object);
    // получаем список поколений текущей модели
    List<GenerationObj> getGenerations(BaseObject object);
    // получаем список кузовов
    List<IdTitleObj> getBodyTypes();

    //-----------------------
    //СТЕКЛО
    //-----------------------
    // получаем список типов стекол
    List<IdTitleObj> getGlassTypes();
    // получаем список торговых точек
    List<IdTitleObj> getSalons();
    // получаем список параметров стекол
    List<IdTitleObj> getGlassOptions();
    // получаем список прозводителей
    List<IdTitleObj> getGlassFactory();
    // получаем список классов установки
    List<InsertClass> getInsertClass();

    //-----------------------
    // пользователи
    //-----------------------
    // получаем пользователя по ключу
    UserObject getUserByKey();
    // получаем список должностей
    List<IdTitleObj> getPositions();
    // получаем список полномочий
    List<IdTitleObj> getPermissions();

    //-----------------------
    // товары и услуги
    //-----------------------
    // получаем список товаров
    ObservableList<GlassObject> getTableGoods(BaseObject object);
    // получаем список услуг
    List<ServiceObject> getServices();

}
