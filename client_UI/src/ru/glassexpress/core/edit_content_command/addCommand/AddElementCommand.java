package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_chain.RequestController;

public abstract class AddElementCommand {


    protected String answer;
    BaseObject baseObject;
    protected Request request;

    protected String key;
    public AddElementCommand(String answer, BaseObject baseObject, String key) {
        this.baseObject=baseObject;
        this.answer = answer;
        this.key=key;
        prepareRequest();
    }

    protected abstract void prepareRequest();


    boolean addElement (String answer) {
        System.out.println("Добавляем элемент");
        if (RequestController.isRequestAccepted(RequestController.recieveResponse(request)))
            return true;

        return false;

    }



}
