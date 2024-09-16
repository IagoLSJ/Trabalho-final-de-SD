class User:
    def __init__(self, id: str, username: str, email: str, userpass: str):
        self.id = id
        self.username = username
        self.email = email
        self.userpass = userpass

    def setUsername(self, username):
        self.username = username

    def setEmail(self, email):
        self.email = email

    def to_dict(self):
        return {
            'id': self.id,
            'username': self.username,
            'email': self.email,
            'userpass': self.userpass,
        }
