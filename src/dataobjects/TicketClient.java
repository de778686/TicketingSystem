// David Emery

// Connects Tickets to Clients

package dataobjects;


public class TicketClient {  
  private int id;
  private int ticket_id;
  private int client_id;
  
  public TicketClient(int id, int ticket_id, int actor_id) {
    this.id = id;
    this.ticket_id = ticket_id;
    this.client_id = actor_id;
  }
  
  public int getId() { return id; }
  public int getClientId() { return client_id; }
  public int getTicketId() { return ticket_id; }
  
  public void setClientId(int actor_id) { this.client_id = actor_id; }
  public void setTicketId(int ticket_id) { this.ticket_id = ticket_id; }
  
  /* We should never need equals and hashCode for these objects
   * The only need for toString is testing.
   */

  @Override
  public String toString() {
    return "(" + ticket_id  + ", " + client_id + ")";
  }
}
