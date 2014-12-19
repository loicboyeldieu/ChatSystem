package chatsystemTDa2;

// TODO: Auto-generated Javadoc
/** One text message, with UID for ack system. */
public class Send extends Message
{
  
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message. */
	private String message;
	
	/** The id. */
	private int id;

	/**
	 * Instantiates a new send.
	 *
	 * @param msg the msg
	 * @param id the id
	 */
	public Send(String msg, int id)
	{
      message = msg;
      this.id = id;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {return message;}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID()         {return id;}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Send [message=" + message + ", id=" + id + "]";
	}
}
