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
			return concernedUser + " accepted to receive your file";
		}
		else{
			return concernedUser + " refused to receive your file";
		}
	}
	
	
}
