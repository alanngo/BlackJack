package service;

import resource.card.*;
import resource.player.*;
import java.util.*;

import static java.util.Collections.*;

public class Game
{
    private static final Suit[] suitVal = Suit.values();
    private static final Rank[] rankVal = Rank.values();

    private static Stack<Card> createDeck(int num)
    {
        Stack<Card> ret = new Stack<>();
        for (int i =0; i<num; i++)
        {
            for (Rank rank: rankVal)
            {
                for (Suit suit: suitVal)
                    ret.push(new Card (rank, suit));
            }
            shuffle(ret);
        }

        return ret;
    }

    private Dealer dealer;
    private Player player;

    private Stack<Card> deck;

    public Game()
    {
        dealer = new Dealer();
        player = new Player();
        deck = createDeck(1);
    }

    public void deal()
    {
        //1st card
        dealer.draw(deck.pop());
        player.draw(deck.pop());

        //2nd card
        dealer.draw(deck.pop());
        player.draw(deck.pop());
    }

    public void checkBlackjack()
    {

    }

}
