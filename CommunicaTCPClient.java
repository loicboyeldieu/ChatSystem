import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sun.xml.internal.ws.message.stream.OutboundStreamHeader;


public class CommunicaTCPClient {

	private Socket socket;

	public CommunicaTCPClient() {

		try {
			socket =new Socket(InetAddress.getLocalHost(), 12457);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public InputStream getInputStream(){
		try {
			return socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public OutputStream getOutputStream(){
		try {
			return socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		CommunicaTCPClient tcpClient=new CommunicaTCPClient();
		
		OutputStreamWriter writer=new OutputStreamWriter(tcpClient.getOutputStream());
		InputStreamReader reader=new InputStreamReader(tcpClient.getInputStream());
		
		
		Communica communicaClient = new Communica(new BufferedWriter(writer),new BufferedReader(reader));
		
		
	}




}
