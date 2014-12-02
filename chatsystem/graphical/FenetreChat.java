package chatsystem.graphical;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import chatsystem.ChatSystem;

public class FenetreChat extends JFrame {

	private static final long serialVersionUID = 1574189318933480883L;

	public static final int WIDTH=1000;
	public static final int HEIGHT=700;

	private JButton disconnectButton;
	
	private JTextField messageTextField;
	
	

	private ChatGUI gui;
	
	
	public FenetreChat(ChatGUI gui) {
		super(ChatSystem.NOM);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.gui=gui;
		this.setVisible(true);
		this.setSize(WIDTH,HEIGHT);
		
		JPanel panel=(JPanel) getContentPane();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0,0));
		
		
		disconnectButton=new JButton("disconnect");
		disconnectButton.setBounds(670, 900, JButton.HEIGHT, JButton.WIDTH);
		
		
		//messageTextField=new JTextField();
		
		panel.add(disconnectButton);
		//panel.add(messageTextField);
		
		
		disconnectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FenetreChat.this.gui.disconnect();
				
			}
		});
		
		

	}
	
	
	public static void main(String[] args) {

					FenetreTest frame = new FenetreTest();
					frame.setVisible(true);
				
			}




}
