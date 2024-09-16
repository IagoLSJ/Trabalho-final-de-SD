from cofre import Cofre


class Esqueleto:

    def __init__(self) -> None:
        self.cofre = Cofre()

    def sign_up(self, requestBody):
        id: str = requestBody['id']
        username: str = requestBody['username']
        email: str = requestBody['email']
        userpass: str = requestBody['userpass']

        response = self.cofre.sign_up(id, username, email, userpass)
        return response

    def login(self, requestBody):
        email: str = requestBody['email']
        userpass: str = requestBody['userpass']
        try:
            response = self.cofre.login(email, userpass)
            return response
        except ValueError as error:
            raise error

    def salvar_senha(self, requestBody):
        title: str = requestBody['title']
        password: str = requestBody['password']
        userId: str = requestBody['userId']
        try:
            response = self.cofre.salvar_senha(title, password, userId)
            return response
        except ValueError as error:
            raise error

    def listar_senhas(self, requestBody):
        userId: str = requestBody['userId']
        try:
            response = self.cofre.listar_senhas(userId)
            return response
        except ValueError as error:
            raise error
