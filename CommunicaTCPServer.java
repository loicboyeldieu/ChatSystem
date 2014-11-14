import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;





public class CommunicaTCPServer {
	public static final int NUM_PORT=12350;
	
	private ServerSocket serverSocket ;
	private int port;
	
	public CommunicaTCPServer(int port){
		try {
			this.port=port;
			serverSocket=new ServerSocket(port);
			System.out.println("Serveur TCP écoutant sur le port : "+port);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Socket getSocketClient(){
		try {
			
			return serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public static void main(String args[]){
		CommunicaTCPServer tcpServer=new CommunicaTCPServer(NUM_PORT);
		Socket client=tcpServer.getSocketClient();
		try{
			OutputStreamWriter out =new OutputStreamWriter(client.getOutputStream());
			InputStreamReader in=new InputStreamReader(client.getInputStream());
			Communica communicaServer=new Communica(new BufferedWriter(out), new BufferedReader(in));
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
		
		
	}
	

}
