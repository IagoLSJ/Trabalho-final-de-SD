package org;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class ClientUDP {
    private DatagramSocket socket;
    private InetAddress address;
    private int portServer;

    public ClientUDP(String ipServer, int portServer){
        try {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName(ipServer);
            this.portServer = portServer;
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(byte[] requestData) {
        try {
            DatagramPacket packet = new DatagramPacket(
                    requestData,
                    requestData.length,
                    this.address,
                    this.portServer
            );
            this.socket.send(packet);
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getReply() throws SocketTimeoutException {
        byte[] response = new byte[1024];

        try {
            DatagramPacket receivedPacket = new DatagramPacket(
                    response,
                    response.length
            );
            this.socket.setSoTimeout(1000);
            this.socket.receive(receivedPacket);
            byte[] data = receivedPacket.getData();
            return Arrays.copyOf(data, receivedPacket.getLength());
        } catch ( SocketTimeoutException ste ) {
            throw ste;
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return null;
    }

    public void close(){
        this.socket.close();
    }
}
