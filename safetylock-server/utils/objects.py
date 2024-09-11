class User :
    def __init__(self, email, userPass) :
        self.email = email
        self.userpass = userPass
        self.username = ''
        self.userId = ''
    
    def setUsername(self, username):
        self.username = username
    
    def setUserId(self, userId):
        self.userId = userId

class Password : 
    def __init__(self, title, description, userId, password, passId) :
        self.title = title
        self.description = description
        self.userId = userId
        self.password = password
        self.passId = passId

class Message : 
    def __init__(self, id, type, endpoint, body):
        self.id = id
        self.type = type
        self.endpoint = endpoint
        self.body = body
        ### exemplo de corpo de mensagem de requisição : 
        # {"id" : 12345,
        # "type" : "request", 
        # "endpoint" : "users/login", 
        # body : {"username" : "Freddy Mercury Prateado", "userpass" : "abcdefg"}}

        ### exemplo de corpo de mensagem de resposta : 
        # {"id" : 12345, 
        # "type" : "response", 
        # "endpoint" : "",
        # body : {"status" : "200", "body" : "Senha salva com sucesso!"}