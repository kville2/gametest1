package domgame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;


import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;


public class GameWindow extends JFrame 
{
	public GameWindow() {
	}

	private JPanel contentPane;
	private JButton btnBuy;
	static Game game;
	static JButton[] imgs;
	static JButton[] pfields;
	static int i;
	static int cash;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					
					GameWindow frame = new GameWindow();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	  public void GameWindow()
	    {
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1347, 869);
			contentPane = new JPanel();
			contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
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
			
			JTextArea taInfo = new JTextArea();
			taInfo.setText("Player info/VPs");
			taInfo.setWrapStyleWord(true);
			taInfo.setEditable(false);
			taInfo.setBounds(0, 0, 437, 118);
			panel_5.add(taInfo);

			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 488, 1103, 341);
			contentPane.add(scrollPane_1);
			
			JPanel pnlHand = new JPanel();
			scrollPane_1.setViewportView(pnlHand);
			Game.onStart();
			imgs = Game.getJButtons();
			JPanel pnlPlayingField = new JPanel();
			scrollPane.add(pnlPlayingField);
			scrollPane.setViewportView(pnlPlayingField);
			pnlPlayingField.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
			for(i = 0; i < imgs.length; i++)
			{
				
				
				pnlHand.add(imgs[i]);
				int in = Arrays.asList(imgs).indexOf(imgs[i]);

				imgs[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
			        {
						int length = pnlPlayingField.getWidth();
						int height = pnlPlayingField.getHeight();
						int index =in;
			            pnlPlayingField.add(imgs[index]);
			            imgs[index].setEnabled(false);
			            if(length < pnlPlayingField.getWidth())
			            {
			            	pnlPlayingField.setPreferredSize(new Dimension(length, height));
			            }
			            pnlHand.remove(imgs[index]);
			            cash = Game.handCash(cash,index);
			            taInfo.setText("Current Cash:" + String.valueOf(cash) );
			            validate();
			            repaint();
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
				public void actionPerformed(ActionEvent e) {
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
			panel_4.add(btnPlayAction, BorderLayout.WEST);
			
			btnBuy = new JButton("Buy Cards");
			panel_4.add(btnBuy, BorderLayout.EAST);
			
			btnEndTurn = new JButton("End Turn");
			panel_4.add(btnEndTurn, BorderLayout.SOUTH);

			
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
		}
	  

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

}
