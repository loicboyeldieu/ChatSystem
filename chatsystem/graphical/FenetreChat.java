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
	
	private ChatGUI gui;
	
	public int heightLabelList = 40;
	public int heightButton = 30;
	
	public FenetreChat(ChatGUI gui) {
		super(ChatSystem.NOM);
		this.gui=gui;
		
		
		
		// Création du panneau
		JPanel panneau = new JPanel();
		setBounds(0,0,WIDTH,HEIGHT);
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
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		disconnectButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FenetreChat.this.gui.disconnect();	
			}
		});
		
		

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
