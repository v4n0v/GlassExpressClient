package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.controllers.AddEmpController;
import ru.glassexpress.core.edit_content_command.EditContentCommand;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.DateObject;
import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class AddNewDayCommand extends AddElementCommand {
    public AddNewDayCommand( BaseObject baseObject, String key) {
        super(null, baseObject, key);
    }

    @Override
    protected void prepareRequest() {
        DateObject date =  (DateObject) baseObject;
        request = new RequestBuilder()
                .setMethod(Resources.METHOD_POST)
                .setAction(Resources.ACTION_INSERT)
                .setTarget(Resources.TARGET_DAY)
                .setKey(key)
                .setRequest("date", String.valueOf(date.getDate()))
                .setRequest("employeesJson", date.getEmployeesJson())
                .setRequest("idAdmin", String.valueOf(date.getIdAdmin()))
                .setRequest("id_salon", String.valueOf(date.getSalonId()))
                .build();
        System.out.println(request);
    }
}
