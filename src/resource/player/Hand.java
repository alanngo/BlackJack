package resource.player;
import java.util.*;
import resource.card.*;

import static resource.card.Rank.*;
import static resource.card.Suit.*;
class Hand
{
    private List<Card> cards;
    static final int WIN = 21;

    private static boolean isAce(Card c)
    {
        return c.equals(new Card(ACE, SPADE))
                ||c.equals(new Card(ACE, HEART))
                ||c.equals(new Card(ACE, CLUB))
                ||c.equals(new Card(ACE, DIAMOND));
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

    /**
     * Check if hand contains pair
     * Ten and Face-cards count as pairs
     * @return does c0.equal(c1)
     * */
    boolean containsPair()
    {
        Card c0 = cards.get(0);
        Card c1 = cards.get(1);
        return c0.equals(c1);
    }

    /**
     * Check if hand is busted
     * Pair of aces should not bust
     * If a split is done involving aces,
     * harden their value
     * @return does hand value exceed 21
     * */
    boolean isBusted()
    {
        if (cards.size()<2)
            return false;

        //hand size of 2 will never bust
        else if (cards.size()==2)
        {
            Card c0 = cards.get(0);
            Card c1 = cards.get(1);
            if (containsPair())
            {
                //soften the pair of aces
                if (isAce(c0) && isAce(c1))
                {
                    c0.soften();
                    c1.soften();
                }
            }
            else
            {
                //harden the aces on splits
                if (isAce(c0))
                    c0.harden();

                if (isAce(c1))
                    c1.harden();
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
