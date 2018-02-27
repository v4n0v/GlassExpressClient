package ru.glassexpress;

import com.google.gson.*;
import ru.glassexpress.objects.*;

import java.util.ArrayList;
import java.util.List;

// класс обработчик входящих JSON
public class JsonController {
    private static final JsonParser parser = new JsonParser();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static JsonController jsonController = new JsonController();

    private JsonController() {
    }

    public static JsonController getInstance() {
        return jsonController;
    }

    public BaseObject convertJsonToObject(String jsonResponse) {
        JsonObject jsonObj = (JsonObject) parser.parse(jsonResponse);
//        String objClass = removeBrackets(jsonObj.get("objClass").toString());
//        List<BaseObject> list = new ArrayList<>();

        return parseJson(jsonObj);

    }

    private String removeBrackets(String type) {
        // type=type.replace("\"", "");
        return type.replace("\"", "");
    }

    private BaseObject parseJson(JsonObject jsonObj) {
        String objClass = removeBrackets(jsonObj.get("objClass").toString());

        switch (objClass) {
            case "composite":
                JsonArray arr = jsonObj.getAsJsonArray("array");
                Composite composite = new Composite();
                for (int i = 0; i < arr.size(); i++) {
                    JsonObject arrayElement = (JsonObject) arr.get(i);
                    composite.addComponent(parseJson(arrayElement));
                }
                return composite;
            default:

                return objectFabric(jsonObj);
        }
    }

    private BaseObject objectFabric(JsonObject jsonObj) {
        String objClass = removeBrackets(jsonObj.get("objClass").toString());

        switch (objClass) {
            case "IdTitleObj":
                //   ArrayList<String> marks = new ArrayList<>();
                return GSON.fromJson(jsonObj, IdTitleObj.class);
            case "generation":
                return GSON.fromJson(jsonObj, GenerationObj.class);
            case "tab_goods_in_stock":
                return GSON.fromJson(jsonObj, TableGoodsInStockRow.class);

            case "error":
                return GSON.fromJson(jsonObj, ErrorObject.class);
            case "ok":
                return GSON.fromJson(jsonObj, OkObject.class);
            default:
                return null;

        }

    }


}

interface FactoryMetod {
    BaseObject createObject(String classObj);
}


class ObjectFactory implements FactoryMetod {

    @Override
    public BaseObject createObject(String classObj) {
        BaseObject object = null;
        switch (classObj) {
            case "IdTitleObj":
                object = new IdTitleObj();
                break;
            case "CarModel":
                //object = new IdTitleObj(); break;
                System.out.println("НЕ ГОТОВО ЕЩЕ");
            default:
                break;
        }
        return object;
    }
}