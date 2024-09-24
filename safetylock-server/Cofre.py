from jsonmanager import JsonManager
from entities.message import Message
from entities.user import User
from entities.password import Password
import json


class Cofre:
    def __init__(self) -> None:
        self.usersdb = '\\data\\usersdb.json'
        self.passworddb = '\\data\\passworddb.json'
        self.jmanager = JsonManager()
        self.e = Exception()
        self.e.add_note('Internal Server Error')

    def sign_up(self, user: User):
        res = self.jmanager.add_to_list(self.usersdb, user.to_dict())
        if not res:
            return 'Internal Server Error'
        else:
            return user

    def login(self, email : str, userpass :str):
        user = self.jmanager.get_user_by_credentials(
            self.usersdb, email, userpass)
        if not user:
            return 'Internal Server Error'
        else:
            return user

    def salvar_senha(self, password: Password):
        res = self.jmanager.add_to_list(self.passworddb, password.to_dict())
        if not res:
            return 'Internal Server Error'
        else:
            return password

    def listar_senhas(self, userId: str):
        res = self.jmanager.get_passwords_by_user_id(userId)

        if res is None:  # Em caso de erro ao recuperar senhas, status de erro
            return 'Internal Server Error'
        else:
            return res if res else []