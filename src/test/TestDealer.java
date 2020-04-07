package test;

import card.*;
import org.junit.*;
import player.*;

import java.util.*;

import static java.lang.System.*;
import static org.junit.Assert.*;
import static card.Rank.*;
import static card.Suit.*;
public class TestDealer
{
	private static final Suit[] suitVal = Suit.values();
	private static final Rank[] rankVal = Rank.values();
	public TestDealer() {}

	private static Stack<Card> createDeck()
	{
		Stack<Card> ret = new Stack<>();
		for (Rank rank: rankVal)
		{
			for (Suit suit: suitVal)
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

		deck.push(new Card(ACE, SPADE));
		deck.push(new Card(JACK, SPADE));

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

		deck.push(new Card(FIVE, DIAMOND));
		deck.push(new Card(ACE, SPADE));
		deck.push(new Card(FIVE, HEART));
		deck.push(new Card(FIVE, CLUB));

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

		deck.push(new Card(NINE, SPADE));
		deck.push(new Card(FIVE, HEART));
		deck.push(new Card(QUEEN, CLUB));

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

		deck.push(new Card(ACE, SPADE));
		deck.push(new Card(ACE, HEART));
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

		deck.push(new Card(NINE, SPADE));
		deck.push(new Card(ACE, HEART));
		deck.push(new Card(FIVE, HEART));
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

		deck.push(new Card(JACK, DIAMOND));
		deck.push(new Card(EIGHT, DIAMOND));
		deck.push(new Card(NINE, SPADE));
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

		deck.push(new Card(EIGHT, DIAMOND));
		deck.push(new Card(SIX, DIAMOND));
		deck.push(new Card(ACE, SPADE));
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
			deck.push(new Card(ACE, SPADE));
			dealer.hit(deck);
			dealer.stand(out);
			out.println("_________________");
			assertFalse(dealer.busted());
		}
	}
}//end of testCases
