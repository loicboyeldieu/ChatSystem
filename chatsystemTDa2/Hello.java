package chatsystemTDa2;

// TODO: Auto-generated Javadoc
/**
 * The Class Hello.
 */
public class Hello extends Message
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2L;

	/** The nickname. */
	private String nickname;

	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public String getNickname(){
		return this.nickname;
	}

	/**
	 * Sets the nickname.
	 *
	 * @param nickname the new nickname
	 */
	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	/**
	 * Instantiates a new hello.
	 *
	 * @param nickname the nickname
	 */
	public Hello(String nickname) {
		this.nickname = nickname;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Hello [nickname=" + nickname + "]";
	}
	
	


}