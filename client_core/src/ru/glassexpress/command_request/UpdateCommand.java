package ru.glassexpress.command_request;

class UpdateCommand extends RequestCommand{

    public UpdateCommand(Operator operator) {
        super(operator);
    }

    @Override
    public void execute() {
        operator.update();
    }
}
