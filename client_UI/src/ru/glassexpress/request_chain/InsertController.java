package ru.glassexpress.request_chain;


import ru.glassexpress.Prefs;
import ru.glassexpress.request_builder.Request;

abstract class InsertController {


    void insert(Request request){
        if (RequestController.isRequestAccepted(RequestController.recieveResponse(request))) {
            System.out.println(request.getTarget() + " успешно добавлена в базу");
            execute();
        } else {
            System.out.println("Фиаско! не добавлено");
        }
    }

    protected abstract void execute();

}


class MarkInsert extends InsertController{

    @Override
    protected void execute() {
        //showMarks();
    }
}