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

    public Message(String currentCard, boolean winner)
    {
        this.currentCard = currentCard;
        this.winner = winner;
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

    public String getCurrentCard()
    {
        return currentCard;
    }

    public String getLastWinner()
    {
        return lastWinner;
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "winner=" + winner +
                ", cardIDlist=" + cardIDlist +
                ", clientResponse='" + clientResponse + '\'' +
                ", currentCard='" + currentCard + '\'' +
                ", lastWinner='" + lastWinner + '\'' +
                '}';
    }

    public void setLastWinner(String lastWinner)
    {
        this.lastWinner = lastWinner;
    }
}
