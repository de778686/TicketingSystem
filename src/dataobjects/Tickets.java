// David Emery


//private Status status;              //status of the ticket (ie open, close, etc.)
//    private Technician creator;         //technician that created the ticket
//    private Client client;              //client of the ticket (ie customer, employee)
//    private Date dateCreated;           //date ticket was created
//    private Date dateModified;          //date of last modification to the ticket
//    private List<Entry> entries;        //list of entries associated with the ticket

// tickets are like movies

package dataobjects;

import models.*;
import java.sql.*;
import java.util.*;

public class Tickets implements DBTable {
  public static final String table = "tickets";
  private DB db = new DB();
  
  // collect Clients:
  Clients clients = new Clients();  
  Collection<Client> clientColl = new LinkedHashSet<Client>();
  
   
    

  @Override
  public String getTableName() { return table; }
  
    /**
     *
     * @param ID
     * @param status
     * @param creator
     * @param client
     * @param dateCreated
     * @param dateModified
     * @param entries
     * @return
     * @throws Exception
     */
    public Ticket add(int ID, Status status, Technician creator,
          Client client, java.sql.Date dateCreated, java.sql.Date dateModified, List<Entry> entries) throws Exception {
    Connection cx = db.connect();
    String sql;
   
    sql = String.format(
      "insert into `%s` (`title`,`year`,`description`) values (?,?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, status.toString());
    st.setString(2, creator.getName());
    st.setString(3, client.getName());
    st.setDate(4, dateCreated);
    st.setDate(5, dateModified);
    
    st.executeUpdate();

    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    return new Ticket(id, status, creator, client, dateCreated); // add method
  }
  
  // Fetch all tickets
  public Collection<Ticket> fetchAll() throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s` order by title", table);
    //System.out.println("sql_op: " + sql_op);
    Statement st = cx.createStatement();

    ResultSet rs = st.executeQuery(sql);

    Collection<Ticket> coll = new LinkedHashSet<Ticket>();
    while (rs.next()) {
      int id = rs.getInt("id");
      
      String status = rs.getString("status");
      
      // Fetch technician name and search clients collection
      String creatorName = rs.getString("creator");
//      Technician tech = 
      
      // Fetch client name and search collection
      String clientName = rs.getString("client");
      clientColl = clients.fetchAll();
      
      java.sql.Date dateCreated = rs.getDate("dateCreated");
//      coll.add(new Ticket(id, status, creatorName, clientName, dateCreated));
    }
    return coll;
  }
  
  public void modify(Movie movie, String descr) throws Exception {
    Connection cx = db.connect();
    
    int id = movie.getId();
    int year = movie.getYear();
    // title, year, description
    String sql_op = String.format(
      "update `%s` set `title`=?,`year`=?,`description`=? where `id`=?", table);
    PreparedStatement st = cx.prepareStatement(sql_op);
    String title = movie.getTitle();
    
    st.setString(1, title);
    st.setInt(2, year);
    st.setString(3, descr);
    st.setInt(4, id);
    st.executeUpdate();
  }
  
  // Setting the description initially to empty
  
//  public Ticket(int ID, Status status, Technician creator, Client client, Date dateCreated) {
//        this.ID = ID;
//        this.status = status;
//        this.creator = creator;
//        this.client = client;
//        this.dateCreated = dateCreated;
//        this.dateModified = null;
//        this.entries = new ArrayList<>();
//    }
  public Ticket add(int ID, String status, Technician creator, Client client, java.sql.Date dateCreated) throws Exception {
    return add(ID, status, creator, client,  dateCreated);
  }
  
  
  // Fetch ===============================================
  public Movie fetch(int id) throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s` where `id`=?", table);
    //System.out.println("sql_op: " + sql_op);
    
    // Testing:
    System.out.println(sql);
    
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    if (!rs.next()) {
      return null;
    }
    return new Movie(id, rs.getString("title"), rs.getInt("year"), rs.getString("description"));
  }
  
  
  
  
  // Fetch a technician for a ticket===============================================
  public Technician fetchTechnician(int id) throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s` where `id`=?", "technicians");
    //System.out.println("sql_op: " + sql_op);
    
    // Testing:
    System.out.println(sql);
    
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    if (!rs.next()) {
      return null;
    }
    return new Technician(rs.getString("name"), rs.getInt("id"), rs.getInt("level"));
  }
  
  
  //============================================================================
  
  
  
  // Fetches all technicians from a given ticket.
  public Collection<Movie>fetchAllForActor(int id) throws Exception {
      Connection cx = db.connect();
 
      // Testing:
      System.out.println("parameter for fetchAllForActor():" + id);
      
     String sql = "select movies.id, title "
                + "from movie_actors join movies join actors on "
                + "movies.id = movie_actors.movie_id and "
                + "actors.id = movie_actors.actor_id where actors.id=?";
     
    PreparedStatement st = cx.prepareStatement(sql);
    
    st.setInt(1, id);
    
    // Testing:
    System.out.println(sql);
    
    ResultSet rs = st.executeQuery();

    Collection<Movie> coll = new LinkedHashSet<Movie>();
    while (rs.next()) {
      int movieId = rs.getInt("id");
      String title = rs.getString("title");
      int year = rs.getInt("year");
      String descr = rs.getString("description");
      coll.add(new Movie(movieId, title, year, descr));
    }
    return coll;
  }
  
 
}
