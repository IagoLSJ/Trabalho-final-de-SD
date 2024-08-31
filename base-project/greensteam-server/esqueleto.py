import servente
import greensteam_pb2 as Game
import greensteam_pb2 as User

def getPublisher(data):
    request = Game.Game()
    request.ParseFromString(data.arguments)
    try:
        reply = servente.getPublisher(request)
        return reply.SerializeToString()
    except ValueError as error:
        raise error


def getLastPlayedGame(data):
    request = User.User()
    request.ParseFromString(data.arguments)
    try:
        reply = servente.getLastPlayedGame(request.name)
        return reply.SerializeToString()
    except ValueError as error:
        raise error

def getReviews(data):
    request = Game.Game()
    request.ParseFromString(data.arguments)
    try:
        reply = servente.getReviews(request.name)
        return reply.SerializeToString()
    except ValueError as error:
        raise error