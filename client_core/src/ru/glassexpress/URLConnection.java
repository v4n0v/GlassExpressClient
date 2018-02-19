package ru.glassexpress;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class URLConnection {

    static final String ADRESS_URL = "http://localhost:8080";
    static final String SERVLET = "/controller";

    static final String KEY = "";
    StringBuffer response = null;


    public String receiveData(String method, String action, String target, String body) {

        try {

            URL url = new URL(ADRESS_URL + SERVLET + "?action=" + action + "&target=" + target + body);//+request);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            System.out.println(con.getResponseMessage());
            if (con.getResponseMessage() != null) {
                int code = con.getResponseCode();

                if (code == 200) {
                    System.out.println("Response code: " + code);
                    System.out.println("Reading " + method);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    if (response.equals(ServerVocabulary.ERROR_RESPONSE)) {
                        errorMessage();
                    }
                    in.close();
                    System.out.println(response.toString());
                } else
                    System.out.println("Server answer error");
            }

        } catch (IOException e) {
            //   e.printStackTrace();
            errorMessage();
        }

        return response.toString();
    }

    private void errorMessage() {
        System.out.println("Connection error");
    }


    public static void main(String[] args) {


//        CarMarks car = new CarMarks(10001, "Volkswagen", "Polo", "sedan", 2);
//        String json = GSON.toJson(car);
//        System.out.println(json);


    }

}
