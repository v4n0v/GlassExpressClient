package ru.glassexpress.core.edit_content_command.addCommand;

import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class AddGlassCommand extends AddElementCommand{

    public AddGlassCommand(BaseObject baseObject, String key) {
        super(null, baseObject, key);
    }

    @Override
    protected void prepareRequest() {

        GlassObject glass = (GlassObject) baseObject;
        request=new RequestBuilder().setMethod(Prefs.METHOD_GET)
                .setTarget(Prefs.TARGET_GLASS)
                .setAction(Prefs.ACTION_INSERT)
                .setKey(key)
                .setRequest("id_car", String.valueOf(glass.getCarId()))
                .setRequest("id_glass_type", String.valueOf(glass.getGlassTypeId()))
                .setRequest("id_glass_opt", String.valueOf(glass.getGlassOptionId()))
                .setRequest("glass_description", glass.getDescription())
                .setRequest("price_in", String.valueOf(glass.getPriceIn()))
                .setRequest("price_out", String.valueOf(glass.getPrice()))
                .setRequest("alert_remainder", String.valueOf(glass.getAlert()))
                .setRequest("glass_factory", String.valueOf(glass.getGlassFactoryId()))
                .setRequest("insert_method", String.valueOf(glass.getInsertMethod()))
                .setRequest("insert_price", String.valueOf(glass.getInsertPrice()))
                .setRequest("body_type", String.valueOf(glass.getBodyTypeId()))
                .setRequest("optListString", glass.getOptListString())
                .build();

    }
}


