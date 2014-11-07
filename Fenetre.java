/**
 * Example of a Fenetre (JFrame) using awt and swing
 * @author your_name
 */

 import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

 public class Fenetre extends JFrame implements ActionListener{

 /** a label for the name */
 private JLabel label;
 private JLabel label2;
 /** a textfield for the name */
 private JTextField text;
 /** a button to perform an action: e.g. say hello (TBD) */
 private JButton button;

 /** Creates a Fenetre */
 public Fenetre() {
   initComponents();
 }

 /** Initializes the Fenetre components */

 private void initComponents() {
 // create the components
 // a new label with the "Nom" as value
 label = new JLabel("Nom: ");
 // a new text field with 20 columns
 text = new JTextField(20);
 // a new button identified as OK
 button = new JButton("OK");
 // configures the JFrame layout using a border layout
 this.setLayout(new BorderLayout());
 // places the components in the layout
 this.add("West",label);
 this.add("Center",text);
 this.add("East",button);
 // packs the fenetre: size is calculated
 // regarding the added components
 this.pack();
 // the JFrame is visible now
 this.setVisible(true);
 
 button.addActionListener(this);
 
 this.addWindowListener(new WindowAdapter(){
	 public void windowClosing(WindowEvent windowEvent){
		 System.exit(0);
	 }
 });
 
 
 }

 /** main entry point */
 public static void main(String[] args) {
    Fenetre f = new Fenetre();
    
  }

public void actionPerformed(ActionEvent e) {
	label2 = new JLabel("Hello "+text.getText());
	this.add(BorderLayout.SOUTH, label2);
	this.pack();
	
}

}