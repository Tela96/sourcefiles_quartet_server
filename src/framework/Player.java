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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return name.equals(player.name);
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
}
