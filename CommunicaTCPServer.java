import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;


public class CommunicaTCPServer {
	
	private ServerSocket serverSocket ;
	
	public CommunicaTCPServer(){
		try {
			int port=12457;
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
		CommunicaTCPServer tcpServer=new CommunicaTCPServer();
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
