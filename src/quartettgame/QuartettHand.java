package quartettgame;

import framework.Card;
import framework.Hand;


public class QuartettHand extends Hand
{


    public Card getTopCard()
    {
        return cards.get(0);
    }

    public void removeTopCard(){
        cards.remove(0);
    }

}
