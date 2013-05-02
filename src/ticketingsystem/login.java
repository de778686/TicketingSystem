package ticketingsystem;
import dataobjects.Technicians;
import dataobjects.Technician;
import java.util.*;
/**
 *
 * @author dg
 */
public class login {
    
    //=====================  Instance Variables  ======================//
    private String userName;
    private String pswd;
    private Technicians technicians;
    
    //========================  Constructors  =========================//
    public login(String username, String password){
        userName = username;
        pswd = password;
    }
    
    //========================  Setters  =========================//
    public void setUserName(String username){
        userName = username;
    }
    
    public void setPassword(String password){
        pswd = password;
    }
    
    //========================  Getters  =========================//
    public String getUserName(){
        return userName;
    }
    
    public String getPassword(){
        return pswd;
    }
    
    //methods
    public boolean validate(){
        
        boolean valid = false;
        Technician currentTech = new Technician(userName, pswd);
        technicians = new Technicians();
        
        try{
            Technician actualTech = technicians.fetchByUsername(userName);
            if(actualTech == null){
                return false;
            }
            else if(actualTech.getPassword().equals(currentTech.getPassword())){
                System.out.println("Got it baby!");
                valid = true;
            }
            else {
                valid = false;
            }
        }
        catch(Exception ex){
            System.out.println("Error loading technicians collection");
        }
        
        return valid;
    }
}
