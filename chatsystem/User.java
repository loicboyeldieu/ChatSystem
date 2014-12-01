package chatsystem;

import java.net.InetAddress;

public class User implements Comparable<User> {


	private String name;
	private InetAddress address;
	

	public User(String name, InetAddress address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public InetAddress getAddress() {
		return address;
	}

	
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	
	
	
	

	@Override
	public String toString() {
		return "User [name=" + name + ", address=" + address + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	@Override
	public int compareTo(User o) {
		
		return this.getAddress().toString().compareTo(o.getName());
	}

}
