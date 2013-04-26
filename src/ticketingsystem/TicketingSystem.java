// David Emery 

package ticketingsystem;


import dataobjects.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import utils.CFormat;
import static utils.SelectOption.*;

@SuppressWarnings("CallToThreadDumpStack")
public class TicketingSystem implements Runnable{

   //=====================  Constants  ==========================//
  public static final int ADMIN = 1;    //level of an admin user
  public static final int STANDARD = 0; //level of a standard user
  
  //=====================  Instance Variables  ======================//
  private Technician currentUser = null;  //authenticated user, null if not authenticated
  private Clients clients = new Clients();  //clients database table access
  private Tickets tickets = new Tickets();  //tickets database table access
  private TicketClients ticketClients = new TicketClients();    //ticket_clients database table access
  private TicketTechnicians ticketTechnicians = new TicketTechnicians();    //ticketTechnicians database table access
  

  public static void main(String[] args) throws Exception {

        //kick off a new TicketingSystem thread
        Thread t = new Thread(new TicketingSystem());
        t.start();
    
  }
    
      

    /**
     * run() begins program execution
     */
    @Override
    public void run() {
        
        System.out.println("====== Welcome to the Ticketing System ===== \n\n");
    
        System.out.println("Clients: ");
        
        //Print client info
        Collection<Client> clientSet = null;
        try{
            clientSet = clients.fetchAll();
        }catch(Exception e1){
            e1.printStackTrace();
            System.exit(0);
            //TODO edit error handling
        for(Client c : clientSet){
            System.out.println(c);
        }
      
      
        System.out.println("\n\nTickets: ");
        
        //Print ticket info
        Collection<Ticket> ticketSet = null;
        try{
            ticketSet = tickets.fetchAll();
        } catch(Exception e2){
            e2.printStackTrace();
            System.exit(0);
            //TODO edit error handling
        }
        for(Ticket t : ticketSet){
            System.out.println(t);
        }
      
        try{
        System.out.println("\n\nThe Technician for ticket no1 is " + tickets.fetchTechnician(1).getName());
        System.out.println("\n\nThe Technician for ticket no2 is " + tickets.fetchTechnician(2).getName());
        }catch(Exception e3){
            e3.printStackTrace();
            System.exit(0);
            //TODO edit error handling
        }
        
    }
      
  } // End of main
    
  /**
   * Display the main menu for the TicketingSystem.  Output will
   * depend on whether the authenticated user is a standard user or an
   * administrator
   * @pre currentUser is not null (there is an authenticated user)
   */
  public void mainMenu(){
    
    //=====================  Local Data  ======================//
    char response;  //used to store user option selection
    boolean quit = false;   //whether main menu should be quit or not
    String prompt = "Please select one of the following options.";
    
    
    //menu wrapped in do-while so there is an opportunity to confirm
    //quitting the application
    do{
    
        //if user is a standard user, display the standard menu
        if(currentUser.getLevel() == STANDARD){
    
            String[] standardOpts =  //standard menu options
                  {
                    "1 - View assigned tickets",
                    "2 - Add a ticket",
                    "3 - Quit"
                  };
            response = variableOption(prompt, standardOpts, '1');
            
            switch(response){

              //view assigned tickets
              case '1':

                  //TODO implement

                  break;


              //add a ticket
              case '2':

                  //TODO implement

                  break;


              //quit
              case '3':

                  //if quit is confirmed, set quit to true so menu loop
                  //will end
                  if(yesNoOption("Are you sure you want to quit?", true)){
                      quit = true;
                  }
                  break;
          }//end switch

        }//end if standard user

    } while(!quit);
    
  }
  
  /**
   * viewTicketsMenu displays the menu for viewing and selecting tickets
   * for the current user
   * @pre currentUser is not null (there is an authenticated user)
   */
  public void viewTicketsMenu(){
      
      //get list of all the tickets for the current user
      Collection<Ticket> ticketSet = null;
      try{
        ticketSet = tickets.fetchAllForTechnician(currentUser.getID());
      }catch(Exception e){
        ticketSet = new LinkedHashSet<>();
      }
      
      //print header
      System.out.println(CFormat.head("Tickets"));
      
      //print tickets formatted by id
      for(Ticket t : ticketSet){
          System.out.println(CFormat.item(t.getID() + " - " + t.getTitle(), 1));
      }
      
  }
}
