package com.lambda.order.model;

public class Request {

    private String httpMethod;
    private String id;
    private Order order;

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
