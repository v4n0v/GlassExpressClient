package ru.glassexpress.request_builder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RequestBuilder {
    StringBuilder req = new StringBuilder();

    String method;
    String target;
    String action;
    String body;
    String key;
    public RequestBuilder setMethod(String method){
        //req.append(method);
        this.method=method;
        return this;
    }



    public RequestBuilder setAction(String action){
       // req.append(target);
        this.action=action;
        return this;
    }

      public RequestBuilder setTarget(String target){
        //   req.append(target);
        this.target=target;
        return this;
    }
    public RequestBuilder setKey(String key){
        //   req.append(target);
        this.key=key;
        return this;
    }

    public RequestBuilder setRequest(String parameter, String value){
     //   req.append("&"+parameter+"="+value);
        try {
            req.append("&"+parameter+"="+ URLEncoder.encode(value, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    public RequestBuilder setBlankRequest(){
        req.append("");
        return this;
    }

    public RequestBuilder setRequest(String parameter){
        req.append("&"+parameter);
        return this;
    }


    public Request build(){
        Request request = new Request();
        request.setMethod(method);
        request.setAction(action);
        request.setTarget(target);
        request.setKey(key);
        request.setBody(req.toString());

        return  request;
    }

}
