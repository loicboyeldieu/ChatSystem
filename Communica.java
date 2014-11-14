import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class Communica extends JFrame implements ActionListener {

	private BufferedWriter writer;
	private BufferedReader reader;
	private JButton bReceive;
	private JButton bSend;
	private JLabel lmessrec;
	private JLabel lmesssend;
	private JTextArea textRec;
	private JTextArea textToSend;
	
	
	
	public static final int WIDTH=300;
	public static final int HEIGHT=200;
	

	public Communica(BufferedWriter writer, BufferedReader reader){
		super("Communica System");

		this.writer = writer;
		this.reader = reader;
		
		this.lmessrec=new JLabel("received message");
		this.lmesssend=new JLabel("message to send");
		
		this.bReceive=new JButton("receive");
		this.bSend=new JButton("send");
		
		this.textRec=new JTextArea();
		this.textRec.setEditable(false);
		
		this.textToSend=new JTextArea();
		
		initComponents();
	}

	
	private void initComponents(){
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowsClosing(WindowEvent e){
			
				System.exit(0);
				Communica.this.dispose();
			}
		});
		
		
		
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		
		JPanel panel=(JPanel) getContentPane();
		
		panel.setLayout(new GridLayout(3,3));
		
		panel.add(lmesssend);
		panel.add(textToSend);
		panel.add(bSend);
		panel.add(bReceive);
		panel.add(lmessrec);
		panel.add(textRec);
		
		bSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				String text=textToSend.getText();
				
					try {
						writer.write(text);
						writer.flush();
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				
			}
		});
		
		bReceive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
					String ligne;
					StringBuilder sb=new StringBuilder();
					while ((ligne = reader.readLine()) != null)
					{
						sb.append(ligne);
					}
					reader.close();
					textRec.setText(sb.toString());
				} catch (IOException ex){
					ex.printStackTrace();
				}
				
			}
		});
	}





	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
