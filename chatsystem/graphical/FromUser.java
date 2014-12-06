package chatsystem.graphical;

import java.io.File;

public interface FromUser {
	
	
	public void selectUser();
	public void sendMessage(String message);
	public void disconnect();
	public void connect();
	public void sendFile(File file);
	public void acceptFile(String name,File location);
	public void declineFile(String name);
	public void selectFile(File file);

}
