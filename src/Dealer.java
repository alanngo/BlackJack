import java.util.*;

public class Dealer extends Player
{
	public Dealer()//constructor
	{
		super();
	}

	//deals to dealer and customer
	public void deal(Stack<Card> deck)
	{
		for (int i=0; i<2; i++)
		{
			Card temp=deck.pop();
			hand.push(temp);
		}
	}//end of deal()
	public void dealToCustomer(Customer bob, Stack<Card> deck)
	{
		for (int i=0; i<2; i++)
		{
			Card temp=deck.pop();
			bob.hand.push(temp);
		}
	}//end of dealToCustomer

	//game functions
	public void showInitialHand()
	{
		System.out.println("______________Dealer Hand______________");
		show(hand.peek());
		System.out.println("Dealer Hand Value: "+hand.peek().getValue());
		System.out.println("___________________________________________");
	}
	public void showHand()
	{
		System.out.println("______________Dealer Hand______________");
		show(hand);
		if(checkForAces())
		{
			/**/System.out.println("Dealer hand value: "+getHandValue());
		}
		else
		{
			/**/System.out.println("Dealer hand value: "+getHandValue());
		}
		System.out.println("___________________________________________");
	}	//end of showHand()
	public void hit(Stack <Card> deck)/**@override**/
	{
		if (getHandValue()>=17)//stand on 17 or higher
		{
			stand();
			return;
		}
		super.hit(deck);
		//showHand();
		checkForAces();
		if (getHandValue()>=17)
		{
			stand();
			return;
		}
		if (gotBusted())
		{
			//showHand();
			System.out.println("Dealer Busted!");
			return;
		}
		if (gotBlackjack())
		{
			//showHand();
			System.out.println("Dealer BLACKJACK!");
			return;
		}
		if (getHandValue()<17)// hit under 17
		{
			hit(deck);
		}
		else
		{
			System.out.println("DEALER ERROR");
		}
		
	}//end of hit
	public void stand()
	{
		System.out.println("***************DEALER FINAL HAND***************");
		showHand();
		System.out.println("*********************************************");
	}//end of stand
	
	//payment functions
	public void pay(Customer c, double bet)//pays if the customer wins
	{
		c.account().deposit(2*bet);
		if (c.doubledDown())
		{
			c.account().deposit(2*bet);
		}
	}//end of pay
	public void payForBlackJack(Customer c, double bet)//pays if the customer gets blackjack
	{
		pay(c, 2*bet);
	}//end of payForBlackJack
	public void payForPush(Customer c, double bet)//pays if the customer gets a push
	{
		pay(c, .5*bet);
	}//end of payForPush
}

