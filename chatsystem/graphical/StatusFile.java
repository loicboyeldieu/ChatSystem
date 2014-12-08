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
			return "<html><p style='color:#14ec26;'>" + concernedUser + " accepted to receive your file</p></html>";
		}
		else{
			return "<html><p style='color:#e32232;'>" + concernedUser + " refused to receive your file</p></html>";
		}
	}
	
	
}
