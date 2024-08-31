class User :
    def __init__(self, email, userPass) :
        self.email = email
        self.userpass = userPass
        self.username = ''
    
    def setUsername(self, username):
        self.username = username

class Password : 
    def __init__(self, title, description, userId, password) :
        self.title = title
        self.description = description
        self.userId = userId
        self.password = password