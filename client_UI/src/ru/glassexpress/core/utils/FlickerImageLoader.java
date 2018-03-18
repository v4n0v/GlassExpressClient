package ru.glassexpress.core.utils;

import ru.glassexpress.core.objects.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickerImageLoader {
        private final String KEY = "9c6b4a5f6ad93dafa5a5ca0ef3b2f864";
    private final int COUNT = 50;
    private final String IMAGE_SIZE = "url_m";

    private final String REQUEST_IMAGE_KEY = "car";
    private Car car;


    public void downloadAndSetImage(final Car car) {
        this.car = car;
        int from = car.getYearFrom();
        int to = car.getYearTo();
         int year= (car.getYearFrom()+car.getYearTo())/2;
        List<String> imageLinks;
        //String links;
        new Thread() {
            @Override
            public void run() {
                try {
                    String strNoSpaces = car.toString().replace(" ", "+");
                    String link = "https://api.flickr.com/services/rest/?safe_search=safe&api_key="
                            + KEY + "&format=json&method=flickr.photos.search&media=photos&extras="
                            + IMAGE_SIZE + "&per_page=" + COUNT
                            + "&content_type=1&sort=relevance";

                    //если есть координаты, то то ним, если нет, то по названию города
                    link+="&text=" + car.getMark()+"+"+car.getModel()+"+"+year+ "+"+ REQUEST_IMAGE_KEY;
                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    if (conn.getResponseCode() != 200) {
                        System.out.println(
                                "Exception: " + conn.getResponseMessage() + ", code " + conn.getResponseCode());
                        throw new IOException(conn.getResponseMessage());
                    }


                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = rd.readLine()) != null) {

                        System.out.println("Line =" + rd.readLine());
                        sb.append(line + "\n");

                    }
                    String st = sb.toString();
                    st = st.replace("jsonFlickrApi(", "");
                    st = st.replace(")", "");
//                    JSsObject json = new JSONObject(st);

                    String[] imageLinks = getImagesArray(st);
                    rd.close();
                    conn.disconnect();


                 //   new BitmapSetterTask(city, context, toolbarImage).execute(imageLinks.get(id));
                  //  new BitmapDownLoader(city,context).execute(imageLinks.get(id));
                } catch (Exception e) {
                    System.out.println("BITMAP" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }.start();

    }

        private String[] getImagesArray(String json) {
        ArrayList<String> links = new ArrayList<>();
        String[] lines = json.split(",");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("\"" + IMAGE_SIZE + "\":")) {
                String[] tempString = lines[i].split("\"" + IMAGE_SIZE + "\":");
                String tmp = tempString[1];
                tmp = tmp.replace("\"", "");
                links.add(tmp);
            }
        }
            System.out.println("Array getting complete");
        String[] result= links.toArray(new String[links.size()]);
        return links.toArray(new String[links.size()]);
    }

}
