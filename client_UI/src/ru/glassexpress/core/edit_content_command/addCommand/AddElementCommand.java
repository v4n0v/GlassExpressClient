package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.core.edit_content_command.EditContentCommand;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Composite;
import ru.glassexpress.request_builder.Request;
import ru.glassexpress.request_chain.RequestController;

import java.util.List;

public abstract class AddElementCommand {


    protected String answer;
    BaseObject baseObject;
    protected Request request;


    public AddElementCommand(String answer, BaseObject baseObject) {
        this.baseObject=baseObject;
        this.answer = answer;
        prepareRequest();
    }

    protected abstract void prepareRequest();


    boolean addElement (String answer) {
        System.out.println("Добавляем модель");
        if (RequestController.isRequestAccepted(RequestController.recieveResponse(request)))
            return true;

        return false;

    }



}
