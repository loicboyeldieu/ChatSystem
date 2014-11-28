import java.io.BufferedReader;
import java.io.IOException;


public class ListenSocket extends Thread {
	
	private BufferedReader reader;
	private String lastLine;
	
	public ListenSocket(BufferedReader reader) {
		super();
		this.reader = reader;
	}


	
	
	
	public String getLastLine() {
		return lastLine;
	}





	public void setLastLine(String lastLine) {
		this.lastLine = lastLine;
	}





	@Override
	public void run() {
		super.run();
		try{
			String ligne;
			StringBuilder sb=new StringBuilder();
			while ((ligne = reader.readLine()) != null)
			{
				sb.append(ligne);
			}
			reader.close();
			lastLine=sb.toString();
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	
	
	
	

}
