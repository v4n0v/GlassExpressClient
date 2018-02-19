package ru.glassexpress.request_chain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.glassexpress.ServerVocabulary;
import ru.glassexpress.URLConnection;
import ru.glassexpress.library.AlertWindow;
import ru.glassexpress.objects.BaseObject;
import ru.glassexpress.objects.CarMark;
import ru.glassexpress.objects.Composite;

import java.util.ArrayList;
import java.util.List;

abstract public class RequestController {
    URLConnection urlConnection;

    public RequestController(String requestType) {
        this.requestType = requestType;
    }

    private String requestType;

   private String method;
    private  String action;
    private  String target;
    private String body;


    public void setNext(RequestController next) {
        this.next = next;
    }

    public void setRequestParams(String method, String action, String target, String body) {
        this.method = method;
        this.action=action;
        this.target=target;
        this.body=body;
    }

    RequestController next;


    BaseObject sendRequest() {
        String jsonResponse = null;
        try {
            jsonResponse = urlConnection.receiveData(method, action, target, body);
            if (jsonResponse != null) {
                if (!jsonResponse.equals(ServerVocabulary.ERROR_RESPONSE)) {
                    executeJSON();
                }
            }
        } catch (Exception e) {
            System.out.println("Это фиаско, братан!");
             AlertWindow.errorMessage("Ошибка соединения с сервером :(");

        }
        return null;
    }
    abstract void executeJSON();
}
    class InsertRequestController extends RequestController{

        public InsertRequestController(String requestType) {
            super(requestType);
        }

        @Override
        void executeJSON() {

        }
    }
class SelectRequestController extends RequestController{

    public SelectRequestController(String requestType) {
        super(requestType);
    }

    @Override
    void executeJSON() {

    }
}

//        public ObservableList<String> getModels(String mark) {
//            String jsonResponse = null;
//            String body = "&mark=" + mark;
//            try {
//                jsonResponse = urlConnection.receiveData("GET", ServerVocabulary.ACTION_SELECT, ServerVocabulary.TARGET_MODEL, body);
//                if (jsonResponse != null) {
//                    if (!jsonResponse.equals(ServerVocabulary.ERROR_RESPONSE)) {
//
//                        // получаем список и переносим его во вью
//                        Composite baseObject = (Composite) jsonController.convertJsonToObject(jsonResponse);
//                        List<BaseObject> list = baseObject.getComponents();
//                        ArrayList<String> carMarks = new ArrayList<>();
//
//                        for (int i = 0; i < list.size(); i++) {
//                            CarMark models = (CarMark) list.get(i);
//                            carMarks.add(models.getTitle());
//                        }
//
//                        return FXCollections.observableArrayList(carMarks);
//                    } else {
//                        modelsList.clear();
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("Это фиаско, братан!");
//                AlertWindow.errorMessage("Ошибка соединения с сервером :(");
//
//            }
//            return null;
//
//        }
