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
        System.out.println("Attempting to connect to " + this.server + ":" + this.port);
        this.client = new Socket(this.server, this.port);
        System.out.println("Connection established");
    }
    
    public void askForChoice() throws IOException
    {
        System.out.println("Asking for choice...");
        OutputStream outToServer = this.client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF("Choice?");
    }
    
    public void readChoice() throws IOException
    {
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        System.out.println("Choice: " + in.readUTF());
    }
    
    public static void main(String[] args)
    {
        Client client = new Client("localhost", 9999);
        try
        {
            client.connect();
            
            client.askForChoice();
            client.readChoice();
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
        
        client = new Client("localhost", 9999);
        try
        {
            client.connect();
            
            client.askForChoice();
            client.readChoice();
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