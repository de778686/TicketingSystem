// David Emery


package models;

import java.sql.*;
import java.util.*;

public class Actors implements DBTable {
  public static final String table = "actors";
  private DB db = new DB();

  @Override
  public String getTableName() { return table; }
  
  public Actor add(String name) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("insert into `%s` (`name`) values (?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, name);
    st.executeUpdate();

    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    return new Actor(id,name);
  }
  
  
  
  
  public void remove(String name) throws Exception {
    Connection cx = db.connect();
    String sql;

    sql = String.format("delete from `%s` where name=(?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, name);
    st.executeUpdate();

  }
  
  public Collection<Actor> fetchAll() throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s`", table);
    //System.out.println("sql_op: " + sql_op);
    
    // Testing:
    System.out.println(sql);
    
    Statement st = cx.createStatement();

    ResultSet rs = st.executeQuery(sql);

    Collection<Actor> coll = new LinkedHashSet<Actor>();
    while (rs.next()) {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      coll.add(new Actor(id, name));
    }
    return coll;
  }
  
  
  public Actor fetch(int id) throws Exception {
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
    return new Actor(id, rs.getString("name"));
  }
  
  
  // Fetchs all actors from a given film.
  public Collection<Actor>fetchAllFromMovie(int id) throws Exception {
      Connection cx = db.connect();

      // Testing:
      System.out.println("parameter for fetchAllFromMovie():" + id);
      
     String sql = "select actors.id, name "
                + "from movie_actors join movies join actors on "
                + "movies.id = movie_actors.movie_id and "
                + "actors.id = movie_actors.actor_id where movies.id=?";
     

    PreparedStatement st = cx.prepareStatement(sql);
    
    st.setInt(1, id);
    
    // Testing:
    System.out.println(sql);
    
    ResultSet rs = st.executeQuery();

    Collection<Actor> coll = new LinkedHashSet<Actor>();
    while (rs.next()) {
      int actorId = rs.getInt("id");
      String name = rs.getString("name");
      coll.add(new Actor(actorId, name));
    }
    return coll;
  }
  
  
  
  
  
  
}
