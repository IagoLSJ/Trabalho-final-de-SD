class Password:
    def __init__(self, title: str, password: str, userId: str):
        self.title = title
        self.password = password
        self.userId = userId

    def to_dict(self):
        return {
            'title': self.title,
            'password': self.password,
            'userId': self.userId
        }
