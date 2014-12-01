package chatsystem.graphical;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import chatsystem.ChatSystem;

public class FenetreChat extends JFrame {

	private static final long serialVersionUID = 1574189318933480883L;

	public static final int WIDTH=1000;
	public static final int HEIGHT=700;

	private JButton send;
	
	private JTextField messageTextField;
	
	

	private ChatGUI gui;
	
	
	public FenetreChat(ChatGUI gui) {
		super(ChatSystem.NOM);
		this.gui=gui;
		this.setVisible(true);
		this.setSize(WIDTH,HEIGHT);
		
		JPanel panel=(JPanel) getContentPane();
		
		send=new JButton("send");
		//messageTextField=new JTextField();
		
		panel.add(send);
		panel.add(messageTextField);
		
		SpringLayout layout=new SpringLayout();
		panel.setLayout(layout);
		
		
		

		
		this.addWindowListener(new WindowAdapter() {

			public void windowsClosing(WindowEvent e){

				System.exit(0);
				FenetreChat.this.dispose();
			}
		});

	}



}
