package chatsystem.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; 

import chatsystem.ChatSystem;

public class FenetreChat extends JFrame implements ListSelectionListener{

	private static final long serialVersionUID = 1574189318933480883L;

	public static final int WIDTH=1000;
	public static final int HEIGHT=600;

	private JButton disconnectButton;
	private JTextField messageTextField;
	private JList liste;
	private JLabel etiquette;
	private JLabel labelListe;
	private JButton deconnexionButton;
	private JLabel conversation;

	
	private ChatGUI gui;
	
	public int heightLabelList = 40;
	public int heightButton = 30;
	
	public FenetreChat(ChatGUI gui) {
		super(ChatSystem.NOM);
		this.gui=gui;
		
		this.setResizable(false);
		
		// Création du panneau
		JPanel panneau = new JPanel();
		setBounds(150,100,WIDTH,HEIGHT);
		// Background de la fenetre
		panneau.setBackground(new Color(255,255,255));
		panneau.setBorder(new EmptyBorder(0, 0, 0, 0));
		panneau.setLayout(new BorderLayout(0,0));
		setContentPane(panneau);
		
		
		JPanel pane = new JPanel();
		pane.setBackground(new Color(240,240,240));
		panneau.add(pane, BorderLayout.CENTER);
		pane.setLayout(null);
		
		
		JPanel leftPane = new JPanel();
		leftPane.setBackground(new Color(50,50,50));
		leftPane.setBounds(0, 0, 200, HEIGHT-20);
		leftPane.setLayout(null);
		pane.add(leftPane);
		
		
		JPanel rightPane = new JPanel();
		rightPane.setBackground(new Color(240,240,240));
		rightPane.setBounds(leftPane.getWidth(),0,WIDTH-leftPane.getWidth(),HEIGHT);
		rightPane.setLayout(null);
		pane.add(rightPane);
		
		messageTextField = new JTextField();
		messageTextField.setBounds(0,HEIGHT-120, rightPane.getWidth(), 100);
		rightPane.add(messageTextField);
		
		// Label de la liste
		String choix[] = {"Martin","Loic","Juju","John de Toulouse","Claire"};
		
		this.labelListe = new JLabel("<html><p style='width:150px; text-align:center; color : white; font-size:10px;'>List of connected users</p></html>");
		labelListe.setBounds(0, 0, leftPane.getWidth(), heightLabelList);
		labelListe.setBackground(Color.RED);
		leftPane.add(labelListe);
		
		
		//this.etiquette = new JLabel("Aucune séléction");
		
		//liste.addListSelectionListener(this);
		
		// création de la liste
		this.liste = new JList(choix);
		liste.setBounds(0, labelListe.getHeight(), 200, HEIGHT-heightLabelList-heightButton-20);
		liste.setBackground(new Color(46,204,113));	
		leftPane.add(liste);
			
		// Button deconnexion
		deconnexionButton = new JButton("Deconnexion");
		deconnexionButton.setBounds(0, leftPane.getHeight()-heightButton, leftPane.getWidth(), heightButton);
		leftPane.add(deconnexionButton);
		
		deconnexionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FenetreChat.this.gui.disconnect();
				FenetreChat.this.dispose();
				new FenetreConnexion().setGui(FenetreChat.this.gui);
				
			}
		});
		
		
		// conversation
		conversation = new JLabel("<html><p style='color:black;'>Lolo : Salut </p></br><p> Juju : ça va ?<p></html>");
		conversation.setBounds(0,0, rightPane.getWidth(), 40);
		rightPane.add(conversation);
		
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*disconnectButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FenetreChat.this.gui.disconnect();	
			}
		});*/
		
		

	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		etiquette.setText((String)liste.getSelectedValue());
	}
	
	
	public static void main(String[] args) {

					FenetreChat frame = new FenetreChat(null);
					frame.setVisible(true);
				
			}


	




}
