class Message:
    def __init__(self, id=None, type=None, method=None, objectReference=None, arguments=None):
        self.id = id
        self.type = type
        self.method = method
        self.objectReference = objectReference
        self.arguments = arguments

    @classmethod
    def from_dict(cls, message_dict):
        return cls(
            message_dict.get('id'),
            message_dict.get('type'),
            message_dict.get('method'),
            message_dict.get('objectReference'),
            message_dict.get('arguments')
        )

    def to_dict(self):
        return {
            'id': self.id,
            'type': self.type,
            'method': self.method,
            'objectReference': self.objectReference,
            'arguments': self.arguments
        }
