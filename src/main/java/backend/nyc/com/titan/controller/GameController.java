package backend.nyc.com.titan.controller;

import backend.nyc.com.titan.common.BoardUtils;
import backend.nyc.com.titan.domain.SessionDB;
import backend.nyc.com.titan.domain.SessionRepository;
import backend.nyc.com.titan.model.Board;
import backend.nyc.com.titan.model.BoardUpdate;
import backend.nyc.com.titan.model.enums.PlayerSide;
import backend.nyc.com.titan.model.requests.BoardUpdateRequest;
import backend.nyc.com.titan.model.requests.JoinGameRequest;
import backend.nyc.com.titan.model.requests.MoveRequest;
import backend.nyc.com.titan.model.requests.NewGameRequest;
import backend.nyc.com.titan.redis.RedisClient;
import backend.nyc.com.titan.serializer.Serializer;
import backend.nyc.com.titan.zeromq.Pub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class GameController {

    private final Pub pub;
    public SessionRepository dao;

    public GameController(SessionRepository dao, Pub pub) {
        this.dao = dao;
        this.pub = pub;
    }

    @GetMapping("/db/board/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getBoardFromLatestVersionOfSession(@PathVariable String id) {
        log.info("Returning board");
        return dao.getBoardFromLatestVersionOfSession(id);
    }

    @PostMapping("/update/board")
    public String getBoard(@RequestBody BoardUpdateRequest updateRequest) {
        log.info("Updating board");
        final String sessionId = updateRequest.getSessionId();
        final String updatedBoard = updateRequest.getNewBoard();
        final SessionDB session = dao.getLatestVersionOfSession(sessionId);
        final int version = session.getVersion();
        pub.addToUpdates(new BoardUpdate(sessionId, updatedBoard));
        dao.insertNewSessionVersion(sessionId, updatedBoard, version + 1);
        return updatedBoard;
    }

    @PostMapping("/move")
    public String move(@RequestBody MoveRequest moveRequest) {
        log.info("Moving piece");
        // Make the move
        String id = moveRequest.getSessionId();
        PlayerSide playerSide = moveRequest.getPlayerSide();
        int fromX = moveRequest.getFromX();
        int fromY = moveRequest.getFromY();
        int toX = moveRequest.getToX();
        int toY = moveRequest.getToY();
        SessionDB session = dao.getLatestVersionOfSession(id);
        Board board = new Board(session.getBoard());
        board.movePiece(playerSide, fromX, fromY, toX, toY);

        // Update the database
        SessionDB dbSession = dao.getLatestVersionOfSession(id);
        int version = dbSession.getVersion();
        String newBoard = Serializer.serializeBoard(board.getPieces());
        int newVersion = version + 1;
        log.info("Inserting into the database the following: (" + id + ", " + newVersion);
        log.info("The board being inserted: " + newBoard);
        dao.insertNewSessionVersion(id, newBoard, newVersion);
        pub.addToUpdates(new BoardUpdate(id, newBoard));

        return newBoard;
    }

    @PostMapping("/new/redis")
    public String newSession(@RequestBody NewGameRequest newGameRequest) {
        log.info("Creating a new session");
        String sessionId = newGameRequest.getSessionId();
        String playerName = newGameRequest.getPlayerName();
        dao.insertNewSession(sessionId, BoardUtils.SAMPLE_BOARD, 1);
        RedisClient.AddNewSession(sessionId);
        RedisClient.AddPlayerToSession(sessionId, PlayerSide.BLUE, playerName);
        RedisClient.PrintPlayersInSession(sessionId);
        log.info("Created new session on server: " + sessionId);
        return "Created new session (Client Log): " + sessionId;
    }

    @PostMapping("/join")
    public String joinSession(@RequestBody JoinGameRequest joinGameRequest) {
        log.info("Joining a new session");
        String sessionId = joinGameRequest.getSessionId();
        String playerName = joinGameRequest.getPlayerName();
        RedisClient.AddPlayerToSession(sessionId, PlayerSide.RED, playerName);
        return "Joined session: " + sessionId;
    }

}
