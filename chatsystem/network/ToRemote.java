package chatsystem.network;

import java.io.File;

import chatsystem.User;

public interface ToRemote {
	
	public void sendHello(String nickname);
	
	public void sendGoodbye(String nickname);
	
	public void sendHelloACK(User remote,User local);
	
	public void sendSend(String msg,int id,User remote);
	
	public void sendSendAck(int id,User remote);
	
	public void sendFileRequest(User remote,String name);
	
	public void sendFileResponse(User remote,String name,boolean response);
	
	public void sendFile(File file,User remote);

}
