package quartettgame;

import framework.Deck;

import java.util.Collections;


/**
 * Created by trixi on 2017.02.28..
 */
public class QuartettDeck extends Deck {



    @Override
    public void shuffleCards() {
        Collections.shuffle(getCards());
    }
}
