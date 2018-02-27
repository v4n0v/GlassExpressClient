package ru.glassexpress.core.get_command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.objects.BaseObject;

import java.util.ArrayList;

public abstract class ObservedGetTableCommand extends ObservedCommand {
    public ObservedGetTableCommand(BaseObject inputObject) {
        super(inputObject);
    }


    public ObservableList<String> returnList() {
        // отправляем запрос, получаем контейнер
        recieveObj();
        if (serverObject != null) {
            // получаем содержимое контейнера
            components = serverObject.getComponents();
            resultString = new ArrayList<>();
            for (int i = 0; i < components.size(); i++) {
                // подготавливаем колонку, для переноса в обсерведлист
                fillObject(i);
            }
            // заполняем колонку и возварщаем ее
            return FXCollections.observableArrayList(resultString);
        }
        return null;
    }


}
