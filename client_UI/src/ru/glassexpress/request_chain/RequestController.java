package ru.glassexpress.request_chain;

import ru.glassexpress.core.ConnectionManager;
import ru.glassexpress.core.JsonController;
import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.core.URLConnection;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.core.objects.BaseObject;
import ru.glassexpress.core.objects.Composite;
import ru.glassexpress.request_builder.Request;

import java.util.List;


// класс формирует и отправляет запросы
public class RequestController {
    static URLConnection urlConnection;
    static JsonController jsonController;
    static ConnectionManager connectionManager;


    public static String recieveResponse(Request request) {
        connectionManager = ConnectionManager.getInstance();

        String jsonResponse = null;
        try {
            String req=request.toString();
            System.out.println(req);
           // String req = URLEncoder.encode(request.toString(), "UTF-8");
            jsonResponse = connectionManager.setRequest(req).getResp();
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
//        try {
//            jsonResponse = urlConnection.receiveData(request);
//
//            if (jsonResponse != null) {
//                if (!jsonResponse.equals(Prefs.ERROR_RESPONSE)) {
//                    return jsonResponse;
//                }
//            } else {
//
//                System.out.println("Это фиаско, братан!");
//
//            }
//
//        } catch (Exception e) {
//            AlertWindow.errorMessage("Ошибка соединения с сервером :(");
//        }
        return null;
    }

    public static BaseObject responseToObject(String jsonResponse) {
        jsonController = JsonController.getInstance();
        if (jsonResponse != null) {
            return jsonController.convertJsonToObject(jsonResponse);
        }
        return null;
    }

    public static boolean isRequestAccepted(String jsonResponse) {
        jsonController = JsonController.getInstance();
        Composite object = (Composite) jsonController.convertJsonToObject(jsonResponse);
        List<BaseObject> answers = object.getComponents();
        if (answers.get(0).getObjectClass().equals("ok")) {
            return true;
        } else


            return false;
    }

}
