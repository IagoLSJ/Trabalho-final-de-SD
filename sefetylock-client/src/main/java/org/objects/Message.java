package org.objects;

import com.google.gson.Gson;

public class Message {
    private int id;
    private int requestId;
    private String type;
    private String endpoint;


    public Message(int id, int requestId, String type, String endpoint) {
        this.id = id;
        this.requestId = requestId;
        this.type = type;
        this.endpoint = endpoint;

    }

    public Message(){}

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


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
