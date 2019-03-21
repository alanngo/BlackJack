import java.util.*;
public class TestCases 
{
	public TestCases()
	{
		//nothing here
	}
	//PLAYER TEST CASES
	public  Stack<Card>testCase1()//both blackjack PUSH
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		deck.push(new Card("A",11,"Spade"));
		deck.push(new Card("K",10,"Spade"));
		deck.push(new Card("A",11,"Heart"));
		deck.push(new Card("K",10,"Heart"));
		//top
		return deck;
	}//PASSED
	public  Stack<Card>testCase2()//no blackjack push
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		deck.push(new Card("Q",10,"Spade"));
		deck.push(new Card("J",10,"Spade"));
		deck.push(new Card("10",10,"Heart"));
		deck.push(new Card("K",10,"Heart"));
		//top
		return deck;
	}//PASSED
	public  Stack<Card>testCase3()//player wins by blackjack 
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		deck.push(new Card("K",10,"Heart"));
		deck.push(new Card("K",10,"Club"));
		deck.push(new Card("A",11,"Spade"));
		deck.push(new Card("K",10,"Spade"));
		//top
		return deck;
	}//PASSED
	public  Stack<Card>testCase4()//player wins by higher value
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		deck.push(new Card("K",10,"Heart"));
		deck.push(new Card("7",7,"Club"));
		deck.push(new Card("8",8,"Spade"));
		deck.push(new Card("K",10,"Spade"));
		//top
		return deck;
	}//PASSED
	public  Stack<Card>testCase5()//player wins by dealer busting
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		deck.push(new Card("10",10,"Heart"));
		deck.push(new Card("K",10,"Heart"));
		deck.push(new Card("5",5,"Club"));
		deck.push(new Card("8",8,"Spade"));
		deck.push(new Card("K",10,"Spade"));
		//top
		return deck;
	}//PASSED
	public  Stack<Card>testCase6()//dealer blackjack
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		deck.push(new Card("10",10,"Heart"));
		deck.push(new Card("A",11,"Heart"));
		deck.push(new Card("5",5,"Club"));
		deck.push(new Card("8",8,"Spade"));
		//top
		return deck;
	}//PASSED
	public  Stack<Card>testCase7()//dealer wins by higher value
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		deck.push(new Card("10",10,"Heart"));
		deck.push(new Card("K",10,"Heart"));
		deck.push(new Card("5",5,"Club"));
		deck.push(new Card("8",8,"Spade"));
		//top
		return deck;
	}//PASSED
	public  Stack<Card>testCase8()//dealer wins by customer busting
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		//rest of the deck
		deck.push(new Card("2",2,"Spade"));
		deck.push(new Card("10",10,"Heart"));
		
		//dealer hand
		deck.push(new Card("K",10,"Heart"));
		deck.push(new Card("5",5,"Club"));
		
		//customer hand
		deck.push(new Card("8",8,"Spade"));
		deck.push(new Card("7",7,"Spade"));
		//top
		return deck;
	}//PASSED
	public  Stack<Card>testCase9()//double down push
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		//rest of the deck
		deck.push(new Card("10",10,"Heart"));
		
		//dealer hand
		deck.push(new Card("K",10,"Heart"));
		deck.push(new Card("Q",10,"Club"));
		
		//customer hand
		deck.push(new Card("3",3,"Spade"));
		deck.push(new Card("7",7,"Spade"));
		//top
		return deck;
	}//PASSED
	
	public  Stack<Card>testCase10()//reaches error
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		//rest of the deck
		deck.push(new Card("3",3,"Heart"));
		deck.push(new Card("4",4,"Heart"));
		deck.push(new Card("5",5,"Heart"));
		deck.push(new Card("3",3,"Heart"));
		deck.push(new Card("4",4,"Heart"));
		deck.push(new Card("5",5,"Heart"));
		deck.push(new Card("3",3,"Heart"));
		deck.push(new Card("4",4,"Heart"));
		deck.push(new Card("5",5,"Heart"));
		
		//dealer hand
		deck.push(new Card("K",10,"Club"));
		deck.push(new Card("4",4,"Spade"));
		
		//customer hand
		deck.push(new Card("7",7,"Heart"));
		deck.push(new Card("10",10,"Heart"));
		//top
		return deck;
	}//PASSED
	public  Stack<Card>testCase11()//pair of aces
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		//rest of the deck
		deck.push(new Card("10",10,"Diamond"));
		deck.push(new Card("10",10,"Heart"));
		
		//dealer hand
		deck.push(new Card("K",10,"Club"));
		deck.push(new Card("4",4,"Spade"));
		
		//customer hand
		deck.push(new Card("A",11,"Spade"));
		deck.push(new Card("A",11,"Heart"));
		//top
		return deck;
	}//IP
	public  Stack<Card>testCase12()//split case
	{
		Stack<Card>deck = new Stack<Card>();
		//bottom
		//rest of the deck
		deck.push(new Card("10",10,"Diamond"));
		deck.push(new Card("10",10,"Heart"));
		
		//dealer hand
		deck.push(new Card("K",10,"Club"));
		deck.push(new Card("4",4,"Spade"));
		
		//customer hand
		deck.push(new Card("9",9,"Spade"));
		deck.push(new Card("9",9,"Heart"));
		//top
		return deck;
	}//PASSED
}//end of testCases
