package org.objects;

import com.google.gson.Gson;

public class Message<T> {
    private int id;
    private int requestId;
    private String type;
    private String endpoint;
    private T body;

    public Message(int id, int requestId, String type, String endpoint, T body) {
        this.id = id;
        this.requestId = id;
        this.type = type;
        this.endpoint = endpoint;
        this.body = body;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getRequestId() {
        return id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public T getBody() {
        return body;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static <T> Message<T> fromJson(String json, Class<T> bodyType) {
        Gson gson = new Gson();
        return gson.fromJson(json, Message.class);
    }
}
