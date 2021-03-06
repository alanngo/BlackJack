package resource.player;
import resource.card.*;

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
    * add a new resource.card to hand
    * @param c: a new resource.card
    * */
    public void draw(Card c){ hand.draw(c);}

    /**
     * show hand
     * @param o: PrintStream used
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
    protected void hit(Stack<Card> deck) { draw(deck.pop()); }

    @Override
    public String toString() {return "'s hand: "+hand.toString();}

}
