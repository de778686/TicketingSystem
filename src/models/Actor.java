// David Emery

package models;

public class Actor {  
  private int id;
  private String name;
 
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Actor)) {
      return false;
    }
    return id == ((Actor)obj).id;
  }

  @Override
  public int hashCode() { return id; }

  public Actor(int id, String name) {
    this.name = name;
    this.id = id;
  }

  public int getId() { return id; }
  public String getName() { return name; }
  
  //public void setName(String name) { name = this.name; }

  @Override
  public String toString() {
    return name;
  }
}
