package player;
import bank.*;
import card.*;

import java.io.PrintStream;
import java.util.*;

public class Player extends AbstractPlayer
{
    private Account funds;
    private Hand splitHand;

    /**
     *  constructor instantiates bank to variable amount
     * @param amt: custom amount
     * */
    public Player(double amt)
    {
        super();
        funds = new Account(amt);
        splitHand = new Hand();
    }

    /**
     * default constructor instantiates bank to $100
     * */
    public Player() {this(100);}


    /*money related*/
    public double bet(double amt)
    {
        funds.withdraw(amt);
        return amt;
    }

    public void getPaid(double amt) { funds.deposit(amt);}

    /*card related*/
    /**
     * clears both split and main hand
     * */
    @Override
    public void clearHand()
    {
        super.clearHand();
        splitHand.clear();
    }

    /**
     * hit once from deck
     * @param deck: community deck
     * */
    @Override
    public void hit(Stack<Card> deck)
    {
        //check deck, avoid EmptyStackException
        if (deck.isEmpty())
            return;

        //pre-hit check
        if (hand.isBlackjack()||hand.isBusted())
            stand(System.out);

        super.hit(deck);

        //post-hit check
        if (hand.isBlackjack()||hand.isBusted())
            stand(System.out);
    }

    @Override
    public void stand(PrintStream o)
    {
        o.print("Player");
        super.stand(o);
        if (!splitHand.empty())
            o.println("Split Hand: "+splitHand.toString());
    }

    /* split related */
    public boolean hasPair() { return hand.containsPair(); }

    public void split(Stack<Card> deck)
    {
        if (hasPair())
            throw new NoPairException("No pair in hand");

        // add new card to main hand
        Card c = hand.discard();
        hit(deck);

        //draw
        splitHand.draw(c);
        splitHand.draw(deck.pop());
    }
    public void hitSplit(Stack<Card> deck)
    {
        if (deck.isEmpty())
            return;

        //pre-hit check
        if (splitHand.isBlackjack()||splitHand.isBusted())
            stand(System.out);

        super.hit(deck);

        //post-hit check
        if (splitHand.isBlackjack()||splitHand.isBusted())
            stand(System.out);
    }
}
