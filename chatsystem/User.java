package chatsystem;

import java.net.InetAddress;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {


	/** The name. */
	private String name;
	
	/** The address. */
	private InetAddress address;
	

	/**
	 * Instantiates a new user.
	 *
	 * @param name the name
	 * @param address the address
	 */
	public User(String name, InetAddress address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public InetAddress getAddress() {
		return address;
	}

	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name+"@"+address;
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	/**
	 * Equals.
	 *
	 * @param u the u
	 * @return true, if successful
	 */
	public boolean equals(User u) {
		return this.address.equals(u.getAddress());
	}
	



}
