package chatsystem.graphical;

import chatsystem.User;

public class StatusFile {
	
	private boolean status;
	private User concernedUser;
	
	public StatusFile(boolean status, User u){
		super();
		this.status = status;
		this.concernedUser = u;
	}
	
	@Override
	public String toString(){
		// status=true -> acceptation 
		if (status){
			return concernedUser + " a accepté de recevoir le fichier";
		}
		else{
			return concernedUser + " a refusé de recevoir le fichier";
		}
	}
	
	
}
