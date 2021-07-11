package backend.nyc.com.titan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class GameController {

    @GetMapping("/board")
    @ResponseStatus(HttpStatus.OK)
    public String getBoard() {
        log.info("Returning board");
        return "<!5~2~F~B~4~4~E~T~5~5~B~F!>@<!2~2~2~2~0~0~1~1~1~1!>";
    }

}
