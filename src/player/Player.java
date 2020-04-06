package player;
import bank.*;
import card.*;

import java.util.*;

public class Customer extends Player
{
    private Account funds;
    private Hand splitHand;
    private double doubleAmt = 0;

    public Customer(double amt)
    {
        super();
        funds = new Account(amt);
        splitHand = new Hand();
    }

    public Customer() {this(100);}


    /*money related*/
    public double bet(double amt)
    {
        funds.withdraw(amt);
        return amt;
    }

    public void getPaid(double amt) { funds.deposit(amt);}

    /*card related*/
    public void split(Stack<Card> deck)
    {
        if (!hand.containsPair())
            throw new NoPairException("No pair in hand");

        // add new card to main hand
        Card c = hand.discard();
        hit(deck);

        //draw
        splitHand.draw(c);
        splitHand.draw(deck.pop());
    }

    @Override
    public void clearHand()
    {
        super.clearHand();
        splitHand.clear();
    }

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
    }

}
