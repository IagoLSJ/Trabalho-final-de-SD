# incluir criptografia aqui https://www.youtube.com/watch?v=9-hcqcaSyoM&list=PLZ6kIzk4n3uRmlJUAIwTLqMIIcgaR3uPa&index=4
from cofre import Cofre
from utils.objects import User
from utils.objects import Password
import json

class Esqueleto :

    def __init__(self) -> None:
        self.cofre = Cofre()

    def sign_up(self, requestBody) :
        user = User(email=requestBody['email'], userPass=requestBody['userPass'])
        user.setUsername(requestBody['username'])
        response = self.cofre.sign_up(user=requestBody)
        return response

    def login(self, requestBody) :
        user = User(requestBody['email'], requestBody['userPass'])
        try : 
            response = self.cofre.login(user=user)
            return response
        except ValueError as error:
            raise error
        

    def salvar_senha(self, requestBody) : 
        password = Password(requestBody['title'], requestBody['description'], requestBody['userId'],
                            requestBody['password'])
        try : 
            response = self.cofre.salvar_senha(password=password)
            return response
        except ValueError as error:
            raise error

    def listar_senhas(self, requestBody) : 
        try : 
            response = self.cofre.listar_senha()
            return response
        except ValueError as error:
            raise error