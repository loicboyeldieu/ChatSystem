import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;


public class CommunicaThread extends Communica{
	
	private ListenSocket listenSocket;
	

	public CommunicaThread(ListenSocket ls, BufferedWriter writer){
		super(writer,null);
		listenSocket=ls;
		
	}


	@Override
	public void initComponents() {
		super.initComponents();
		bSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

					String ligne;
					StringBuilder sb=new StringBuilder();
					while ((ligne = listenSocket.getLastLine()) != null)
					{
						sb.append(ligne);
					}
					textRec.setText(sb.toString());

			}
		});
	}
	
	
	
	
	
	

}
