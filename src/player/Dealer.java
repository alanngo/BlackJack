package player;

import card.Card;

import java.io.PrintStream;
import java.util.*;
public class Dealer extends Player
{
    public Dealer(){super();}

    @Override
    public void hit(Stack<Card> deck)
    {
        //check deck, avoid EmptyStackException
        if (deck.isEmpty())
            return;

        //pre-hit check
        if (hand.isBlackjack()||hand.isBusted())
            return;

        super.hit(deck);

        //post-hit check
        if (hand.isBlackjack()||hand.isBusted())
            return;

        //dealer must stand on 17 or higher
        if (hand.getHandValue()>=17)
        {
            stand(System.out);
            return;
        }

        //hit again
        hit(deck);
    }

    @Override
    public void stand(PrintStream o)
    {
        o.print("Dealer");
        super.stand(o);
    }

}
