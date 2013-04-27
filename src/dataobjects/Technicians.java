// David Emery


//   Client Class:
//    
//    //=====================  Instance Variables  ======================//
//    private String name;            //name of client
//    private String phone;           //phone number for client (primary)
//    private String altPhone;        //phone number for client (secondary)
//    private String email;           //email address of client
//    private String address;         //address of client
//    private int ID;   


package dataobjects;

import java.sql.*;
import java.util.*;

public class Technicians implements DBTable {
  public static final String table = "technicians";
  private DB db = new DB();

  @Override
  public String getTableName() { return table; }
  
  
  
  // Simple add:
  public Technician add(String name, int level) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("insert into `%s` (`name`, `level`) values (?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, name);
    st.setInt(2, level);
    st.executeUpdate();

    
    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    
    return new Technician(name, id, level);
  }
  
  
  public Technician add(String name, String phone, String altPhone, String email, String address) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("insert into `%s` (`name`) values (?) ('phone') values (?)"
            + "('altPhone') values (?) ('email') values (?) ('address') values (?) ", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, name);
    st.executeUpdate();

    
    
    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    int level = rs.getInt(2);
    
    return new Technician(name, id, level);
  }
  
  
  
  
  public void remove(String name) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("delete from `%s` where name=(?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, name);
    st.executeUpdate();

  }
  
  public Collection<Technician> fetchAll() throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s`", table);
    //System.out.println("sql_op: " + sql_op);
    
    // Testing:
//    System.out.println(sql);
    
    Statement st = cx.createStatement();

    ResultSet rs = st.executeQuery(sql);

    Collection<Technician> coll = new LinkedHashSet<Technician>();
    while (rs.next()) {
      int ID = rs.getInt("id");
      String name = rs.getString("name");
      int level = rs.getInt("level");
      coll.add(new Technician(name, ID, level));
    }
    return coll;
  }
  
  
  // Fetch a Technician with a given id:
  public Technician fetch(int id) throws Exception {
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
    return new Technician(rs.getString("name"), id, rs.getInt("level"));
  }
  
  
  // Fetchs all technicians from a given ticket.
  public Collection<Technician>fetchAllFromTicket(int id) throws Exception {
      Connection cx = db.connect();

      // Testing:
//      System.out.println("parameter for fetchAllFromTicket():" + id);
      
     String sql = "select technician.id, name "
                + "from ticket_technicians join tickets join technicians on "
                + "tickets.id = ticket_technicians.ticket_id and "
                + "technicians.id = ticket_technicians.technician_id where tickets.id=?";
    PreparedStatement st = cx.prepareStatement(sql);
    
    st.setInt(1, id);
    
    // Testing:
    System.out.println(sql);
    
    ResultSet rs = st.executeQuery();

    Collection<Technician> coll = new LinkedHashSet<Technician>();
    while (rs.next()) {
      int technicianId = rs.getInt("id");
      String name = rs.getString("name");
      int level = rs.getInt("level");
      coll.add(new Technician(name, technicianId, level));
    }
    return coll;
  }
  
  
  
  
  
  
  
  
}
