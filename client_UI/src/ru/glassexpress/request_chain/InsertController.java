package ru.glassexpress.request_chain;


import ru.glassexpress.ServerVocabulary;

abstract class InsertController {


    void insert(String target, String body){
        if (RequestController.isRequestAccepted(RequestController.recieveResponse("GET", ServerVocabulary.ACTION_INSERT, target, body))) {
            System.out.println(target + " успешно добавлена в базу");
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