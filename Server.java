package domgame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 
  
// Server class 
public class Server  
{ 
	static Vector<ClientHandler> ar = new Vector();
	
	static int i =0;
	static String chatDisplay;
	
    public static void main(String[] args) throws IOException  
    { 
        // server is listening on port 5056 
        ServerSocket ss = new ServerSocket(6654); 
        
        Socket s;

        while (true)  
        { 

            s = ss.accept();
            System.out.println("New client request received : + s");
            
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            System.out.println("Creating a new handler for this client...");
            
            ClientHandler mtch = new ClientHandler(s, "client " + i, dis, dos);
            
            Thread t = new Thread(mtch);
            
            System.out.println("Adding this client to acive client list");
            
            ar.add(mtch);
            
            t.start();
            
            i++;
        } 
    } 
} 
  
// ClientHandler class 
class ClientHandler implements Runnable
{
	Scanner scn = new Scanner(System.in);
	private String name;
	final DataInputStream dis;
	final DataOutputStream dos;
	Socket s;
	boolean isLoggedin;
	
	public ClientHandler(Socket s, String name, DataInputStream dis, DataOutputStream dos)
	{
		this.dis = dis;
		this.dos = dos;
		this.name = name;
		this.s = s;
		this.isLoggedin = true;
		
	}

	@Override
	public void run() 
	{
		String received;
		while(true)
		{
			try
			{
				received = dis.readUTF();
				System.out.println(received);
				
				if(received.contentEquals("logout"))
				{
					this.isLoggedin=false;
					this.s.close();
					break;
				}
				
				StringTokenizer st = new StringTokenizer(received, "#");
				String MsgToSend = st.nextToken();
				String recipient = st.nextToken();
				
				for (ClientHandler mc : Server.ar)
				{
					if(mc.name.equals(recipient) && mc.isLoggedin==true)
					{
						mc.dos.writeUTF(this.name+" : " +MsgToSend);
					}
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		
		} 
		try
		{
			this.dis.close();
			this.dos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
		
   
} 


