import java.util.*;
public class Game 
{
	private Scanner in;
	private boolean splitFlagIsThrown; //if customer splits without a pair
	public Game()//constructor
	{
		in= new Scanner(System.in);
		splitFlagIsThrown=false;
	}
	
	public boolean threwSplitFlag()
	{
		return splitFlagIsThrown;
	}
	
	public Stack<Card> createDeck()//start a new deck
	{
		Stack<Card> deck = new Stack<Card>();
		String [] face= {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		String [] suit ={"Spade","Heart","Diamond","Club"};
		for (int n=0; n<8; n++)//create 8 decks
		{
			for (int x=0; x<suit.length; x++)
			{
				for (int i=0; i<face.length-4; i++)//pushes the number cards
				{
					deck.push(new Card(face[i], i+2,suit[x]));
				}
				for (int i=9; i<face.length-1; i++)//pushes the face cards
				{
					deck.push(new Card(face[i], 10,suit[x]));
				}
				deck.push(new Card(face[face.length-1], 11,suit[x]));//pushes aces
			}
		}
		return deck;
	}//end of create deck
	public void showInitialHand(Customer customer, Dealer dealer)
	{
		customer.showHand();
		dealer.showInitialHand();
	}
	public void showFinalHand(Customer customer, Dealer dealer)
	{
		customer.stand();
		customer.standSplit();
		dealer.stand();
	}
	public void resetHand(Customer customer, Dealer dealer)
	{
		customer.clearHand();
		dealer.clearHand();
		splitFlagIsThrown=false;
	}
	public void startGame(Customer customer, Dealer dealer, Stack<Card> deck)
	{
		dealer.dealToCustomer(customer, deck);
		dealer.deal(deck);
		if (dealer.gotBlackjack()|| customer.gotBlackjack())
		{
			return;
		}
	}//end of startGame
	public void goAgain(Customer customer, Dealer dealer, Stack<Card> deck)
	{
		dealer.dealToCustomer(customer, deck);
		dealer.deal(deck);
		//showInitialHand(customer, dealer);
		if (dealer.gotBlackjack()|| customer.gotBlackjack())
		{
			return;
		}
	}//end of goAgain
	public void menu()
	{
		System.out.println("MENU");
		System.out.println("1. hit");
		System.out.println("2. stand");
		System.out.println("3. double down");
		System.out.println("4. split");
	}//end of menu

	public void customerTurn(Customer customer, Dealer dealer, Stack<Card> deck, double bet)
	{
		if (dealer.gotBlackjack()||customer.gotBlackjack())
		{
			return;
		}
		menu();
		//in.reset();
		System.out.print("What do you want to do? ");
		int  choice= in.nextInt();
		switch (choice)
		{
		case 1://hit
			customer.hit(deck);
			//customer.checkForAces();
			if (customer.gotBusted())
			{
				return;
			}
			if (customer.gotBlackjack())
			{
				return;
			}
			return;
		case 2://stand
			customer.stand();
			return;
		case 3://double down
			if (bet>customer.account().getBalance())
			{
				System.out.println("Insufficient funds GET OUT OF MY CASINO!");
				System.exit(0);
			}
			customer.doubleDown(deck, bet);
			//dealerTurn(customer, dealer, deck, bet);
			return;
		case 4://split TODO
			try
			{
				customer.split(deck, bet);
			}
			catch(NoPairException x)
			{
				splitFlagIsThrown=true;
				System.out.println(x);
				return;
			}
			//dealerTurn(customer, dealer, deck, bet);
			return;
		}	
	}//end of customerTurn
	public void dealerTurn(Customer customer, Dealer dealer, Stack<Card>deck, double bet)
	{//1st CHECK
		if (!dealer.gotBusted()&&(customer.gotBusted()||dealer.getHandValue()>customer.getHandValue())&&!dealer.gotBlackjack())//dealer wins by higher value or customer busts
		{
			/**/showFinalHand(customer, dealer);
			if(customer.gotBusted())
			{
				System.out.println("YOU BUSTED!");
			}
			System.out.println("DEALER WINS!");
			seperator();
			//System.out.println("Your Balance: $"+customer.account().getBalance());
			return;
		}
		dealer.hit(deck);
		dealer.checkForAces();
		//dealer.showHand();
		 if (dealer.gotBlackjack()&&!customer.gotBlackjack())//dealer has blackjack
		{
			System.out.println("dealer blackjack");
			/**/showFinalHand(customer, dealer);
			System.out.println("DEALER BLACKJACK!");
			seperator();
			//System.out.println("Your Balance: $"+customer.account().getBalance());
			/**/return;
		}
		else if (dealer.gotBusted()&&!customer.gotBlackjack())//you win by dealer busting
		{
			/**/showFinalHand(customer, dealer);
			System.out.println("DEALER BUSTED! YOU WIN!");
			dealer.pay(customer, bet);
			seperator();
			//System.out.println("Your Balance: $"+customer.account().getBalance());
			/**/return;
		}
		else if((dealer.getHandValue()==customer.getHandValue())||(dealer.gotBlackjack()&&customer.gotBlackjack()))//push
		{
			System.out.println("both values are = or both blackjack");
			/**/showFinalHand(customer, dealer);
			System.out.println("PUSH");
			dealer.payForPush(customer, bet);
			seperator();
			//System.out.println("Your Balance: $"+customer.account().getBalance());
			/**/return;
		}
		else if((customer.getHandValue()>dealer.getHandValue())&&!customer.gotBlackjack())//you win by higher value
		{
			/**/showFinalHand(customer, dealer);
			System.out.println("YOU WIN BY HIGHER VALUE!");
			dealer.pay(customer, bet);
			seperator();
			//System.out.println("Your Balance: $"+customer.account().getBalance());
			/**/return;
		}
		else if (customer.gotBlackjack())//customer gets blackjack
		{
			/**/showFinalHand(customer, dealer);
			System.out.println("YOU WIN BY BLACKJACK!");
			dealer.payForBlackJack(customer, bet);
			seperator();
			//System.out.println("Your Balance: $"+customer.account().getBalance());
			/**/return;
		}
		else if (!dealer.gotBusted()&&(customer.gotBusted()||dealer.getHandValue()>customer.getHandValue())&&!dealer.gotBlackjack())//dealer wins by higher value or customer busts
		{//2nd CHECK
			System.out.println("*customer busts or dealer wins by higher value*");
			showFinalHand(customer, dealer);
			if(customer.gotBusted())
			{
				System.out.println("YOU BUSTED!");
			}
			System.out.println("DEALER WINS!");
			seperator();
			//System.out.println("Your Balance: $"+customer.account().getBalance());
			return;
		}
		else//should never go here
		{
			System.out.println("ERROR");
			/**/return;
		}
	}//end of dealerTurn
	public void handleSplits(Customer customer, Dealer dealer, Stack<Card>deck, double bet)//pay according to split hand
	{//1st CHECK
		if (!customer.splitted()||splitFlagIsThrown)
		{
			return;
		}
		else
		{
			if (!dealer.gotBusted()&&(customer.gotBustedOnSplit()||dealer.getHandValue()>customer.getSplitHandValue())&&!dealer.gotBlackjack())//dealer wins by higher value or customer busts
			{
				/**/showFinalHand(customer, dealer);
				if(customer.gotBustedOnSplit())
				{
					System.out.println("YOU BUSTED!");
				}
				System.out.println("DEALER WINS!");
				seperator();
				//System.out.println("Your Balance: $"+customer.account().getBalance());
				return;
			}
			//dealer.hit(deck);
			//dealer.checkForAces();
			//dealer.showHand();
			 if (dealer.gotBlackjack()&&!customer.gotBlackjackOnSplit())//dealer has blackjack
			{
				System.out.println("dealer blackjack");
				/**/showFinalHand(customer, dealer);
				System.out.println("DEALER BLACKJACK!");
				seperator();
				//System.out.println("Your Balance: $"+customer.account().getBalance());
				/**/return;
			}
			else if (dealer.gotBusted()&&!customer.gotBlackjackOnSplit())//you win by dealer busting
			{
				/**/showFinalHand(customer, dealer);
				System.out.println("DEALER BUSTED! YOU WIN!");
				dealer.pay(customer, bet);
				seperator();
				//System.out.println("Your Balance: $"+customer.account().getBalance());
				/**/return;
			}
			else if((dealer.getHandValue()==customer.getSplitHandValue())||(dealer.gotBlackjack()&&customer.gotBlackjackOnSplit()))//push
			{
				System.out.println("both values are = or both blackjack");
				/**/showFinalHand(customer, dealer);
				System.out.println("PUSH");
				dealer.payForPush(customer, bet);
				seperator();
				//System.out.println("Your Balance: $"+customer.account().getBalance());
				/**/return;
			}
			else if((customer.getSplitHandValue()>dealer.getHandValue())&&!customer.gotBlackjackOnSplit())//you win by higher value
			{
				/**/showFinalHand(customer, dealer);
				System.out.println("YOU WIN BY HIGHER VALUE!");
				dealer.pay(customer, bet);
				seperator();
				//System.out.println("Your Balance: $"+customer.account().getBalance());
				/**/return;
			}
			else if (customer.gotBlackjackOnSplit())//customer gets blackjack
			{
				/**/showFinalHand(customer, dealer);
				System.out.println("YOU WIN BY BLACKJACK!");
				dealer.payForBlackJack(customer, bet);
				seperator();
				//System.out.println("Your Balance: $"+customer.account().getBalance());
				/**/return;
			}
			else if (!dealer.gotBusted()&&(customer.gotBustedOnSplit()||dealer.getHandValue()>customer.getSplitHandValue())&&!dealer.gotBlackjack())//dealer wins by higher value or customer busts
			{//2nd CHECK
				System.out.println("*customer busts or dealer wins by higher value*");
				showFinalHand(customer, dealer);
				if(customer.gotBustedOnSplit())
				{
					System.out.println("YOU BUSTED!");
				}
				System.out.println("DEALER WINS!");
				seperator();
				//System.out.println("Your Balance: $"+customer.account().getBalance());
				return;
			}
			else//should never go here
			{
				System.out.println("ERROR");
				/**/return;
			}
		}//end of else statement
	}//end of handleSplits
	public void seperator()//create room
	{
		for (int i=0; i<10; i++)
		{
			System.out.println();
		}	
	}//end of separator
}//end of class