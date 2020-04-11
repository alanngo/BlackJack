package service;

import resource.card.*;
import resource.player.*;
import java.util.*;

class Helper
{
    static void deal(Dealer dealer, Player player, Stack<Card> deck)
    {
        //1st card
        dealer.draw(deck.pop());
        player.draw(deck.pop());

        //2nd card
        dealer.draw(deck.pop());
        player.draw(deck.pop());
    }

    static void clearHand(Dealer dealer, Player player, Stack<Card> dec)
    {
        dealer.clearHand();
        player.clearHand();
    }

    static void payPush(Player player, double amt) { player.getPaid(amt); }

    static void payWin(Player player, double amt) { player.getPaid(2 * amt); }

    static void payBlackjack(Player player, double amt) { player.getPaid(4 * amt); }
}
