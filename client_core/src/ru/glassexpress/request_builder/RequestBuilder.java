package ru.glassexpress.request_builder;

public class RequestBuilder {
    StringBuilder req = new StringBuilder();

    String method;
    String target;
    String action;
    String body;

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

    public RequestBuilder setRequest(String parameter, String value){
        req.append("&"+parameter+"="+value);
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
        request.setBody(req.toString());

        return  request;
    }

}
