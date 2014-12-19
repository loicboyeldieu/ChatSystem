package chatsystem.graphical;

import java.io.File;

import chatsystem.ChatController;
import chatsystem.User;
import chatsystem.graphical.notification.FileNotifications;
import chatsystem.graphical.notification.MessageDisplay;
import chatsystem.graphical.notification.Notification;
import chatsystem.graphical.notification.StatusFile;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatGUI is the Facade for all the GUI related components
 */
public class ChatGUI implements FromUser{
	
	
	/** The controller. */
	private ChatController controller;
	
	/** The fenetre connect. */
	private FenetreConnect fenetreConnect;
	
	/** The fenetre chat. */
	private FenetreChat fenetreChat;
	
	/**
	 * Instantiates a new chat gui.
	 *
	 * @param connexion the connexion window
	 */
	public ChatGUI(FenetreConnect connexion){
		fenetreConnect=connexion;
		fenetreConnect.setGui(this);
	}

	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public ChatController getController() {
		return controller;
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	public void setController(ChatController controller) {
		this.controller = controller;
	}

	/* (non-Javadoc)
	 * @see chatsystem.graphical.FromUser#selectUser()
	 */
	@Override
	public void selectUser() {
		this.controller.onSelectedUser(fenetreChat.getValue());

	}

	/* (non-Javadoc)
	 * @see chatsystem.graphical.FromUser#sendMessage(java.lang.String)
	 */
	@Override
	public void sendMessage(String message) {
		controller.performSend(message);
	}

	/* (non-Javadoc)
	 * @see chatsystem.graphical.FromUser#disconnect()
	 */
	@Override
	public void disconnect() {
		this.controller.processDisconnect();
	}

	/* (non-Javadoc)
	 * @see chatsystem.graphical.FromUser#connect()
	 */
	@Override
	public void connect() {
		System.out.println("Gui :connect");
		String nickname=FenetreConnect.getNickname();
		if (nickname.compareTo("")==0) nickname="anonymous";
		this.fenetreChat=new FenetreChat(this);
		
		this.controller.processConnect(nickname);
		

		
	}
	
	/* (non-Javadoc)
	 * @see chatsystem.graphical.FromUser#sendFile(java.io.File)
	 */
	@Override
	public void sendFile(File file) {
		controller.performFileRequest(file.getName());
		controller.setFiletoSend(file);
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see chatsystem.graphical.FromUser#acceptFile(java.lang.String, java.io.File)
	 */
	@Override
	public void acceptFile(String name,File file) {
		controller.performResponse(name,true);
		controller.performReceivingFile(file,name);
		this.displayFileNotificationTransfer(false);
	}

	/* (non-Javadoc)
	 * @see chatsystem.graphical.FromUser#declineFile(java.lang.String)
	 */
	@Override
	public void declineFile(String name) {
		controller.performResponse(name,false);
	}
	
	

	/* (non-Javadoc)
	 * @see chatsystem.graphical.FromUser#selectFile(java.io.File)
	 */
	@Override
	public void selectFile(File file) {
		controller.setFiletoSend(file);
	}

	/**
	 * Adds the User to the connected user list.
	 *
	 * @param u the u
	 */
	public void addToConnectedUserList(User u){
		if (fenetreChat==null){
			System.out.println("pd");
		}
		
		this.fenetreChat.addtoList(u);
	}
	
	/**
	 * Removes the user u from the list 
	 *
	 * @param u the user
	 */
	public void removeUser(User u){
		this.fenetreChat.removefromList(u);
	}
	

	/**
	 * Display message.
	 *
	 * @param fromUser the user who sent the message
	 * @param toUser the to user who received the message
	 * @param message the message
	 */
	public void displayMessage(User fromUser, User toUser, String message) {
		fenetreChat.addMessage(new MessageDisplay(fromUser, toUser, message));
		
	}
	
	/**
	 * Display notification.
	 *
	 * @param user the user who just left /came in
	 * @param goodbye true if he left /false if he came in
	 */
	public void displayNotification(User user,boolean goodbye){
		fenetreChat.addMessage(new Notification(user, goodbye));
	}
	
	/**
	 * Display status file.
	 *
	 * @param status the status 
	 * @param user the user that sent the file
	 */
	public void displayStatusFile(boolean status, User user){
		fenetreChat.addMessage(new StatusFile(status, user));
	}
	
	/**
	 * Display file notification transfer.
	 *
	 * @param endTransfer the end transfer
	 */
	public void displayFileNotificationTransfer(boolean endTransfer){
		fenetreChat.addMessage(new FileNotifications(endTransfer));
	}
	
	/**
	 * Display file request.
	 *
	 * @param u the user that sent the file
	 * @param name the name
	 */
	public void displayFileRequest(User u,String name){
		
		if (u==null){
			u=ChatController.getLocalUser();
		}
		
		fenetreChat.displayDialog(u.getName(),name);
	}
	
	/**
	 * Who am i.
	 *
	 * @return the local user
	 */
	public User whoAmI(){
		return ChatController.getLocalUser();
	}




	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		FenetreConnect f=new FenetreConnect();
		ChatGUI gui=new ChatGUI(f);
		
		//ChatController controller=new ChatController(null);
		//gui.setController(controller);
		
		//FenetreChat f2=new FenetreChat(gui);

	}




	
	


}
