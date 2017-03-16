package quartettgame;

import framework.Card;

import java.util.Comparator;

/**
 * Created by trixi on 2017.02.28..
 */
public class QuartettCard extends Card
{
    private String description;
    private AttributeLevel power;
    private AttributeLevel intelligence;
    private AttributeLevel reflex;


    public QuartettCard(String name, String description, AttributeLevel power, AttributeLevel intelligence, AttributeLevel reflex) {
        super(name);
        this.description = description;
        this.power = power;
        this.intelligence = intelligence;
        this.reflex = reflex;
    }
    public int getPowerLevel() {
        return power.getValue();
    }
    public int getReflexLevel() {
        return reflex.getValue();
    }
    public int getIntelligenceLevel() {
        return intelligence.getValue();
    }

    public static class PowerComparator implements Comparator<QuartettCard>{

        @Override
        public int compare(QuartettCard firstCard, QuartettCard secondCard) {
            int firsCardPower = firstCard.power.getValue();
            int secondCardPower = secondCard.power.getValue();
            if (firsCardPower > secondCardPower){
                return 1;
            }
            if(secondCardPower > firsCardPower){
                return -1;
            }
            return 0;
        }
    }

    public static class IntelligenceComparator implements Comparator<QuartettCard>{

        @Override
        public int compare(QuartettCard firstCard, QuartettCard secondCard) {
            int firsCardIntelligence = firstCard.intelligence.getValue();
            int secondCardIntelligence = secondCard.intelligence.getValue();
            if (firsCardIntelligence > secondCardIntelligence){
                return 1;
            }
            if(secondCardIntelligence > firsCardIntelligence){
                return -1;
            }
            return 0;
        }
    }
    public static  class ReflexComparator implements Comparator<QuartettCard>{

        @Override
        public int compare(QuartettCard firstCard, QuartettCard secondCard) {
            int firsCardReflex = firstCard.reflex.getValue();
            int secondCardReflex = secondCard.reflex.getValue();
            if (firsCardReflex > secondCardReflex){
                return 1;
            }
            if(secondCardReflex > firsCardReflex){
                return -1;
            }
            return 0;
        }
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString()
    {
        return "--------------------\n" +
                "Name of the card:  " + getName() + "\n" +
                "Description:   " + getDescription() + "\n" +
                "--------------------\n" +
                "Power:  " + getPowerLevel() + "\n" +
                "Intelligence:  " + getIntelligenceLevel() + "\n" +
                "Reflex:    " + getReflexLevel() + "\n" +
                "--------------------\n" +
                "\n";
    }

}
