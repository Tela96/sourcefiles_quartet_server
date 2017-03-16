package quartettgame;

import framework.Card;
import framework.Deck;
import framework.DeckBuilder;
import framework.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuartettDeckBuilder implements DeckBuilder, Parser
{
    private List<List<String>> dataList;
    private String csvfile = "masterCrokCards.csv";
    private final int NAME = 0;
    private final int DESCRIPTION = 1;
    private final int POWER = 2;
    private final int INTELLIGENCE= 3;
    private final int REFLEX = 4;



    public QuartettDeckBuilder(String csvfile) {
        this.csvfile = csvfile;
        dataList = new ArrayList<>();
    }

    @Override
    public Deck buildDeck()
    {
        Deck quartettDeck = new QuartettDeck();
        parseFile();
        for (List row : dataList)
        {
            quartettDeck.addCard(createCard(row));
        }
        return quartettDeck;
    }
    private AttributeLevel findEnum(int value)
    {
        for (AttributeLevel a: AttributeLevel.values())
        {
            if(a.getValue() == value)
            {
                return a;
            }

        }
        return null;
    }
    private Card createCard(List row)
    {
        String name, description;
        AttributeLevel power, intelligence, reflex;

        name = (String) row.get(NAME);
        description = (String) row.get(DESCRIPTION);
        power = findEnum(Integer.parseInt((String)row.get(POWER)));
        intelligence = findEnum((Integer.parseInt((String)row.get(INTELLIGENCE))));
        reflex = findEnum(Integer.parseInt((String)row.get(REFLEX)));
        Card newCard = new QuartettCard(name, description, power, intelligence, reflex);
        return newCard;

    }

    @Override
    public void parseFile()
    {
        BufferedReader br;
        String line = "", csvSplitby = ",";
        try
        {
            br = new BufferedReader(new FileReader(csvfile));
            while((line = br.readLine()) != null)
            {
                String[] tempArray = line.split(csvSplitby);
                dataList.add(Arrays.asList(tempArray));
            }
        }
        catch(Exception e)
        {
            System.out.println("Wrong data");
        }
    }
}
