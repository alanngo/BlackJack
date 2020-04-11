package resource.card;

import java.util.*;
import static resource.card.Helper.*;
public class Card
{
	public static final int HARD = 11;
	public static final int SOFT = 1;
	private Rank rank;
	private Suit suit;
	private int value;

	public Card(Rank r, Suit s)
	{
		rank = r;
		suit = s;
		value = getCardValue(rank);
	}

	//if this resource.card is an ace and it causes a bust
	public void soften() {value=SOFT;}

	public void harden(){value = HARD;}

	public int getValue(){return value;}

	@Override
	public String toString()
	{
		return"("+rank+"::"+suit+")";
	}

	@Override
	public int hashCode() { return Objects.hash(value); }

	//compare resource.card by value
	@Override
	public boolean equals(Object o)
	{
		if (o==this)
			return true;
		if (!(o instanceof Card))
			return false;

		return value == ((Card) o).value||
				rank==((Card) o).rank;
	}
}//end of class