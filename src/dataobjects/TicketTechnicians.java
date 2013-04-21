// David Emery

package dataobjects;

import java.sql.*;
import java.util.*;

public class TicketTechnicians implements DBTable {
  public static final String table = "ticket_technicians";
  private DB db = new DB();

  @Override
  public String getTableName() { return table; }
  
  // add a technician to a ticket:
  public TicketTechnician add(int technician_id, int ticket_id) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format(
      "insert into `%s` (`ticket_id`,`technician_id`) values (?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, ticket_id);
    st.setInt(2, technician_id);
    st.executeUpdate();

    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    return new TicketTechnician(id,ticket_id,technician_id);
  }
  
  // Remove a Technician from a Ticket
  public void remove(int technicianId, int ticketId) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("delete from `%s` where technician_id=(?) and ticket_id=(?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, technicianId);
    st.setInt(2, ticketId);
    st.executeUpdate();
  }
  
  
  
  
}
