package ru.glassexpress.core.command_request;

class SelectCommand extends RequestCommand{

    public SelectCommand(Operator operator) {
        super(operator);
    }

    @Override
    public void execute() {
        operator.select();
    }
}
