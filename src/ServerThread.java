import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akos on 2017.03.02..
 */
public class ServerThread extends Thread
{
    List<String>responses;
    private Socket socket;
    private String cardId;
    public ServerThread(Socket socket){
        this.socket = socket;
        cardId = "";
        responses = new ArrayList<>();
    }

    public Socket getSocket()
    {
        return socket;
    }

    public String getCardId()
    {
        return cardId;
    }

    public void setCardId(String cardId)
    {
        this.cardId = cardId;
    }

    public void run(){
        DataOutputStream out = null;
        DataInputStream in = null;
        try
        {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

        }catch (IOException e){
            return;
        }
        while (true){
            try
            {

                out.writeUTF(cardId);
               responses.add(in.readUTF()+ socket.getRemoteSocketAddress());

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
