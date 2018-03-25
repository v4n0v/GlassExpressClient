package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.library.Resources;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.UserObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class AddUserCommand extends AddElementCommand {
    public AddUserCommand(BaseObject baseObject, String key) {
        super(null, baseObject, key);
    }

    @Override
    protected void prepareRequest() {
        UserObject user = (UserObject) baseObject;

        request = new RequestBuilder()
                .setMethod(Resources.METHOD_POST)
                .setAction(Resources.ACTION_INSERT)
                .setTarget(Resources.TARGET_USER)
                .setKey(key)
                .setRequest("first_name", user.getName())
                .setRequest("last_name", user.getLastName())
                .setRequest("id_pos", String.valueOf(user.getPositionId()))
                .setRequest("id_salon",String.valueOf(user.getSalonId()))
                .setRequest("key_user", String.valueOf(user.getKey()))
                .setRequest("id_permission", String.valueOf(user.getPermission()))
                .setRequest("login", user.getLogin())
                .setRequest("passHash", String.valueOf(user.getPassHash()))
                .build();
    }
}

//(first_name,   last_name,    id_pos, id_salon, key_user, id_permission)