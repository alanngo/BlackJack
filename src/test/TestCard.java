package test;

import card.*;
import org.junit.*;

import static org.junit.Assert.*;
import static java.lang.System.*;
import static card.Rank.*;
import static card.Suit.*;

public class TestCard
{
    /*equals with same rank and suit*/
    @Test
    public void test0()
    {
        out.println("test 0: same suit same rank");
        Card c0 = new Card(ACE, CLUB);
        Card c1 = new Card(ACE, CLUB);
        assertEquals(c0, c1);
    }

    /*equals with same rank different suit suit*/
    @Test
    public void test1()
    {
        out.println("test 1: same suit different rank");
        Card c0 = new Card(ACE, CLUB);
        Card c1 = new Card(ACE, SPADE);
        assertEquals(c0, c1);
    }

    /*equals with different rank same suit*/
    @Test
    public void test2()
    {
        out.println("test 2: different suit same rank");
        Card c0 = new Card(ACE, CLUB);
        Card c1 = new Card(KING, CLUB);
        assertNotEquals(c0, c1);
    }

    /*equals with different rank same different*/
    @Test
    public void test3()
    {
        out.println("test 3: different suit different rank");
        Card c0 = new Card(ACE, HEART);
        Card c1 = new Card(KING, CLUB);
        assertNotEquals(c0, c1);
    }

    @Test
    public void test4()
    {
        out.println("test 4: cards with 10 value");
        Card c0 = new Card(TEN, DIAMOND);
        Card c1 = new Card(JACK, SPADE);
        Card c2 = new Card(QUEEN, HEART);
        Card c3 = new Card(KING, CLUB);
        assertEquals(c0, c1);
        assertEquals(c0, c2);
        assertEquals(c0, c3);
        assertEquals(c1, c2);
        assertEquals(c1, c3);
        assertEquals(c2, c3);
    }
}
