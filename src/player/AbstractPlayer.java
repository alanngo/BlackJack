package player;
import card.*;

import java.io.*;
import java.util.*;
abstract class AbstractPlayer
{
    // current hand of cards
    protected Hand hand;

    /**
     * protected constructor
     * */
    protected AbstractPlayer() { hand = new Hand(); }

    /**
    * add a new card to hand
    * @param c: a new card
    * */
    protected void draw(Card c){ hand.draw(c);}

    /**
    * show hand
     * @param o: show hand in console
    * */
    protected void stand(PrintStream o) { o.println("'s Hand: "+ hand.toString()); }

    /**
    * clears current hand
    * */
    public void clearHand(){hand.clear();}

    /**
    * check if busted
    * @return is hand over 21
    * */
    public boolean busted() {return hand.isBusted();}

    /**
     * check if blackjack
     * @return is hand value 21 and size < 2
     * */
    public boolean blackjack() {return hand.isBlackjack();}

    /**
     * add cards from deck to hand
     * @param deck: community deck
     * */
    protected void hit(Stack<Card> deck)
    {
        Card tmp = deck.pop();
        draw(tmp);
    }

    @Override
    public String toString() {return "Player's hand: "+hand.toString();}

}
