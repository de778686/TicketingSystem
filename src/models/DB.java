// David Emery


package models;

import java.sql.*;
import java.util.Properties;

public class DB {

  public enum Model { mysql, sqlite };

  private static Model model = Model.mysql; // Model.mysql or Model.sqlite
  
  private static String props_file 
    = "models" + java.io.File.separator + model + ".properties";
  
  private static Connection cx = null;

  public void exec(String sql_op) throws Exception {
    connect();
    Statement st = cx.createStatement();
    st.executeUpdate(sql_op);
  }

  public static Model getModel() {
    return model;
  }

  Connection connect() throws Exception {
    if (cx == null || cx.isClosed()) {
      Properties props = Util.loadFile(props_file);

      // must provide url
      String url = props.getProperty("url");
      if (url == null) {
        throw new Exception("no such property: url");
      }

      // username and password are optional, depending on database
      String username = props.getProperty("username");
      String password = props.getProperty("password");

      String driver = props.getProperty("driver");
      if (driver != null) {
        Class.forName(driver); // load driver if necessary
      }
      cx = DriverManager.getConnection(url, username, password);
    }
    return cx;
  }
}
