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

// clients are like Actors

package dataobjects;

import java.sql.*;
import java.util.*;

public class Clients implements DBTable {
  public static final String table = "clients";
  private DB db = new DB();

  @Override
  public String getTableName() { return table; }
  
  public Client add(String name, String phone, String altPhone, String email, String address) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("insert into `%s` (`name`, `phone`, `altPhone`, `email`, `address`) values (?,?,?,?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, name);
    st.setString(2, phone);
    st.setString(3, altPhone);
    st.setString(4, email);
    st.setString(5, address);

//    System.out.println(st);
    
    st.executeUpdate();

    // get the maximun id from mySQL table
    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    return new Client(name, phone, altPhone, email, address, id);
  }
  
  
  
  
  public void remove(String name) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("delete from `%s` where name=(?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, name);
    st.executeUpdate();

  }
  
  // Returns a collection of clients fetched from the clients table.
  public Collection<Client> fetchAll() throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s`", table);
    //System.out.println("sql_op: " + sql_op);
    
    // Testing:
//    System.out.println(sql);
    
    Statement st = cx.createStatement();

    ResultSet rs = st.executeQuery(sql);

    Collection<Client> coll = new LinkedHashSet<Client>();
    while (rs.next()) {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      String phone = rs.getString("phone");
      String altPhone = rs.getString("altPhone");
      String email = rs.getString("email");
      String address = rs.getString("address");
      coll.add(new Client(name, phone, altPhone, email, address, id));
    }
    return coll;
  }
  
  
  public Client fetch(int id) throws Exception {
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
    // public Client(String name, String phone, String altPhone, String email, String address, int ID) {
    return new Client(rs.getString("name"), rs.getString("phone"), rs.getString(""), rs.getString("name"), 
            rs.getString("name"), id);
  }
  
  
  
  
  
  
  // Fetch a client from a ticket

  public Client fetchFromTicket(int id) throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s` where `id`=?", "ticket_clients");
    //System.out.println("sql_op: " + sql_op);
    
    // Testing:
//    System.out.println(sql);
    
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    if (!rs.next()) {
      return null;
    }
    return new Client(rs.getString("name"), rs.getString("phone"), id);
  }
  
  
  
}
