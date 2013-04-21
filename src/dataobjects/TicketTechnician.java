// David Emery

package dataobjects;


public class TicketTechnician {  
  private int id;
  private int ticket_id;
  private int technician_id;
  
  public TicketTechnician(int id, int ticket_id, int actor_id) {
    this.id = id;
    this.ticket_id = ticket_id;
    this.technician_id = actor_id;
  }
  
  public int getId() { return id; }
  public int getTechnicianId() { return technician_id; }
  public int getTicketId() { return ticket_id; }
  
  public void setTechnicianId(int actor_id) { this.technician_id = actor_id; }
  public void setTicketId(int ticket_id) { this.ticket_id = ticket_id; }
  
  /* We should never need equals and hashCode for these objects
   * The only need for toString is testing.
   */

  @Override
  public String toString() {
    return "(" + ticket_id  + ", " + technician_id + ")";
  }
}
