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