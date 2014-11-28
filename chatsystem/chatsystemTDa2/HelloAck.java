package chatsystemTDa2;

public class HelloAck extends Message
{
   private static final long serialVersionUID = 1L;

   private String nickname;

   public String getNickname(){
          return this.nickname;
   }

   public void setNickname(String nickname){
          this.nickname = nickname;
   }

}
