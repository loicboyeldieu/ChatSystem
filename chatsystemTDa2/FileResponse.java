package chatsystemTDa2;


// TODO: Auto-generated Javadoc
/**
 * The Class FileResponse.
 */
public class FileResponse extends Message {
	
	/** The response. */
	private boolean response;
	
	/** The name. */
	private String name;
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5L;
	
	/**
	 * Instantiates a new file response.
	 *
	 * @param response the response
	 * @param name the name
	 */
	public FileResponse(boolean response,String name){
		this.response=response;
		this.name=name;
	}
	
	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public boolean getResponse(){
		return response;
	}
	
	/**
	 * Sets the response.
	 *
	 * @param response the new response
	 */
	public void setResponse(boolean response){
		this.response=response;
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
		return "FileResponse[name="+name+", response="+response+"]";
	}

}
