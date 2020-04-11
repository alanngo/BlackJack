import service.*;
import java.util.*;
import static java.lang.System.*;
public class Main
{

    public static void main(String[] args)
    {
        Game game = new Game();

        //start game
        out.print("Place bets: ");
        double bet = new Scanner(in).nextDouble();
        game.placeBet(bet);
        game.deal();
    }
}
