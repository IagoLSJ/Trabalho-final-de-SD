package com.greensteam;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class UDPClient {

    DatagramSocket socket;
    InetAddress serverAddress;
    int port;

    public UDPClient(String serverIP, int port) {
        try {
            this.serverAddress = InetAddress.getByName(serverIP);
            this.port = port;
            this.socket = new DatagramSocket();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(byte[] request) {
        try {
            DatagramPacket sendPacket = new DatagramPacket(request, request.length, serverAddress, port);
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getReply() throws SocketTimeoutException {
        byte[] buffer = new byte[1024];

        try {
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            socket.setSoTimeout(1000);
            socket.receive(receivePacket);
            byte[] data = receivePacket.getData();
            return Arrays.copyOf(data, receivePacket.getLength());

        } catch (SocketTimeoutException ste) {
            throw ste;

        } catch (IOException e ) {
            e.printStackTrace();

        }

        return null;
    }

    public void close() {
        socket.close();
    }
    
}