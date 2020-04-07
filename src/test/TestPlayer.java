package test;

import card.*;
import org.junit.*;
import player.*;

import java.io.*;
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
        Player player = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.ACE, Suit.SPADE));
        deck.add(new Card(Rank.JACK, Suit.SPADE));

        for (int i =0; i<2; i++)
            player.hit(deck);

        assertTrue(player.blackjack());
        assertFalse(player.busted());
    }

    /*non-blackjack win*/
    @Test
    public void test1()
    {
        out.println("test 1: non-blackjack win");
        Player player = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.ACE, Suit.SPADE));
        deck.add(new Card(Rank.FIVE, Suit.HEART));
        deck.add(new Card(Rank.FIVE, Suit.CLUB));

        for (int i =0; i<3; i++)
            player.hit(deck);

        assertFalse(player.blackjack());
        assertFalse(player.busted());
    }

    /*bust*/
    @Test
    public void test2()
    {
        out.println("test 2: bust ");
        Player player = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.NINE, Suit.SPADE));
        deck.add(new Card(Rank.FIVE, Suit.HEART));
        deck.add(new Card(Rank.QUEEN, Suit.CLUB));

        for (int i =0; i<3; i++)
            player.hit(deck);

        assertTrue(player.busted());
    }

    /*pair aces no bust*/
    @Test
    public void test3()
    {
        out.println("test 3: pair of aces ");
        Player player = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.ACE, Suit.SPADE));
        deck.add(new Card(Rank.ACE, Suit.HEART));

        for (int i =0; i<2; i++)
            player.hit(deck);

        player.stand(out);
        assertFalse(player.busted());
        assertTrue(player.hasPair());
    }

    /*soften ace*/
    @Test
    public void test4()
    {
        out.println("test 4: soften aces ");
        Player player = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.NINE, Suit.SPADE));
        deck.add(new Card(Rank.ACE, Suit.HEART));
        deck.add(new Card(Rank.FIVE, Suit.HEART));

        for (int i =0; i<3; i++)
            player.hit(deck);

        player.stand(out);
        assertFalse(player.busted());
    }

    /*split with no pair*/
    @Test
    public void test5() throws IOException
    {
        out.println("test 5: split hand with no pair");
        Player player = new Player();
        Stack<Card> deck = new Stack<>();
        deck.add(new Card(Rank.THREE, Suit.CLUB));
        deck.add(new Card(Rank.SIX, Suit.SPADE));
        deck.add(new Card(Rank.EIGHT, Suit.DIAMOND));
        deck.add(new Card(Rank.FIVE, Suit.HEART));
        try
        {
            player.hit(deck);
            player.hit(deck);

            player.split(deck);
        }
        catch(Exception e)
        {
            PrintWriter pw = new PrintWriter(new File("split.err"));
            e.printStackTrace(pw);
            pw.close();
        }
        finally
        {
            player.stand(out);
            assertFalse(player.hasPair());
        }
    }
}
