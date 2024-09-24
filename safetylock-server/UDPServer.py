import json
import socket
from despachante import Despachante
from entities.message import Message


class UDPServer:
    def __init__(self, server_port):
        self.server_port = server_port
        self.server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.server_socket.bind(("localhost", self.server_port))
        print("Servidor aguardando conexões na porta", self.server_port)

    def start(self):
        try:
            while True:
                data, client_address = self.server_socket.recvfrom(1024)
                print(f"Conexão de {client_address[0]}:{client_address[1]}")
                connection = Connection(
                    self.server_socket, client_address, data)
                connection.run()
        except Exception as e:
            print(f"Listen: {e}")


class Connection:
    # Usar um dicionário para rastrear IDs e resultados
    processed_requests = {}

    def __init__(self, server_socket, client_address, data):
        self.incoming_data = data
        self.server_socket = server_socket
        self.client_address = client_address
        self.despachante = Despachante()

    def run(self):
        try:
            json_string = self.incoming_data.decode('utf-8')
            msg = json.loads(json_string)

            request_id = msg.get('id')
            if request_id in Connection.processed_requests:
                print("Duplicated Request")
                self.send_reply(Connection.processed_requests[request_id], request_id)
            else:
                resultado = self.despachante.dispatch(msg['method'], msg['arguments'])
                result_data = resultado
                Connection.processed_requests[request_id] = result_data
                self.send_reply(result_data, request_id)

        except Exception as e:
            print(f"Connection: {e}")

    def send_reply(self, response_data, request_id):
        res = Message(Connection.processed_requests[request_id], type='response', arguments={'status' : int(500), 'body' : response_data}).to_dict()
        response_json = json.dumps(res).encode('utf-8')
        self.server_socket.sendto(response_json, self.client_address)


if __name__ == "__main__":
    server_port = 9090
    server = UDPServer(server_port)
    server.start()
