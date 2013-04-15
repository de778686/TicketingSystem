// David Emery

package dataobjects;

import models.*;
import java.sql.*;
import java.util.*;

public class TicketClients implements DBTable {
  public static final String table = "ticket_clients";
  private DB db = new DB();

  @Override
  public String getTableName() { return table; }
  
  public TicketClient add(int ticket_id, int client_id) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format(
      "insert into `%s` (`ticket_id`,`client_id`) values (?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, ticket_id);
    st.setInt(2, client_id);
    st.executeUpdate();

    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    return new TicketClient(id,ticket_id,client_id);
  }
  
  
  
  
  public void remove(int clientId, int ticketId) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("delete from `%s` where client_id=(?) and ticket_id=(?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, clientId);
    st.setInt(2, ticketId);
    st.executeUpdate();
  }
  
  
  
  
}
