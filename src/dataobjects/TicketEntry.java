package dataobjects;

// David Emery

public class TicketEntry {  
  private int id;
  private int ticket_id;
  private int entry_id;
  
  public TicketEntry(int id, int ticket_id, int technican_id) {
    this.id = id;
    this.ticket_id = ticket_id;
    this.entry_id = entry_id;
  }
  
  public int getId() { return id; }
  public int getEntryId() { return entry_id; }
  public int getTicketId() { return ticket_id; }
  
  public void setEntryId(int entry_id) { this.entry_id = entry_id; }
  public void setTicketId(int ticket_id) { this.ticket_id = ticket_id; }
  
  /* We should never need equals and hashCode for these objects
   * The only need for toString is testing.
   */

  @Override
  public String toString() {
    return "(" + ticket_id  + ", " + entry_id + ")";
  }
}
