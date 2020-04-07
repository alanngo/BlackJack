package player;
import java.util.*;
import card.*;

class Hand
{
    private List<Card> cards;
    static final int WIN = 21;

    private static boolean isAce(Card c)
    {
        return c.equals(new Card(Rank.ACE, Suit.SPADE))
                ||c.equals(new Card(Rank.ACE, Suit.HEART))
                ||c.equals(new Card(Rank.ACE, Suit.CLUB))
                ||c.equals(new Card(Rank.ACE, Suit.DIAMOND));
    }

    Hand() { cards = new ArrayList<>();}

    /**
     * @return total hand value
     * */
    int getHandValue()
    {
        int sum =0;
        for (Card c: cards)
            sum+=c.getValue();
        return sum;
    }

    void draw(Card card){ cards.add(card);}
    Card discard() { return cards.remove(0); }
    void clear() {cards.clear();}
    boolean empty(){return cards.isEmpty();}
    /* win/lose conditions */

    boolean containsPair()
    {
        Card c0 = cards.get(0);
        Card c1 = cards.get(1);
        return c0.equals(c1);
    }

    boolean isBusted()
    {
        if (cards.size()<2)
            return false;

        //hand size of 2 will never bust
        else if (cards.size()==2)
        {
            //pair of aces case
            Card c0 = cards.get(0);
            Card c1 = cards.get(1);
            if (containsPair()&&(isAce(c0)&&isAce(c1)))
            {
                c0.soften();
                c1.soften();
            }
            return false;
        }

        //hand size > 2
        for (Card card: cards)
        {
            if (isAce(card)&& getHandValue() > WIN)
                card.soften();
        }

        return getHandValue() > WIN;
    }

    /**
     * @return  is hand value 21, and size > 2
     * */
    boolean win() {return (getHandValue() == WIN);}

    /**
     * @return is hand blackjack
     * */
    boolean isBlackjack() {return win() && cards.size()==2; }

    @Override
    public String toString() { return cards.toString()+"->"+getHandValue();}
}
