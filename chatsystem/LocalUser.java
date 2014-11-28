import java.net.InetAddress;
import java.net.UnknownHostException;


public class LocalUser {
	
	//_____________________________Attributs__________________________________//
	//________________________________________________________________________//
	
	private String nickname;
	
	//____________________________Constructors________________________________//
	//________________________________________________________________________//
	
	public LocalUser(String nickname){
		this.setNickname(nickname);
	}

	//__________________________Getters & setters ____________________________//
	//________________________________________________________________________//
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	//________________________________________________________________________//
	//________________________________________________________________________//
	
	//getLocalAdress : get the local Adress of a local user
	public static String getIP(){
		InetAddress LocalAdress = null;
		
		try {
			LocalAdress = InetAddress.getLocalHost();
			System.out.println("The local adress is : "+LocalAdress );
			
		} catch (UnknownHostException e) {
			System.out.println("Error : the local adress is not known");
			e.printStackTrace();
		}
		
		return LocalAdress.toString();
	}
	
	//________________________________________________________________________//
	//________________________________________________________________________//
	
	//concatIPNickname returns the nickname:localadress 
	public String concatIPNickname(){
		return this.nickname.concat(":"+this.getIP());
	}
	
	//________________________________________________________________________//
	//________________________________________________________________________//
	
	// Test
	public static void main(String args[]){
		LocalUser Loic = new LocalUser("Loic");
		System.out.println(Loic.concatIPNickname());	
	}
	
}
