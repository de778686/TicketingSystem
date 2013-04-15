// David Emery

package models;

import java.sql.*;
import java.util.*;

public class MovieActors implements DBTable {
  public static final String table = "movie_actors";
  private DB db = new DB();

  @Override
  public String getTableName() { return table; }
  
  public MovieActor add(int movie_id, int actor_id) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format(
      "insert into `%s` (`movie_id`,`actor_id`) values (?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, movie_id);
    st.setInt(2, actor_id);
    st.executeUpdate();

    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    return new MovieActor(id,movie_id,actor_id);
  }
  
  
  
  
  public void remove(int actorId, int movieId) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("delete from `%s` where actor_id=(?) and movie_id=(?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setInt(1, actorId);
    st.setInt(2, movieId);
    st.executeUpdate();
  }
  
  
  
  
}
