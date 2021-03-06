package chatsystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import chatsystem.graphical.ChatGUI;
import chatsystem.graphical.FenetreConnect;
import chatsystem.network.ChatNI;
import chatsystem.network.UDPSender;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatSystem is the main class of our project, and the class to launch to run our application
 */
public class ChatSystem {
	
	/** The Constant NOM. */
	public static final String NOM="Chat System";
	
	/** The ni. */
	private ChatNI ni;
	
	/** The controller. */
	private ChatController controller;
	
	/** The gui. */
	private ChatGUI gui;
	
	
	/** The number. */
	public static int NUMBER=0;
	
	/**
	 * Instantiates a new chat system.
	 *
	 * @param ni the ni
	 * @param controller the controller
	 * @param gui the gui
	 */
	public ChatSystem(ChatNI ni,ChatController controller,ChatGUI gui){
		this.ni=ni;
		this.controller=controller;
		this.gui=gui;
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		byte[] buffer = new byte[ChatNI.MAX_SIZE_BUFFER];
		byte [] bytes1={(byte)192,(byte)2,(byte)10,(byte)1};
		byte [] bytes2={(byte)193,(byte)3,(byte)10,(byte)1};
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		try{
			ChatNI ni= new ChatNI(new UDPSender(new DatagramSocket()));
			ChatGUI gui= new ChatGUI(new FenetreConnect());
			ChatController controller=new ChatController(ni,gui);
			
			
			
			User julien = new User("jul", InetAddress.getLocalHost());
			User aurel = new User("aurel", InetAddress.getByAddress(bytes1));
			User lolo= new User("lolo",InetAddress.getByAddress(bytes2));
			
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
			
		
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		

	}

}
