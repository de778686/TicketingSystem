package ticketingsystem;
/**
 *
 * @author dg
 */
public class login {
    private String userName;
    private String pswd;
    
    public login(String username, String password){
        userName = username;
        pswd = password;
    }
    
    //setters
    public void setUserName(String username){
        userName = username;
    }
    
    public void setPassword(String password){
        pswd = password;
    }
    
    //getters
    public String getUserName(){
        return userName;
    }
    
    public String getPassword(){
        return pswd;
    }
    
    //methods
    public boolean validate(){

        if(1 > 2){
            return true;
        }
        else{
            //testing condition
            return false;
        }
    }
}
