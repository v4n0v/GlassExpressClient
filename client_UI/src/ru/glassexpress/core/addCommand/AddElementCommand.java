package ru.glassexpress.core.addCommand;

import ru.glassexpress.Prefs;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Car;
import ru.glassexpress.objects.Composite;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_builder.RequestBuilder;
import ru.glassexpress.request_chain.RequestController;

import java.util.List;

public abstract class AddElementCommand {

    Request request;
    String answer;
    BaseObject baseObject;


    protected Composite serverObject;

    protected List<BaseObject> components;

    public AddElementCommand(String answer, BaseObject baseObject) {
        this.baseObject=baseObject;
        this.answer = answer;
        prepareRequest();
    }

    abstract void prepareRequest();

    boolean addElement (String answer) {
        System.out.println("Добавляем модель");
        if (RequestController.isRequestAccepted(RequestController.recieveResponse(request)))
            return true;

        return false;

    }

    public boolean isOk(String answer) {

        serverObject = (Composite) RequestController.responseToObject(RequestController.recieveResponse(request));
        if (serverObject != null) {
            components = serverObject.getComponents();
            if (components.get(0).getObjectClass().equals("ok")){
                return true;
            }
        }
        return false;
    }


}
