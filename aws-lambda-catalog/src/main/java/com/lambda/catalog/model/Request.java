package com.lambda.catalog.model;


public class Request {

    private String httpMethod;

    private String id;

    private String name;

    private Item item;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }
}

