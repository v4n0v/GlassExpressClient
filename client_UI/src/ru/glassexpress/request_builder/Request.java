package ru.glassexpress.request_builder;

public class Request {
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    String method;
    String target;
    String action;
    String body;

    @Override
    public String toString() {
        return  "?target="+target+"&action="+action+"&target="+target+body.toString();
    }
}
