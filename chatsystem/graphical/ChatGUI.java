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
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		
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
		System.out.println("Bonjour "+nickname);
		this.controller.processConnect(nickname);
		
	}


	
	public static void main(String[] args) {
		
		FenetreConnexion f=new FenetreConnexion();
		ChatGUI gui=new ChatGUI(f);
		
		ChatController controller=new ChatController(null);
		gui.setController(controller);
		
		//FenetreChat f2=new FenetreChat(gui);

	}

	
	


}
