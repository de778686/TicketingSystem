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
      Clients clients = new Clients();
      Technicians technicians = new Technicians();
      TicketClients ticket_clients = new TicketClients();
      TicketTechnicians ticket_technicians = new TicketTechnicians();
      

      // Loop which creates tables:
      for (DBTable table : new DBTable[]{ tickets, clients, technicians, ticket_clients, ticket_technicians }) {
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

      System.out.println("\n===> populate " + clients.getTableName());
      
      // Generated test data:
      // public Client add(String name, String phone, String altPhone, String email, String address)
      
      clients.add("Fred", "6104568795", "74125893", "fred@bob.com", "243 g koewr rd");
      clients.add("Corbin", "645458446", "5454512121", "scrum@agile.com", "458 MacBook rd");
      clients.add("Steve", "6454sreg58446", "5454shs512121", "scrum@agilhe.com", "458 MahcBook rd");
      
      
      tickets.add(001, "title1","open", "Bob", "ABC Broadcasting", "Today");
      tickets.add(002, "title2","closed", "Steve", "Philmont Acadamey", "8/7/13");
      tickets.add(003, "title3","open", "Steve", "Philmont Acadamey", "8/7/13");
      
      technicians.add("Gary", 4);
      technicians.add("Peter", 3);
      
      // Add a technician to a ticket
      ticket_technicians.add(1,1);
      ticket_technicians.add(2,1);
      ticket_technicians.add(3,1);

      ticket_technicians.remove(3,1);
      
      
      
      int id;
      
      // Movies are Tickets
      // id, status, creator, client
//      Collection<Movie> movies_list = new LinkedList<Movie>();
//      movies_list.add( movies.add("The Godfather", 1972, descrip("gf")) );
//      movies_list.add( movies.add("GoodFellas", 1990, "") );
//      movies_list.add( movies.add("Analyze This", 1999, descrip("analyze")) );
//      movies_list.add( movies.add("One Flew Over the Cuckoo's Nest", 1975, "") );
//      movies_list.add( movies.add("The Godfather: Part II", 1974, descrip("gf2")) );
//      movies_list.add( movies.add("Casablanca", 1942, "") );
//      movies_list.add( movies.add("The Big Sleep", 1946, descrip("sleep")) );
//      movies_list.add( movies.add("The Missouri Breaks", 1976, "") );
//      movies_list.add( movies.add("Lonesome Dove", 1989, "") );
//      
      id = 1;
//      for (Movie movie : movies_list) {
//        System.out.printf("%2d: %s\n", id++, movie.toString());
//      }

//      System.out.println("\n===> populate " + actors.getTableName());
      //Technicians:
//      String[] names = {
//        "Al Pacino",
//        "Marlon Brando",
//        "Jack Nickolson",
//        "Humphrey Bogart",
//        "Lauren Bacall",
//        "Robert DeNiro",
//        "Robert Duvall",
//      };

//      id = 1;
//      for (String name : names) {
//        clients.add(name);
//        System.out.printf("%2d: %s\n", id++, name);
//      }
      
      
//      clients.add("Bob", "605045", "123453", "er@awe.com", "asdfasdfa");
      

//      System.out.println("\n===> populate " + movie_actors.getTableName());
//      Collection<MovieActor> movie_actors_list = new LinkedList<MovieActor>();
//      movie_actors_list.add( movie_actors.add(1, 1) );
//      movie_actors_list.add( movie_actors.add(1, 2) );
//      movie_actors_list.add( movie_actors.add(1, 7) );
//      movie_actors_list.add( movie_actors.add(2, 6) );
//      movie_actors_list.add( movie_actors.add(3, 6) );
//      movie_actors_list.add( movie_actors.add(4, 3) );
//      movie_actors_list.add( movie_actors.add(5, 1) );
//      movie_actors_list.add( movie_actors.add(5, 6) );
//      movie_actors_list.add( movie_actors.add(5, 7) );
//      movie_actors_list.add( movie_actors.add(6, 4) );
//      movie_actors_list.add( movie_actors.add(7, 4) );
//      movie_actors_list.add( movie_actors.add(7, 5) );
//      movie_actors_list.add( movie_actors.add(8, 2) );
//      movie_actors_list.add( movie_actors.add(8, 3) );
//      movie_actors_list.add( movie_actors.add(9, 7) );

//      for (MovieActor movie_actor : movie_actors_list) {
//        System.out.println(movie_actor);
//      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
