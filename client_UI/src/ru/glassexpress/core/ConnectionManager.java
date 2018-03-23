package ru.glassexpress.core;




import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import ru.glassexpress.core.data.Log2File;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;

public class ConnectionManager implements Callable<String>{
    private String responseBody;
    String request;
     static final String ADRESS_URL = "http://localhost:8080";
//     static final String ADRESS_URL = "http://ge-servlet-glass-express.1d35.starter-us-east-1.openshiftapps.com/";

   //static final String ADRESS_URL = "http://glass-express.jelastic.regruhosting.ru";
    static final String SERVLET = "/controller";

    private ConnectionManager() {
    }


    public static ConnectionManager getInstance() {
        return manager;
    }

    static ConnectionManager manager = new ConnectionManager();



    public ConnectionManager setRequest(String request){
        this.request=request;
        return this;
    }


    public  String getResp() throws Exception {
//        FutureTask<String> futureTask = new FutureTask<String>(this);
//        new Thread(futureTask).start();
//        return futureTask.get();
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(ADRESS_URL+SERVLET+request);
        System.out.println(Thread.currentThread().getName());
//       Log2File.writeLog("Executing request " + ADRESS_URL+SERVLET+request);
//        System.out.println("Executing request " + httpget.getRequestLine());

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    Log2File.writeLog(Level.WARNING,"Unexpected response status: " + status);
                    throw new ClientProtocolException("Unexpected response status: " + status);

                }
            }

        };

        responseBody = httpclient.execute(httpget, responseHandler);
        Log2File.writeLog("----------------------------------------");
        System.out.println("----------------------------------------");

        httpclient.close();
        return responseBody;
    }

    public String call() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(ADRESS_URL+SERVLET+request);
        System.out.println(Thread.currentThread().getName());
        Log2File.writeLog("Executing request " + httpget.getRequestLine());
         System.out.println("Executing request " + httpget.getRequestLine());

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    Log2File.writeLog(Level.WARNING,"Unexpected response status: " + status);
                    throw new ClientProtocolException("Unexpected response status: " + status);

                }
            }

        };

        responseBody = httpclient.execute(httpget, responseHandler);
        Log2File.writeLog("----------------------------------------");
        System.out.println("----------------------------------------");

        httpclient.close();
        return responseBody;
    }

    static class MyCallable implements Callable<String> {
        private String responseBody;
        String request;

        public MyCallable(String request) {
            this.request = request;
        }

        public String call() throws Exception {
            CloseableHttpClient httpclient = HttpClients.createDefault();

            HttpGet httpget = new HttpGet(request);
        //    System.out.println(Thread.currentThread().getName());
            // System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        Log2File.writeLog(Level.WARNING,"Unexpected response status: " + status);
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };

            responseBody = httpclient.execute(httpget, responseHandler);
          //  System.out.println("----------------------------------------");
            //     System.out.println(responseBody);
            Log2File.writeLog("----------------------------------------");
            //   listener.onReceiveResponse(responseBody);
            httpclient.close();
            return responseBody;
        }

    }

}
