package quartettgame;

import framework.*;
import multiplayer.Message;
import multiplayer.ServerThread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by trixi on 2017.02.28..
 */
public class QuartettGame implements Game
{
    /* meghatározza a framework.Player -eket
    *  felépíti a játékteret
    */
    private Deck quartetDeck;
    private int numOfPlayers;
    private List<QuartettCard> cardsFromTie;
    private Map<QuartettCard, QuartettPlayer> cardsInPlay;
    private List<ServerThread> serverThreads;
    private Map<QuartettPlayer,ServerThread>clients;

    public QuartettGame(QuartettDeckBuilder b, int numOfPlayers, List<ServerThread> serverThreads) {
        cardsFromTie = new ArrayList<>();
        quartetDeck = b.buildDeck();
        cardsInPlay = new HashMap<>();
        this.numOfPlayers = numOfPlayers;
        this.serverThreads = serverThreads;
        clients = new ConcurrentHashMap<>();
    }

    private void createPlayers(){
        QuartettPlayer lasWinner = null;
        for (int i = 0; i < numOfPlayers; i++){

            clients.put( new QuartettPlayer(serverThreads.get(i).getPLayername(), new QuartettHand()),serverThreads.get(i));

        }

    }


    private QuartettPlayer pickStaringPlayer(){
        Random random = new Random();
        return clients.keySet().toArray(new QuartettPlayer[clients.keySet().size()])[random.nextInt(numOfPlayers)];
    }

    private boolean readMessage(){


        while (true){
            int playerready = 0;
        for (ServerThread server:clients.values())
        {
            if(server.isFlag())playerready++;
        }
        if(playerready == numOfPlayers){
            return true;
        }



    }
    }
    private void sendMessage(QuartettPlayer lastWinner){
        ServerThread server;
        Message message;
        List<String> cardIDs = buildCardIDList();
        for (QuartettPlayer player: clients.keySet())
        {
         server = clients.get(player);

         if(player.equals(lastWinner))
         {
            message = new Message(true, cardIDs);
            message.setLastWinner(lastWinner.getName());
            server.sendData(message);
            System.out.println("message with \n\t" + message + "\n \t data sent.");
         }
         else
            {
                message = new Message(false, cardIDs);
                message.setLastWinner(lastWinner.getName());
                server.sendData(message);
                System.out.println("message with \n\t" + message + "\n \t data sent.");
            }
        }
    }
    public List<String> buildCardIDList()
    {
        List<String> cardIDs = new ArrayList<>();
        for (QuartettCard card: cardsInPlay.keySet())
        {
            cardIDs.add(card.getName());
        }
        return cardIDs;
    }
    public void sendTopCards(QuartettPlayer lastWinner)
    {
        QuartettCard topCard = null;
        String winner = lastWinner.getName();
        ServerThread server;
        String cardName = "";
        Message message;
        for (QuartettPlayer player:clients.keySet())
        {
            for (Map.Entry<QuartettCard, QuartettPlayer > entry : cardsInPlay.entrySet())
            {
                if (entry.getValue().getName().equals(player.getName()))
                {
                    topCard = entry.getKey();
                    cardName = topCard.getName();
                    server = clients.get(player);
                    if (lastWinner.getName().equals(entry.getValue().getName()))
                    {
                        message = new Message(cardName, true);
                    }
                    else
                    {
                        message = new Message(cardName, false);
                    }
                    message.setLastWinner(winner);
                    server.sendData(message);
                    System.out.println("data sent");
                }

            }


        }
    }
    public void setFlagstoFalse()
    {
        ServerThread server;
        for (QuartettPlayer player: clients.keySet())
        {
            server = clients.get(player);
            server.setFlag(false);
        }
    }
    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        int rounds = 1;
        String chosenAttr = "";
        createPlayers();
        quartetDeck.shuffleCards();
        dealCards();
        QuartettPlayer lastWinner = pickStaringPlayer();
        do {
            System.out.println("R O U N D : " + rounds);
            System.out.println("Current player: " + lastWinner.getName());
            System.out.println((lastWinner.showCard()));
            gatherCards();
            sendTopCards(lastWinner);
            if(readMessage()){
                System.out.println("Waiting for client responses...");
                chosenAttr = clients.get(lastWinner).getMessage().getClientResponse();
                setFlagstoFalse();
            }
            lastWinner = decideWinner(chosenAttr, lastWinner);
            sendMessage(lastWinner);
            if(readMessage()){
                setFlagstoFalse();
            }
            giveCardsToWinner(lastWinner);
            cardsInPlay.clear();
            rounds++;

        }while (checkWinCondition());

    }

    private void sendFinalMessage(QuartettPlayer lastwinner){

    }
    private void printCards(){
        System.out.println("Every card in this round: \n");
        for (QuartettCard card: cardsInPlay.keySet()){
            System.out.println("--------------------");
            System.out.println("--------------------");
            System.out.println(cardsInPlay.get(card).getName());
            System.out.println(card);
        }
    }
    private boolean checkWinCondition(){
        for (QuartettPlayer player:clients.keySet()){
            if (player.checkNumberOfCards() == 0){
                return false;
            }
        }
        return true;
    }
    private void giveCardsToWinner(QuartettPlayer winner){
        if (cardsFromTie.size() == 0){
            for (Card card: cardsInPlay.keySet()) {
                winner.getHand().addCard(card);
            }
        }else {
            for (Card card: cardsFromTie){
                winner.getHand().addCard(card);
            }
        }
    }

    public QuartettPlayer winnerOfTheGame(){
        QuartettPlayer winner = clients.keySet().toArray(new QuartettPlayer[clients.keySet().size()])[0];

        for (QuartettPlayer p: clients.keySet()){
            if (p.checkNumberOfCards() > winner.checkNumberOfCards()){
                winner = p;
            }
        }
        return winner;
    }

    private boolean checkTie(List<QuartettCard> cardList, String attribute){
        QuartettCard firstCard = cardList.get(cardList.size()-1);
        QuartettCard secondCard = cardList.get(cardList.size()-2);
        switch (attribute){
            case "P":
                if(firstCard.getPowerLevel() == secondCard.getPowerLevel()){
                    return true;
                }
            case "I":
                if(firstCard.getIntelligenceLevel() == secondCard.getIntelligenceLevel()){
                    return true;
                }
            case "R":
                if(firstCard.getReflexLevel() == secondCard.getReflexLevel()){
                    return true;
                }
        }
        return false;
    }

    private QuartettPlayer decideWinner(String input, QuartettPlayer lastWinner){
        boolean tie = false;
        List<QuartettCard> cardList = new ArrayList();
        QuartettPlayer winningPlayer;
        QuartettCard winningCard;
        cardList.addAll(cardsInPlay.keySet());
        switch (input){
            case "P":
                cardList.sort(new QuartettCard.PowerComparator());
                tie = checkTie(cardList, "P");
                break;
            case "I":
                cardList.sort(new QuartettCard.IntelligenceComparator());
                tie = checkTie(cardList, "I");
                break;
            case "R":
                cardList.sort(new QuartettCard.ReflexComparator());
                tie = checkTie(cardList, "R");
                break;
        }

        if (tie){
            System.out.println("There was a tie, collecting cards now for the next round.");
            cardsFromTie = cardList;
            return lastWinner;
        }
        winningCard = cardList.get(cardList.size()-1);
        winningPlayer = cardsInPlay.get(winningCard);
        System.out.println("--------------------");
        System.out.println("Winner of this round: " + winningPlayer.getName());
        System.out.println("Winning card: ");
        System.out.println(winningCard);
        return winningPlayer;
    }

    private void gatherCards(){
        for (QuartettPlayer player: clients.keySet()){
            cardsInPlay.put((QuartettCard)player.showCard(), player);
            player.getHand().removeTopCard();
        }
    }

    public void dealCards() {
        int index = 0;
        List<Card> currentDeck = quartetDeck.getCards();
        while(index < currentDeck.size()-4){
            for (QuartettPlayer p: clients.keySet()){
                 p.getHand().addCard(currentDeck.get(index));
                index++;
            }
        };
    }
}
