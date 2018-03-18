package ru.glassexpress.core.command_request;

public class RequestOperator {
    InsertCommand insert;
    DeleteCommand delete;
    SelectCommand select;
    UpdateCommand update;

    public RequestOperator(InsertCommand insert, DeleteCommand delete, SelectCommand select, UpdateCommand update) {
        this.insert = insert;
        this.delete = delete;
        this.select = select;
        this.update = update;
    }

    void insertRequest(){
        insert.execute();
    }
    void deleteRequest(){
        delete.execute();
    }
    void updateRequest(){
        update.execute();
    }
    void selectRequest(){
        select.execute();
    }

}
