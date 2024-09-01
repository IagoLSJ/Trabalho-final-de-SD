public class Client {
        DatagramSocket socket;
        InetAddress address;
        int portServer;

        public ClientUDP(String ipServer, int portServer){
            try{
                this.socket = new DatagramSocket();
                this.address new InetAddress.getByName(ipServer);
                this.portServer = portServer;
            } catch ( UnknowHostException | SocketException e) {
                e.printStackTrace();
            }
        }

        public void sendRequest(Object requestObject){
            try{
                byte[] requestData = objectMapper.writeValuesAsBytes(requestObject);
                DatagramPacket packet = new DatagramPacket(requestData, requestData.length, this.address, this.portServer);
                socket.send(packet);
            } catch ( IOException e) {
                e.printStackTrace();
            }
        }

        public void close(){
            socket.close();
        }
}
