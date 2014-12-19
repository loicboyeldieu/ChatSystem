package chatsystemTDa2;

// TODO: Auto-generated Javadoc
/**
 * The Class Goodbye.
 */
public class Goodbye extends Message
{
   
   /** The Constant serialVersionUID. */
   private static final long serialVersionUID = 3L;

   /** The nickname. */
   private String nickname;

   
   
   /**
    * Instantiates a new goodbye.
    *
    * @param nickname the nickname
    */
   public Goodbye(String nickname){
          this.nickname = nickname;
   }

   
   
   /**
    * Gets the nickname.
    *
    * @return the nickname
    */
   public String getNickname(){
          return this.nickname;
   }



/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Goodbye [nickname=" + nickname + "]";
}
   
   
}
