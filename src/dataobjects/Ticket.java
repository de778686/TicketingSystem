
package dataobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Ticket models all the information needed for a help ticket
 * @author Quinn
 */
public class Ticket {
    
    //=====================  Instance Variables  ======================//
    private int ID;                     //id of ticket used for associations
                                        //in the database
    private Status status;              //status of the ticket (ie open, close, etc.)
    private Technician creator;         //technician that created the ticket
    private Client client;              //client of the ticket (ie customer, employee)
    private Date dateCreated;           //date ticket was created
    private Date dateModified;          //date of last modification to the ticket
    private List<Entry> entries;        //list of entries associated with the ticket
    
    // String alternatives: 
    private String creatorName;
    private String clientName;
    private String statusName;
    private String dateCreatedString;
    
    //========================  Constructors  =========================//

    /**
     * This constructor creates a new Ticket object with fields initialized
     * to the values passed as parameters.  date modified and entries are
     * not necessary for this constructor
     * @param ID
     * @param status
     * @param creator
     * @param client 
     * @param dateCreated
     */
    public Ticket(int ID, Status status, Technician creator, Client client, Date dateCreated) {
        this.ID = ID;
        this.status = status;
        this.creator = creator;
        this.client = client;
        this.dateCreated = dateCreated;
        this.dateModified = null;
        this.entries = new ArrayList<>();
    }
    
    
    
    
    // This constructor creats Ticket with just strings.  
    public Ticket(int ID, String status, String aCreator, String aClient, String dateCreated) {
        this.ID = ID;
        this.statusName = status;
        this.creatorName = aCreator;
        this.clientName = aClient;
        this.dateCreatedString = dateCreated;
        this.dateModified = null;
    }
    
    
    
    

    /**
     * This constructor creates a new Ticket object with fields initialized
     * to the values passed as parameters.
     * @param ID
     * @param status
     * @param creator
     * @param client
     * @param dateCreated
     * @param dateModified
     * @param entries 
     */
    public Ticket(int ID, Status status, Technician creator, Client client, 
            Date dateCreated, Date dateModified, List<Entry> entries) {
        this.ID = ID;
        this.status = status;
        this.creator = creator;
        this.client = client;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.entries = entries;
    }
    
    
    //========================  Getters  =========================//

    /**
     * return id of the ticket
     * @return 
     */
    public int getID() {
        return ID;
    }

    /**
     * return status of ticket
     * @return 
     */
    public Status getStatus() {
        return status;
    }

    /**
     * return creator of the ticket
     * @return 
     */
    public Technician getCreator() {
        return creator;
    }

    /**
     * return client associated with the ticket
     * @return 
     */
    public Client getClient() {
        return client;
    }

    /**
     * return date ticket was created
     * @return 
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * return date ticket was last modified
     * @return null if there is no modified date for the object
     */
    public Date getDateModified() {
        return dateModified;
    }

    /**
     * return list of entries associated with the ticket
     * @return 
     */
    public List<Entry> getEntries() {
        return entries;
    }
    
    /**
     * Iterator() returns an iterator over the ticket's entries
     * @return 
     */
    public Iterator<Entry> iterator(){
        return entries.iterator();
    }
    
    
    //========================  Setters  =========================//

    /**
     * Change the status of the ticket.
     * @param status 
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * set the date modified of the ticket
     * @param dateModified 
     */
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
    
    //========================  Mutators  =========================//
    /**
     * addEntry adds a new entry to the ticket
     * @param entry
     * @return true if add was successful
     */
    public boolean addEntry(Entry entry){
        return entries.add(entry);
    }
    
    /**
     * removeEntry removes an entry from the ticket
     * @param entry
     * @return true if removal was successful
     */
    public boolean removeEntry(Entry entry){
        return entries.remove(entry);
    }
    
    
    //=======================  Overrides  ======================//

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.ID;
        hash = 23 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 23 * hash + Objects.hashCode(this.creator);
        hash = 23 * hash + Objects.hashCode(this.client);
        hash = 23 * hash + Objects.hashCode(this.dateCreated);
        hash = 23 * hash + Objects.hashCode(this.dateModified);
        hash = 23 * hash + Objects.hashCode(this.entries);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ID=" + ID + ", status=" + status 
                + ", creator=" + creator + ", client=" + client 
                + ", dateCreated=" + dateCreated + ", dateModified=" 
                + dateModified + ", entries=" + entries + '}';
    }
    
    
    
    
}
