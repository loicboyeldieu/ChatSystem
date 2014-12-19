package chatsystemTDa2;

// TODO: Auto-generated Javadoc
/**
 * The Class HelloAck.
 */
public class HelloAck extends Message
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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
	 * Instantiates a new hello ack.
	 *
	 * @param nickname the nickname
	 */
	public HelloAck(String nickname) {
		this.nickname = nickname;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HelloAck [nickname=" + nickname + "]";
	}
	
	



}
