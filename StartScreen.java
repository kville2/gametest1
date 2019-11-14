package domgame;

import java.net.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class StartScreen extends JFrame 
{
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    ArrayList<String> kingArray;
    static String buyScreenKingdoms;
    int port = 2222;
    Boolean isConnected = false;
    int maxKingdoms = 0;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
	private JButton btnBuy;
	static Game game;
	static JButton[] imgs;
	static JButton[] pfields;
	static JLabel[] tempfields;
	static int i;
	static int cash;
	static boolean activePlayer = false;
	static String king = "";
	static String playerTurnNum = "";
	static String turnOrder ="";
	static String kings;
	static String tempKings[];
	static String tempHand[];
	static String kingdoms[];
	StartScreen sc;
	static String imagePath;
	static java.net.URL imageUrl;
	public static int buys = 1;
	static Boolean gameStarted = false;
	static int imageL;
    
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
     
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat", tLeader = "Leader";;
            String kingdoms = "Kingdoms";
            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");
                     

                     if(data[0].equals(kingdoms))
                     {
                    	 buyScreenKingdoms = stream;
                    	 startGameScreen();
                     }
                     
                     
                     else if(data[0].equals("pField"))
                     {
                    	 
                    		int length = pnlPlayingField.getWidth();
         					int height = pnlPlayingField.getHeight();
         					pnlPlayingField.setVisible(true);
         					JLabel newLbl = new JLabel(new ImageIcon(GameWindow.class.getResource("/Images/" + data[1] +".jpg")));
                    		pnlPlayingField.add(newLbl);
                    		newLbl.setVisible(true);
                    		if(length < pnlPlayingField.getWidth())
           		            {
           		            	pnlPlayingField.setPreferredSize(new Dimension(length, height));
           		            }
                    		pnlPlayingField.validate();
         		            pnlPlayingField.repaint();
                    	
                    	 
                     }
                     
                     else if(data[0].equals("NextTurn"))
                     {
                    	 tempHand = Game.handn.split(":");
                    	 pnlPlayingField.removeAll();
                    	 pnlPlayingField.revalidate();
                    	 pnlPlayingField.repaint();
                    	 if(data[1].equals(playerTurnNum))
                    	 {
                    		 btnBuy.setEnabled(true);
                    		 btnEndTurn.setEnabled(true);
                    		 activePlayer = true;
                    		 pnlHand.revalidate();
                    		 pnlHand.repaint();
                    		 for(i = 0; i < imgs.length; i++)
             				{
             					
             						String isVictory;
             						Card ck1;
             						ck1 = new Card(CardName.valueOf(tempHand[i].toUpperCase()));
             						isVictory = ck1.getType();
             						
             						
             						
             						if(isVictory.equals("Victory"))
             						{
             							imgs[i].setEnabled(false);
             						}
             						else
             						{
             							imgs[i].setEnabled(true);
             						}

             						
             					}
             				}
                    	 }
                    
              

                     else if (data[2].equals(chat)) 
                     {
                    	if(playerTurnNum.equals(""))
                    	{
                    		playerTurnNum = data[3];
                    		turnOrder = data[4].toString();
                    	}
                    	
                		if(playerTurnNum.equals("Player1"))
                		{
                			activePlayer = true;
                		}
                    	
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
    
    public void startBuyScreen()
    {
    	BuyScreen fr = new BuyScreen();
    	fr.setVisible(true);
    }
    
   
    
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
    	String[] items = {"Cellar", "Chapel", "Moat", "Harbinger", "Merchant", "Vassal", "Village", "Workshop", "Bureaucrat", "Gardens", "Militia", "Moneylender", "Poacher", "Remodel", "Smithy", "Throne_Room", "Bandit", "Council_Room", "Festival", "Laboratory", "Library", "Market", "Mine", "Sentry", "Witch", "Artisan"};
    	for(int i =0; i<items.length; i++)
    	{
    		mdl1.add(i, items[i]);
    	}
    	
    	JScrollPane scrollPane_2 = new JScrollPane();
    	scrollPane_2.setBounds(160, 11, 102, 419);
    	panel_1.add(scrollPane_2);
    	
    	lstSelected = new JList();
    	lstModel2 = new DefaultListModel();
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
    	btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               startGameActionPerformed(evt);
            }
        });
    	
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

    public void startGameScreen()
    {
    	gameInit();
    	
    }
    
    public  void gameInit()
    {
     
    
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1347, 869);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1129, 11, 202, 332);
		panel_2.setBorder(null);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDeck = new JLabel("");
		lblDeck.setIcon(new ImageIcon(GameWindow.class.getResource("/Images/Artisan.jpg")));
		panel_2.add(lblDeck, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setPreferredSize(new Dimension(512, 448));
		scrollPane.setBounds(10, 11, 1103, 341);
		contentPane.add(scrollPane);
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 363, 439, 120);
		contentPane.add(scrollPane_2);
		
		JPanel panel_5 = new JPanel();
		scrollPane_2.setViewportView(panel_5);
		panel_5.setLayout(null);
		
	    taInfo = new JTextArea();
		taInfo.setText("Player info/VPs");
		taInfo.setWrapStyleWord(true);
		taInfo.setEditable(false);
		taInfo.setBounds(0, 0, 437, 118);
		panel_5.add(taInfo);

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 488, 1103, 341);
		contentPane.add(scrollPane_1);
		
		pnlHand = new JPanel();
		scrollPane_1.setViewportView(pnlHand);
		pnlPlayingField = new JPanel();
		scrollPane.add(pnlPlayingField);
		scrollPane.setViewportView(pnlPlayingField);
		pnlPlayingField.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
	  	if(gameStarted == false)
    	{
    		Game.onStart();
    		gameStarted = true;
    		
    	}
	  	imgs = Game.getJButtons();
    	
		for(i = 0; i < imgs.length; i++)
		{
			
			
			pnlHand.add(imgs[i]);
			int in = Arrays.asList(imgs).indexOf(imgs[i]);
			if(activePlayer != true )
			{
				imgs[i].setEnabled(false);
			}

			


			imgs[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
		        {
					String tempString = null;
					int index =in;  
		            pnlHand.remove(imgs[index]);		            
		            cash = Game.handCash(cash,index);
		            taInfo.setText("Current Cash:" + String.valueOf(cash) );
		            validate();
		            repaint();
		            tempString = Game.handNames[index];
		            sendPlayingField(tempString);
		            
		        }
				
		    });
		}
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBounds(1129, 497, 202, 332);
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(GameWindow.class.getResource("/Images/Copper.jpg")));
		panel_3.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JButton btnBuyArea = new JButton("Buy Area");
		btnBuyArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				BuyScreen fr = new BuyScreen();
				kingdoms = buyScreenKingdoms.split(":");
				java.net.URL imageUrl = Game.class.getResource("/Images/" + kingdoms[1] + ".jpg");
				BuyScreen.btn1.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/" + kingdoms[2] + ".jpg");
				BuyScreen.btn2.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/" + kingdoms[3] + ".jpg");
				BuyScreen.btn3.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/" + kingdoms[4] + ".jpg");
				BuyScreen.btn4.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/" + kingdoms[5] + ".jpg");
				BuyScreen.btn5.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/" + kingdoms[6] + ".jpg");
				BuyScreen.btn6.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/" + kingdoms[7] + ".jpg");
				BuyScreen.btn7.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/" + kingdoms[8] + ".jpg");
				BuyScreen.btn8.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/" + kingdoms[9] + ".jpg");
				BuyScreen.btn9.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/" + kingdoms[10] + ".jpg");
				BuyScreen.btn10.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/Copper.jpg");
				BuyScreen.btnCopper.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/Silver.jpg");
				BuyScreen.btnSilver.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/Gold.jpg");
				BuyScreen.btnGold.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/Estate.jpg");
				BuyScreen.btnEstate.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/Duchy.jpg");
				BuyScreen.btnDuchy.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/Province.jpg");
				BuyScreen.btnProvince.setDisabledIcon(new ImageIcon(imageUrl));
				imageUrl = Game.class.getResource("/Images/Curse.jpg");
				BuyScreen.btnCurse.setDisabledIcon(new ImageIcon(imageUrl));
				BuyScreen.btnCopper.setEnabled(false);
				BuyScreen.btnSilver.setEnabled(false);
				BuyScreen.btnGold.setEnabled(false);
				BuyScreen.btnEstate.setEnabled(false);
				BuyScreen. btnDuchy.setEnabled(false);
				BuyScreen.btnProvince.setEnabled(false);
				BuyScreen.btnCurse.setEnabled(false);
				BuyScreen.btn1.setEnabled(false);
				BuyScreen.btn2.setEnabled(false);
				BuyScreen.btn3.setEnabled(false);
				BuyScreen.btn4.setEnabled(false);
				BuyScreen.btn5.setEnabled(false);
				BuyScreen.btn6.setEnabled(false);
				BuyScreen.btn7.setEnabled(false);
				BuyScreen.btn8.setEnabled(false);
				BuyScreen.btn9.setEnabled(false);
				BuyScreen.btn10.setEnabled(false);
				fr.setVisible(true);
			}
		});
		btnBuyArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuyArea.setBounds(1129, 354, 89, 132);
		contentPane.add(btnBuyArea);
		
		JButton btnTrashScreen = new JButton("Trash Area");
		btnTrashScreen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTrashScreen.setBounds(1242, 354, 89, 132);
		contentPane.add(btnTrashScreen);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(460, 363, 202, 120);
		contentPane.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		lblPhase = new JLabel("SelectAction");
		lblPhase.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPhase.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblPhase, BorderLayout.NORTH);
		
		btnPlayAction = new JButton("Play Action");
		boolean checkAction = Game.isAction();
		if(checkAction == false)
		{
			btnPlayAction.setEnabled(false);
			lblPhase.setText("Select Treasures");
		}
		
		else
		{
			btnPlayAction.setEnabled(true);
		}
		
		if(activePlayer != true)
		{
			btnPlayAction.setEnabled(false);
		}
		else
		{
			btnPlayAction.setEnabled(true);
		}

		panel_4.add(btnPlayAction, BorderLayout.WEST);
		
		btnBuy = new JButton("Buy Cards");
		panel_4.add(btnBuy, BorderLayout.EAST);
		if(activePlayer != true)
		{
			btnBuy.setEnabled(false);
		}
		else
		{
			btnBuy.setEnabled(true);
		}
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				BuyScreen fr = new BuyScreen();
				fr.setVisible(true);
					
			}
		});
	
		btnEndTurn = new JButton("End Turn");
		panel_4.add(btnEndTurn, BorderLayout.SOUTH);
		if(activePlayer == true)
		{
			btnEndTurn.setEnabled(true);
		}
		else
		{
			btnEndTurn.setEnabled(false);
		}
		btnEndTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				pnlPlayingField.removeAll();
				pnlPlayingField.revalidate();
				pnlPlayingField.repaint();
				cash = 0;
				buys = 1;
				btnBuy.setEnabled(false);
				btnPlayAction.setEnabled(false);
				btnEndTurn.setEnabled(false);
				activePlayer = false;
				pnlHand.removeAll();
				pnlHand.revalidate();
				pnlHand.repaint();
				Game.handn = "";
				Game.newHand();
				imgs = Game.getJButtons();
				startGameScreen();
				writer.println("endOfTurn:" + turnOrder);
				writer.flush();
			}
		});

		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(672, 363, 439, 120);
		contentPane.add(scrollPane_3);
		
		JPanel panel_6 = new JPanel();
		scrollPane_3.setViewportView(panel_6);
		panel_6.setLayout(null);
		
		taTurnLogs = new JTextArea();
		taTurnLogs.setText("Turn Logs");
		taTurnLogs.setEditable(false);
		taTurnLogs.setWrapStyleWord(true);
		taTurnLogs.setBounds(0, 0, 437, 118);
		panel_6.add(taTurnLogs);
		this.setVisible(true);
		repaint();
	}
    
    private void sendPlayingField(String kingdoms2)
    {
    	writer.println("pField:" + kingdoms2);
    	writer.flush();
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

    private void startGameActionPerformed(ActionEvent evt)
    {
    	
    	for(int m = 0; m <lstModel2.size(); m++)
    	{
    		 
    		 king += lstModel2.get(m).toString()+ ":";
    	}
    	kings = king;
    	kingdoms = kings.split(":");
    	king = "Kingdoms:" + king;
    	buyScreenKingdoms = king;
    	try
    	{
    		writer.println(buyScreenKingdoms);
    		writer.flush();
    	}
    	catch(Exception ex)
    	{
    		
    	}
    	
    	
    }
    
    public static String setBuyScreen()
    {
    	return buyScreenKingdoms;
    }
    
    private void transferCards(java.awt.event.ActionEvent evt)
    {
    	
    }
    
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
        if ((tf_chat.getText()).equals(nothing)) 
        {
            tf_chat.setText("");
            tf_chat.requestFocus();
        }
        else 
        {
            try 
            {
               writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }

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
    private JPanel pnlHand;
    private JPanel contentPane;
    private JPanel pnlPlayingField;
    private JButton btnAddKingdom;
    private JList lstSelected;
    private JList lstKingdoms;
    private JComboBox cmboPresets;
    private JButton btnRemove;
    private JButton btnStartGame;
    private JTextArea taTurnLogs;
    private JButton btnEndTurn;
    private JLabel lblPhase;
    private JButton btnPlayAction;
    private DefaultListModel lstModel2;
    private JTextArea taInfo;
    private JButton btnCopper;
    private JButton btnSilver;
    private JButton btnGold;
    private JButton btnEstate;
    private JButton btnDuchy;
    private JButton btnProvince;
    private JButton btnCurse;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btn10;



    // End of variables declaration//GEN-END:variables
}
