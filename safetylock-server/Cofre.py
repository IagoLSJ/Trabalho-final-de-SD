from jsonmanager import JsonManager
from entities.message import Message
from entities.user import User
from entities.password import Password


class Cofre:
    def __init__(self) -> None:
        self.usersdb = '\\data\\usersdb.json'
        self.passworddb = '\\data\\passworddb.json'
        self.jmanager = JsonManager()

    def sign_up(self, id: str, username: str, email: str, userpass: str) -> Message:
        user = User(id, username, email, userpass)
        res = self.jmanager.add_to_list(self.usersdb, user.to_dict())
        if not res:
            message = Message(
                arguments={"status": 500, "message": "Internal Server Error"}
            )
        else:
            message = Message(
                arguments={
                    "data": user.to_dict(),
                    "status": 200, "message": "Seja bem-vinda(o), " + username + "!"}
            )

        return message.arguments

    def login(self, email: str, userpass: str) -> Message:
        user = self.jmanager.get_user_by_credentials(
            self.usersdb, email, userpass)

        if not user:
            message = Message(
                arguments={"status": 401,
                           "message": "Credenciais inválidas!"}
            )
        else:
            message = Message(
                arguments={"data": user, "status": 200,
                           "message": "Seja bem-vinda(o), " + user['username'] + "!"}
            )

        return message.arguments

    def salvar_senha(self, title: str, password: str, userId: str) -> Message:
        senha = Password(title, password, userId)
        res = self.jmanager.add_to_list(self.passworddb, senha.to_dict())
        if not res:
            message = Message(
                arguments={"status": 500, "message": "Internal Server Error"}
            )
        else:
            message = Message(
                arguments={"data": senha.to_dict(), "status": 200,
                           "message": "Senha salva com sucesso!"}
            )

        return message.arguments

    def listar_senhas(self, userId: str) -> Message:
        res = self.jmanager.get_passwords_by_user_id(userId)

        if res is None:  # Em caso de erro ao recuperar senhas, status de erro
            message = Message(
                arguments={"status": 500, "message": "Internal Server Error"}
            )
        else:
            message = Message(
                arguments={"data": res if res else [],  # Retorna lista vazia se res for vazio
                           "status": 200,
                           "message": "Senhas retornadas com sucesso!"}
            )

        return message.arguments
