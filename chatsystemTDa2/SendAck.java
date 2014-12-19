package chatsystemTDa2;

// TODO: Auto-generated Javadoc
/**
 * The Class SendAck.
 */
public class SendAck extends Message
{
   
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id_message. */
	private int id_message ;

	/**
	 * Instantiates a new send ack.
	 *
	 * @param id_message the id_message
	 */
	public SendAck(int id_message){
		super();
		this.id_message = id_message ;
	}

	/**
	 * Gets the id_message.
	 *
	 * @return the id_message
	 */
	public int getId_message() {
		return id_message;
	}

	/**
	 * Sets the id_message.
	 *
	 * @param id_message the new id_message
	 */
	public void setId_message(int id_message) {
		this.id_message = id_message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SendAck [id_message=" + id_message + "]";
	}

}