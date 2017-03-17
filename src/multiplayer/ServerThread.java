package multiplayer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akos on 2017.03.02..
 */
public class ServerThread extends Thread
{
    private boolean flag;
    private Message message;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String PLayername;
    private Socket socket;

    public ServerThread(Socket socket)throws IOException{
        this.socket = socket;
        in = new ObjectInputStream(this.socket.getInputStream());
        out = new ObjectOutputStream(this.socket.getOutputStream());
        flag = false;



    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    public Socket getSocket()
    {
        return socket;
    }

    public Message getMessage()
    {
        return message;
    }

    public String getPLayername()
    {
        return PLayername;
    }
    public void sendData(Message message){
        try
        {
            out.writeObject(message);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void run(){

        try
        {
            PLayername = (String)in.readObject();
            System.out.println("Name of the player is:\t" + PLayername);

        } catch (IOException e)
        {
            return;
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        while (true)
        {
            try
            {
                System.out.println("Receiving data from" + socket.getRemoteSocketAddress());
                message = (Message) in.readObject();
                System.out.println("Data received:\n \t " + message);
                flag = true;

            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}
