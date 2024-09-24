from cofre import Cofre
from entities.user import User
from entities.password import Password


class Esqueleto:

    def __init__(self) -> None:
        self.cofre = Cofre()

    def sign_up(self, requestBody):
        try:
            requestBody = requestBody.to_dict()
            user = User(requestBody['id'], requestBody['username'], requestBody['email'], requestBody['userpass'])
            response = self.cofre.sign_up(user=user)
            return response
        except Exception as e:
            return str(e)

    def login(self, requestBody):
        try:
            #requestBody = requestBody.to_dict()
            user = User(requestBody['email'], requestBody['userPass'])
            response = self.cofre.login(user)
            return response
        except ValueError as error:
            return str(error)
        except Exception as e:
            return str(e)

    def salvar_senha(self, requestBody):
        try:
            requestBody = requestBody.to_dict()
            password = Password(requestBody['title'], requestBody['password'], requestBody['userId'])
            response = self.cofre.salvar_senha(password=password)
            return response
        except ValueError as error:
            return str(error)
        except Exception as e:
            return str(e)

    def listar_senhas(self, requestBody):
        userId: str = requestBody['userId']
        try:
            response = self.cofre.listar_senhas(userId)
            return response
        except ValueError as error:
            return str(error)
        except Exception as e:
            return str(e)
