package org.entities;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.UUID;

public class Message implements Serializable {
    private String id;
    private String type;
    private String method;
    private String objectReference;
    private Object arguments;

    public Message() {
        this.id = generateMessageId();
    }

    // Gera um UUID aleatório para userId
    private String generateMessageId() {
        return UUID.randomUUID().toString();
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getObjectReference() {
        return objectReference;
    }

    public void setObjReference(String objectReference) {
        this.objectReference = objectReference;
    }

    public Object getArguments() {
        return arguments;
    }

    public void setArguments(Object arguments) {
        this.arguments = arguments;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
