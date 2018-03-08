package ru.glassexpress.core.edit_content_command.updCommand;

import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_chain.RequestController;

public abstract class UpdateElementCommand {
    Request request;

    int id;

    public UpdateElementCommand(int id) {
        this.id = id;

    }

    abstract void prepareRequest();

    boolean updateElement() {
        //поготавливаем запрос
        prepareRequest();
        System.out.println("Обновляем элемент");
        if (RequestController.isRequestAccepted(RequestController.recieveResponse(request)))
            return true;
        return false;

    }


}