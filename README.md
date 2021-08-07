# Videos To Show Off Various Game Features

## Create, Join, and Spectate Games
* The player who creates the game is always blue
* The player who joins the game is always red
* Any player after the first two who join a game is a spectator (cannot move any pieces)

https://user-images.githubusercontent.com/8243054/128615750-2a9f4824-66cc-4844-9d66-168e6ff9721b.mp4

## Players can only move their pieces during their turn
* Every move is updated on each player/spectator's board without the need to manually refresh
* The blue/red circles above are highlighted (in green) to show which player is meant to move next
* Spectators can ONLY spectate. They cannot move any pieces

https://user-images.githubusercontent.com/8243054/128615894-6b7d91e3-4ce1-4874-ab7f-335e5ba80e4a.mp4

## Players can rejoin the match and will be able to resume with their correct pieces
* The player name can be seen as an ID. Once a player creates/joins a match, if they are assigned to that color, the REDIS database will remember which pieces they control

https://user-images.githubusercontent.com/8243054/128615979-9263b948-c5cf-45b0-9dcd-e3a6660da471.mp4

# Commands

    (Latest version = 0.7.0)

   * mvn clean install -DskipTests
   * docker build -t ivorytoast3853/titan-amg:0.7.0 .
   * docker push ivorytoast3853/titan-amg:0.7.0
   * docker run -p 8080:8080 ivorytoast3853/titan-amg:0.7.0


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
1. UI -> https://pluto-ivorytoast.vercel.app/
2. Backend -> http://proxy.titan-backend-nyc.com:8080

### Endpoints List
#### GET
* /game/v1/board/{id}
* /game/v1/players/{id}

#### POST
* /game/v1/new
* /game/v1/move
* /game/v1/update
* /game/v1/join

# Interesting Features To Point Out
## Running on Digital Ocean Infrastructure
### Summary
* Has 1 Droplet (Titan)
* Has 1 Managed Database Cluster
* Has 1 Google Domain (titan-backend-nyc.com)
### Security
* Firewall Setup:
   * SSH on Port 22 only allowed from my IP and titan itself. This was necessary because it is exposed to the internet there were thousands of login attempts per minute on the droplet from global IP addresses
   * TCP (Port 81) from my IP address. This is to allow myself to manage the Nginx Proxy Manager application
   * TCP (Port 443 -> HTTPS) for all IPv4 and IPv6 addresses to allow any user to interact with the Vercel front end UI 
   * All requests are proxied through an Nginx proxy to allow a client to hit a HTTPS endpoint even though Titan runs on HTTP

## The login css came from: https://tailwindcomponents.com/component/login. All credit to Cristian Mosquera Mosquera for creating such a nice login layout
