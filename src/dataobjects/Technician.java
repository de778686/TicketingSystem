package dataobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Technician is the base data class representing individuals open, close, edit,
 * and manage tickets in the system
 * @author Quinn
 */
public class Technician {
 
    //=====================  Instance Variables  ======================//
    private String name;    //name of the technician
    private int ID;         //id used for associations in the database
    private int level;      //tech level
    private List<Ticket> associatedTickets; //list of tickets associated with
                                            //this technician

    
    //====================  Constructors  =====================//
    
    /**
     * This constructor creates a new Technician object with the name,
     * id, and level passed as parameters.
     * @param name
     * @param ID
     * @param level 
     */
    public Technician(String name, int ID, int level) {
        this.name = name;
        this.ID = ID;
        this.level = level;
        this.associatedTickets = new ArrayList<>();
    }

    /**
     * This constructor creates a new Technician object with the name,
     * id, level, and associated tickets passed as a parameter
     * @param name
     * @param ID
     * @param level
     * @param associatedTickets 
     */
    public Technician(String name, int ID, int level, List<Ticket> associatedTickets) {
        this.name = name;
        this.ID = ID;
        this.level = level;
        this.associatedTickets = associatedTickets;
    }
    
    
    //====================  Getters  =======================//

    /**
     * Returns name of the technician
     * @return name as a String
     */
    public String getName() {
        return name;
    }

    /*
     * Returns ID of the technician
     * @return id as int
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns technician level
     * @return level as int
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns reference to list of tickets associated with this technician
     * @return List of associated tickets (by reference)
     */
    public List<Ticket> getAssociatedTickets() {
        return associatedTickets;
    }
    
    //======================  Setters  =======================//

    /**
     * setLevel sets the technicians level field.  This modification is allowed
     * so that a technician can be promoted or demoted
     * @param level 
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
    //=====================  Mutators  ========================//
    /**
     * addTicket adds a ticket to the list of tickets associated with
     * the technician
     * @param ticket ticket to associate with technician
     * @return true if the add was successful
     */
    public boolean addTicket(Ticket ticket){
        return associatedTickets.add(ticket);
    }

    /**
     * removeTicket removes a ticket from the list of tickets associated
     * with the technician
     * @param ticket
     * @return true if removal was successful
     */
    public boolean removeTicket(Ticket ticket){
        return associatedTickets.remove(ticket);
    }
    
    
    //=====================  Overrides  =======================//

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + this.ID;
        hash = 53 * hash + this.level;
        hash = 53 * hash + Objects.hashCode(this.associatedTickets);
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
        final Technician other = (Technician) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Technician{" + "name=" + name + ", ID=" + ID
                + ", level=" + level + ", associatedTickets=" 
                + associatedTickets + '}';
    }
    
    
    
}
