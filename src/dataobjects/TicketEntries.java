// David Emery

package dataobjects;

import java.sql.*;
import java.util.*;

public class TicketEntries implements DBTable {
  public static final String table = "ticket_entries";
  private DB db = new DB();

  @Override
  public String getTableName() { return table; }
  
  // Add an entry to a ticket:
  public TicketEntry add(int entry_id, int ticket_id) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format(
      "insert into `%s` (`ticket_id`,`entry_id`) values (?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, ticket_id);
    st.setInt(2, entry_id);
    st.executeUpdate();

    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    return new TicketEntry(id,ticket_id,entry_id);
  }
  
  // Remove an entry from a Ticket
  public void remove(int entryId, int ticketId) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("delete from `%s` where entry_id=(?) and ticket_id=(?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, entryId);
    st.setInt(2, ticketId);
    st.executeUpdate();
  }
  
}
