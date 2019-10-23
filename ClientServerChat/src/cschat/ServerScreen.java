package cschat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ServerScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerScreen frame = new ServerScreen();
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
	public ServerScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 593, 312);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		taDisplay = new JTextArea();
		panel.add(taDisplay, BorderLayout.CENTER);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(51, 334, 89, 58);
		contentPane.add(btnStart);
		
		btnOnline = new JButton("Start");
		btnOnline.setBounds(191, 334, 89, 58);
		contentPane.add(btnOnline);
		
		btnClear = new JButton("Start");
		btnClear.setBounds(331, 334, 89, 58);
		contentPane.add(btnClear);
		
		btnEnd = new JButton("Start");
		btnEnd.setBounds(471, 334, 89, 58);
		contentPane.add(btnEnd);
	}
	
	private JButton btnStart;
	private JButton btnClear;
	private JButton btnOnline;
	private JButton btnEnd;
	private JTextArea taDisplay;
}
