package ru.glassexpress;

import javafx.collections.ObservableList;


// все методы глобального контролера
public interface ClientControllerInterface {
    // получения списоков
    ObservableList<String> getMarks();
    ObservableList<String> getModels();
    ObservableList<String> getGenerations();


    //добавление в базу
    void addMark(String carMark);
    void addModel(String carModel);
    void addGeneration(int from, int to);

    //удаление из базы
    void delModel(String carModel);
    void delGeneration(int from, int to);

    // сигнал к обновлению всех элементов UI
    void refresh();
}
