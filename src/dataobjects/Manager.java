
package dataobjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manager is a subclass of Technician and represents a technician with management
 * functionality.
 * @author Quinn
 */
public class Manager extends Technician{
    
    //=====================  Instance Variables  ======================//
    private List<Technician> supervisees;   //list of technicians supervised
                                            //by this manager
    
    //========================  Constructors  =========================//
    /**
     * This constructor creates a new Manager object without any associated tickets or
     * supervisees.  The name, id, and level are set according to the values
     * passed in as parameters
     * @param name
     * @param ID
     * @param level 
     */
    public Manager(String name, int ID, int level) {
        super(name, ID, level);
        this.supervisees = new ArrayList<>();
    }

    /**
     * This constructor creates a new Manager object without any associated
     * tickets but with a set of associated
     * supervisees.  All values are set according to the values passed in
     * as parameters
     * @param supervisees
     * @param name
     * @param ID
     * @param level 
     */
    public Manager(List<Technician> supervisees, String name, int ID, int level) {
        super(name, ID, level);
        this.supervisees = supervisees;
    }

    /**
     * This constructor creates a new Manager object with a set of associated
     * tickets but without any supervisees
     * @param name
     * @param ID
     * @param level
     * @param associatedTickets 
     */
    public Manager(String name, int ID, int level, List<Ticket> associatedTickets) {
        super(name, ID, level, associatedTickets);
        this.supervisees = new ArrayList<>();
    }

    /**
     * This constructor creates a new Manager object with a set of associated
     * tickets and a set of associated supervisees.  All values are set
     * based on the parameters passed in.
     * @param supervisees
     * @param name
     * @param ID
     * @param level
     * @param associatedTickets 
     */
    public Manager(List<Technician> supervisees, String name, int ID, int level, List<Ticket> associatedTickets) {
        super(name, ID, level, associatedTickets);
        this.supervisees = supervisees;
    }

    //========================  Getters  =========================//
    /**
     * getSupervisees returns a reference to the list of supervisees associated
     * with the manager
     * @return
     */
    public List<Technician> getSupervisees() {
        return supervisees;
    }

    /**
     * getSuperviseeTickets returns a list containing all tickets associated
     * with the supervisees of the manager but NOT the manager him/herself
     * @return List of tickets associated with supervisees
     */
    public List<Ticket> getSuperviseeTickets(){
        List<Ticket> superviseeTickets = new ArrayList<>();
        for(Technician t : supervisees){
            superviseeTickets.addAll(t.getAssociatedTickets());
        }
        return superviseeTickets;
    }
    
    /**
     * iterator() returns an iterator over the supervisees associated with
     * the manager object
     * @return 
     */
    public Iterator<Technician> iterator(){
        return supervisees.iterator();
    }
    
    //========================  Mutators  =========================//
    /**
     * addSupervisee adds a technician to the list of technicians supervised
     * by the manager
     * @param supervisee
     * @return true if add was successful
     */
    public boolean addSupervisee(Technician supervisee){
        return supervisees.add(supervisee);
    }
    
    /**
     * removeSupervisee removes a technician from the list of technicians
     * supervised by the manager
     * @param supervisee
     * @return true if removal was successful
     */
    public boolean removeSupervisee(Technician supervisee){
        return supervisees.remove(supervisee);
    }
    
    //=========================  Overrides  =======================//
    
    //no need to override since this class will inherit toString(), equals(),
    //and hashCode() from the Technician class
    
    
    
}
