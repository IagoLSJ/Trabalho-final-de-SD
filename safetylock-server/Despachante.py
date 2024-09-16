import json
from esqueleto import Esqueleto
from entities.message import Message


class Despachante:
    def dispatch(self, request):
        try:
            # Converte a string JSON em um dicionário
            request_dict = json.loads(request)

            # Cria um objeto Message a partir do dicionário
            message = Message.from_dict(request_dict)

            # Converte arguments de JSON para dicionário, se necessário
            if isinstance(message.arguments, str):
                message.arguments = json.loads(message.arguments)

            esqueleto = Esqueleto()

            # Verifica o método solicitado e chama o método correspondente no Esqueleto
            if message.method == "sign_up":
                message.arguments = esqueleto.sign_up(message.arguments)
                message.type = 'response'
                return json.dumps(message.to_dict())

            elif message.method == "login":
                message.arguments = esqueleto.login(message.arguments)
                message.type = 'response'
                return json.dumps(message.to_dict())

            elif message.method == "salvar_senha":
                message.arguments = esqueleto.salvar_senha(message.arguments)
                message.type = 'response'
                return json.dumps(message.to_dict())

            elif message.method == "listar_senhas":
                message.arguments = esqueleto.listar_senhas(message.arguments)
                message.type = 'response'
                return json.dumps(message.to_dict())
            else:
                return json.dumps({"error": "Invalid method"})

        except Exception as e:
            print(f"Erro no Despachante: {e}")
            return json.dumps({"error": "Internal server error"})
