package framework;

/**
 * Created by trixi on 2017.02.28..
 */
public abstract class Player
{
    protected String name;
    protected Hand hand;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Hand hand) {

        this.name = name;
        this.hand = hand;
    }


    public String getName() {
        return name;
    }

    public abstract Card showCard();
}
