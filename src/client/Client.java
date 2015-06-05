package client;

import java.io.*;
import java.net.*;

/**
 *
 * @author Alex
 */
public class Client
{
    private Socket client;
    private String server;
    private int port;

    public Client(String host, int port)
    {
        this.server = host;
        this.port = port;
    }
    
    public void connect() throws IOException
    {
        System.out.println("Attempting to connect to server " + this.server + ":" + this.port + "...");
        this.client = new Socket(this.server, this.port);
        System.out.println("Connection to server " + this.server + ":" + this.port + " established");
    }
    
    public void askForTimeToServer() throws IOException
    {
        System.out.println("Asking for time to " + this.server + this.port + "...");
        DataOutputStream outToServer = new DataOutputStream(this.client.getOutputStream());
        outToServer.writeUTF("Time?");
    }
    
    public void readTimeFromServer() throws IOException
    {
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        System.out.println("Time: " + in.readUTF());
    }
    
    public static void main(String[] args)
    {
        Client client = new Client("localhost", 9999);
        try
        {
            client.connect();
            client.askForTimeToServer();
            client.readTimeFromServer();
        }
        catch(UnknownHostException exception)
        {
            System.err.println("Host unknown");
            System.err.println("Cannot establish connection");
        }
        catch(IOException exception)
        {
            System.err.println("Cannot establish connection");
            System.err.println("Server may not be up");
        }
    }
}