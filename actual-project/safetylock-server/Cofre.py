import json
from jsonmanager import JsonManager

class Cofre :

    def __init__(self) -> None:
        self.usersdb = '\\data\\usersdb.json'
        self.passworddb = '\\data\\passworddb.json'
        self.jmanager = JsonManager()
    
    def sign_up(self, user) :
        res = self.jmanager.add_to_list(self.usersdb, user)
        if not res:
            return {"status" : "500", "body" : "Internal Server Error"}
        else : 
            return {"status" : "200", "body" : "Seja bem-vinda(o),"+user['username']+"!"}

    def login(self, user) :
        data = self.jmanager.get_file_contents(self.usersdb)
        if not data : 
            return {"status" : "500", "body" : "Internal Server Error"}
        else :
            for item in data :
                if item['email'] == user['email']:
                    if item['password'] == user['password'] :
                        return {"status" : "200", "body" : "Seja bem-vinda(o),"+item['username']+"!"}
                    else : 
                        return {"status" : "401", "body" : "Credenciais inválidas!"}
                    
            return {"status" : "404", "body" : "O email informado não está na nossa base de dados!"}
            
                 
    
    def salvar_senha(self, password) :
        res = self.jmanager.add_to_list(self.passworddb, password)
        if not res:
            return {"status" : "500", "body" : "Internal Server Error"}
        else : 
            return {"status" : "200", "body" : "Senha salva com sucesso!"}
    
    def listar_senha(self) :
        res = self.jmanager.get_file_contents(self.passworddb)
        if not res:
            return {"status" : "500", "body" : "Internal Server Error"}
        else : 
            return {"status" : "200", "body" : res}