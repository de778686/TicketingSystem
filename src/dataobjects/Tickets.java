// David Emery


//private Status status;              //status of the ticket (ie open, close, etc.)
//    private Technician creator;         //technician that created the ticket
//    private Client client;              //client of the ticket (ie customer, employee)
//    private Date dateCreated;           //date ticket was created
//    private Date dateModified;          //date of last modification to the ticket
//    private List<Entry> entries;        //list of entries associated with the ticket


package dataobjects;

import java.sql.*;
import java.util.*;

public class Tickets implements DBTable {
  public static final String table = "tickets";
  private DB db = new DB();
  
  // collect Clients:
  String clients = new String();  
  Collection<String> clientColl = new LinkedHashSet<String>();

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
  public Ticket add(String title, String status, String creator,
          String client, String dateCreated) throws Exception {
    Connection cx = db.connect();
    String sql;
   
    sql = String.format(
      "insert into `%s` (`title`,`status`,`creator`, `client`, `dateCreated`, `dateModified`) values (?,?,?,?,?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1,title);
    st.setString(2, status);
    st.setString(3, creator);
    st.setString(4, client);
    st.setString(5, dateCreated);
    st.setString(6, "");
    
    st.executeUpdate();

    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    return new Ticket(id, title, status.toString(), creator, client, dateCreated); // add method
  }
  
  public void remove(int ID) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("delete from `%s` where id=(?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, ID);
    st.executeUpdate();
  }
  
  
    public Ticket add(int ID, String title,Status status, Technician creator,
          String client, java.sql.Date dateCreated, java.sql.Date dateModified, List<Entry> entries) throws Exception {
    Connection cx = db.connect();
    String sql;
   
    sql = String.format(
      "insert into `%s` (`title`,`year`,`description`) values (?,?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, status.toString());
    st.setString(2, creator.getName());
    //@David Emery... the line below used to be "client.getName"
    st.setString(3, client.toString());
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
      
      String clientName = rs.getString("client");
      
      String dateCreated = rs.getString("dateCreated");
      coll.add(new Ticket(id, title, status, creatorName, clientName, dateCreated));
    }
    return coll;
  }
  
  
  public Ticket add(int ID, String status, Technician creator, String client, java.sql.Date dateCreated) throws Exception {
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

  //============================================================================
  // Fetches all tickets for a given technician.
  // Takes technicians id as a parameter
  public Collection<Ticket>fetchAllForTechnician(int id) throws Exception {
      Connection cx = db.connect();
 
      // Testing:
//      System.out.println("parameter for fetchAllFortechnician():" + id);
      
     String sql = "select * "
                + "from ticket_technicians join tickets join technicians on "
                + "tickets.id = ticket_technicians.ticket_id and "
                + "technicians.id = ticket_technicians.technician_id where technicians.id=?";
     
    PreparedStatement st = cx.prepareStatement(sql);
    
    st.setInt(1, id);
    
    // Testing:
//    System.out.println(sql);
    
    ResultSet rs = st.executeQuery();

    Collection<Ticket> coll = new LinkedHashSet<Ticket>();
    while (rs.next()) {
      int ticketID = rs.getInt("ticket_id");
      String title = rs.getString("title");
      String status = rs.getString("status");
      String creator = rs.getString("creator");
      String client = rs.getString("client");
      String dateCreated = rs.getString("dateCreated");
      String dateModified = rs.getString("dateModified");
 
      coll.add(new Ticket(ticketID, title, status, creator, client, dateCreated));
    }
    return coll;
  }
  
 
}
