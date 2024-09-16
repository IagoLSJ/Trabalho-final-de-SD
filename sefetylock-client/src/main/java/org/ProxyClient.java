package org;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

import org.entities.Message;
import org.entities.Password;
import org.entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.lang.reflect.Type;

public class ProxyClient {
    int requestId;
    ClientUDP clientUDP = new ClientUDP("localhost", 9090);
    Gson gson = new Gson();

    public User login(User user) {
        String jsonRequest = gson.toJson(user);
        String jsonResponse = doOperation("User", "login", jsonRequest);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            throw new RuntimeException("Empty response from server.");
        }

        try {
            return gson.fromJson(jsonResponse, User.class);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Failed to deserialize response to User object.", e);
        }
    }

    public User sing_up(User user) {
        String jsonRequest = gson.toJson(user);
        String jsonResponse = doOperation("User", "sign_up", jsonRequest);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            throw new RuntimeException("Empty response from server.");
        }

        try {
            return gson.fromJson(jsonResponse, User.class);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Failed to deserialize response to User object.", e);
        }
    }

    public Password salvar_senha(Password password) {
        String jsonRequest = gson.toJson(password);
        String jsonResponse = doOperation("Password", "salvar_senha", jsonRequest);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            throw new RuntimeException("Empty response from server.");
        }

        try {
            return gson.fromJson(jsonResponse, Password.class);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Failed to deserialize response to User object.", e);
        }
    }

    public List<Password> listar_senhas(String userId) {
        Map<String, String> argumentsMap = new HashMap<>();
        argumentsMap.put("userId", userId);
        String jsonRequest = gson.toJson(argumentsMap);

        String jsonResponse = doOperation("Password", "listar_senhas", jsonRequest);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            throw new RuntimeException("Empty response from server.");
        }

        try {
            Type passwordListType = new TypeToken<List<Password>>() {
            }.getType();
            return gson.fromJson(jsonResponse, passwordListType);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Failed to deserialize response to list of Password objects.", e);
        }
    }

    public String doOperation(String objectRef, String method, String args) {
        int attempt = 0;
        String data = empacotaMensagem(objectRef, method, args);

        while (attempt++ < 5) {
            try {
                // Envia a solicitação
                clientUDP.sendRequest(data.getBytes(StandardCharsets.UTF_8));

                // Recebe a resposta
                String resposta = new String(clientUDP.getReply(), StandardCharsets.UTF_8);

                // Desempacota a mensagem
                Message message = desempacotaMensagem(resposta);

                if (message != null) {
                    // Extraí o conteúdo da chave "data"
                    Object arguments = message.getArguments();
                    if (arguments instanceof Map) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> argsMap = (Map<String, Object>) arguments;
                        if (argsMap.containsKey("data")) {
                            return gson.toJson(argsMap.get("data"));
                        } else {
                            throw new RuntimeException("Received error: " + argsMap.get("status")
                                    + " with message: " + argsMap.get("message"));
                        }
                    }
                }
            } catch (SocketTimeoutException ste) {
                System.out.println("Socket timeout occurred. Retrying...");
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                break;
            }
        }
        throw new RuntimeException("Operation couldn't be completed after 5 attempts.");
    }

    private String empacotaMensagem(String objectRef, String method, String args) {
        try {
            Message message = new Message();
            message.setType("request");
            message.setMethod(method);
            message.setObjReference(objectRef);
            message.setArguments(args);

            return gson.toJson(message);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            throw new RuntimeException("Unexpected error occurred during JSON parsing");
        }
    }

    private Message desempacotaMensagem(String resposta) {
        try {
            return gson.fromJson(resposta, Message.class);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            throw new RuntimeException("Unexpected error occurred");
        }
    }
}
