package cschat;

import java.net.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class StartScreen extends JFrame 
{
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    int maxKingdoms = 0;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    //--------------------------//
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    //--------------------------//
    
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    //--------------------------//
    
    public void userRemove(String data) 
    {
         ta_chat.append(data + " is now offline.\n");
    }
    
    //--------------------------//
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             //users.append(token + "\n");
         }
    }
    
    //--------------------------//
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);

    }
    
    public StartScreen() 
    {
        init();
    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                        ta_chat.append(data[0] + ": " + data[1] + "\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        ta_chat.removeAll();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    public void init() 
    {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(100, 100, 600, 570);
    	contentPane = new JPanel();
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	contentPane.setLayout(new BorderLayout(0, 0));
    	setContentPane(contentPane);
    	
    	JPanel panel = new JPanel();
    	contentPane.add(panel, BorderLayout.CENTER);
    	panel.setLayout(null);
    	
    	JPanel panel_1 = new JPanel();
    	panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
    	panel_1.setBounds(10, 11, 265, 441);
    	panel.add(panel_1);
    	panel_1.setLayout(null);
    	
    	JScrollPane scrollPane_1 = new JScrollPane();
    	scrollPane_1.setBounds(10, 11, 102, 419);
    	panel_1.add(scrollPane_1);
    	
    	lstKingdoms = new JList<String>();
    	scrollPane_1.setViewportView(lstKingdoms);
    	DefaultListModel mdl1 = new DefaultListModel();
    	lstKingdoms.setModel(mdl1);
    	String[] items = {"Cellar", "Chapel", "Moat", "Harbinger", "Merchant", "Vassal", "Village", "Workshop", "Bureacrat", "Gardens", "Militia", "Moneylender", "Poacher", "Remodel", "Smithy", "Throne Room", "Bandit", "Council Room", "Festival", "Laboratory", "Library", "Market", "Mine", "Sentry", "Witch", "Artisan"};
    	for(int i =0; i<items.length; i++)
    	{
    		mdl1.add(i, items[i]);
    	}
    	
    	JScrollPane scrollPane_2 = new JScrollPane();
    	scrollPane_2.setBounds(160, 11, 102, 419);
    	panel_1.add(scrollPane_2);
    	
    	lstSelected = new JList();
    	DefaultListModel lstModel2 = new DefaultListModel();
    	lstSelected.setModel(lstModel2);
    	scrollPane_2.setViewportView(lstSelected);
    	
    	cmboPresets = new JComboBox();
    	cmboPresets.setModel(new DefaultComboBoxModel(new String[] {"Custom Kingdoms"}));
    	cmboPresets.setBounds(10, 463, 265, 22);
    	panel.add(cmboPresets);
    	
    	b_disconnect = new JButton("Exit");
    	b_disconnect.setFont(new Font("Tahoma", Font.PLAIN, 10));
    	b_disconnect.setBounds(505, 463, 57, 51);
    	panel.add(b_disconnect);	
    	 b_disconnect.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 b_disconnectActionPerformed(evt);
             }
         });
    	
    	btnStartGame = new JButton("Start");
    	btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 10));
    	btnStartGame.setBounds(285, 463, 57, 51);
    	panel.add(btnStartGame);	
    	
    	tf_username = new JTextField();
    	tf_username.setBounds(285, 440, 277, 20);
    	panel.add(tf_username);
    	tf_username.setColumns(10);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBounds(285, 11, 279, 418);
    	panel.add(scrollPane);		
    	
    	JPanel panel_2 = new JPanel();
    	panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
    	scrollPane.setViewportView(panel_2);
    	panel_2.setLayout(new BorderLayout(0, 0));	
    	
    	ta_chat = new JTextArea();
    	ta_chat.setWrapStyleWord(true);
    	ta_chat.setEditable(false);
    	panel_2.add(ta_chat, BorderLayout.CENTER);	
    	
    	b_send = new JButton("Send");
    	b_send.setFont(new Font("Tahoma", Font.PLAIN, 10));
    	b_send.setBounds(438, 463, 57, 51);
    	panel.add(b_send);	
    	b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });
    	
    	b_connect = new JButton("Join");
    	b_connect.setFont(new Font("Tahoma", Font.PLAIN, 10));
    	b_connect.setBounds(352, 463, 57, 51);
    	panel.add(b_connect);
    	b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });
    	
    	btnRemove = new JButton("<");
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
    				maxKingdoms -=1;
    				lstModel2.removeElementAt(index);
    			}
    			else {break;}
    			}
    			
    ;

    			}
    	}});
    	btnRemove.setBounds(114, 280, 41, 42);
    	panel_1.add(btnRemove);
    	
    	btnAddKingdom = new JButton(">");
    	btnAddKingdom.setBounds(114, 119, 41, 42);
    	btnAddKingdom.setEnabled(true);
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
    			
    			else
    			{
    				
    				break;
    			}

    			}
    		}
    	});
    	tf_chat = new JTextField();
    	tf_chat.setBounds(10, 492, 265, 22);
    	panel.add(tf_chat);
    	tf_chat.setColumns(10);
    	
    	
    }

    
    

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_addressActionPerformed
       
    }//GEN-LAST:event_tf_addressActionPerformed

    private void tf_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_portActionPerformed
   
    }//GEN-LAST:event_tf_portActionPerformed

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
    
    }//GEN-LAST:event_tf_usernameActionPerformed

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        if (isConnected == false) 
        {
            username = tf_username.getText();
            tf_username.setEditable(false);

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            ta_chat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_anonymousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_anonymousActionPerformed
        tf_username.setText("");
        if (isConnected == false) 
        {
            String anon="anon";
            Random generator = new Random(); 
            int i = generator.nextInt(999) + 1;
            String is=String.valueOf(i);
            anon=anon.concat(is);
            username=anon;
            
            tf_username.setText(anon);
            tf_username.setEditable(false);

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            ta_chat.append("You are already connected. \n");
        }        
    }//GEN-LAST:event_b_anonymousActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
               writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new StartScreen().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_anonymous;
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_name;
    private javax.swing.JLabel lb_password;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_password;
    private javax.swing.JTextField tf_port;
    private javax.swing.JTextField tf_username;
    private JPanel contentPane;
    private JButton btnAddKingdom;
    private JList lstSelected;
    private JList lstKingdoms;
    private JComboBox cmboPresets;
    private JButton btnRemove;
    private JButton btnStartGame;
    // End of variables declaration//GEN-END:variables
}
