// David Emery

package dataobjects;

import java.sql.*;
import java.util.*;

public class Technicians implements DBTable {
  public static final String table = "technicians";
  private DB db = new DB();

  @Override
  public String getTableName() { return table; }
  
  
  
  // Simple add:
  public Technician add(String username, String password, String name, int level) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("insert into `%s` (`username`, `password`, `name`, `level`) values (?,?,?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, username);
    st.setString(2, password);
    st.setString(3, name);
    st.setInt(4, level);
    st.executeUpdate();

    
    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    //Review this to make sure that the auto add for ID is working. Need to be max + 1 (David & David)
    int id = rs.getInt(1);
    
    return new Technician(id, username, password, name, level);
  }

  public void remove(String username) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("delete from `%s` where username=(?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, username);
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
      int id = rs.getInt("id");
      String username = rs.getString("username");
      String password = rs.getString("password");
      String name = rs.getString("name");
      int level = rs.getInt("level");
      coll.add(new Technician(id, username, password, name, level));
    }
    return coll;
  }
  
  //This needs to be tested: (David & David)
  // Fetch a Technician with a given id:
  public Technician fetch(int id) throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s` where `id`=?", table);
    //System.out.println("sql_op: " + sql_op);
    
    // Testing:
    //System.out.println(sql);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    if (!rs.next()) {
      return null;
    }
    return new Technician(id, rs.getString("username"), rs.getString("password"), rs.getString("name"),  rs.getInt("level"));
  }
  
  //This needs to be tested: (David & David)
  // Fetch a Technician with a given id:
  public Technician fetchByUsername(String username) throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s` where `username`=?", table);
    //System.out.println("sql_op: " + sql_op);
    
    // Testing:selec
    //System.out.println(sql);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, username);
    ResultSet rs = st.executeQuery();
    if (!rs.next()) {
      return null;
    }
    return new Technician(rs.getInt("id"), username, rs.getString("password"), rs.getString("name"),  rs.getInt("level"));
  }
  
  // Fetchs all technicians from a given ticket.
  public Collection<Technician>fetchAllFromTicket(int id) throws Exception {
      Connection cx = db.connect();

      // Testing:
//      System.out.println("parameter for fetchAllFromTicket():" + id);
      
     String sql = "select * "
                + "from ticket_technicians join tickets join technicians on "
                + "tickets.id = ticket_technicians.ticket_id and "
                + "technicians.id = ticket_technicians.technician_id where tickets.id=?";
    PreparedStatement st = cx.prepareStatement(sql);
    
    st.setInt(1, id);
    
    // Testing:
    //System.out.println(sql);
    
    ResultSet rs = st.executeQuery();

    Collection<Technician> coll = new LinkedHashSet<Technician>();
    while (rs.next()) {
      int technicianId = rs.getInt("id");
      String username = rs.getString("username");
      String password = rs.getString("password");
      String name = rs.getString("name");
      int level = rs.getInt("level");
      coll.add(new Technician(technicianId, username, password, name, level));
    }
    return coll;
  }
  
  
  
  
  
  
  
  
}
