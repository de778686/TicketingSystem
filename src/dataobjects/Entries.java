// David Emery

//=====================  Instance Variables  ======================//
//    private int ID;                 //id of entry used for association in the database
//    private Date creationDate;      //date entry was created
//    private Technician creator;     //creator of the entry
//    private String text;            //text of the entry

package dataobjects;

import java.sql.*;
import java.util.*;

public class Entries implements DBTable {

  public static final String table = "entries";
  private DB db = new DB();
  
  @Override
  public String getTableName() { return table; }
  
  // Add method:
  public Entry add(String creationDate, String creatorName, String text) throws Exception {
    Connection cx = db.connect();
    String sql;
   
    sql = String.format(
      "insert into `%s` (`creationDate`,`creator`, `text`) values (?,?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, creationDate);
    st.setString(2, creatorName);
    st.setString(3, text);
    st.executeUpdate();

    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    
    return new Entry(id, creationDate, creatorName, text);
  } // End of add method
  
  
  
  // Fetchs all entries from a given ticket.
  public List<Entry>fetchAllFromTicket(int ticketID) throws Exception {
      Connection cx = db.connect();

     String sql = "select entries.id, entries.creationDate, entries.creator, entries.text "
                + "from ticket_entries join tickets join entries on "
                + "tickets.id = ticket_entries.ticket_id and "
                + "entries.id = ticket_entries.entry_id where tickets.id=?";
    PreparedStatement st = cx.prepareStatement(sql);
    
    st.setInt(1, ticketID);
    
    ResultSet rs = st.executeQuery();

    ArrayList<Entry> list = new ArrayList<>();
    while (rs.next()) {
      
      int entryId = rs.getInt("id");
      String creationDate = rs.getString("creationDate");
      //String modifiedDate = rs.getString("modifiedDate");
      String creator = rs.getString("creator");
      //String editor = rs.getString("editor");
      String text = rs.getString("text");
      
      list.add(new Entry(entryId, creationDate, creator, text));
    }
    return list;
  }
  
  
} // End of class
