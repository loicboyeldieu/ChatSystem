package chatsystem.network;

import chatsystem.User;

public interface ToRemote {
	
	public void sendHello(String nickname);
	
	public void sendGoodbye(String nickname);
	
	public void sendHelloACK(User remote,User local);
	
	public void sendSend(String msg,int id,User remote);
	
	public void sendSendAck(int id,User remote);

}
