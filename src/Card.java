
public class Card 
{
	private String face;
	private int value;
	private String suit;
	
	public Card(String f, int v, String s)//constructor
	{
		face=f;
		value=v;
		suit=s;
	}
	
	public String getFace()
	{
		return face;
	}
	public int getValue()
	{
		return value;
	}
	public String getSuit()
	{
		return suit;
	}
	
	public void soften()//if this card is an ace and it causes a bust
	{
		value=1;
	}
	public void setValue(int v)
	{
		value=v;
	}
	public String toString()
	{
		return"("+ face+","+suit+")";
	}
}//end of class