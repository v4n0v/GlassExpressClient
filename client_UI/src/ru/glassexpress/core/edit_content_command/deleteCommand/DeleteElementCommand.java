package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Composite;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_chain.RequestController;

import java.util.List;

public abstract class DeleteElementCommand {
    Request request;
    int id;


    public DeleteElementCommand(int id) {
        this.id = id;

        prepareRequest();
    }

    abstract void prepareRequest();

    boolean deleteElement() {
        System.out.println("Удаляем модель");
        if (RequestController.isRequestAccepted(RequestController.recieveResponse(request)))
            return true;

        return false;

    }


}
