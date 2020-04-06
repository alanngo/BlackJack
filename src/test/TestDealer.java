package test;

import card.*;
import org.junit.*;
import player.*;

import java.util.*;

import static java.lang.System.*;
import static org.junit.Assert.*;
public class TestDealer
{
	public TestDealer() {}

	private static Stack<Card> createDeck()
	{
		Stack<Card> ret = new Stack<>();
		for (Rank rank: Rank.values())
		{
			for (Suit suit: Suit.values())
				ret.push(new Card (rank, suit));
		}
		Collections.shuffle(ret);
		return ret;
	}

	/*blackjack win*/
	@Test
	public void test0()
	{
		out.println("test 0: blackjack ");
		Dealer dealer = new Dealer();
		Stack<Card> deck = new Stack<>();

		deck.push(new Card(Rank.ACE, Suit.SPADE));
		deck.push(new Card(Rank.JACK, Suit.SPADE));

		dealer.hit(deck);

		assertTrue(dealer.blackjack());
		assertFalse(dealer.busted());
	}

	/*non-blackjack win*/
	@Test
	public void test1()
	{
		out.println("test 1: non blackjack win");
		Dealer dealer = new Dealer();
		Stack<Card> deck = new Stack<>();

		deck.push(new Card(Rank.FIVE, Suit.DIAMOND));
		deck.push(new Card(Rank.ACE, Suit.SPADE));
		deck.push(new Card(Rank.FIVE, Suit.HEART));
		deck.push(new Card(Rank.FIVE, Suit.CLUB));

		dealer.hit(deck);

		assertFalse(dealer.blackjack());
		assertFalse(dealer.busted());
	}

	/*bust*/
	@Test
	public void test2()
	{
		out.println("test 2: bust");
		Dealer dealer = new Dealer();
		Stack<Card> deck = new Stack<>();

		deck.push(new Card(Rank.NINE, Suit.SPADE));
		deck.push(new Card(Rank.FIVE, Suit.HEART));
		deck.push(new Card(Rank.QUEEN, Suit.CLUB));

		dealer.hit(deck);

		assertTrue(dealer.busted());
	}

	/*pair aces no bust*/
	@Test
	public void test3()
	{
		out.println("test 3: pair of aces");
		Dealer dealer = new Dealer();
		Stack<Card> deck = new Stack<>();

		deck.push(new Card(Rank.ACE, Suit.SPADE));
		deck.push(new Card(Rank.ACE, Suit.HEART));
		dealer.hit(deck);
		dealer.stand(out);

		assertFalse(dealer.busted());
	}

	/*soften ace*/
	@Test
	public void test4()
	{
		out.println("test 4: soften ace");
		Dealer dealer = new Dealer();
		Stack<Card> deck = new Stack<>();

		deck.push(new Card(Rank.NINE, Suit.SPADE));
		deck.push(new Card(Rank.ACE, Suit.HEART));
		deck.push(new Card(Rank.FIVE, Suit.HEART));
		dealer.hit(deck);
		dealer.stand(out);

		assertFalse(dealer.busted());
	}

	/*stand on hard 17 or higher*/
	@Test
	public void test5()
	{
		out.println("test 5: stand on hard 17 or higher");
		Dealer dealer = new Dealer();
		Stack<Card> deck = new Stack<>();

		deck.push(new Card(Rank.JACK, Suit.DIAMOND));
		deck.push(new Card(Rank.EIGHT, Suit.DIAMOND));
		deck.push(new Card(Rank.NINE, Suit.SPADE));
		dealer.hit(deck);

		assertFalse(dealer.busted());
	}

	/*stand on soft 17 or higher*/
	@Test
	public void test6()
	{
		out.println("test 6: stand on soft 17 or higher");
		Dealer dealer = new Dealer();
		Stack<Card> deck = new Stack<>();

		deck.push(new Card(Rank.EIGHT, Suit.DIAMOND));
		deck.push(new Card(Rank.SIX, Suit.DIAMOND));
		deck.push(new Card(Rank.ACE, Suit.SPADE));
		dealer.hit(deck);

		assertFalse(dealer.busted());
	}

	/* 'n' aces*/
	@Test
	public void test7()
	{
		out.println("test 7: n amount of aces");
		Dealer dealer = new Dealer();
		Stack<Card> deck = new Stack<>();

		for (int i =1; i<=21; i++)
		{
			out.println(i+" aces");
			deck.push(new Card(Rank.ACE, Suit.SPADE));
			dealer.hit(deck);
			dealer.stand(out);
			out.println("_________________");
			assertFalse(dealer.busted());
		}
	}
}//end of testCases
