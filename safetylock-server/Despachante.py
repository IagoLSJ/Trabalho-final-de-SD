import json
from esqueleto import Esqueleto
from entities.message import Message


class Despachante:
    def dispatch(self, method, request_args):
        try:
            # Converte a string JSON em um dicionário
            esqueleto = Esqueleto()

            # Verifica o método solicitado e chama o método correspondente no Esqueleto
            if method == "sign_up":
                response = esqueleto.sign_up(request_args)
                return response

            elif method == "login":
                response = esqueleto.login(request_args)
                return response

            elif method == "salvar_senha":
                response = esqueleto.salvar_senha(request_args)
                return response

            elif method == "listar_senhas":
                response = esqueleto.listar_senhas(request_args)
                return response
            else:
                return json.dumps({"error": "Invalid method"})

        except Exception as e:
            print(f"Erro no Despachante: {e}")
            return json.dumps({"error": "Internal server error"})
