package ticketingsystem;

import dataobjects.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static utils.CFormat.*;
import static utils.SelectOption.*;

/**
 * Ticketing System is the main class for the TicketingSystem application
 * @author CQD2 Software
 * Contributors:
 *      David Emery
 *      Quinn Detweiler
 *      David Grant
 *      Corbin Hall
 */
@SuppressWarnings("CallToThreadDumpStack")
public class TicketingSystem implements Runnable{

   //=====================  Constants  ==========================//
  public static final int ADMIN = 1;    //level of an admin user
  public static final int STANDARD = 0; //level of a standard user
  
  //=====================  Instance Variables  ======================//
  private Technician currentUser = null;  //authenticated user, null if not authenticated
  private Tickets tickets = new Tickets();  //tickets database table access
  private Technicians technicians = new Technicians();
  private TicketTechnicians ticketTechnicians = new TicketTechnicians();    //ticketTechnicians database table access
  private Scanner keyboard = new Scanner(System.in);

  /**
   * Main method for the TicketingSystem application which starts a new
   * thread and runs the application
   * @param args
   * @throws Exception 
   */
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

        System.out.println("*********** CQD2 Ticketing System ***********\n");
        boolean validated = false;
        int counter = 0;
        do{
            System.out.println("Enter username: ");
            String username = keyboard.nextLine();
            System.out.println("Enter password: ");
            String password = keyboard.nextLine();

            login currLogin = new login(username, password);
            if (currLogin.validate()){
                validated = true;
                //reset counter
                counter = 0;
                //present main menu
                mainMenu();
            }
            else{
                counter ++;
                validated = false;
                System.out.println("Invalid username/password!");
            }
        }
        while(!validated && (counter < 3));
        
        System.out.println("Too many invalid authentication attempts. Program will now exit");
        System.exit(0);
        
        

            
        
        
        
        

    } // End of main

    /**
     * Display the main menu for the TicketingSystem.  Output will
     * depend on whether the authenticated user is a standard user or an
     * administrator
     * @pre currentUser is not null (there is an authenticated user)
     */
    public void mainMenu() {

        //=====================  Local Data  ======================//
        char response;  //used to store user option selection
        boolean quit = false;   //whether main menu should be quit or not
        String prompt = "Please select one of the following options.";


        //menu wrapped in do-while so there is an opportunity to confirm
        //quitting the application
        do {

            //if user is a standard user, display the standard menu
            if (currentUser.getLevel() == ADMIN) {

                String[] standardOpts = //standard menu options
                        {
                    "1 - View all tickets",
                    "2 - View assigned tickets",
                    "3 - View a ticket",
                    "4 - Add a ticket",  
                    "5 - Update a ticket",
                    "6 - Delete a ticket",
                    //View a technician //view all technicians
                    "7 - Add a technician",
                    "8 - Modify technician level",
                    "9 - Quit"
                  };
            response = variableOption(prompt, standardOpts, '1');
            
            switch(response){

              //view all tickets
              case '1':
                  viewAllTickets();
                  break;
                  
              //view assigned tickets
              case '2':
                  viewAssignedTickets();
                  break;
                  
              //view a ticket
              case '3':
                  viewTicket();
                  break;
                  
              //add a ticket
              case '4':
                  addTicket();
                  break;
                  
              //update a ticket
              case '5':
                  updateTicket();
                  break;
                  
              //delete a ticket
              case '6':
                  deleteTicket();
                  break;
                  
              //view a ticket
              case '7':
                  addTechnician();
                  break;
                  
              //view a ticket
              case '8':
                  modifyTechnicianLevel();
                  break;
                  
              case '9':

                  //if quit is confirmed, set quit to true so menu loop
                  //will end
                  if(yesNoOption("Are you sure you want to quit?", true)){
                      quit = true;
                  }
                  break;
          }//end switch
            

        }//end if standard user
            else{
                String[] standardOpts = //standard menu options
                {
 
                    "1 - View assigned tickets",
                    "2 - View a ticket",
                    "3 - Add a ticket",  
                    "4 - Update a ticket",
                    "5 - Quit"
                };
                response = variableOption(prompt, standardOpts, '1');

                switch(response){
                    //view assigned ticket
                    case '1':
                        viewAssignedTickets();
                        break;

                    //view a ticket
                    case '2':
                        viewTicket();
                        break;
                        

                    //add a ticket
                    case '3':
                        addTicket();
                        break;
                        

                    //update a ticket
                    case '4':
                        updateTicket();
                        break;

                    case '5':

                        //if quit is confirmed, set quit to true so menu loop
                        //will end
                        if(yesNoOption("Are you sure you want to quit?", true)){
                            quit = true;
                        }
                        break; 
                }
            }
            
            
            
    } while(!quit);
    
  }
  
  /**
   * viewTicketsMenu displays the menu for viewing and selecting tickets
   * for the current user
   * @pre currentUser is not null (there is an authenticated user)
   */
  public void viewTicketsMenu(){
      
      //=====================  Local Data  ======================//
      boolean quit = false;
      
      //wrap in do while to allow repetition of menu
      do{
      
        //if logged in user is a standard user, get a list of all tickets
        //assigned to him/her
        Collection<Ticket> ticketSet = null;
        try{
          if(currentUser.getLevel() == STANDARD){
              ticketSet = tickets.fetchAllForTechnician(currentUser.getID());
          } else {
              //TODO implement for admin in UI-G/19
          }
        }catch(Exception e){

            //if there is an error fetching from the database
            //print an error message to the console and then return
            //to previous procedure (previous menu)
            System.out.println("Error connecting to database.  Please try again");
            return;
        }

        //print header
        System.out.println(head("Assigned Tickets"));

        //if there are tickets for the user, print tickets formatted by id

        Collection<Integer> ticketIDs = new HashSet<>();
        if(!ticketSet.isEmpty()){
          for(Ticket t : ticketSet){

              //store ticket IDs for selection later
              ticketIDs.add(t.getID());
              System.out.print(item(t.getID() + ": " + t.getTitle(), 1));

          }
          System.out.println(""); //for formatting purposes

          //allow user to select a ticket or quit
          String[] options = {
            "Ticket ID number to view details",
            "l - list tickets again",
            "q - return to the main menu"
          };
          String prompt = "Please enter one of the following:";

          String response = complexVariableOption(prompt, options, 1, ticketIDs, 'l');

          //user entered a valid ticket ID
          if(response.charAt(0)=='I'){

              int ticketID = Integer.parseInt(response.substring(1));
              System.out.println("Selected ID = " +ticketID);
              //TODO implement in UI-G/8

          //user asked to list tickets
          } else if(response.charAt(1)=='L'){
              //do nothing to loop the menu again
          } else if(response.charAt(1)=='Q'){
              System.out.println("Quit");
              quit = true;
          }

        //if there are not tickets for the user, print out message
        } else {
            System.out.println("You have no assigned tickets!");
        }
      
      } while (!quit);  
  }
  
  //get and displays information on all tickets
  public void viewAllTickets(){
      
  }
  
  //displays all tickets based on technician's ID in ticket's table
  public void viewAssignedTickets(){
      
  }
  
  //displays ticket details based on user entry
  public void viewTicket(){
      
  }
  
  //creates a ticket entry which is by default assigned to the creator. Only technicians with
  //level 1 or > 0 can assign this ticket to a different technician
  public void addTicket(){
      
  }
  
  //update ticket information with limitations based on technician level
  public void updateTicket(){
      
  }
  
  //deletes or closes the ticket in question. only technicians with level > 0 can do this.
  public void deleteTicket(){
      
  }
  
  //adds a new entry into the technician table. only technicians with level > 0 can do this.
  public void addTechnician(){
      
  }
  
  //modifies the level of the technician. only technicians with level > 0 can do this.
  public void  modifyTechnicianLevel(){
      
  }
}
