package card;

import java.util.Objects;

public class Card
{
	private Rank rank;
	private Suit suit;
	private int value;

	public Card(Rank r, Suit s)
	{
		rank = r;
		suit = s;
		value = Helper.getValue(rank);
	}

	//if this card is an ace and it causes a bust
	public void soften() {value=1;}

	public int getValue(){return value;}

	@Override
	public String toString()
	{
		return"("+rank+"::"+suit+")";
	}

	@Override
	public int hashCode() { return Objects.hash(rank, value); }

	//compare card by value
	@Override
	public boolean equals(Object o)
	{
		if (o==this)
			return true;
		if (!(o instanceof Card))
			return false;

		return value == ((Card) o).value;
	}
}//end of class