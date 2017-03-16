package multiplayer;

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

    private String PLayername;
    private Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public Socket getSocket()
    {
        return socket;
    }

    public String getPLayername()
    {
        return PLayername;
    }

    public void run(){
        DataOutputStream out = null;
        DataInputStream in = null;
        try
        {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            PLayername = in.readUTF();

        }catch (IOException e){
            return;
        }

        while (true){
            try
            {
                out.writeUTF("csá");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
