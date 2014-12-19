package chatsystem.graphical;



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

// TODO: Auto-generated Javadoc
/**
 * The Class FenetreConnexion.
 */
public class FenetreConnexion extends JFrame {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6780578387145020970L;
	
	/** The Constant WIDTH. */
	public static final int WIDTH=400;
	
	/** The Constant HEIGHT. */
	public static final int HEIGHT=100;

	/** The connexion label. */
	private JLabel connexionLabel;
	
	/** The connexion area. */
	private JTextField connexionArea;
	
	/** The connexion button. */
	private JButton connexionButton;
	
	/** The valeur. */
	private static String valeur;
	
	/** The gui. */
	private ChatGUI gui;
	
	/**
	 * Instantiates a new fenetre connexion.
	 */
	public FenetreConnexion(){
		super(ChatSystem.NOM);
		connexionLabel=new JLabel("Your nickname here : ");
		connexionButton=new JButton("Connect");
		connexionArea=new JTextField("",15);
		
		
		JPanel panel=(JPanel) getContentPane();
		
		SpringLayout layout=new SpringLayout();
		panel.setLayout(layout);
		
		panel.add(connexionLabel);
		panel.add(connexionArea);
		panel.add(connexionButton);
		
		
		// Adjust constraints for the label so it's at (5,5).
	    layout.putConstraint(SpringLayout.WEST, connexionLabel, 5, SpringLayout.WEST, panel);
	    layout.putConstraint(SpringLayout.NORTH, connexionLabel, 5, SpringLayout.NORTH, panel);
		
	    //
	    layout.putConstraint(SpringLayout.WEST, connexionArea, 5, SpringLayout.EAST, connexionLabel);
	    layout.putConstraint(SpringLayout.NORTH, connexionArea, 5, SpringLayout.NORTH, panel);
	    
	    
	    //
	    layout.putConstraint(SpringLayout.NORTH, connexionButton, 15, SpringLayout.SOUTH, connexionLabel);
	    
	    this.pack();
	    this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
	    
	    connexionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				valeur=connexionArea.getText();
				FenetreConnexion.this.gui.connect();
				closeWindows();
				
			}
		});
	    
		this.addWindowListener(new WindowAdapter() {
			public void windowsClosing(WindowEvent e){
				System.exit(0);
			}
		});
	
	}
	
	/**
	 * Close windows.
	 */
	public void closeWindows(){
		FenetreConnexion.this.dispose();
	}
	
	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public static String getNickname(){
		return valeur;
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new FenetreConnexion();

	}


	/**
	 * Gets the gui.
	 *
	 * @return the gui
	 */
	public ChatGUI getGui() {
		return gui;
	}


	/**
	 * Sets the gui.
	 *
	 * @param gui the new gui
	 */
	public void setGui(ChatGUI gui) {
		this.gui = gui;
	}
	
	

}
