import java.util.*;

public class Customer extends Player 
{
	private Stack<Card> splitHand;
	private Account acc;//customer's account
	private Scanner in; //scanner
	private boolean dd; //customer doubled down
	private boolean sp; //customer made a split
	private boolean nb; //customer bet more than balance
	
	public Customer(double bet)//constructor
	{
		super();
		splitHand= new Stack<Card>();//create a new hand
		in = new Scanner(System.in);
		acc=new Account(bet);//start a new account with $1K
		dd= false;
		sp= false;
		nb= false;
	}
	
	//card interactions
	public void clearHand()/**@override**/
	{
		super.clearHand();
		splitHand.clear();
		dd=false;
		sp=false;
		nb=false;
	}//end of clearHand
	public void showHand()
	{
		System.out.println("______________Your Hand______________");
		show(hand);
		if(checkForAces())
		{
			/**/System.out.println("Your hand value: "+getHandValue());
		}
		else
		{
			/**/System.out.println("Your hand value: "+getHandValue());
		}
		System.out.println("___________________________________________");
	}	//end of showHand()
	
	//Account functions
	public Account account()
	{
		return acc;
	}
	public void makeBet(double x)
	{
		try
		{
			acc.withdraw(x);
		}
		catch(NegativeBalanceException e)
		{
			nb=true;
			System.out.println("Trying to win on deficit huh? ");
		}
	}
	
	//condition related
	public boolean doubledDown()
	{
		return dd;
	}
	public boolean splitted()
	{
		return sp;
	}
	public boolean overBetted()
	{
		return nb;
	}
	public boolean gotBustedOnSplit()
	{
		return (getSplitHandValue()>21);
	}
	public boolean gotBlackjackOnSplit()
	{
		return (getSplitHandValue()==21);
	}

	//interacts with the first hand
	public void hit(Stack <Card> deck)/**@override**/
	{
		super.hit(deck);
		//System.out.print(temp);
		/**/showHand();
		checkForAces();
		if (gotBusted()||gotBlackjack())
		{
			showHand();
			return;
		}
		System.out.print("Go again? 1.yes 2.no ");
		int again= in.nextInt();
		if (again==1 &&!gotBusted()&&!gotBlackjack())
		{
			hit(deck);
		}
		else
		{
			stand();
			return;
		}
	}//end of hit
	public void stand()
	{
		System.out.println("***************YOUR FINAL HAND***************");
		showHand();
		//showSplitHand();
		System.out.println("*********************************************");
	}//end of stand
	public void doubleDown(Stack <Card> deck, double bet)
	{
		dd=true;
		makeBet(bet);
		Card temp=deck.pop();
		hand.push(temp);
		showHand();
		if (gotBusted())
		{
			System.out.println("You Busted! Value:"	+getHandValue());
			return;
		}
		if (gotBlackjack())
		{
			System.out.println("BLACKJACK!");
			return;
		}
		
	}//end of doubleDown
	
	//interacts with the split hand
	public boolean checkSplitHand()//checks the split hand for aces
	{
		boolean containsAces=false;//check for aces in the second hand
		for (Card c : splitHand)
		{
			if (c.getFace().equals("A")&&getSplitHandValue()>21)
			{
				c.soften();
				containsAces= true;
			}
		}
		if (splitHand.get(0).getFace().equals("A")&&splitHand.get(1).getFace().equals("A"))//check for pair aces
		{
			splitHand.get(0).soften();
			splitHand.get(1).soften();
		}
		return containsAces;
	}//end of checkSplitHand
	public void hitSplit(Stack <Card> deck)
	{
		Card temp=deck.pop();
		splitHand.push(temp);
		//System.out.print(temp);
		/**/showSplitHand();
		checkSplitHand();
		if (gotBustedOnSplit()||gotBlackjackOnSplit())
		{
			showSplitHand();
			return;
		}
		System.out.print("Go again? 1.) yes 2.) no");
		int again= in.nextInt();
		if (again==1&&!gotBusted()&&!gotBlackjack())
		{
			hitSplit(deck);
		}
		else
		{
			standSplit();
			return;
		}
	}//end of hit
	public void standSplit()//stand on the split hand
	{
		System.out.println("***************YOUR FINAL SPLIT HAND***************");
		if (splitHand.isEmpty())
		{
			System.out.println("NONE");
		}
		else
		{
			showSplitHand();
		}
		System.out.println("*********************************************");
	}//end of standSplit
	public void doubleDownSplit(Stack <Card> deck, double bet)
	{
		dd=true;
		makeBet(bet);
		Card temp=deck.pop();
		splitHand.push(temp);
		showHand();
		if (gotBustedOnSplit())
		{
			System.out.println("You Busted! Value:"	+getHandValue());
			return;
		}
		if (gotBlackjackOnSplit())
		{
			System.out.println("BLACKJACK!");
			return;
		}
		
	}//end of doubleDown
	public Stack<Card> getSplitHand() 
	{
		return splitHand;
	}//end of getHand
	public void showSplitHand()
	{
		if (splitHand.isEmpty())
		{
			return;
		}
		else
		{
			System.out.println("______________Split Hand______________");
			super.show(splitHand);
			if(checkSplitHand())
			{
				/**/System.out.println("Split hand value: "+getSplitHandValue());
			}
			else
			{
				/**/System.out.println("Split hand value: "+getSplitHandValue());
			}
			System.out.println("___________________________________________");
		}
		
	}	//end of showSplitHand
	public int getSplitHandValue()
	{
		int count=0;
		for (Card c: splitHand)
		{
			count+=c.getValue();
		}
		return count;
	}//end of getSplitHandValue
	private void splitHelper(int choice, Stack<Card>deck, double bet)
	{
		if (gotBlackjackOnSplit())
		{
			return;
		}
		System.out.print("Choice on split hand? 1.(hit), 2.(stand) 3.(double down) ");//for the split hand
		choice = in.nextInt();
		switch (choice)
		{
			case 1:
				hitSplit(deck);
				break;
			case 2: 
				standSplit();
				break;
			case 3: 
				doubleDownSplit(deck, bet);
				break;
		}
	}//end of splitHelper
	
	public void split(Stack <Card> deck, double bet) throws NoPairException //TODO
	{
		int firstCardValue=hand.get(0).getValue();
		int secondCardValue=hand.get(1).getValue();
		int choice =0;
		if (firstCardValue!=(secondCardValue))
		{
			throw new NoPairException("You do not have a pair! No split allowed!");
		}
		else
		{
			makeBet(bet);
			sp=true;
			//split action
			Card temp = hand.pop();
			splitHand.push(temp);
			Card temp2= deck.pop();
			hand.push(temp2);
			Card temp3=deck.pop();
			splitHand.push(temp3);
			
			//handle aces
			if (hand.get(0).getValue()==1&&hand.get(1).getValue()!=1)
			{
				hand.get(0).setValue(11);
			}
			if (splitHand.get(0).getValue()==1&&splitHand.get(1).getValue()!=1)
			{
				splitHand.get(0).setValue(11);
			}
			
			showHand();
			showSplitHand();
			boolean flag=false;
			if (gotBlackjack())
			{
				flag=true;
				splitHelper(choice, deck, bet);
				return;
			}
			System.out.print("Choice on first hand? 1.(hit), 2.(stand) 3.(double down) ");//for the first hand
			choice = in.nextInt();
			switch (choice)
			{
				case 1:
					hit(deck);
					break;
				case 2: 
					stand();
					break;
				case 3: 
					doubleDown(deck, bet);
					break;
			}
			if (gotBlackjackOnSplit())
			{
				return;
			}
			if (!flag)
			{
				splitHelper(choice, deck, bet);
			}
		}
	}//end of split
	
	public String toString()
	{
		return "Customer";
	}
}
