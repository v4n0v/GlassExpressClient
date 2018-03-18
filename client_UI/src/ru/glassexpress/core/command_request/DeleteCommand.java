package ru.glassexpress.core.command_request;

class DeleteCommand extends RequestCommand{

    public DeleteCommand(Operator operator) {
        super(operator);
    }

    @Override
    public void execute() {
        operator.delete();
    }
}
