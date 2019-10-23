package cschat;
//Java implementation for a client 
//Save file as Client.java 

import java.io.*; 
import java.net.*; 
import java.util.Scanner;

import javax.swing.JFrame;

import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
  
public class Client  extends JFrame
{ 
    final static int ServerPort = 6654;
    static String mainString;
    static String inStrings;
	static Scanner scn = new Scanner(System.in);
	static boolean hasSent;
    
    public static void main(String args[]) throws UnknownHostException, IOException
    {
    	StartScreen scrn = new StartScreen();
    	scrn.setVisible(true);

    	
    	InetAddress ip = InetAddress.getByName("localhost");
    	
    	Socket s = new Socket(ip, ServerPort);
    	
    	DataInputStream dis = new DataInputStream(s.getInputStream());
    	DataOutputStream dos = new DataOutputStream(s.getOutputStream());
    	
    	Thread sendMessage = new Thread(new Runnable()  
        { 
            @Override
            public void run() 
            { 
                while (true) 
                {               
                    try 
                    
                    { 
                   
                       mainString = scn.nextLine();
                        // write on the output stream 
                        dos.writeUTF(mainString); 
                    } catch (IOException e) { 
                        e.printStackTrace(); 
                    } 
                    
                    hasSent = false;
                	}
                } 
            
        });    	
    	Thread readMessage = new Thread(new Runnable()  
        { 
            @Override
            public void run() { 
            	
            	StartScreen c = new StartScreen();
  
                while (true) { 
                    try { 
                        // read the message sent to this client 
                    	
                    	
                        String msg = dis.readUTF(); 
                        setChat(msg);
                       
                        c.repaint();
                    } catch (IOException e) { 
  
                        e.printStackTrace(); 
                    } 
                } 
            } 
        }); 
    	
    	sendMessage.start();
    	readMessage.start();
    			
    }
    
    public static void setChat(String msg)
    {
    	mainString = msg;
    	mainString += "\n";
    	hasSent = true;
    }
    
    public static void newMessage(String s)
    {
    	mainString = s;
    }
    
    public String readMessage()
    {
    	return mainString;
    }
    
    public static void sent()
    {
    	System.out.println(mainString);
    }
} 

