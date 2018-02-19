package ru.glassexpress.request_chain;

import ru.glassexpress.JsonController;
import ru.glassexpress.ServerVocabulary;
import ru.glassexpress.URLConnection;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.BaseObject;

public class RequestController {
    static URLConnection urlConnection;
    static JsonController jsonController;



    public static String recieveResponse(String method, String action, String target, String body) {
        urlConnection = URLConnection.getInstance();
        String jsonResponse = null;
        try {
            jsonResponse = urlConnection.receiveData(method, action, target, body);

        if (jsonResponse != null) {
                if (!jsonResponse.equals(ServerVocabulary.ERROR_RESPONSE)) {
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
        if (jsonResponse.equals("ok")) {
            return true;
        }

        return false;
    }

}
