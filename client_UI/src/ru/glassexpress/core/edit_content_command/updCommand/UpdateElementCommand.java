package ru.glassexpress.core.edit_content_command.updCommand;

import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_chain.RequestController;

public abstract class UpdateElementCommand {
    Request request;
BaseObject object;
    int id;
    String key;
    public UpdateElementCommand(BaseObject object, String key) {
//        this.id = id;
        this.object=object;
        this.key=key;
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