package chatsystem.graphical;

import java.util.Date;

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
		Date date = new Date();
		// status=true -> acceptation 
		if (status){
			return "<html><p style='color:#20cc0a'>["+date.toString().subSequence(11, 16)+"] " + concernedUser + " accepted to receive your file</p></html>";
		}
		else{
			return "<html><p style='color:#e32232;'>["+date.toString().subSequence(11, 16)+"] " + concernedUser + " refused to receive your file</p></html>";
		}
	}
	
	
}
