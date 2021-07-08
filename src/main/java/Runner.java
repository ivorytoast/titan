import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.Session;

public class Runner {

    public static void main(String[] args) {
        Session session = new Session("<!5~2~F~B~5~5~E~T~5~5~B~F!>@<!2~2~2~2~0~0~1~1~1~1!>");
        session.getBoard().printBoard();
        Player tony = new Player(1);
        Player luke = new Player(2);
        session.getBoard().movePiece(tony,3,0, 2, 0);
        session.getBoard().movePiece(tony,2,0, 3, 0);
        System.out.println("---------");
        session.getBoard().printBoard();
    }

}
