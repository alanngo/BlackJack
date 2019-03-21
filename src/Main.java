import java.util.*;
public class Main
{
	static Scanner in = new Scanner(System.in);
	static TestCases x=new TestCases();//used for debugging
	public static void main(String[] args) 
	{
		//test cases
		
		/**PASSED/RESOLVED TEST CASES
		 * 
		 * 1: both blackjack
		 * 2. both push
		 * 3. customer immediate blackjack
		 * 4. customer wins by higher value
		 * 5. customer wins by dealer busting
		 * 6. dealer blackjack
		 * 7. dealer wins by higher value
		 * 8. dealer wins by customer busting
		 * 9. Double down push (check payout)
		 * 10. Error Case 1
		 * 11.Pair of aces in hand
		 * 12.Check split function
		 * */
		/**REMAINING TEST CASES
		 
		 **/
		//initialize game
		Dealer dealer = new Dealer();
		System.out.print("Start by putting money into the game");
		double credit = in.nextDouble();
		Customer customer=new Customer(credit);
		Game game= new Game();
		Stack<Card> deck = game.createDeck();
		/**/Collections.shuffle(deck);
		double bet=0;
		game.startGame(customer, dealer, deck);
		
		while (!deck.isEmpty()&&customer.account().hasMoney())
		{
			try
			{
				System.out.println("Your Balance: $"+customer.account().getBalance());
				System.out.print("Make a bet");
				bet= in.nextDouble();
				customer.makeBet(bet);
				if (customer.overBetted())
				{
					game.resetHand(customer, dealer);
					game.goAgain(customer, dealer, deck);
				}
				else
				{
					game.showInitialHand(customer, dealer);
					game.customerTurn(customer, dealer, deck, bet);
					if (game.threwSplitFlag())
					{
						System.out.println("ERROR! YOU CAN ONLY SPLIT ON A PAIR");
						dealer.pay(customer, .5*bet);
						game.resetHand(customer, dealer);
						game.goAgain(customer, dealer, deck);
					}
					else
					{
						game.dealerTurn(customer, dealer, deck, bet);
						game.handleSplits(customer, dealer, deck, bet);
						game.resetHand(customer, dealer);
						game.goAgain(customer, dealer, deck);
					}
				}
			}
			catch (EmptyStackException e)
			{
				System.out.println("GAME OVER");
			}
		}//end of while loop
		
	}//end of main function
	
}//end of Main