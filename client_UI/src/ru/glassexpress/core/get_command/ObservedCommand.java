package ru.glassexpress.core.get_command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Composite;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_chain.RequestController;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservedCommand {
    // объект-контейнер с сервера в виде коллекции c закоголовко типа объекта
    protected Composite serverObject;

    // содержимое контейнера
    protected List<BaseObject> components;
    // даанные, которые заносятсяв колонку таблицы
    protected ArrayList<String> resultString;
    // запрос на сервер
    protected Request request;
    // единица содержимого контейнера
    protected BaseObject inputObject;

    public Composite getServerObject() {
        return serverObject;
    }

    public List<BaseObject> getComponents() {
        return components;
    }



    public ObservedCommand(BaseObject inputObject) {
        this.inputObject=inputObject;
        buildRequest();
    }

    // возвращаем список строк
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


    abstract void buildRequest();

      void recieveObj(){
        serverObject = (Composite) RequestController.responseToObject(RequestController.recieveResponse(request));
    }

    // подготавливаем колонку, для переноса в обсерведлист
    abstract void fillObject(int i);

}
