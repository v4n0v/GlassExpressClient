package ru.glassexpress.core.edit_content_command.deleteCommand;

public class DeleteOperator {
    private final String key;
    DeleteElementCommand comand;

    public DeleteOperator(String key) {
        this.key=key;
    }

    public boolean deleteGenerationIsComplete(int id) {
        comand=new DeleteGenerationCommand(id);
        return comand.deleteElement();
    }

    public boolean deleteModelIsComplete(int id) {
        comand=new DeleteModelCommand(id);
        return comand.deleteElement();
    }

    public boolean deleteMarkIsComplete(int id) {
        comand=new DeleteMarkCommand(id);
        return comand.deleteElement();
    }


    public boolean deleteGlassIsComplete(int id) {
        comand=new DeleteGlassCommand(id);
        return comand.deleteElement();
    }
}
