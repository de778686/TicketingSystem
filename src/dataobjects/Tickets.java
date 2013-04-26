// David Emery


//private Status status;              //status of the ticket (ie open, close, etc.)
//    private Technician creator;         //technician that created the ticket
//    private Client client;              //client of the ticket (ie customer, employee)
//    private Date dateCreated;           //date ticket was created
//    private Date dateModified;          //date of last modification to the ticket
//    private List<Entry> entries;        //list of entries associated with the ticket

// tickets are like movies

package dataobjects;

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
     * @param title
     * @param status
     * @param creator
     * @param client
     * @param dateCreated
     * @param dateModified
     * @param entries
     * @return
     * @throws Exception
     */
  
  
  //  Simple version:
  public Ticket add(int ID,String title, String status, String creator,
          String client, String dateCreated) throws Exception {
    Connection cx = db.connect();
    String sql;
   
    sql = String.format(
      "insert into `%s` (`id`, `title`,`status`,`creator`, `client`, `dateCreated`, `dateModified`) values (?,?,?,?,?,?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, ID);
    st.setString(2,title);
    st.setString(3, status);
    st.setString(4, creator);
    st.setString(5, client);
    st.setString(6, dateCreated);
    st.setString(7, "");
    
    st.executeUpdate();

    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    return new Ticket(id, title, status.toString(), creator, client, dateCreated); // add method
  }
  
  
  
  
    public Ticket add(int ID, String title,Status status, Technician creator,
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
    return new Ticket(id, title, status, creator, client, dateCreated); // add method
  }
  
  // Fetch all tickets
  public Collection<Ticket> fetchAll() throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s`", table);
    //System.out.println("sql_op: " + sql_op);
    Statement st = cx.createStatement();

    ResultSet rs = st.executeQuery(sql);

    Collection<Ticket> coll = new LinkedHashSet<Ticket>();
    while (rs.next()) {
        
      int id = rs.getInt("id");
      
      String status = rs.getString("status");
      
      String title = rs.getString("title");
      // Fetch technician name and search clients collection
      String creatorName = rs.getString("creator");
//      Technician tech = 
      
      // Fetch client name and search collection
      String clientName = rs.getString("client");
      clientColl = clients.fetchAll();
      
//      java.sql.Date dateCreated = rs.getDate("dateCreated");
      String dateCreated = rs.getString("dateCreated");
      coll.add(new Ticket(id, title, status, creatorName, clientName, dateCreated));
    }
    return coll;
  }
  
  // Modify a Ticket:  
//  public void modify(Movie movie, String descr) throws Exception {
//    Connection cx = db.connect();
//    
//    int id = movie.getId();
//    int year = movie.getYear();
//    // title, year, description
//    String sql_op = String.format(
//      "update `%s` set `title`=?,`year`=?,`description`=? where `id`=?", table);
//    PreparedStatement st = cx.prepareStatement(sql_op);
//    String title = movie.getTitle();
//    
//    st.setString(1, title);
//    st.setInt(2, year);
//    st.setString(3, descr);
//    st.setInt(4, id);
//    st.executeUpdate();
//  }
  
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
  
  // Fetch a Ticket ===============================================
  public Ticket fetch(int id) throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s` where `id`=?", table);
    //System.out.println("sql_op: " + sql_op);
    
    // Testing:
//    System.out.println(sql);
    
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    if (!rs.next()) {
      return null;
    }
    return new Ticket(id,rs.getString("title"), rs.getString("status"), rs.getString("creator"), rs.getString("client"), rs.getString("dateCreated"));
  }
  
  
  
  
  
  
  // Fetch a technician for a ticket===============================================
  public Technician fetchTechnician(int id) throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s` where `id`=?", "technicians");
    //System.out.println("sql_op: " + sql_op);
    
    // Testing:
//    System.out.println(sql);
    
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    if (!rs.next()) {
      return null;
    }
    return new Technician(rs.getString("name"), rs.getInt("id"), rs.getInt("level"));
  }
  
  
  //============================================================================
  
  
  
  // Fetches all tickets for a given technician.
  public Collection<Ticket>fetchAllForTechnician(int id) throws Exception {
      Connection cx = db.connect();
 
      // Testing:
//      System.out.println("parameter for fetchAllForActor():" + id);
      
     String sql = "select movies.id, title "
                + "from movie_actors join movies join actors on "
                + "movies.id = movie_actors.movie_id and "
                + "actors.id = movie_actors.actor_id where actors.id=?";
     
    PreparedStatement st = cx.prepareStatement(sql);
    
    st.setInt(1, id);
    
    // Testing:
    System.out.println(sql);
    
    ResultSet rs = st.executeQuery();

    Collection<Ticket> coll = new LinkedHashSet<Ticket>();
    while (rs.next()) {
      int ticketID = rs.getInt("id");
      String title = rs.getString("title");
      String status = rs.getString("status");
      String creator = rs.getString("creator");
      String client = rs.getString("client");
      String dateCreated = rs.getString("dateCreated");
      
      // This constructor creats Ticket with just strings.  
//    public Ticket(int ID, String status, String aCreator, String aClient, Date dateCreated) {
//        this.ID = ID;
//        this.statusName = status;
//        this.creatorName = aCreator;
//        this.clientName = aClient;
//        this.dateCreated = dateCreated;
//    }
      
      coll.add(new Ticket(id,title, status, creator, client, dateCreated));
    }
    return coll;
  }
  
 
}
