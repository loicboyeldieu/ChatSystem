package chatsystem.graphical;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import chatsystem.ChatSystem;

public class FenetreConnect extends JFrame {


	private static final long serialVersionUID = 6780578387145020970L;
	
	public static final int WIDTH=400;
	public static final int HEIGHT=100;

	private JLabel welcomeLabel, connexionLabel;
	private JTextField connexionArea;
	private JButton connexionButton;
	
	private static String valeur;
	private ChatGUI gui;
	
	public FenetreConnect(){
		super(ChatSystem.NOM);
		
		setBounds(400,300,WIDTH,HEIGHT);
		
		welcomeLabel=new JLabel("<html><p style='color:white; font-family:Calibri; font-size : 14px; margin-left:10px;'>Bienvenue sur le Chat System<p></html>");
		connexionLabel = new JLabel("<html><p style='color:white; font-family:Calibri; font-size : 12px; margin-left:10px;'>Choisissez un pseudo<p></html>");
		connexionButton=new JButton("Connect");
		connexionArea=new JTextField("",15);
		
		JPanel Pane = new JPanel();
		this.setContentPane(Pane);
		Pane.setBackground(new Color(230, 126, 34));
		Pane.setLayout(new BorderLayout());
		
		
		//TOP PANE
		JPanel topPane = new JPanel();
		topPane.setBackground(new Color(230, 126, 34));
		topPane.setLayout(new BorderLayout());
		Pane.add(topPane,BorderLayout.NORTH);
		
		topPane.add(welcomeLabel, BorderLayout.CENTER);
		topPane.add(connexionLabel, BorderLayout.SOUTH);
		
		//BOTTOM PANE
		JPanel bottomPane = new JPanel();
		bottomPane.setBackground(new Color(52, 152, 219));
		bottomPane.setLayout(new BorderLayout());
		Pane.add(bottomPane,BorderLayout.CENTER);
		
		bottomPane.add(connexionArea,BorderLayout.CENTER);
		bottomPane.add(connexionButton, BorderLayout.EAST);
		
		
		
		
		
	    this.pack();
	    this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    connexionButton.addActionListener(new ActionListener() {
			
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		valeur=connexionArea.getText();
				FenetreConnect.this.gui.connect();
				closeWindows();
				
			}
		});
	    
		this.addWindowListener(new WindowAdapter() {
			public void windowsClosing(WindowEvent e){
				System.exit(0);
			}
		});
	
	}
	
	public void closeWindows(){
		FenetreConnect.this.dispose();
	}
	
	public static String getNickname(){
		return valeur;
	}
	
	
	public static void main(String[] args) {
		new FenetreConnect();

	}


	public ChatGUI getGui() {
		return gui;
	}


	public void setGui(ChatGUI gui) {
		this.gui = gui;
	}
	
	

}

