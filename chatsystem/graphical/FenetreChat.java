package chatsystem.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import chatsystem.ChatSystem;
import chatsystem.User;

public class FenetreChat extends JFrame implements ListSelectionListener{

	private static final long serialVersionUID = 1574189318933480883L;

	public static final int WIDTH=1000;
	public static final int HEIGHT=600;

	private JButton disconnectButton,sendButton;
	private JTextArea messageTextArea;
	private JList liste;
	private JLabel labelListe;
	private JButton deconnexionButton;
	private JList conversation;
	private JScrollPane listScrollPane = new JScrollPane();
	private JLabel nomUser;
	
	
	private User selectedValue;
	
	final DefaultListModel model=new DefaultListModel();
	
	final DefaultListModel modelConversation=new DefaultListModel();
	
	private ChatGUI gui;
	
	
	
	
	public FenetreChat(ChatGUI gui) {
		super(ChatSystem.NOM);
		this.gui=gui;
		
		
		
		
		
		
		// Création du panneau
		JPanel panneau = new JPanel();
		setBounds(150,100,WIDTH,HEIGHT);
	
		// Panel de la fenetre
		panneau.setBackground(new Color(255,255,255));
		panneau.setBorder(new EmptyBorder(0, 0, 0, 0));
		panneau.setLayout(new BorderLayout(0,0));
		setContentPane(panneau);
		
		JPanel pane = new JPanel();
		pane.setBackground(new Color(240,240,240));
		pane.setBounds(0, 0, WIDTH, HEIGHT);
		panneau.add(pane, BorderLayout.CENTER);
		pane.setLayout(new BorderLayout(0,0));
		
		
		JPanel paneGauche = new JPanel();
		paneGauche.setBackground(new Color(52, 73, 94));
		paneGauche.setPreferredSize(new Dimension(250,700));
		paneGauche.setLayout(new BorderLayout());
		pane.add(paneGauche, BorderLayout.WEST);
		
		
		//Label de la liste
		this.labelListe = new JLabel("<html><p style='color:white; font-family:Calibri; font-size : 14px; margin-left:30px;'>List of connected users<p></html>");
		labelListe.setPreferredSize(new Dimension(250,40));
		paneGauche.add(labelListe, BorderLayout.NORTH);

		// création de la liste
		this.liste = new JList(model);
		liste.setBackground(new Color(52, 152, 219));
		liste.addListSelectionListener(this);
		paneGauche.add(liste, BorderLayout.CENTER);
		
		// Button deconnexion
		deconnexionButton = new JButton("<html><p style='color:black; font-family:Calibri; font-size : 12px;'>Disconnect<p></html>");
		deconnexionButton.setPreferredSize(new Dimension(200,40));
		paneGauche.add(deconnexionButton, BorderLayout.SOUTH);
		
		
		// PANEL BLEU AU CENTRE
		JPanel paneCenter = new JPanel();
		paneCenter.setBackground(new Color(230, 126, 34));
		paneCenter.setPreferredSize(new Dimension(900,700));
		paneCenter.setLayout(new BorderLayout());
		pane.add(paneCenter, BorderLayout.CENTER);
		
		nomUser = new JLabel("<html><p style='color:white; font-family:Calibri; font-size : 14px; margin-left:30px;'>Welcome ??? to chat System<p></html>");
		nomUser.setPreferredSize(new Dimension(250,40));
		paneCenter.add(nomUser, BorderLayout.NORTH);
		
		
		//conversation
		conversation = new JList(modelConversation);
		conversation.setBackground(new Color(236, 240, 241));
		conversation.setEnabled(false);
		UIManager.put("Label.disabledForeground", Color.BLACK);
		paneCenter.add(conversation, BorderLayout.CENTER);
		
		//BottomPanel est contenu dans Right Panel et comporte le textfield et le bouton Send
		JPanel bottomPane = new JPanel();
		bottomPane.setBackground(new Color(52, 73, 94));
		bottomPane.setLayout(new BorderLayout());
		paneCenter.add(bottomPane, BorderLayout.SOUTH);
		
		
		messageTextArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(messageTextArea);
		scrollPane.setPreferredSize(new Dimension(200,40));
		bottomPane.add(scrollPane, BorderLayout.CENTER);
		
		sendButton = new JButton("<html><p style='color:black; font-family:Calibri; font-size : 12px;'>Send<p></html>");
		sendButton.setPreferredSize(new Dimension(100,40));
		bottomPane.add(sendButton, BorderLayout.EAST);
		
		
		
//		//********* LEFT PANEL ********//
//		
//		JPanel leftPane = new JPanel();
//		leftPane.setBackground(new Color(50,50,50));
//		leftPane.setBounds(0, 0, widthLeftPane, HEIGHT-heightBar);
//		leftPane.setLayout(new BorderLayout());
//		pane.add(leftPane);
//		
//		//Label de la liste
//		this.labelListe = new JLabel("<html><p style='width:200px; text-align:center; color : white; font-size:10px;'>List of connected users</p></html>");
//		labelListe.setSize(leftPane.getWidth(), heightLabelList);
//		leftPane.add(labelListe,BorderLayout.NORTH);
//			
//			
//		// création de la liste
//		this.liste = new JList(model);
//		liste.setBounds(0, labelListe.getHeight(), leftPane.getWidth(), HEIGHT-heightBar-labelListe.getHeight());
//		liste.setBackground(new Color(189, 195, 199));
//		liste.addListSelectionListener(this);
//		leftPane.add(liste, BorderLayout.CENTER);
//		
//		
//		// Button deconnexion
//		deconnexionButton = new JButton("Disconnect");
//		leftPane.add(deconnexionButton, BorderLayout.SOUTH);
//	
//		
//		//********* RIGHT PANEL ********//
//		
//		JPanel rightPane = new JPanel();
//		rightPane.setBackground(new Color(192, 57, 43));
//		rightPane.setBounds(leftPane.getWidth(),0,WIDTH-leftPane.getWidth(),HEIGHT-heightBar);
//		rightPane.setLayout(new BorderLayout());
//		pane.add(rightPane);
//		
//		nomUser = new JLabel("<html><p style='width:auto; text-align:center; color : white; font-size:10px;'>Welcome ??? to the chat System</p></html>");
//		nomUser.setBounds(leftPane.getWidth(), 0, HEIGHT-leftPane.getWidth(), 0);
//		rightPane.add(nomUser, BorderLayout.NORTH);
//		
//		
//		//conversation
//		conversation = new JList(modelConversation);
//		conversation.setBounds(0,0, rightPane.getWidth(), 100);
//		conversation.setBackground(new Color(220, 225, 227));
//		conversation.setEnabled(false);
//		UIManager.put("Label.disabledForeground", Color.BLACK);
//		rightPane.add(conversation, BorderLayout.CENTER);
//		
//		//BottomPanel est contenu dans Right Panel et comporte le textfield et le bouton Send
//		JPanel bottomPane = new JPanel();
//		bottomPane.setBackground(Color.WHITE);
//		bottomPane.setLayout(new BorderLayout());
//		rightPane.add(bottomPane, BorderLayout.SOUTH);
//		
//		messageTextArea = new JTextArea("Type text here",3, 3);
//		messageTextArea.setBounds(0,HEIGHT-heightBar-heightTextArea, rightPane.getWidth(), heightTextArea);
//		bottomPane.add(messageTextArea, BorderLayout.WEST);
//		
//		sendButton = new JButton("Send");
//		sendButton.setBounds(200,HEIGHT-150,100,50);
//		bottomPane.add(sendButton, BorderLayout.EAST);
		
		
		
		
		
		
		
		
		
		
		
		
//		deconnexionButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				FenetreChat.this.gui.disconnect();
//				FenetreChat.this.dispose();
//				new FenetreConnexion().setGui(FenetreChat.this.gui);
//				
//			}
//		});
		
		
		
		
//		sendButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				String message =messageTextArea.getText();
//				FenetreChat.this.gui.sendMessage(message);
//				
//			}
//		});
		
		
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
	
	public void addtoList(User u) {
		this.model.addElement(u);
		System.out.println(this.model.toString());
	}
	
	public void removefromList(User u){
		this.model.removeElement(u);
	}

	
	public User getValue(){
		return this.selectedValue;
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		this.selectedValue=(User)liste.getSelectedValue();
		this.gui.selectUser();
	}
	
	
	public void addMessage(MessageDisplay messageDisplay){
		this.modelConversation.addElement(messageDisplay);
	}
	
	
	public static void main(String[] args) {
		try{
			FenetreChat frame = new FenetreChat(null);
			frame.setVisible(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
				
				
			}




	




}
