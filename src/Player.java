import java.util.*;
public abstract class Player 
{
	protected Stack<Card>hand;
	
	public Player()
	{
		hand= new Stack<Card>();
	}
	public Stack<Card> getHand() 
	{
		return hand;
	}//end of getHand
	public boolean checkForAces()//if any aces exist that would cause a bust, change ace value to 1
	{
		boolean containsAces=false;
		for (Card c : hand)
		{
			if ((c.getFace().equals("A")&&getHandValue()>21)||(hand.get(0).getFace().equals("A")&&hand.get(1).getFace().equals("A")))
			{
				c.soften();
				containsAces= true;
			}
		}
		/*if (hand.get(0).getFace().equals("A")&&hand.get(1).getFace().equals("A"))//check for pair aces
		{
			hand.get(0).soften();
			hand.get(1).soften();
		}*/
		return containsAces;
	}
	public int getHandValue()
	{
		int count=0;
		for (Card c: hand)
		{
			count+=c.getValue();
		}
		return count;
	}//end of getHandValue
	public boolean gotBusted()
	{
		return getHandValue()>21;
	}
	public boolean gotBlackjack()
	{
		return getHandValue()==21;
	}
	public void clearHand()
	{
		hand.clear();
	}//end of clearHand

	//game related
	public void hit(Stack<Card>deck)
	{
		Card temp=deck.pop();
		hand.push(temp);
	}
	//other functions
	public void show(Stack <Card> cards)
	{
		for (Card c: cards)
		{
			System.out.print(c+" ");	
		}
		System.out.println("");
	}//end of show
	public void show(Card card)//used to show the initial dealer hand
	{
		System.out.println(card);
	}
}
