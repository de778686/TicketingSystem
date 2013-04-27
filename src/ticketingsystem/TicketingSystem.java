package ticketingsystem;

import dataobjects.*;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.CFormat;
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

        System.out.println("====== Welcome to the Ticketing System ===== \n\n");

        System.out.println("Technicians: ");

        //Print client info
        Collection<Technician> technicianSet = null;
        try {
            technicianSet = technicians.fetchAll();
        } catch (Exception e1) {
            e1.printStackTrace();
            System.exit(0);
            //TODO edit error handling
        }
        for (Technician t : technicianSet) {
            System.out.println(t);
        }

        System.out.println("\n\nTickets: ");

        //Print ticket info
        Collection<Ticket> ticketSet = null;
        try {
            ticketSet = tickets.fetchAll();
        } catch (Exception e2) {
            e2.printStackTrace();
            System.exit(0);
            //TODO edit error handling
        }
        for (Ticket t : ticketSet) {
            System.out.println(t);
        }

        try {
            System.out.println("\n\nThe Technician for ticket no1 is " + tickets.fetchTechnician(1).getName());
            System.out.println("\n\nThe Technician for ticket no2 is " + tickets.fetchTechnician(2).getName());
        } catch (Exception e3) {
            e3.printStackTrace();
            System.exit(0);
            //TODO edit error handling
        }
        
        //for testing only!!
        try {
            currentUser = technicians.fetch(1);
        } catch (Exception ex) {
            Logger.getLogger(TicketingSystem.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        mainMenu();

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
            if (currentUser.getLevel() == STANDARD) {

                String[] standardOpts = //standard menu options
                        {
                    "1 - View assigned tickets",
                    "2 - Add a ticket",
                    "3 - Quit"
                  };
            response = variableOption(prompt, standardOpts, '1');
            
            switch(response){

              //view assigned tickets
              case '1':

                  viewTicketsMenu();
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
      System.out.println(CFormat.head("Assigned Tickets"));
      
      //if there are tickets for the user, print tickets formatted by id
      if(!ticketSet.isEmpty()){
        for(Ticket t : ticketSet){
            System.out.print(CFormat.item(t.getID() + ": " + t.getTitle(), 1));
        }
        System.out.println(""); //for formatting purposes
        
      //if there are not tickets for the user, print out message
      } else {
          System.out.println("You have no assigned tickets!");
      }
      
  }
}
