package backend.nyc.com.titan.redis;

import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.enums.PlayerSide;
import lombok.extern.java.Log;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Log
public class RedisClient {

    private final static char[] PLAYER_TYPES = new char[]{'B', 'R', 'S'};

    private final static Jedis jedis = new Jedis("redis");

    public RedisClient() { }

    public static void AddPlayerToSession(String sessionId, PlayerSide playerSide, String playerName) {
        String key = getPlayerKey(sessionId, playerSide);
        jedis.set(key, playerName);
        log.info("Added " + playerName + " to session " + sessionId + " as the " + playerSide.name() + " player");
    }

    private static PlayerSide playerSideCharToPlayerSide(char c) {
        if (c == 'B') {
            return PlayerSide.BLUE;
        } else if (c == 'R') {
            return PlayerSide.RED;
        } else {
            return PlayerSide.SPECTATOR;
        }
    }

    public static List<Player> GetPlayerListFromSession(String sessionId) {
        List<Player> players = new ArrayList<>();
        for (char c : PLAYER_TYPES) {
            String key = sessionId + "_" + c;
            String value = jedis.get(key);
            if (value != null) {
                players.add(new Player(value, playerSideCharToPlayerSide(c)));
            }
        }
        return players;
    }

    public static Player GetPlayerFromSession(String sessionId, PlayerSide playerSide) {
        String key = getPlayerKey(sessionId, playerSide);
        String playerName = jedis.get(key);
        if (playerName == null) {
            log.info("No value found for key: " + key);
            return null;
        } else {
            return new Player(playerName, playerSide);
        }
    }

    public static void AddNewSession(String sessionId) {
        AddPlayerToSession(sessionId, PlayerSide.NON_PLAYER, "Spectator");
    }

    public static void PrintPlayersInSession(String sessionId) {
        log.info("----------- List of Players in " + sessionId + " -------------");
        for (char c : PLAYER_TYPES) {
            String key = sessionId + "_" + c;
            String value = jedis.get(key);
            if (value != null) {
                log.info("(" + c + ")" + value);
            }
        }
        log.info("-----------------------------------------------------------");
    }

    private static String getPlayerKey(String sessionId, PlayerSide playerSide) {
        String key;
        if (playerSide == PlayerSide.BLUE) {
            key = sessionId + "_B";
        } else if (playerSide == PlayerSide.RED) {
            key = sessionId + "_R";
        } else {
            key = sessionId + "_S";
        }
        return key;
    }

}
