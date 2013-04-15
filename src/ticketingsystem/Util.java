package ticketingsystem;

import java.io.*;

public class Util {
  public static String readTextFile(String filename) throws IOException {
    return readTextFile(new File(filename));
  }
  public static String readTextFile(File file) throws IOException {
    FileInputStream istr = new FileInputStream(file);
    InputStreamReader irdr = new InputStreamReader(istr);
    int size = (int) file.length();  // get the file size (in bytes)
    char[] data = new char[size];    // allocate char array of size
    irdr.read(data, 0, size);        // read into char array
    irdr.close();
    return new String(data);
  }
}
