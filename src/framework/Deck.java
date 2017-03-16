package framework;

import java.util.ArrayList;

/**
 * Created by trixi on 2017.02.28..
 */
public abstract class Deck
{
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public  void addCard(Card card){
        cards.add(card);
    }

    public abstract void shuffleCards();
}
