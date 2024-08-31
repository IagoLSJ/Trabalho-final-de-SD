import greensteam_pb2 as Game
import greensteam_pb2 as User
import greensteam_pb2 as Publisher
import greensteam_pb2 as Comment
import greensteam_pb2 as Reviews

user = User.User()
user.name = "Ataraxia"
user2 = User.User()
user2.name = "Hugo"

game = Game.Game()
game.name = "Yario"

comment = Comment.Comment()
comment.content = "Bom demai"
comment.authorName = "Hugo"
comment.recomendation = True

game.reviews.comments.append(comment)

game2 = Game.Game()
game2.name = "RORR"
game2.description = "RISK OF REPROVAR"
game2.downloadQuantity = 10
game2.reviewsPercentage = 90

user.library.append(game)
user.library.append(game2)
userGames = user.library
users = [user, user2]

publisher = Publisher.Publisher()
publisher.name = "Intendo"
publisher.followers = 200000
publisher.games.append(game)
publishers = [publisher]

games = []
games.append(game)
games.append(game2)

def getPublisher(game) -> Publisher:
    for publisher in publishers:
        if game.name in [x.name for x in publisher.games]:
            return publisher
    raise ValueError("Game not found")


def getLastPlayedGame(name) -> Game:
    for user in users:
        if(user.name == name):
            userGames = user.library
            if(userGames):
                return userGames[-1]
            else:
                raise ValueError( name + " library is empty")
    raise ValueError("User not found")


def getReviews(name) -> Reviews:
    for game in games:
        if(name == game.name):
            comments = game.reviews.comments
            if(comments):
                return game.reviews
            else:
                raise ValueError( name + " don't have reviews")
    raise ValueError("Game not found")
