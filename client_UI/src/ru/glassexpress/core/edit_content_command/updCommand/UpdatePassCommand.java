package ru.glassexpress.core.edit_content_command.updCommand;

import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.library.Resources;
import ru.glassexpress.request_builder.RequestBuilder;

public class UpdatePassCommand extends UpdateElementCommand {
    public UpdatePassCommand(UserObject user, String key) {
        super(user, key);
    }

    @Override
    void prepareRequest() {

        UserObject user = (UserObject) object;

        request = new RequestBuilder().setMethod(Resources.METHOD_POST)
                .setTarget(Resources.TARGET_USER_PASS)
                .setAction(Resources.ACTION_UPD)
                .setKey(key)
                //   .setRequest("mark", car.getMark())
                .setRequest("login",  user.getLogin())
                .setRequest("pass",String.valueOf(user.getPassHash()))

                .build();
    }
}