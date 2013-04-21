// David Emery

package dataobjects;

import java.util.Properties;

class Util {
  static Properties loadFile(String file) throws java.io.IOException {
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    java.io.InputStream input = loader.getResourceAsStream(file);
    if (input == null) {
      throw new java.io.IOException("cannot read properties file: " + file);
    }
    Properties props = new Properties();
    props.load(input);
    input.close();
    return props;
  }  
}
