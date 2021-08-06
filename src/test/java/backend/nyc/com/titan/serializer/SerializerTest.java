package backend.nyc.com.titan.serializer;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.enums.PlayerSide;
import backend.nyc.com.titan.model.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SerializerTest {

    Flag r_f = new Flag(PlayerSide.RED);
    Bomb r_b = new Bomb(PlayerSide.RED);
    Marshall r_10_1 = new Marshall(PlayerSide.RED);
    Marshall r_10_2 = new Marshall(PlayerSide.RED);
    Terrain t_1 = new Terrain(PlayerSide.NON_PLAYER);
    Empty e_1 = new Empty(PlayerSide.NON_PLAYER);
    Flag b_f = new Flag(PlayerSide.BLUE);
    Bomb b_b = new Bomb(PlayerSide.BLUE);
    Marshall b_10_1 = new Marshall(PlayerSide.BLUE);
    Marshall b_10_2 = new Marshall(PlayerSide.BLUE);

    Piece[] red_one = new Piece[]{r_f, r_b};
    Piece[] red_two = new Piece[]{r_10_1, r_10_2};
    Piece[] empty_one = new Piece[]{e_1, t_1};
    Piece[] blue_one = new Piece[]{b_f, b_b};
    Piece[] blue_two = new Piece[]{b_10_1, b_10_2};

    Piece[][] testOne = new Piece[][]{red_one, red_two, empty_one, blue_two, blue_one};
    Piece[][] testTwo = new Piece[][]{red_two, empty_one, blue_two};

    String fakeId = "12345";

    @Test
    void serialize_1() {
        String output = Serializer.serializeBoard(fakeId, testOne, "B");
        assertEquals("5~2@F~B~10~10~E~T~10~10~F~B@R~R~R~R~E~E~B~B~B~B@B", output);
    }

    @Test
    void serialize_2() {
        String output = Serializer.serializeBoard(fakeId, testTwo, "R");
        assertEquals("3~2@10~10~E~T~10~10@R~R~E~E~B~B@R", output);
    }

    @Test
    void serialize_3() {
        String output = Serializer.serializeBoard(fakeId, null, "");
        assertEquals("", output);
    }

    @Test
    void serialize_4() {
        String output = Serializer.serializeBoard(fakeId, new Piece[][]{}, "");
        assertEquals("", output);
    }

    @Test
    void deserialize_1() {
        Piece[][] output = Serializer.deserializeBoard("5~2@F~B~10~10~E~T~10~10~F~B@R~R~R~R~E~E~B~B~B~B");
        assertTrue(areEqual(testOne, output));
    }

    @Test
    void deserialize_2() {
        Piece[][] output = Serializer.deserializeBoard("3~2@10~10~E~T~10~10@R~R~E~E~B~B");
        assertTrue(areEqual(testTwo, output));
    }

    @Test
    void deserialize_3() {
        Piece[][] output = Serializer.deserializeBoard("");
        assertTrue(areEqual(new Piece[][]{}, output));
    }

    @Test
    void deserialize_4() {
        Piece[][] output = Serializer.deserializeBoard(null);
        assertTrue(areEqual(new Piece[][]{}, output));
    }

    public boolean areEqual(Piece[][] expected, Piece[][] actual) {
        if (expected == null && actual == null) {
            return true;
        }
        if (expected == null || actual == null) {
            return false;
        }
        if (expected.length != actual.length) {
            return false;
        }
        if (expected.length == 0) {
            return true;
        }
        if (expected[0].length != actual[0].length) {
            return false;
        }
        if (expected[0].length == 0) {
            return true;
        }
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                if (expected[i][j].getType() != actual[i][j].getType()) {
                    return false;
                }
            }
        }
        return true;
    }

}
