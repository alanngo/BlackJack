package player;
import card.*;

import java.io.*;
import java.util.*;
abstract class Player
{
    // current hand of cards
    protected Hand hand;

    protected Player() { hand = new Hand(); }

    /*
    * add a new card to hand
    * @param c: a new card
    * */
    public void draw(Card c){ hand.draw(c);}

    /*
    * show hand
    * */
     protected void stand(PrintStream o) { o.println("'s Hand: "+ hand.toString()); }

    /*
    * clears current hand
    * */
    public void clearHand(){hand.clear();}

    /*
    * check if busted
    * */
    public boolean busted() {return hand.isBusted();}

    /*
     * check if blackjack
     * */
    public boolean blackjack() {return hand.isBlackjack();}

    /*
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
