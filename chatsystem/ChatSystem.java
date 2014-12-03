package chatsystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import chatsystem.graphical.ChatGUI;
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
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		try{
			ChatNI ni= new ChatNI(new UDPReceiver(packet), new UDPSender(new DatagramSocket()));
			ChatController controller=new ChatController(ni);
			ChatGUI gui= new ChatGUI(new FenetreConnexion());
			gui.setController(controller);
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		

	}

}
