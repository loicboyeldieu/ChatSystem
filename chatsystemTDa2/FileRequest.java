package chatsystemTDa2;

// TODO: Auto-generated Javadoc
/**
 * The Class FileRequest.
 */
public class FileRequest extends Message {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4L;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new file request.
	 *
	 * @param name the name
	 */
	public FileRequest(String name){
		this.name=name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name){
		this.name=name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "FileRequest [name=" + name + "]";
	}

}
