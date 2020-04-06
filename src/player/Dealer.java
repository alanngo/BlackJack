package player;

import card.Card;

import java.io.PrintStream;
import java.util.*;
public class Dealer extends AbstractPlayer
{
    public Dealer(){super();}

    /**
     * draw from deck until dealer lands on 17
     * */
    @Override
    public void hit(Stack<Card> deck)
    {
        //check deck, avoid EmptyStackException
        if (deck.isEmpty())
            return;

        //pre-hit check
        if (hand.isBlackjack()||hand.isBusted())
        {
            stand(System.out);
            return;
        }

        super.hit(deck);

        //post-hit check
        if (hand.isBlackjack()||hand.isBusted())
        {
            stand(System.out);
            return;
        }

        //dealer must stand on 17 or higher
        if (hand.getHandValue()>=17)
        {
            stand(System.out);
            return;
        }

        //hit again if under 17
        hit(deck);
    }

    @Override
    public void stand(PrintStream o)
    {
        o.print("Dealer");
        super.stand(o);
    }

}
