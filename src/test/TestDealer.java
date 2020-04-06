package test;

import card.*;
import org.junit.*;
import player.*;

import java.util.*;

import static java.lang.System.*;
import static org.junit.Assert.*;
public class TestDealer
{
	public TestDealer()
	{
		//nothing here
	}

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
		dealer.stand(out);

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
		deck.push(new Card(Rank.ACE, Suit.SPADE));
		deck.push(new Card(Rank.FIVE, Suit.HEART));
		deck.push(new Card(Rank.FIVE, Suit.CLUB));

		dealer.hit(deck);
		dealer.stand(out);

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
		dealer.stand(out);

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

	//stand on 17 or higher
	@Test
	public void test5()
	{
		out.println("test 5: stand on 17 or higher");
		Dealer dealer = new Dealer();
		Stack<Card> deck = new Stack<>();

		deck.push(new Card(Rank.QUEEN, Suit.HEART));
		deck.push(new Card(Rank.SEVEN, Suit.HEART));
		deck.push(new Card(Rank.ACE, Suit.HEART));
		deck.push(new Card(Rank.TWO, Suit.HEART));
		deck.push(new Card(Rank.NINE, Suit.SPADE));
		dealer.hit(deck);

		assertFalse(dealer.busted());
	}
}//end of testCases
