
package dataobjects;

import java.util.Objects;

/**
 * the Client class models the person for whom a ticket has been created.
 * For example, a private help desk may create tickets for home users' computers
 * in order to track progress.  In this case, the owner of the computer would
 * be the client.
 * @author Quinn
 */
public class Client {
    
    //=====================  Instance Variables  ======================//
    private String name;            //name of client
    private String phone;           //phone number for client (primary)
    private String altPhone;        //phone number for client (secondary)
    private String email;           //email address of client
    private String address;         //address of client
    private int ID;                 //id used for associations in the database
    
    //========================  Constructors  =========================//

    /**
     * this construct creates a new Client object with only the necessary fields
     * initialized based on parameters passed in.  All other fields are initialized
     * to default values.
     * @param name
     * @param phone
     * @param ID 
     */
    public Client(String name, String phone, int ID) {
        this.name = name;
        this.phone = phone;
        this.altPhone = null;
        this.email = null;
        this.address = null;
        this.ID = ID;
    }

    /**
     * this constructor creates a new Client object and initializes all its fields
     * to the values passed in as parameters
     * @param name
     * @param phone
     * @param altPhone
     * @param email
     * @param address
     * @param ID 
     */
    public Client(String name, String phone, String altPhone, String email, String address, int ID) {
        this.name = name;
        this.phone = phone;
        this.altPhone = altPhone;
        this.email = email;
        this.address = address;
        this.ID = ID;
    }
    
    
    //========================  Getters  =========================//
    /**
     * return name of client
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * return primary phone number for client as string
     * @return 
     */
    public String getPhone() {
        return phone;
    }

    /**
     * return alternative phone number for client as string
     * @return null if there is no alternative phone number associated with
     *         the client
     */
    public String getAltPhone() {
        return altPhone;
    }

    /**
     * return email of client as string
     * @return null if there is no email address associated with the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * return address of client as a string
     * @return null if there is no address associated with the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * return ID of client as a string
     * @return 
     */
    public int getID() {
        return ID;
    }
    
    
    //========================  Setters  =========================//
    /**
     * set primary phone number for client
     * @param phone 
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * set alternative phone number of client
     * @param altPhone 
     */
    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    /**
     * set email of client
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set address of client
     * @param address 
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    
    //==================  Overrides ===================//

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.phone);
        hash = 61 * hash + Objects.hashCode(this.altPhone);
        hash = 61 * hash + Objects.hashCode(this.email);
        hash = 61 * hash + Objects.hashCode(this.address);
        hash = 61 * hash + this.ID;
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
        final Client other = (Client) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "name=" + name + ", phone=" + phone 
                + ", altPhone=" + altPhone + ", email=" + email 
                + ", address=" + address + ", ID=" + ID + '}';
    }
    
    
    
    
}
