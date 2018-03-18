package ru.glassexpress.core.edit_content_command.deleteCommand;

import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_chain.RequestController;

public abstract class DeleteElementCommand {
    Request request;
    int id;


    public DeleteElementCommand(int id) {
        this.id = id;


    }

    abstract void prepareRequest();

    boolean deleteElement() {
        System.out.println("Готовим запрос");
        prepareRequest();
        System.out.println("Удаляем элемент "+request.getTarget());
        if (RequestController.isRequestAccepted(RequestController.recieveResponse(request)))
            return true;

        return false;

    }


}
