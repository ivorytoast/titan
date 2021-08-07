# Videos To Show Off Various Game Features

## Create, Join, and Spectate Games
* The player who creates the game is always blue
* The player who joins the game is always red
* Any player after the first two who join a game is a spectator (cannot move any pieces)

https://user-images.githubusercontent.com/8243054/128615750-2a9f4824-66cc-4844-9d66-168e6ff9721b.mp4

## Players can only move their pieces during their turn
* Spectators can ONLY spectate. They cannot move any pieces

https://user-images.githubusercontent.com/8243054/128615894-6b7d91e3-4ce1-4874-ab7f-335e5ba80e4a.mp4


# Commands

    (Latest version = 0.5.0)

   * mvn clean install -DskipTests
   * docker build -t ivorytoast3853/titan-amg:0.5.0 .
   * docker push ivorytoast3853/titan-amg:0.5.0
   * docker run -p 8080:8080 ivorytoast3853/titan-amg:0.5.0


   * docker-compose -f docker-compose.yml up --build -d
   * docker-compose down

# What is Titan
A backend service which emulates the game Stratego. All through REST endpoints, one can perform the following:
### Features Added   
* Create a new game session
* Join an existing game session
* Spectate an existing game session
* Move pieces that belong to you
* Java Client
### Features To Be Added
* Replay existing games

### Why Contribute
It is currently running on a Digital Ocean Droplet which is connected to a Digital Ocean managed PostgreSQL database
and (in the future) be connected to a Digital Ocean managed Redis cluster. Right now, Redis is run through docker-compose.

Therefore, you can experiment with the game. See what changes you would like to have implemented and go make those changes.

### Technologies Used
* Java
* Spring
* Docker
* Redis
* ZeroMQ
* PostgreSQL

# API Docs
### Urls
1. http://localhost:8080
2. http://proxy.titan-backend-nyc.com:8080

### Endpoints List
#### GET
* /game/db/v1/board/{id}
* /game/cache/v1/players/{id}

#### POST
* /game/v1/new
* /game/move
* /game/update/board
* /game/join

#### Tips To Understand The Endpoints
* If it has a "/db/" in the path, it is going to the PostgreSQL database. This means you are going to retrieve board related information
* If it has a "/cache/" in the path, it is going to the Redis cache. This is where the metadata (such as each game's session player list) is stored


## Endpoints In Details
1. /game/db/v1/board/{id}
   * {id} = [Type=String, Value=SessionId]
   * Returns the latest board in the given SessionId
