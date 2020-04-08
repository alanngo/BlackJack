package test;

import resource.card.*;
import resource.player.*;
import org.junit.*;


import java.io.*;
import java.util.*;

import static java.lang.System.*;
import static org.junit.Assert.*;
import static resource.card.Rank.*;
import static resource.card.Suit.*;

public class TestPlayer
{
//    private static final Suit[] suitVal = Suit.values();
//    private static final Rank[] rankVal = Rank.values();
    public TestPlayer() {  }

    @Before
    public void init() {out.println("\n*Testing player functionality*");}

    /*blackjack win*/
    @Test
    public void test0()
    {
        out.println("test 0: blackjack ");
        Player player = new Player();
        Stack<Card> deck = new Stack<>();
        deck.push(new Card(ACE, SPADE));
        deck.push(new Card(JACK, SPADE));

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
        deck.push(new Card(ACE, SPADE));
        deck.push(new Card(FIVE, HEART));
        deck.push(new Card(FIVE, CLUB));

        for (int i =0; i<3; i++)
            player.hit(deck);

        player.stand(out);
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
        deck.push(new Card(NINE, SPADE));
        deck.push(new Card(FIVE, HEART));
        deck.push(new Card(QUEEN, CLUB));

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
        deck.push(new Card(ACE, SPADE));
        deck.push(new Card(ACE, HEART));

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
        deck.push(new Card(NINE, SPADE));
        deck.push(new Card(ACE, HEART));
        deck.push(new Card(FIVE, HEART));

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
        deck.push(new Card(THREE, CLUB));
        deck.push(new Card(SIX, SPADE));
        deck.push(new Card(EIGHT, DIAMOND));
        deck.push(new Card(FIVE, HEART));
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

    /*split w/ pair*/
    @Test
    public void test6()
    {
        out.println("test 6: split hand with pair");
        Player player = new Player();
        Stack<Card> deck = new Stack<>();
        deck.push(new Card(THREE, CLUB));
        deck.push(new Card(SIX, SPADE));
        deck.push(new Card(FIVE, DIAMOND));
        deck.push(new Card(FIVE, HEART));

        player.hit(deck);
        player.hit(deck);
        assertTrue(player.hasPair());

        player.split(deck);
        player.stand(out);
        player.standSplit(out);
        assertFalse(player.hasPair());
    }

    /*split w/ pair of aces*/
    @Test
    public void test7()
    {
        out.println("test 7: split hand with pair");
        Player player = new Player();
        Stack<Card> deck = new Stack<>();
        deck.push(new Card(THREE, CLUB));
        deck.push(new Card(SIX, SPADE));
        deck.push(new Card(ACE, DIAMOND));
        deck.push(new Card(ACE, HEART));

        player.hit(deck);
        player.hit(deck);
        assertTrue(player.hasPair());

        player.split(deck);
        player.stand(out);
        player.standSplit(out);
        assertFalse(player.hasPair());
    }

    /*split causes blackjack*/
    @Test
    public void test8()
    {
        out.println("test 8: split hand with blackjack");
        Player player = new Player();
        Stack<Card> deck = new Stack<>();
        deck.push(new Card(KING, CLUB));
        deck.push(new Card(TEN, SPADE));
        deck.push(new Card(ACE, DIAMOND));
        deck.push(new Card(ACE, HEART));

        player.hit(deck);
        player.hit(deck);
        assertTrue(player.hasPair());

        player.split(deck);
        player.stand(out);
        player.standSplit(out);
        assertFalse(player.hasPair());
        assertTrue(player.blackjack());
        assertTrue(player.splitBlackjack());
    }

    @Test
    public void test9()
    {
        out.println("test 8: split hand busted");
        Player player = new Player();
        Stack<Card> deck = new Stack<>();

        deck.push(new Card(EIGHT, CLUB));
        deck.push(new Card(SEVEN, DIAMOND));
        deck.push(new Card(SIX, CLUB));
        deck.push(new Card(FIVE, SPADE));
        deck.push(new Card(JACK, DIAMOND));
        deck.push(new Card(TEN, HEART));

        player.hit(deck);
        player.hit(deck);
        assertTrue(player.hasPair());

        player.split(deck);
        player.hit(deck);
        player.hitSplit(deck);
        assertTrue(player.busted());
        assertTrue(player.splitBusted());

    }
}
