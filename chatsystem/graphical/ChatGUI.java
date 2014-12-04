package chatsystem.graphical;

import chatsystem.ChatController;
import chatsystem.User;

public class ChatGUI implements FromUser{
	
	
	private ChatController controller;
	
	private FenetreConnexion fenetreConnexion;
	private FenetreChat fenetreChat;
	
	public ChatGUI(FenetreConnexion connexion){
		fenetreConnexion=connexion;
		fenetreConnexion.setGui(this);
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
		this.fenetreChat=new FenetreChat(this);
		String nickname=FenetreConnexion.getNickname();
		if (nickname.compareTo("")==0) nickname="anonymous";
		this.controller.processConnect(nickname);
		
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



	
	public static void main(String[] args) {
		
		FenetreConnexion f=new FenetreConnexion();
		ChatGUI gui=new ChatGUI(f);
		
		//ChatController controller=new ChatController(null);
		//gui.setController(controller);
		
		//FenetreChat f2=new FenetreChat(gui);

	}


	
	


}
