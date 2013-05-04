package ticketingsystem;

import java.util.LinkedHashSet;
import dataobjects.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static utils.CFormat.*;
import utils.SelectOption;
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
public class TicketingSystem implements Runnable {

    //=====================  Constants  ==========================//
    public static final int ADMIN = 1;    //level of an admin user
    public static final int STANDARD = 0; //level of a standard user
    //=====================  Instance Variables  ======================//
    private Technician currentUser = null;  //authenticated user, null if not authenticated
    private Tickets tickets = new Tickets();  //tickets database table access
    private Entries entries = new Entries();
    private TicketEntries ticketEntries = new TicketEntries();
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

        System.out.println(titleBox("CQD2 Ticketing System"));
        boolean validated = false;
        int counter = 0;
        do {
            System.out.println("Enter username: ");
            String username = keyboard.nextLine();
            System.out.println("Enter password: ");
            String password = keyboard.nextLine();

            login currLogin = new login(username, password);
            if (currLogin.validate()) {
                validated = true;
                try {
                    currentUser = technicians.fetchByUsername(username);
                } catch (Exception ex) {
                    System.out.println("Error fetching current user!");
                }

                //reset counter
                counter = 0;

            } else {
                counter++;
                validated = false;
                System.out.println("Invalid username/password!");
            }
        } while (!validated && (counter < 3));

        //if counter is 3, there were too many login attempts
        if (counter == 3) {
            System.out.println("Too many invalid authentication attempts. Program will now exit");
            System.exit(0);

            //if counter is not 3, login was successful and we should proceed
            //to the main menu
        } else {
            mainMenu();
        }

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
                    "1 - View tickets",
                    "2 - Add a ticket",
                    "3 - Add a technician",
                    "4 - Assign a technician",
                    "q - Quit"
                };
                response = variableOption(prompt, standardOpts, '1');

                switch (response) {

                    //view all tickets
                    case '1':
                        viewTicketsMenu();
                        break;

                    //add a ticket
                    case '2':
                        addTicket();
                        break;

                    //add a technician
                    case '3':
                        addTechnician();
                        break;

                    //modify technician level
                    case '4':
                        assignTechnician();
                        break;

                    //quit program
                    case 'Q':

                        //if quit is confirmed, set quit to true so menu loop
                        //will end
                        if (yesNoOption("Are you sure you want to quit?", true)) {
                            quit = true;
                        }
                        break;
                }//end switch


            }//end if standard user
            else {
                String[] standardOpts = //standard menu options
                        {
                    "1 - View tickets",
                    "2 - Add a ticket",
                    "q - Quit"
                };
                response = variableOption(prompt, standardOpts, '1');

                switch (response) {
                    //view assigned ticket
                    case '1':
                        viewTicketsMenu();
                        break;

                    //add a ticket
                    case '2':
                        addTicket();
                        break;

                    case 'Q':

                        //if quit is confirmed, set quit to true so menu loop
                        //will end
                        if (yesNoOption("Are you sure you want to quit?", true)) {
                            quit = true;
                        }
                        break;
                }
            }

        } while (!quit);

    }

    /**
     * viewTicketsMenu displays the menu for viewing and selecting tickets
     * for the current user
     * @pre currentUser is not null (there is an authenticated user)
     */
    public void viewTicketsMenu() {

        //=====================  Local Data  ======================//
        boolean quit = false;

        //wrap in do while to allow repetition of menu
        do {

            //if logged in user is a standard user, get a list of all tickets
            //assigned to him/her
            Collection<Ticket> ticketSet = null;
            try {
                if (currentUser.getLevel() == STANDARD) {
                    ticketSet = tickets.fetchAllForTechnician(currentUser.getID());
                } else {
                    //TODO implement for admin in UI-G/19
                }
            } catch (Exception e) {

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
            if (!ticketSet.isEmpty()) {
                for (Ticket t : ticketSet) {

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
                if (response.charAt(0) == 'I') {

                    //go to the submenu for this ticket
                    int ticketID = Integer.parseInt(response.substring(1));
                    System.out.println("Ticket " + ticketID + " selected.");
                    ticketSubMenu(ticketID);

                    //user asked to list tickets
                } else if (response.charAt(1) == 'L') {
                    //do nothing to loop the menu again
                } else if (response.charAt(1) == 'Q') {
                    //System.out.println("Quit");
                    quit = true;
                }

                //if there are not tickets for the user, print out message
            } else {
                System.out.println("You have no assigned tickets!");
            }

        } while (!quit);
    }

    /**
     * ticketSubMenu allows a user to manipulate information about a
     * particular ticket
     * @param ticketID t
     */
    public void ticketSubMenu(int ticketID) {

        while (true) {
            String[] standardOptions = {
                "1 - View ticket details",
                "2 - Add entry", // DavidE
                "3 - Go Back"
            };
            String prompt = "Please select one of the following options";

            char choice; //hold user's menu choice

            if (currentUser.getLevel() == STANDARD) {
                choice = SelectOption.variableOption(prompt, standardOptions, '1');

                switch (choice) {

                    case '1':
                        viewTicket(ticketID);
                        break;
                    case '2':
                        try {
                            addEntry(ticketID);
                        } catch (Exception ex) {
                            System.out.println("Unable to add entry");
                            ex.printStackTrace();
                        }
                        break;
                    case '3':
                        //leave method to return to previous menu
                        return;

                }
            }
        }
    }

    //displays ticket details based on user entry
    public void viewTicket(int ticketID) {

        //=====================  Local Data  ======================//
        Ticket ticket;  //ticket to view details from

        try {
            ticket = tickets.fetch(ticketID);
        } catch (Exception e) {
            System.out.println("Unable to display info for this ticket.");
            return;
        }

        //build table based on ticket info
        ArrayList<ArrayList<String>> table = new ArrayList<>();

        //general info
        ArrayList<String> row1 = new ArrayList<>();
        row1.add("Ticket ID:");
        row1.add(Integer.toString(ticket.getID()));
        table.add(row1);
        ArrayList<String> row2 = new ArrayList<>();
        row2.add("Date Created:");
        row2.add(ticket.getDateCreatedString().toString());
        table.add(row2);
        ArrayList<String> row3 = new ArrayList<>();


        //assigned technicians
        List<Technician> techs = null;
        try {
            techs = ticketTechnicians.fetchTechniciansByTicketID(ticketID);
        } catch (Exception e) {
            //TODO improve error handling here in future release
            System.out.println("Unable to display info for this ticket.");
            return;
        }

        //if there are techs to add, add them to the list
        if (techs != null && !techs.isEmpty()) {
            //make first row of tech info have row title
            ArrayList<String> row4 = new ArrayList<>();
            row4.add("Assigned Technicians:");
            row4.add(techs.get(0).getName());
            table.add(row4);

            for (int i = 1; i < techs.size(); i++) {
                row4 = new ArrayList<>();
                row4.add("");
                row4.add(techs.get(i).getName());
            }
        }

        //print out title
        System.out.println(head("Ticket Info:") + NL);

        //print out description
        System.out.println(ticket.getTitle());

        //print table out
        System.out.println(toTable(table, false));

        //print out ticket entries - David E: ================================//
        Entry entry;

        // entries for ticket:
        List<Entry> entriesList = null;
        try {
            entriesList = entries.fetchAllFromTicket(ticketID);
        } catch (Exception e) {
            //TODO improve error handling here in future release
            System.out.println("Unable to display entries for this ticket.");
            return;
        }

        if (entriesList.isEmpty()) {
            System.out.println("This Ticket has no entries.");
        } else {
            System.out.println("Entries: ");
        }

        // One table for every entry: 
        for (int i = 0; i < entriesList.size(); i++) {
            entry = entriesList.get(i);
            //build a table based on entries
            ArrayList<ArrayList<String>> entryTable = new ArrayList<>();
            ArrayList<String> entryRow1 = new ArrayList<>();
            entryRow1.add("Entry ID:");
            entryRow1.add(Integer.toString(entry.getID()));
            entryTable.add(entryRow1);
            ArrayList<String> entryRow2 = new ArrayList<>();
            entryRow2.add("Creation Date:");
            entryRow2.add(entry.getCreationDate());
            entryTable.add(entryRow2);
            ArrayList<String> entryRow3 = new ArrayList<>();
            entryRow3.add("Creator:");
            entryRow3.add(entry.getCreator());
            entryTable.add(entryRow3);
            ArrayList<String> entryRow4 = new ArrayList<>();
            entryRow4.add("Text:");
            entryRow4.add(entry.getText());
            entryTable.add(entryRow4);

            //print the entryTable out
            System.out.println(toTable(entryTable, false));
        }

    } // End of viewTicket.

    //creates a ticket entry which is by default assigned to the creator. Only technicians with
    //level 1 or > 0 can assign this ticket to a different technician
    public void addTicket() {
    }

    // Creates an entry and links it to the ticket specified by the parameter(ticketID):
    public void addEntry(int ticketID) throws Exception {
        Entry entry;
        String creationDate;
        String creatorName;
        String text;

        System.out.println("creationDate:");
        creationDate = keyboard.nextLine();
        System.out.println("creatorName:");
        creatorName = keyboard.nextLine();
        System.out.println("text:");
        text = keyboard.nextLine();

        // creates entry in DataBase:
        entry = entries.add(creationDate, creatorName, text);

        // links entry to ticket:
        ticketEntries.add(entry.getID(), ticketID);
    }

    //update ticket information with limitations based on technician level
    public void updateTicket() {
    }

    //deletes or closes the ticket in question. only technicians with level > 0 can do this.
    public void deleteTicket() {
        String line = prompt("Ticket ID to remove (-1 to cancel): ");
        try {
            int id = Integer.parseInt(line);
            if (id != -1) {
                try {
                    tickets.remove(id);
                } catch (Exception e) {
                    System.err.println("Unable to delete ticket " + id);
                }
            } else {
                System.out.println("Cancelled remove.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Not a number.");
        }
    }

    //adds a new entry into the technician table. only technicians with level > 0 can do this.
    public void addTechnician() {
    }

    //modifies the level of the technician. only technicians with level > 0 can do this.
    public void modifyTechnicianLevel() {
    }

    // View the information for a single technician
    public void viewTechnician() throws Exception {
        String username = prompt("Technician User Name: ");
        System.out.print(technicians.fetchByUsername(username).toString());
    }

    public void viewAllTechnicians() throws Exception {
        Collection<Technician> techs = technicians.fetchAll();

        Iterator<Technician> itr = techs.iterator();
        while (itr.hasNext()) {
            Technician tech = itr.next();
            System.out.println(tech.toString());
        }
    }

    private String prompt(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private void assignTechnician() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
