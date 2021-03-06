package ru.glassexpress.core.get_command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.Composite;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_chain.RequestController;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservedCommand {
    // объект-контейнер с сервера в виде коллекции c закоголовком типа объекта
    protected Composite serverObject;

    // содержимое контейнера
    protected List<BaseObject> components;
    // даанные, которые заносятсяв колонку таблицы
    protected ArrayList<BaseObject> resultString;
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

    protected String key;

    public ObservedCommand(BaseObject inputObject,String key) {
        this.inputObject=inputObject;
        this.key=key;
        buildRequest();
    }

    // возвращаем список строк
    public ObservableList<BaseObject> returnRecievedList() {
        // отправляем запрос, получаем контейнер
        receiveObj();
        if (serverObject != null) {
            // получаем содержимое контейнера
            components = serverObject.getComponents();
//            resultString = new ArrayList<>();
//            for (int i = 0; i < components.size(); i++) {
//                // подготавливаем колонку, для переноса в обсерведлист
//                fillObject(i);
//            }
            // заполняем колонку и возварщаем ее
            return FXCollections.observableArrayList(components);
        }
        return null;
    }

    public String rawResponse(){
        return  RequestController.recieveResponse(request);
    }


    public abstract void buildRequest();

      void receiveObj(){
        serverObject = (Composite) RequestController.responseToObject(RequestController.recieveResponse(request));
    }

    // подготавливаем колонку, для переноса в обсерведлист
//    abstract void fillObject(int i);

}
