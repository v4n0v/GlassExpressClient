package ru.glassexpress.core;


import ru.glassexpress.core.data.Prefs;
import ru.glassexpress.request_builder.Request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class URLConnection {
    private static  URLConnection urlConnection = new URLConnection();
    static final String ADRESS_URL = "http://localhost:8080";
//    static final String ADRESS_URL = "http://env-1571803.jelastic.regruhosting.ru";
    static final String SERVLET = "/controller";
//    http://env-1571803.jelastic.regruhosting.ru/controller?target=mark&action=list&target=mark
    static final String KEY = "";
    StringBuffer response = null;

    private URLConnection() {
    }

   public static URLConnection getInstance(){
        return  urlConnection;
    }


    public String receiveData(Request request) throws Exception{


        String req = URLEncoder.encode(request.toString(), "UTF-8");
            URL url = new URL(ADRESS_URL + SERVLET + req);//+request);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(request.getMethod());
            System.out.println(con.getResponseMessage());
            if (con.getResponseMessage() != null) {
                int code = con.getResponseCode();

                if (code == 200) {
                    System.out.println("Response code: " + code);
                    System.out.println("Reading " + request.getMethod());
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    if (response.equals(Prefs.ERROR_RESPONSE)) {
                        errorMessage();
                    }
                    in.close();
                    System.out.println(response.toString());
                } else
                    System.out.println("Server answer error");
            }



        return response.toString();
    }

    private void errorMessage() {
        System.out.println("Connection error");
    }


    public static void main(String[] args) {


    }

}
