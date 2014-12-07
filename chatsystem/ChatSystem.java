package chatsystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import chatsystem.graphical.ChatGUI;
import chatsystem.graphical.FenetreConnect;
import chatsystem.graphical.FenetreConnexion;
import chatsystem.network.ChatNI;
import chatsystem.network.UDPReceiver;
import chatsystem.network.UDPSender;

public class ChatSystem {
	
	public static final String NOM="Chat System";
	
	private ChatNI ni;
	private ChatController controller;
	private ChatGUI gui;
	
	
	public static int NUMBER=0;
	
	public ChatSystem(ChatNI ni,ChatController controller,ChatGUI gui){
		this.ni=ni;
		this.controller=controller;
		this.gui=gui;
	}
	
	
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
