package test;

import card.Card;
import card.Rank;
import card.Suit;
import org.junit.*;
import player.*;

import java.util.*;

import static java.lang.System.*;
import static org.junit.Assert.*;

public class TestPlayer
{
    public TestPlayer() { }

    /*blackjack win*/
    @Test
    public void test0()
    {
        out.println("test 0: blackjack ");
        Player customer = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.ACE, Suit.SPADE));
        deck.add(new Card(Rank.JACK, Suit.SPADE));

        for (int i =0; i<2; i++)
            customer.hit(deck);

        customer.stand(out);
        assertTrue(customer.blackjack());
        assertFalse(customer.busted());
    }

    /*non-blackjack win*/
    @Test
    public void test1()
    {
        Player customer = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.ACE, Suit.SPADE));
        deck.add(new Card(Rank.FIVE, Suit.HEART));
        deck.add(new Card(Rank.FIVE, Suit.CLUB));

        for (int i =0; i<3; i++)
            customer.hit(deck);

        customer.stand(out);
        assertFalse(customer.blackjack());
        assertFalse(customer.busted());
    }

    /*bust*/
    @Test
    public void test2()
    {
        Player customer = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.NINE, Suit.SPADE));
        deck.add(new Card(Rank.FIVE, Suit.HEART));
        deck.add(new Card(Rank.QUEEN, Suit.CLUB));

        for (int i =0; i<3; i++)
            customer.hit(deck);

        customer.stand(out);
        assertTrue(customer.busted());
    }

    /*pair aces no bust*/
    @Test
    public void test3()
    {
        Player customer = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.ACE, Suit.SPADE));
        deck.add(new Card(Rank.ACE, Suit.HEART));

        for (int i =0; i<2; i++)
            customer.hit(deck);

        customer.stand(out);
        assertFalse(customer.busted());
    }

    /*soften ace*/
    @Test
    public void test4()
    {
        Player customer = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.NINE, Suit.SPADE));
        deck.add(new Card(Rank.ACE, Suit.HEART));
        deck.add(new Card(Rank.FIVE, Suit.HEART));

        for (int i =0; i<3; i++)
            customer.hit(deck);

        customer.stand(out);
        assertFalse(customer.busted());
    }
}
