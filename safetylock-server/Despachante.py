import json
from esqueleto import Esqueleto
from utils.objects import Message

class Despachante :
    def dispatch(self, request) : 
        requestJson = json.loads(request)
        esqueleto = Esqueleto()

        try : 
            message = Message(requestJson['id'], requestJson['type'], requestJson['endpoint'], 
                              requestJson['body'])
            if message['endpoint'] == "/signup":
                response = esqueleto.sign_up(message['body'])
                return json.dumps(response)
            elif message['endpoint'] == "/login":
                response = esqueleto.login(message['body'])
                return json.dumps(response)
            elif message['endpoint'] == "/salvar_senha":
                response = esqueleto.salvar_senha(message['body'])
                return json.dumps(response)
            elif message['endpoint'] == "/listar_senha":
                response = esqueleto.listar_senhas(message['body'])
                return json.dumps(response)
        except : 
            pass
    