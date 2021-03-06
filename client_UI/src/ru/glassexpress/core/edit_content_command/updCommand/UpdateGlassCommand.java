package ru.glassexpress.core.edit_content_command.updCommand;

import ru.glassexpress.library.Resources;
import ru.glassexpress.core.objects.GlassObject;
import ru.glassexpress.request_builder.RequestBuilder;

public class UpdateGlassCommand extends UpdateElementCommand {

   // GlassObject glass;

    public UpdateGlassCommand(GlassObject glass, String key) {
        super(glass,key);
     //   this.glass = glass;
    }

    @Override
    void prepareRequest() {
        GlassObject glass = (GlassObject) object;
        request=new RequestBuilder().setMethod(Resources.METHOD_GET)
                .setTarget(Resources.TARGET_GLASS)
                .setAction(Resources.ACTION_UPD)
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
                .setRequest("id_glass", String.valueOf(glass.getId()))
                .build();
    }
}
