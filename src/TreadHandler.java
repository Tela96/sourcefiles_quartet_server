import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by akos on 2017.03.02..
 */
public class TreadHandler extends  Thread
{

    private List<ServerThread> serverThreads;
    private ServerSocket serverSocket;
    private int port = 8312;

    public TreadHandler()throws IOException{
        serverThreads = new CopyOnWriteArrayList<>();
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);


    }

    public List<ServerThread> getServerThreads()
    {
        return serverThreads;
    }

    public void run() {
        int n=0;
        while (n < 2) {

            ServerThread thread = null;
            try
            {   Socket client = serverSocket.accept();
                System.out.println("Client connected with this ip: " + client.getRemoteSocketAddress());
                thread = new ServerThread(client);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            serverThreads.add(thread);
            n++;
            thread.start();
        }
    }
    public void game() {
        while (true){



        }


    }
}
