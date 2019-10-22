package domgame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.MutableComboBoxModel;

import java.awt.event.ActionListener;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class StartScreen extends JFrame 
{

	private JPanel contentPane;
	private JTextField txtName;
	private int maxKingdoms = 0;
	String givename;
	String chatDisplay;
	static String mainDisplay;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		
		
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					
					StartScreen frame = new StartScreen();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
		
		try
		{
			 
	            
	         while(true)
	    		{
	        	 InetAddress ip = InetAddress.getByName("localhost"); 
		         // establish the connection with server port 5056 
		         Socket s = new Socket(ip, 7000); 	
		         // obtaining input and out streams 
		         DataInputStream dis = new DataInputStream(s.getInputStream()); 
		         DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
		            // the following loop performs the exchange of 
		            // information between client and client handler 
	    			mainDisplay =dis.readUTF();
	    			
	    		}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Create the frame.
	 */
	public StartScreen() 
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
		
		JList lstKingdoms = new JList();
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
		
		JList lstSelected = new JList();
		DefaultListModel lstModel2 = new DefaultListModel();
		lstSelected.setModel(lstModel2);
		scrollPane_2.setViewportView(lstSelected);
		
		JComboBox cmboPresets = new JComboBox();
		cmboPresets.setModel(new DefaultComboBoxModel(new String[] {"Custom Kingdoms"}));
		cmboPresets.setBounds(10, 463, 265, 22);
		panel.add(cmboPresets);
		
		
		
		
		JButton btnLeave = new JButton("Exit");
		btnLeave.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLeave.setBounds(505, 463, 57, 51);
		panel.add(btnLeave);
		
		JButton btnStartGame = new JButton("Start");
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnStartGame.setBounds(285, 463, 57, 51);
		panel.add(btnStartGame);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(10, 494, 265, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JTextArea taChatText = new JTextArea();
		taChatText.setBounds(285, 435, 277, 22);
		panel.add(taChatText);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 11, 279, 418);
		panel.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JTextArea taChatDisplay = new JTextArea();
		taChatDisplay.setWrapStyleWord(true);
		taChatDisplay.setEditable(false);
		panel_2.add(taChatDisplay, BorderLayout.CENTER);
		
		JButton btnSet = new JButton("Set");
		btnSet.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSet.setBounds(438, 463, 57, 51);
		panel.add(btnSet);
		
		JButton btnJoin = new JButton("Join");
		btnJoin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnJoin.setBounds(352, 463, 57, 51);
		panel.add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
				InetAddress ip = InetAddress.getByName("localhost");
				
				Socket s = new Socket(ip,7000);
				DataInputStream dis = new DataInputStream(s.getInputStream());
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				String name = txtName.getText();				
				dos.writeUTF(name);				
				chatDisplay = dis.readUTF();				
				taChatDisplay.append(chatDisplay + "\n");
				s.close();
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnRemove = new JButton("<");
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
		
		JButton btnAddKingdom = new JButton(">");
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
	}
}
