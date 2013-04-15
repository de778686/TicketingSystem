// David Emery

package models;

import java.sql.*;
import java.util.*;

public class Movies implements DBTable {
  public static final String table = "movies";
  private DB db = new DB();

  @Override
  public String getTableName() { return table; }
  
  public Movie add(String title, int year, String description) throws Exception {
    Connection cx = db.connect();
    String sql;
   
    sql = String.format(
      "insert into `%s` (`title`,`year`,`description`) values (?,?,?)", table);
    PreparedStatement st = cx.prepareStatement(sql);
    st.setString(1, title);
    st.setInt(2, year);
    st.setString(3, description);
    st.executeUpdate();

    sql = String.format("select max(`id`) from `%s`",table);
    Statement st1 = cx.createStatement();
    ResultSet rs = st1.executeQuery(sql);
    rs.next();
    int id = rs.getInt(1);
    return new Movie(id, title, year, description);
  }
  
  
  public Collection<Movie> fetchAll() throws Exception {
    Connection cx = db.connect();
    String sql = String.format("select * from `%s` order by title", table);
    //System.out.println("sql_op: " + sql_op);
    Statement st = cx.createStatement();

    ResultSet rs = st.executeQuery(sql);

    Collection<Movie> coll = new LinkedHashSet<Movie>();
    while (rs.next()) {
      int id = rs.getInt("id");
      String title = rs.getString("title");
      int year = rs.getInt("year");
      String descr = rs.getString("description");
      coll.add(new Movie(id, title, year, descr));
    }
    return coll;
  }
  
  public void modify(Movie movie, String descr) throws Exception {
    Connection cx = db.connect();
    
    int id = movie.getId();
    int year = movie.getYear();
    // title, year, description
    String sql_op = String.format(
      "update `%s` set `title`=?,`year`=?,`description`=? where `id`=?", table);
    PreparedStatement st = cx.prepareStatement(sql_op);
    String title = movie.getTitle();
    
    st.setString(1, title);
    st.setInt(2, year);
    st.setString(3, descr);
    st.setInt(4, id);
    st.executeUpdate();
  }
  
  // Setting the description initially to empty
  public Movie add(String title, int year) throws Exception {
    return add(title, year, "");
  }
  
  
  // Fetch ===============================================
  public Movie fetch(int id) throws Exception {
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
    return new Movie(id, rs.getString("title"), rs.getInt("year"), rs.getString("description"));
  }
  
  
  // Fetches all actors from a given film.
  public Collection<Movie>fetchAllForActor(int id) throws Exception {
      Connection cx = db.connect();
 
      // Testing:
      System.out.println("parameter for fetchAllForActor():" + id);
      
     String sql = "select movies.id, title "
                + "from movie_actors join movies join actors on "
                + "movies.id = movie_actors.movie_id and "
                + "actors.id = movie_actors.actor_id where actors.id=?";
     
    PreparedStatement st = cx.prepareStatement(sql);
    
    st.setInt(1, id);
    
    // Testing:
    System.out.println(sql);
    
    ResultSet rs = st.executeQuery();

    Collection<Movie> coll = new LinkedHashSet<Movie>();
    while (rs.next()) {
      int movieId = rs.getInt("id");
      String title = rs.getString("title");
      int year = rs.getInt("year");
      String descr = rs.getString("description");
      coll.add(new Movie(movieId, title, year, descr));
    }
    return coll;
  }
  
 
}
