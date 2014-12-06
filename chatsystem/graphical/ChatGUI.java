package chatsystem.graphical;

import java.net.InetAddress;

import chatsystem.ChatController;
import chatsystem.User;

public class ChatGUI implements FromUser{
	
	
	private ChatController controller;
	
	private FenetreConnect fenetreConnect;
	private FenetreChat fenetreChat;
	
	public ChatGUI(FenetreConnect connexion){
		fenetreConnect=connexion;
		fenetreConnect.setGui(this);
	}

	public ChatController getController() {
		return controller;
	}

	public void setController(ChatController controller) {
		this.controller = controller;
	}

	@Override
	public void selectUser() {
		this.controller.onSelectedUser(fenetreChat.getValue());

	}

	@Override
	public void sendMessage(String message) {
		controller.performSend(message);
	}

	@Override
	public void disconnect() {
		this.controller.processDisconnect();
	}

	@Override
	public void connect() {
		System.out.println("Gui :connect");
		String nickname=FenetreConnect.getNickname();
		if (nickname.compareTo("")==0) nickname="anonymous";
		this.controller.processConnect(nickname);
		this.fenetreChat=new FenetreChat(this);

		
		
	}
	
	
	public void addToConnectedUserList(User u){
		this.fenetreChat.addtoList(u);
	}
	
	public void removeUser(User u){
		this.fenetreChat.removefromList(u);
	}
	

	public void displayMessage(User fromUser, User toUser, String message) {
		fenetreChat.addMessage(new MessageDisplay(fromUser, toUser, message));
		
	}
	
	public void displayNotification(User user,boolean goodbye){
		fenetreChat.addMessage(new Notification(user, goodbye));
	}
	
	public User whoAmI(){
		return controller.getLocalUser();
	}




	
	public static void main(String[] args) {
		
		FenetreConnect f=new FenetreConnect();
		ChatGUI gui=new ChatGUI(f);
		
		//ChatController controller=new ChatController(null);
		//gui.setController(controller);
		
		//FenetreChat f2=new FenetreChat(gui);

	}


	
	


}
