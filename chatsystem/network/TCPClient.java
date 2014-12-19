package chatsystem.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.Socket;

// TODO: Auto-generated Javadoc
/**
 * The Class TCPClient.
 */
public class TCPClient extends Thread {
	
	/** The socket. */
	private Socket socket;
	
	/** The file. */
	private File file;
	
	/** The ni. */
	private ChatNI ni;
	
	/**
	 * Instantiates a new TCP client.
	 *
	 * @param adress the adress
	 * @param file the file
	 * @param ni the ni
	 */
	public TCPClient(InetAddress adress,File file,ChatNI ni){
		this.ni=ni;
		
		try{
			socket=new Socket(adress, ChatNI.NUM_PORT);
			this.file=file;
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			super.run();
			long length = file.length();
		    if (length > Integer.MAX_VALUE) {
		        System.out.println("File is too large.");
		    } else {
		    	byte[] bytes = new byte[(int) length];

		    	FileInputStream fis = new FileInputStream(file);
		        BufferedInputStream bis = new BufferedInputStream(fis);
		        BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
		        
		        int count;
		        while ((count = bis.read(bytes)) > 0) {
		            out.write(bytes, 0, count);
		        }

		        out.flush();
		        out.close();
		        fis.close();
		        bis.close();
		        socket.close();
		        ni.onFileCompleted();
		    }
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	

}
