package chatsystem.network;

public interface ToRemote {
	
	public void sendHello(String nickname);
	
	public void sendGoodbye(String nickname);
	
	public void sendHelloACK(String nickname);
	
	public void sendSend(String msg,int id);
	
	public void sendSendAck(int id);

}
