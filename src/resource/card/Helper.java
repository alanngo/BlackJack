package resource.card;

class Helper
{
    static int getCardValue(Rank rank)
    {
        //assign values to resource.card
        switch (rank)
        {
            case ACE:
                return 11;
            case KING:
            case QUEEN:
            case JACK:
            case TEN:
                return 10;
            case NINE:
                return 9;
            case EIGHT:
                return 8;
            case SEVEN:
                return 7;
            case SIX:
                return 6;
            case FIVE:
                return 5;
            case FOUR:
                return 4;
            case THREE:
                return 3;
            case TWO:
                return 2;
        }
        return 0;
    }
}
