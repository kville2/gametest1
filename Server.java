package domgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class Server extends javax.swing.JFrame 
{
   ArrayList clientOutputStreams;
   ArrayList<String> users;
   String[] kingdoms;
   int maxKingdoms = 0;
   String king;
   public  int playerNum = 0;
   

   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;

       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                ta_chat.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat", kingdoms = "Kingdoms",   p1 = "p1", p2 ="p2", p3 ="p3", p4 = "p4", p5 ="p5", p6 = "p6";
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    ta_chat.append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                        ta_chat.append(token + "\n");
                    }
                    
                    if (data[0].equals(kingdoms))
                    {
                    	
                    	tellEveryone(message);
                    }
                    
                    else if(data[0].equals("pField"))
                    {
                    	tellEveryone("pField:" + data[1]);
                    }

                    else if (data[2].equals(connect)) 
                    {
                    	playerNum +=1;
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat + ":Player" + playerNum));
                        userAdd(data[0]);
                    } 

                    else if (data[2].equals(disconnect)) 
                    {
                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        tellEveryone(message);
                    } 
                    else 
                    {
                        ta_chat.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                ta_chat.append("Lost a connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }
   
   @SuppressWarnings("unchecked")
   public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 593, 398);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		ta_chat = new JTextArea();
		panel.add(ta_chat, BorderLayout.CENTER);
		
		b_start = new JButton("Start");
		b_start.setBounds(10, 420, 89, 58);
		contentPane.add(b_start);
		 b_start.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                b_startActionPerformed(evt);
	            }
	        });
		
		b_users = new JButton("Users");
		b_users.setBounds(109, 420, 89, 58);
		contentPane.add(b_users);
		 b_users.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                b_usersActionPerformed(evt);
	            }
	        });
		b_clear = new JButton("Clear");
		b_clear.setBounds(415, 420, 89, 58);
		contentPane.add(b_clear);
		 b_clear.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                b_clearActionPerformed(evt);
	            }
	        });
		 
		b_end = new JButton("End");
		b_end.setBounds(514, 420, 89, 58);
		contentPane.add(b_end);
		b_end.addActionListener(new java.awt.event.ActionListener() {
	           public void actionPerformed(java.awt.event.ActionEvent evt) {
	               b_endActionPerformed(evt);
	           }
	       });
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(613, 11, 281, 441);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 102, 419);
		panel_1.add(scrollPane_1);
		
		lstKingdoms = new JList<String>();
		DefaultListModel mdl1 = new DefaultListModel();
   	lstKingdoms.setModel(mdl1);
   	String[] items = {"Cellar", "Chapel", "Moat", "Harbinger", "Merchant", "Vassal", "Village", "Workshop", "Bureacrat", "Gardens", "Militia", "Moneylender", "Poacher", "Remodel", "Smithy", "Throne Room", "Bandit", "Council Room", "Festival", "Laboratory", "Library", "Market", "Mine", "Sentry", "Witch", "Artisan"};
   	for(int i =0; i<items.length; i++)
   	{
   		mdl1.add(i, items[i]);
   	}
		scrollPane_1.setViewportView(lstKingdoms);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(160, 11, 105, 419);
		panel_1.add(scrollPane_2);
		
		lstSelected = new JList<String>();
		lstModel2 = new DefaultListModel();
		lstSelected.setModel(lstModel2);
		scrollPane_2.setViewportView(lstSelected);
		
		JButton btnRemove = new JButton("<");
		btnRemove.setEnabled(true);
		btnRemove.setBounds(114, 280, 41, 42);
		panel_1.add(btnRemove);
		btnRemove.setEnabled(true);
   	btnRemove.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent e) 
   		{
   			while(!lstSelected.isSelectionEmpty())
   			{
   				
   			if(maxKingdoms <=10 && maxKingdoms > 0)
   			{
   			String rem = (String) lstSelected.getSelectedValue();
   			mdl1.addElement(rem);
   			int index = lstSelected.getSelectedIndex();
   			if(index >=0)
   			{
   				btnBeginGame.setEnabled(false);
   				maxKingdoms -=1;
   				lstModel2.removeElementAt(index);
   			}
   			else 
   			{
   				break;
   			}
   			}
   			
   

   			}
   	}});
		
		JButton btnAddKingdom = new JButton(">");
		btnAddKingdom.setEnabled(true);
		btnAddKingdom.setBounds(114, 119, 41, 42);
		panel_1.add(btnAddKingdom);
		btnAddKingdom.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent e) 
   		{
   			
   			while(!lstKingdoms.isSelectionEmpty())
   			{	
   			if(maxKingdoms <10) 
   			{	
   			String add = (String) lstKingdoms.getSelectedValue();
   			lstModel2.addElement(add);
   			int inde = lstKingdoms.getSelectedIndex();
   			if(inde >=0)
   			{
   				maxKingdoms +=1;
   				mdl1.removeElementAt(inde);
   			}
   			}
   			
   			if(maxKingdoms == 10)
   			{
   				btnBeginGame.setEnabled(true);
   				
   			}
   			else
   			{
   				break;
   			}

   			}
   		}
   	});
		
		JComboBox cmboPresets = new JComboBox();
		cmboPresets.setModel(new DefaultComboBoxModel(new String[] {"Custom Kingdoms"}));
		cmboPresets.setBounds(613, 456, 281, 22);
		contentPane.add(cmboPresets);
		
		btnBeginGame = new JButton("Begin Game");
		btnBeginGame.setBounds(208, 420, 197, 58);
		contentPane.add(btnBeginGame);
		btnBeginGame.setEnabled(false);
		btnBeginGame.addActionListener(new java.awt.event.ActionListener() {
	           public void actionPerformed(java.awt.event.ActionEvent evt) {
	               b_beginActionPerformed(evt);
	           }
	       });
		
		
		
	}
	

    public Server() 
    {
        init();
    }

   

    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_endActionPerformed
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        ta_chat.append("Server stopping... \n");
        
        ta_chat.setText("");
    }//GEN-LAST:event_b_endActionPerformed

    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_startActionPerformed
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        ta_chat.append("Server started...\n");
    }//GEN-LAST:event_b_startActionPerformed

    private void b_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_usersActionPerformed
        ta_chat.append("\n Online users : \n");
        for (String current_user : users)
        {
            ta_chat.append(current_user);
            ta_chat.append("\n");
        }    
        
    }//GEN-LAST:event_b_usersActionPerformed

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_clearActionPerformed
        ta_chat.setText("");
    }//GEN-LAST:event_b_clearActionPerformed
    

	private void b_beginActionPerformed(ActionEvent evt)
    {

    	Iterator it = clientOutputStreams.iterator();
    	
    	while(it.hasNext())
    	{
    		try
    		{
    			PrintWriter writer = (PrintWriter) it.next();
    			writer.println(king);
    			ta_chat.append("Sending kingdoms");
    			ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
    		}
    		catch (Exception ex)
    		{
    			
    		}
    	}
    	
    	
    }

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new Server().setVisible(true);
            }
        });
    }
    
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  

            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);

                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);

				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				ta_chat.append("Got a connection. \n");
                }
            }
            catch (Exception ex)
            {
                ta_chat.append("Error making a connection. \n");
            }
        }
    }
    
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        ta_chat.append("Before " + name + " added. \n");
        users.add(name);
        ta_chat.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    

    public void tellEveryonePlayerPosition(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
            	
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                ta_chat.append("Sending: " + message + "\n");
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
            	
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                ta_chat.append("Sending: " + message + "\n");
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_start;
    private javax.swing.JButton b_users;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_name;
    private javax.swing.JTextArea ta_chat;
    private JButton btnBeginGame;
    private JPanel contentPane;
    private JList lstSelected;
    private JList<String> lstKingdoms;
    private DefaultListModel<String> lstModel2;
}
