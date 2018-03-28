package ru.glassexpress.core.edit_content_command.updCommand;

import ru.glassexpress.core.objects.*;

public class UpdateOperator {

    private final String key;
    private  UpdateElementCommand comand;

    public UpdateOperator(String key) {
        this.key=key;
    }

//    public boolean updAutoInsertClass(int idClass,int autoId, String key) {
//        comand=new UpdateInsertClassCommand(idClass, autoId, key);
//        return comand.updateElement();
//    }

    public boolean editGlassIsComplete(GlassObject glassPrepared, String key) {
        comand=new UpdateGlassCommand(glassPrepared, key);
        return  comand.updateElement();
    }


    public boolean editGenerationIsComplete(GenerationObj obj) {
        comand=new UpdateGenerationCommand(obj, key);
        return  comand.updateElement();
    }

    public boolean updateModelIsComplete(IdTitleObj model) {
        comand=new UpdateModelCommand(model, key);
        return  comand.updateElement();
    }

    public boolean updateMarkIsComplete(IdTitleObj mark) {
        comand=new UpdateMarkCommand(mark, key);
        return  comand.updateElement();
    }
    public boolean updateLoginComplete(UserObject user) {
        comand=new UpdateUserLogin(user, key);
        return  comand.updateElement();
    }

    public boolean updatePassComplete(UserObject user) {
        comand=new UpdatePassCommand(user, key);
        return  comand.updateElement();
    }
}
