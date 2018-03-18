package ru.glassexpress.core.command_request;

abstract class RequestCommand implements CommandRequest{
    Operator operator;

    public RequestCommand(Operator operator) {
        this.operator = operator;
    }
}
