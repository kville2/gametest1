package domgame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;

public class BuyScreen extends JFrame {

	private JPanel contentPane;
	static int cash;
	static int vp;
	static int buys;
	static String kings;
	static String kingdoms[];
	static String imagePath;
	static java.net.URL imageUrl;
	static String councilRoom = "Council_Room";
	static String throneRoom = "Throne_Room";
	static int btn1Cost;
	static int btn2Cost;
	static int btn3Cost;
	static int btn4Cost;
	static int btn5Cost;
	static int btn6Cost;
	static int btn7Cost;
	static int btn8Cost;
	static int btn9Cost;
	static int btn10Cost;
	Card ck;
	BuyScreen fr1;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyScreen frame = new BuyScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BuyScreen() {
		
		
		kings = StartScreen.setBuyScreen();
		kingdoms = kings.split(":");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1318, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		buyPanel = new JPanel();
		buyPanel.setAutoscrolls(true);
		buyPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setViewportView(buyPanel);
		GridBagLayout gbl_buyPanel = new GridBagLayout();
		gbl_buyPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_buyPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_buyPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_buyPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		buyPanel.setLayout(gbl_buyPanel);
		
		btnCopper = new JButton("10");
		btnCopper.setBackground(SystemColor.control);
		btnCopper.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnCopper.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCopper.setVerticalTextPosition(SwingConstants.TOP);
		btnCopper.setIcon(new ImageIcon(BuyScreen.class.getResource("/Images/Copper.jpg")));
		GridBagConstraints gbc_btnCopper = new GridBagConstraints();
		gbc_btnCopper.insets = new Insets(0, 0, 5, 5);
		gbc_btnCopper.gridx = 0;
		gbc_btnCopper.gridy = 0;
		buyPanel.add(btnCopper, gbc_btnCopper);
		if(StartScreen.buys != 0)
		{
			btnCopper.setEnabled(true);
		}
		else
		{
			btnCopper.setEnabled(false);
		}
		btnCopper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard("COPPER");
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);

			}
		});
		
		btnSilver = new JButton("10");
		btnSilver.setIcon(new ImageIcon(BuyScreen.class.getResource("/Images/Silver.jpg")));
		btnSilver.setVerticalTextPosition(SwingConstants.TOP);
		btnSilver.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSilver.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSilver.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnSilver = new GridBagConstraints();
		gbc_btnSilver.insets = new Insets(0, 0, 5, 5);
		gbc_btnSilver.gridx = 1;
		gbc_btnSilver.gridy = 0;
		buyPanel.add(btnSilver, gbc_btnSilver);
		if(StartScreen.cash >= 3 && StartScreen.buys >=1)
		{
			btnSilver.setEnabled(true);
		}
		else
		{
			btnSilver.setEnabled(false);
		}
		btnSilver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard("SILVER");
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btnGold = new JButton("10");
		btnGold.setIcon(new ImageIcon(BuyScreen.class.getResource("/Images/Gold.jpg")));
		btnGold.setVerticalTextPosition(SwingConstants.TOP);
		btnGold.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGold.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnGold.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnGold = new GridBagConstraints();
		gbc_btnGold.insets = new Insets(0, 0, 5, 5);
		gbc_btnGold.gridx = 2;
		gbc_btnGold.gridy = 0;
		buyPanel.add(btnGold, gbc_btnGold);
		if(StartScreen.cash >= 6 && StartScreen.buys >=1)
		{
			btnGold.setEnabled(true);
		}
		else
		{
			btnGold.setEnabled(false);
		}
		btnGold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard("GOLD");
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btnEstate = new JButton("10");
		btnEstate.setIcon(new ImageIcon(BuyScreen.class.getResource("/Images/Estate.jpg")));
		btnEstate.setVerticalTextPosition(SwingConstants.TOP);
		btnEstate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEstate.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnEstate.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnEstate = new GridBagConstraints();
		gbc_btnEstate.insets = new Insets(0, 0, 5, 5);
		gbc_btnEstate.gridx = 3;
		gbc_btnEstate.gridy = 0;
		buyPanel.add(btnEstate, gbc_btnEstate);
		if(StartScreen.cash >= 2 && StartScreen.buys >=1)
		{
			btnEstate.setEnabled(true);
		}
		else
		{
			btnEstate.setEnabled(false);
		}
		btnEstate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard("ESTATE");
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btnDuchy = new JButton("10");
		btnDuchy.setIcon(new ImageIcon(BuyScreen.class.getResource("/Images/Duchy.jpg")));
		btnDuchy.setVerticalTextPosition(SwingConstants.TOP);
		btnDuchy.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDuchy.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDuchy.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnDuchy = new GridBagConstraints();
		gbc_btnDuchy.insets = new Insets(0, 0, 5, 5);
		gbc_btnDuchy.gridx = 4;
		gbc_btnDuchy.gridy = 0;
		buyPanel.add(btnDuchy, gbc_btnDuchy);
		if(StartScreen.cash >= 5 && StartScreen.buys >=1)
		{
			btnDuchy.setEnabled(true);
		}
		else
		{
			btnDuchy.setEnabled(false);
		}
		btnDuchy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard("DUCHY");
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btnProvince = new JButton("10");
		btnProvince.setIcon(new ImageIcon(BuyScreen.class.getResource("/Images/Province.jpg")));
		btnProvince.setVerticalTextPosition(SwingConstants.TOP);
		btnProvince.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProvince.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnProvince.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnProvince = new GridBagConstraints();
		gbc_btnProvince.insets = new Insets(0, 0, 5, 0);
		gbc_btnProvince.gridx = 5;
		gbc_btnProvince.gridy = 0;
		buyPanel.add(btnProvince, gbc_btnProvince);
		if(StartScreen.cash >= 8 && StartScreen.buys >=1)
		{
			btnProvince.setEnabled(true);
		}
		else
		{
			btnProvince.setEnabled(false);
		}
		btnProvince.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard("PROVINCE");
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btnCurse = new JButton("10");
		btnCurse.setIcon(new ImageIcon(BuyScreen.class.getResource("/Images/Curse.jpg")));
		btnCurse.setVerticalTextPosition(SwingConstants.TOP);
		btnCurse.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCurse.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnCurse.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btnCurse = new GridBagConstraints();
		gbc_btnCurse.insets = new Insets(0, 0, 5, 5);
		gbc_btnCurse.gridx = 0;
		gbc_btnCurse.gridy = 1;
		buyPanel.add(btnCurse, gbc_btnCurse);
		if(StartScreen.buys >=1)
		{
			btnCurse.setEnabled(true);
		}
		else
		{
			btnCurse.setEnabled(false);
		}
		btnCurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard("CURSE");
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btn1 = new JButton("10");
		btn1.setVerticalTextPosition(SwingConstants.TOP);
		btn1.setHorizontalTextPosition(SwingConstants.CENTER);
		btn1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn1.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btn1 = new GridBagConstraints();
		gbc_btn1.insets = new Insets(0, 0, 5, 5);
		gbc_btn1.gridx = 1;
		gbc_btn1.gridy = 1;
		imageUrl = Game.class.getResource("/Images/" + kingdoms[1] + ".jpg");
		btn1.setIcon(new ImageIcon(imageUrl));		
		buyPanel.add(btn1, gbc_btn1);
		if(kingdoms[1].equals("Throne_Room"))
		{
			ck = new Card(CardName.valueOf("Throneroom".toUpperCase()));
		}
		
		else if(kingdoms[1].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf("Councilroom".toUpperCase()));
		}
		else if(!kingdoms[1].equals("Throne_Room") && !kingdoms[1].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf(kingdoms[1].toUpperCase()));
		}
		btn1Cost = ck.getCost();
		if(StartScreen.cash >= btn1Cost && StartScreen.buys >=1)
		{
			btn1.setEnabled(true);
		}
		else
		{
			btn1.setEnabled(false);
		}
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard(kingdoms[1]);
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		
		
		btn2 = new JButton("10");
		btn2.setVerticalTextPosition(SwingConstants.TOP);
		btn2.setHorizontalTextPosition(SwingConstants.CENTER);
		btn2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn2.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btn2 = new GridBagConstraints();
		gbc_btn2.insets = new Insets(0, 0, 5, 5);
		gbc_btn2.gridx = 2;
		gbc_btn2.gridy = 1;
		imageUrl = Game.class.getResource("/Images/" + kingdoms[2] + ".jpg");
		btn2.setIcon(new ImageIcon(imageUrl));
		buyPanel.add(btn2, gbc_btn2);
		if(kingdoms[2].equals("Throne_Room"))
		{
			ck = new Card(CardName.valueOf("Throneroom".toUpperCase()));
		}
		
		else if(kingdoms[2].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf("Councilroom".toUpperCase()));
		}
		else if(!kingdoms[2].equals("Throne_Room") && !kingdoms[2].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf(kingdoms[1].toUpperCase()));
		}
		btn2Cost = ck.getCost();
		if(StartScreen.cash >= btn2Cost && StartScreen.buys >=1)
		{
			btn2.setEnabled(true);
		}
		else
		{
			btn2.setEnabled(false);
		}
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard(kingdoms[2]);
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btn3 = new JButton("10");
		btn3.setVerticalTextPosition(SwingConstants.TOP);
		btn3.setHorizontalTextPosition(SwingConstants.CENTER);
		btn3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn3.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btn3 = new GridBagConstraints();
		gbc_btn3.insets = new Insets(0, 0, 5, 5);
		gbc_btn3.gridx = 3;
		gbc_btn3.gridy = 1;
		imageUrl = Game.class.getResource("/Images/" + kingdoms[3] + ".jpg");
		btn3.setIcon(new ImageIcon(imageUrl));
		buyPanel.add(btn3, gbc_btn3);
		if(kingdoms[3].equals("Throne_Room"))
		{
			ck = new Card(CardName.valueOf("Throneroom".toUpperCase()));
		}
		
		else if(kingdoms[3].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf("Councilroom".toUpperCase()));
		}
		else if(!kingdoms[3].equals("Throne_Room") && !kingdoms[3].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf(kingdoms[3].toUpperCase()));
		}
		btn3Cost = ck.getCost();
		if(StartScreen.cash >= btn3Cost && StartScreen.buys >=1)
		{
			btn3.setEnabled(true);
		}
		else if(kingdoms[3] != "Throne_Room" && kingdoms[3] != "Council_Room")
		{
			btn3.setEnabled(false);
		}
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard(kingdoms[3]);
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btn4 = new JButton("10");
		btn4.setVerticalTextPosition(SwingConstants.TOP);
		btn4.setHorizontalTextPosition(SwingConstants.CENTER);
		btn4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn4.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btn4 = new GridBagConstraints();
		gbc_btn4.insets = new Insets(0, 0, 5, 5);
		gbc_btn4.gridx = 4;
		gbc_btn4.gridy = 1;
		imageUrl = Game.class.getResource("/Images/" + kingdoms[4] + ".jpg");
		btn4.setIcon(new ImageIcon(imageUrl));
		buyPanel.add(btn4, gbc_btn4);
		if(kingdoms[4].equals("Throne_Room"))
		{
			ck = new Card(CardName.valueOf("Throneroom".toUpperCase()));
		}
		
		else if(kingdoms[4].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf("Councilroom".toUpperCase()));
		}
		else if(!kingdoms[4].equals("Throne_Room") && !kingdoms[4].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf(kingdoms[4].toUpperCase()));
		}
		btn4Cost = ck.getCost();
		if(StartScreen.cash >= btn4Cost && StartScreen.buys >=1)
		{
			btn4.setEnabled(true);
		}
		else if(kingdoms[4] != "Throne_Room" && kingdoms[4] != "Council_Room")
		{
			btn4.setEnabled(false);
		}
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard(kingdoms[4]);
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btn5 = new JButton("10");
		btn5.setVerticalTextPosition(SwingConstants.TOP);
		btn5.setHorizontalTextPosition(SwingConstants.CENTER);
		btn5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn5.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btn5 = new GridBagConstraints();
		gbc_btn5.insets = new Insets(0, 0, 5, 0);
		gbc_btn5.gridx = 5;
		gbc_btn5.gridy = 1;
		imageUrl = Game.class.getResource("/Images/" + kingdoms[5] + ".jpg");
		btn5.setIcon(new ImageIcon(imageUrl));
		buyPanel.add(btn5, gbc_btn5);
		if(kingdoms[5].equals("Throne_Room"))
		{
			ck = new Card(CardName.valueOf("Throneroom".toUpperCase()));
		}
		
		else if(kingdoms[5].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf("Councilroom".toUpperCase()));
		}
		else if(!kingdoms[5].equals("Throne_Room") && !kingdoms[5].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf(kingdoms[5].toUpperCase()));
		}
		btn5Cost = ck.getCost();
		if(StartScreen.cash >= btn5Cost && StartScreen.buys >=1)
		{
			btn5.setEnabled(true);
		}
		else
		{
			btn5.setEnabled(false);
		}
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard(kingdoms[5]);
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btn6 = new JButton("10");
		btn6.setIcon(new ImageIcon(BuyScreen.class.getResource("/Images/Woodcutter.jpg")));
		btn6.setVerticalTextPosition(SwingConstants.TOP);
		btn6.setHorizontalTextPosition(SwingConstants.CENTER);
		btn6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn6.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btn6 = new GridBagConstraints();
		gbc_btn6.insets = new Insets(0, 0, 0, 5);
		gbc_btn6.gridx = 0;
		gbc_btn6.gridy = 2;
		imageUrl = Game.class.getResource("/Images/" + kingdoms[6] + ".jpg");

		btn6.setIcon(new ImageIcon(imageUrl));
		buyPanel.add(btn6, gbc_btn6);
		if(kingdoms[6].equals("Throne_Room"))
		{
			ck = new Card(CardName.valueOf("Throneroom".toUpperCase()));
		}
		
		else if(kingdoms[6].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf("Councilroom".toUpperCase()));
		}
		else if(!kingdoms[6].equals("Throne_Room") && !kingdoms[6].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf(kingdoms[6].toUpperCase()));
		}
		btn6Cost = ck.getCost();
		if(StartScreen.cash >= btn6Cost && StartScreen.buys >=1)
		{
			btn6.setEnabled(true);
		}
		else
		{
			btn6.setEnabled(false);
		}
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard(kingdoms[6]);
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btn7 = new JButton("10");
		btn7.setVerticalTextPosition(SwingConstants.TOP);
		btn7.setHorizontalTextPosition(SwingConstants.CENTER);
		btn7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn7.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btn7 = new GridBagConstraints();
		gbc_btn7.insets = new Insets(0, 0, 0, 5);
		gbc_btn7.gridx = 1;
		gbc_btn7.gridy = 2;
		imageUrl = Game.class.getResource("/Images/" + kingdoms[7] + ".jpg");
		btn7.setIcon(new ImageIcon(imageUrl));
		buyPanel.add(btn7, gbc_btn7);
		if(kingdoms[7].equals("Throne_Room"))
		{
			ck = new Card(CardName.valueOf("Throneroom".toUpperCase()));
		}
		
		else if(kingdoms[7].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf("Councilroom".toUpperCase()));
		}
		else if(!kingdoms[7].equals("Throne_Room") && !kingdoms[7].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf(kingdoms[7].toUpperCase()));
		}
		btn7Cost = ck.getCost();
		if(StartScreen.cash >= btn7Cost && StartScreen.buys >=1)
		{
			btn7.setEnabled(true);
		}
		else
		{
			btn7.setEnabled(false);
		}
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard(kingdoms[7]);
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btn8 = new JButton("10");
		btn8.setVerticalTextPosition(SwingConstants.TOP);
		btn8.setHorizontalTextPosition(SwingConstants.CENTER);
		btn8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn8.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btn8 = new GridBagConstraints();
		gbc_btn8.insets = new Insets(0, 0, 0, 5);
		gbc_btn8.gridx = 2;
		gbc_btn8.gridy = 2;
		imageUrl = Game.class.getResource("/Images/" + kingdoms[8] + ".jpg");
		btn8.setIcon(new ImageIcon(imageUrl));
		buyPanel.add(btn8, gbc_btn8);
		if(kingdoms[8].equals("Throne_Room"))
		{
			ck = new Card(CardName.valueOf("Throneroom".toUpperCase()));
		}
		
		else if(kingdoms[8].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf("Councilroom".toUpperCase()));
		}
		else if(!kingdoms[8].equals("Throne_Room") && !kingdoms[8].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf(kingdoms[8].toUpperCase()));
		}
		btn8Cost = ck.getCost();
		if(StartScreen.cash >= btn8Cost && StartScreen.buys >=1)
		{
			btn8.setEnabled(true);
		}
		else
		{
			btn8.setEnabled(false);
		}
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard(kingdoms[8]);
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		btn9 = new JButton("10");
		btn9.setVerticalTextPosition(SwingConstants.TOP);
		btn9.setHorizontalTextPosition(SwingConstants.CENTER);
		btn9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn9.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btn9 = new GridBagConstraints();
		gbc_btn9.insets = new Insets(0, 0, 0, 5);
		gbc_btn9.gridx = 3;
		gbc_btn9.gridy = 2;
		imageUrl = Game.class.getResource("/Images/" + kingdoms[9] + ".jpg");
		btn9.setIcon(new ImageIcon(imageUrl));
		buyPanel.add(btn9, gbc_btn9);
		if(kingdoms[9].equals("Throne_Room"))
		{
			ck = new Card(CardName.valueOf("Throneroom".toUpperCase()));
		}
		
		else if(kingdoms[9].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf("Councilroom".toUpperCase()));
		}
		else if(!kingdoms[9].equals("Throne_Room") && !kingdoms[9].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf(kingdoms[9].toUpperCase()));
		}
		btn9Cost = ck.getCost();
		if(StartScreen.cash >= btn9Cost && StartScreen.buys >=1)
		{
			btn9.setEnabled(true);
		}
		else
		{
			btn9.setEnabled(false);
		}
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard(kingdoms[9]);
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
	    btn10 = new JButton("10");
		btn10.setVerticalTextPosition(SwingConstants.TOP);
		btn10.setHorizontalTextPosition(SwingConstants.CENTER);
		btn10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn10.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btn10 = new GridBagConstraints();
		gbc_btn10.insets = new Insets(0, 0, 0, 5);
		gbc_btn10.gridx = 4;
		gbc_btn10.gridy = 2;
		imageUrl = Game.class.getResource("/Images/" + kingdoms[10] + ".jpg");
		btn10.setIcon(new ImageIcon(imageUrl));
		buyPanel.add(btn10, gbc_btn10);
		if(kingdoms[10].equals("Throne_Room"))
		{
			ck = new Card(CardName.valueOf("Throneroom".toUpperCase()));
		}
		
		else if(kingdoms[10].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf("Councilroom".toUpperCase()));
		}
		else if(!kingdoms[10].equals("Throne_Room") && !kingdoms[10].equals("Council_Room"))
		{
			ck = new Card(CardName.valueOf(kingdoms[10].toUpperCase()));
		}
		btn10Cost = ck.getCost();
		if(StartScreen.cash >= btn10Cost && StartScreen.buys >=1)
		{
			btn10.setEnabled(true);
		}
		else
		{
			btn10.setEnabled(false);
		}
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Game.buyCard(kingdoms[10]);
				StartScreen.buys -= 1;
				dispose();
				fr1 = new BuyScreen();
				fr1.setVisible(true);
			}
		});
		
		JPanel buyPanel_1 = new JPanel();
		buyPanel_1.setLayout(null);
		GridBagConstraints gbc_buyPanel_1 = new GridBagConstraints();
		gbc_buyPanel_1.fill = GridBagConstraints.BOTH;
		gbc_buyPanel_1.gridx = 5;
		gbc_buyPanel_1.gridy = 2;
		buyPanel.add(buyPanel_1, gbc_buyPanel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 11, 187, 258);
		buyPanel_1.add(textArea);
		
		JButton btnEndBuy = new JButton("End Buys");
		btnEndBuy.setBounds(10, 280, 89, 69);
		buyPanel_1.add(btnEndBuy);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(109, 280, 89, 69);
		buyPanel_1.add(btnClear);
	}
	
	static JPanel buyPanel;
	static JButton btnCopper;
    static JButton btnSilver;
    static JButton btnGold;
    static JButton btnEstate;
    static JButton btnDuchy;
    static JButton btnProvince;
    static JButton btnCurse;
    static JButton btn1;
    static JButton btn2;
    static JButton btn3;
    static JButton btn4;
    static JButton btn5;
    static JButton btn6;
    static JButton btn7;
    static JButton btn8;
    static JButton btn9;
    static JButton btn10;


}
