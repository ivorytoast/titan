package backend.nyc.com.titan.controller;

import backend.nyc.com.titan.common.Utils;
import backend.nyc.com.titan.domain.SessionDB;
import backend.nyc.com.titan.domain.SessionRepository;
import backend.nyc.com.titan.model.Session;
import backend.nyc.com.titan.model.requests.BoardUpdateRequest;
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

    @GetMapping("/board")
    @ResponseStatus(HttpStatus.OK)
    public String getBoard() {
        log.info("Returning board");
        return "<!5~2~F~B~4~4~E~T~5~5~B~F!>@<!2~2~2~2~0~0~1~1~1~1!>";
    }

    @GetMapping("/db/board")
    @ResponseStatus(HttpStatus.OK)
    public String getBoardFromLatestVersionOfSession() {
        log.info("Returning board");
        return dao.getBoardFromLatestVersionOfSession("B1212345");
    }

    @PostMapping("/update/board")
    public String getBoard(@RequestBody BoardUpdateRequest updateRequest) {
        log.info("Updating board");
        String updatedBoard = updateRequest.getNewBoard();
        SessionDB session = dao.getLatestVersionOfSession("B1212345");
        int version = session.getVersion();
        pub.addToUpdates(updatedBoard);
        dao.insertNewSessionVersion(updatedBoard, version + 1);
        return updatedBoard;
    }

    @GetMapping("/new/session")
    @ResponseStatus(HttpStatus.OK)
    public String newSession() {
        log.info("Creating a new session");
        dao.insertNewSession("B1212346", Utils.SAMPLE_BOARD, 1);
        return "Created new session: " + "B1212346";
    }

}
