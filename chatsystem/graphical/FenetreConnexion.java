package chatsystem.graphical;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import chatsystem.ChatSystem;

public class FenetreConnexion extends JFrame {


	private static final long serialVersionUID = 6780578387145020970L;
	
	public static final int WIDTH=400;
	public static final int HEIGHT=300;

	private JLabel connexionLabel;
	private JTextField connexionArea;
	private JButton connexionButton;
	
	public FenetreConnexion(){
		super(ChatSystem.NOM);
		connexionLabel=new JLabel("Your nickname here : ");
		connexionButton=new JButton("Connect");
		connexionArea=new JTextField("",15);
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
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
	    
		this.addWindowListener(new WindowAdapter() {

			public void windowsClosing(WindowEvent e){

				System.exit(0);
				FenetreConnexion.this.dispose();
			}
		});
	
	}
	
	
	public static void main(String[] args) {
		new FenetreConnexion();

	}

}
