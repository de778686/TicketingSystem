// David Emery
package ticketingsystem;

import java.sql.*;
import java.util.*;

//import models.*;
import dataobjects.*;

public class Setup {

    public static String descrip(String prefix) {
        String f = "setup" + java.io.File.separator
                + "descriptions" + java.io.File.separator + prefix + ".txt";
        try {
            return Util.readTextFile(f);
        } catch (java.io.IOException ex) {
            return "";
        }
    }
    private static final boolean populate = true;
    private static DB.Model model = DB.getModel();  // mysql or sqlite

    @SuppressWarnings("CallToThreadDumpStack")
    public static void main(String[] args) throws Exception {
        System.out.println("model used: " + model);

        DB db = new DB();
        String sql_op;

        try {
            Tickets tickets = new Tickets();
            Technicians technicians = new Technicians();
            TicketTechnicians ticket_technicians = new TicketTechnicians();
            Entries entries = new Entries();
            TicketEntries ticket_entries = new TicketEntries();

            // Loop which creates tables:
            for (DBTable table : new DBTable[]{tickets, technicians, ticket_technicians, entries, ticket_entries}) {
                String table_name = table.getTableName();
                System.out.println();

                sql_op = String.format("drop table if exists `%s`", table_name);
                System.out.println(sql_op);
                db.exec(sql_op);

                String table_def_file =
                        "setup" + java.io.File.separator + table_name + "-" + model + ".sql";
                String table_def = Util.readTextFile(table_def_file);
                sql_op = String.format("create table `%s` (%s)", table_name, table_def);
                System.out.println(sql_op);
                db.exec(sql_op);
            }

            if (!populate) {
                return;
            }


            // =======================================================================
            // Test data:

            tickets.add(001, "title1", "open", "Bob", "ABC Broadcasting", "Today");
            tickets.add(002, "title2", "closed", "Steve", "Philmont Acadamey", "8/7/13");
            tickets.add(003, "title3", "open", "Steve", "Philmont Acadamey", "8/7/13");


            System.out.println("\n\nOur Tickets are: ");
            for (Ticket t : tickets.fetchAll()) {
                System.out.println(t.toString());
            }


            technicians.add("a", "a", "A", 0); // For testing
            technicians.add("corbus", "maximus1", "Corbin", 0);  // 1
            technicians.add("davidus", "apple1", "David", 0);  // 2
            technicians.add("quinntus", "mango1", "Quinn", 1); // 2

            System.out.println("\n\nOur Technicians are: ");
            for (Technician t : technicians.fetchAll()) {
                System.out.println(t.toString());
            }


            // Add a technician to a ticket: (TechID, TicketID)
            ticket_technicians.add(1, 1); // put Gary on ticket 1
            ticket_technicians.add(1, 2); // put Gary on ticket 2
            ticket_technicians.add(2, 1); // put Peter on ticket 1
            ticket_technicians.add(3, 1);
            ticket_technicians.add(3, 3); // put Corbin on Ticket 


            // Fetch all of Tech #1 tickets:
            System.out.println("\n\nTechnician 1's tickets:");
            for (Ticket t : tickets.fetchAllForTechnician(1)) {
                System.out.println(t.toString());
            }

            // Fetch all of Corbin's tickets:
            System.out.println("\n\nCorbin's tickets are:");
            for (Ticket t : tickets.fetchAllForTechnician(3)) {
                System.out.println(t.toString());
            }


            // This call removes a technician from a ticket
            ticket_technicians.remove(3, 1);

            // Added functionality 4/30/13:
            // Create Entry:
            entries.add("4/29/13", "Gary", "Had a lot of fun working with ABC");
            // Associate it with appropriate ticket: (entryID, ticketID)
            ticket_entries.add(1, 1);
            
            // Create Entry:
            entries.add("1/1/13", "Corbin", "Had a lot of fun working with the ball");
            // Associate it with appropriate ticket: (entryID, ticketID)
            ticket_entries.add(2, 1);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
