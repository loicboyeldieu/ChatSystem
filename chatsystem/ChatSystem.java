package chatsystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import chatsystem.network.ChatNI;
import chatsystem.network.UDPReceiver;
import chatsystem.network.UDPSender;

public class ChatSystem {
	
	public static final String NOM="Chat System";
	
	private ChatNI ni;
	private ChatController controller;
	
	public static int NUMBER=0;
	
	public ChatSystem(ChatNI ni,ChatController controller){
		this.ni=ni;
		this.controller=controller;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Chat System v1.0");
		Scanner sc =new Scanner(System.in);
		System.out.println("Votre nom : ");
		String nom=sc.nextLine();
		byte[] buffer = new byte[ChatNI.MAX_SIZE_BUFFER];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		try{
			ChatNI ni= new ChatNI(new UDPReceiver(packet), new UDPSender(new DatagramSocket()));
			ChatController controller=new ChatController(ni, new User(nom, InetAddress.getLocalHost()));
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		

	}

}
