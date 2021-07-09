import backend.nyc.com.titan.model.Board;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.Session;
import backend.nyc.com.titan.serializer.Serializer;

public class Runner {

    public static void main(String[] args) {
        Session session = new Session("<!5~2~F~B~4~4~E~T~5~5~B~F!>@<!2~2~2~2~0~0~1~1~1~1!>");

        String savedBoard = Serializer.serializeBoard(session.getBoard().getPieces());

        Board board = new Board(savedBoard);

        session.getBoard().printBoard();
        System.out.println("---------");

        session.getBoard().movePiece(new Player(1),3,0, 2, 0);
        session.getBoard().printBoard();
        System.out.println("---------");

        session.getBoard().movePiece(new Player(1),2, 0, 1, 0);
        session.getBoard().printBoard();
        System.out.println("---------");

        session.getBoard().movePiece(new Player(1),1, 0, 0, 0);
        session.getBoard().printBoard();
        System.out.println("---------");
    }

}
