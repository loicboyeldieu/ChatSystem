import java.io.BufferedReader;


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
		// TODO Auto-generated method stub
		super.run();
	}
	
	
	
	
	

}
