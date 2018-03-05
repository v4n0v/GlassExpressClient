package ru.glassexpress.request_chain;

import ru.glassexpress.JsonController;
import ru.glassexpress.Prefs;
import ru.glassexpress.URLConnection;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.Composite;
import ru.glassexpress.request_builder.Request;

import java.util.List;

public class RequestController {
    static URLConnection urlConnection;
    static JsonController jsonController;



    public static String recieveResponse(Request request) {
        urlConnection = URLConnection.getInstance();
        String jsonResponse = null;
        try {
            jsonResponse = urlConnection.receiveData(request);

        if (jsonResponse != null) {
                if (!jsonResponse.equals(Prefs.ERROR_RESPONSE)) {
                    return jsonResponse;
                }
            } else {

                System.out.println("Это фиаско, братан!");

            }

        } catch (Exception e) {
            AlertWindow.errorMessage("Ошибка соединения с сервером :(");
        }
        return null;
    }

    public static BaseObject responseToObject(String jsonResponse){
        jsonController=JsonController.getInstance();
        if (jsonResponse!=null) {
            return jsonController.convertJsonToObject(jsonResponse);
        }
        return null;
    }

    public static boolean isRequestAccepted(String jsonResponse){
        jsonController=JsonController.getInstance();
        Composite object = (Composite) jsonController.convertJsonToObject(jsonResponse);
        List<BaseObject> answers =  object.getComponents();
        if (answers.get(0).getObjectClass().equals("ok")){
            return true;
        } else


        return false;
    }

}
