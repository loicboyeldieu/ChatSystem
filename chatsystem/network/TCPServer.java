package chatsystem.network;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread{
	
	private ServerSocket listenSocket;

	private File location;
	
	public TCPServer(File location){
		this.location=location;
		try{
			listenSocket = new ServerSocket(ChatNI.NUM_PORT);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		super.run();
	    InputStream is = null;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    Socket clientSocket=null;
	    int bufferSize = 0;
	    try{
	    	System.out.println("Avant d'accepter");
	    	while (clientSocket==null){
	    		clientSocket = listenSocket.accept();
	    	}
	    	System.out.println("Après acceptation");
		    is = clientSocket.getInputStream();
	        bufferSize = clientSocket.getReceiveBufferSize();
	        //fos = new FileOutputStream("D:/GROS DESIGN/resultat");
	        fos=new FileOutputStream(location);
	        bos = new BufferedOutputStream(fos);
	        byte[] bytes = new byte[bufferSize];

	        int count;

	        while ((count = is.read(bytes)) > 0) {
	            bos.write(bytes, 0, count);
	        }

	        bos.flush();
	        bos.close();
	        is.close();
	        clientSocket.close();
	        listenSocket.close();
	        System.out.println("Server :On a fini");
	    }catch (Exception e){
			e.printStackTrace();
		}
		

	}
	
	public static void main(String[] args) {
		try {
			TCPServer tcpServer = new TCPServer(new File("C:/"));
			tcpServer.start();
			System.out.println("Thread principal : server démarré");
			TCPClient tcpClient = new TCPClient(InetAddress.getLocalHost(), new File("D:/cléssha1.txt"));
			tcpClient.start();
		}catch (Exception e){
			e.printStackTrace();
		}

	}


	
}
