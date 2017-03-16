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
            PLayername = in.readUTF();

        } catch (IOException e)
        {
            return;
        }

        while (true)
        {
            try
            {
                message = (Message) in.readObject();
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
