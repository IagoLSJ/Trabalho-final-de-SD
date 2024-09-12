package org;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.objects.Message;
import org.objects.Password;
import org.objects.User;

import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

public class ProxyClient {
    int requestId;
    ClientUDP clientUDP = new ClientUDP("localhost",9090);
    Gson gson = new Gson();

    public User login(User user){
        String jsonResponse = doOperation("User", "login", gson.toJson(user));

        return gson.fromJson(jsonResponse, User.class);
    }

    public User sing_up(User user){
        String jsonResponse = doOperation("User", "sing_up", gson.toJson(user));

        return gson.fromJson(jsonResponse, User.class);
    }

    public Password salvar_senha(Password password){
        String jsonResponse = doOperation("Password", "salvar_senha", gson.toJson(password));

        return gson.fromJson(jsonResponse, Password.class);
    }

    public Password listar_senha(){
        String jsonResponse = doOperation("Password", "listar_senha", gson.toJson(new Password()));

        return gson.fromJson(jsonResponse, Password.class);
    }

    public String doOperation(String objectRef, String method, String args) {
        int attempt = 0;
        String data = empacotaMensagem(objectRef, method, args);

        while (attempt++ < 5) {
            try {
                clientUDP.sendRequest(data.getBytes(StandardCharsets.UTF_8));

                String resposta = new String(clientUDP.getReply(), StandardCharsets.UTF_8);

                Message message = desempacotaMensagem(resposta);

                if (message.getEndpoint() != null && !message.getType().isEmpty()) {
                    throw new RuntimeException(message.toJson());
                }

                return message.toJson();

            } catch (SocketTimeoutException ste) {
                System.out.println("Socket timeout occurred. Retrying...");

            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }

        throw new RuntimeException("Operation couldn't be completed after 5 attempts.");
    }

    private String empacotaMensagem(String objectRef, String method, String args) {

        Message message = new Message();
        message.setType("Error");
        message.setId(this.requestId++);

        return gson.toJson(message);
    }

    private Message desempacotaMensagem(String resposta) {
        return gson.fromJson(resposta, Message.class);
    }
}
