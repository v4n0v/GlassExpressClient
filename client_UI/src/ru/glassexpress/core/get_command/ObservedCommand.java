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
    protected Composite serverObject;

    protected List<BaseObject> components;
    protected ArrayList<String> resultString;
    protected Request request;
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

    abstract void buildRequest();

    public ObservableList<String> returnList() {

         serverObject = (Composite) RequestController.responseToObject(RequestController.recieveResponse(request));
        if (serverObject != null) {
            components = serverObject.getComponents();
            resultString = new ArrayList<>();
            for (int i = 0; i < components.size(); i++) {
                fillObject(i);
            }
            return FXCollections.observableArrayList(resultString);
        }
        return null;
    }






    abstract void fillObject(int i);

}
