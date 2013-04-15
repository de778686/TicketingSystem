// David Emery

package models;

public class Movie {  
  private int id;
  private String title;
  private int year;
  private String description;

  public Movie() { }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Movie)) {
      return false;
    }
    return id == ((Movie)obj).id;
  }

  @Override
  public int hashCode() { return id; }

  public Movie(int id, String title, int year, String description) {
    this.title = title;
    this.year = year;
    this.description = description;
    this.id = id;
  }

  public int getId() { return id; }
  public String getTitle() { return title; }
  public int getYear() { return year; }
  public String getDescription() { return description; }

  public void setId(int id) { this.id = id; }
  public void setTitle(String title) { this.title = title; }
  public void setYear(int year) { this.year = year; }
  public void setDescription(String description) { this.description = description; }

  @Override
  public String toString() {
    return title + " (" + year + ")";
  }
}
