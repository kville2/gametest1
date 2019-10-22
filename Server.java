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
	
	static String chatDisplay;
	ChatDisplay chat;
    public static void main(String[] args) throws IOException  
    { 
        // server is listening on port 5056 
        ServerSocket ss = new ServerSocket(7000); 
          
        // running infinite loop for getting 
        // client request 
        while (true)  
        { 

            Socket s = null; 
              
            try 
            { 
                // socket object to receive incoming client requests 
                s = ss.accept(); 
                  
                System.out.println("A new client is connected : " + s); 
                  
                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
                  
                System.out.println("Assigning new thread for this client"); 
  
                // create a new thread object 
                Thread t = new ClientHandler(s, dis, dos); 
  
                // Invoking the start() method 
                t.start(); 
                  
            } 
            catch (Exception e){ 
                s.close(); 
                e.printStackTrace(); 
            } 
        } 
    } 
} 
  
// ClientHandler class 
class ClientHandler extends Thread  
{ 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
    } 
  
    @Override
    public void run()  
    { 
        String received; 
        String toreturn;
        String chatDisplay;
        ChatDisplay chat = new ChatDisplay();
            try { 
            	
            	
                chatDisplay = dis.readUTF();
                chat.addChat(chatDisplay);
                chatDisplay = chat.returnChat();
                dos.writeUTF(chatDisplay); 
                

                } 
             catch (IOException e) { 
                e.printStackTrace(); 
                try
                { 
                    // closing resources 
                    this.dis.close(); 
                    this.dos.close(); 
                      
                }catch(IOException e1){ 
                    e1.printStackTrace(); 
                } 
       } 
          

    } 
} 


