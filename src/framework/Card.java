package framework;

/**
 * Created by trixi on 2017.02.28..
 */
public abstract class Card
{
    private String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String toString();

}
