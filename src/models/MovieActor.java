// David Emery

package models;

public class MovieActor {  
  private int id;
  private int movie_id;
  private int actor_id;
  
  public MovieActor(int id, int movie_id, int actor_id) {
    this.id = id;
    this.movie_id = movie_id;
    this.actor_id = actor_id;
  }
  
  public int getId() { return id; }
  public int getActorId() { return actor_id; }
  public int getMovieId() { return movie_id; }
  
  public void setActorId(int actor_id) { this.actor_id = actor_id; }
  public void setMovieId(int movie_id) { this.movie_id = movie_id; }
  
  /* We should never need equals and hashCode for these objects
   * The only need for toString is testing.
   */

  @Override
  public String toString() {
    return "(" + movie_id  + ", " + actor_id + ")";
  }
}
