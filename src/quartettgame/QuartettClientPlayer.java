package quartettgame;

import framework.Client;
import framework.Hand;

import java.net.ServerSocket;

/**
 * Created by akos on 2017.03.02..
 */
public class QuartettClientPlayer extends QuartettPlayer implements Client
{
    private ServerSocket serverSocket;

    public QuartettClientPlayer(String name, Hand hand, ServerSocket serverSocket)
    {
        super(name, hand);
        this.serverSocket = serverSocket;
    }

    public ServerSocket getServerSocket()
    {
        return serverSocket;
    }
}
