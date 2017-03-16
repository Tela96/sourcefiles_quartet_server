package multiplayer;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable
{
    private boolean winner;
    private List<String> cardIDlist;
    private String clientResponse;
    private String currentCard;
    private String lastWinner;

    public Message(boolean winner, List<String> cardIDlist)
    {
        this.winner = winner;
        this.cardIDlist = cardIDlist;
    }

    public Message(String clientResponse)
    {
        this.clientResponse = clientResponse;
    }

    public Message(String currentCard, String lastWinner)
    {
        this.currentCard = currentCard;
        this.lastWinner = lastWinner;
    }

    public boolean isWinner()
    {
        return winner;
    }

    public List<String> getCardIDlist()
    {
        return cardIDlist;
    }

    public String getClientResponse()
    {
        return clientResponse;
    }
}
