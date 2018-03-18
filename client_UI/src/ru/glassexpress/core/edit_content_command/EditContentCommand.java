package ru.glassexpress.core.edit_content_command;

import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.Composite;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_chain.RequestController;

import java.util.List;

public abstract class EditContentCommand {
    protected Request request;

    public EditContentCommand() {

        prepareRequest();
    }

    protected abstract void prepareRequest();



    public boolean isOk(String answer) {

        Composite serverObject = (Composite) RequestController.responseToObject(RequestController.recieveResponse(request));
        if (serverObject != null) {
            List<BaseObject> components = serverObject.getComponents();
            if (components.get(0).getObjectClass().equals("ok")){
                return true;
            }
        }
        return false;
    }


}
