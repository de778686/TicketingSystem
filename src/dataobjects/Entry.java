// Changed creator and dates from type Technician to type String


package dataobjects;

import java.util.Date;
import java.util.Objects;

/**
 * Entry models a single entry in a ticket
 * @author Quinn
 */
public class Entry {
    
    //=====================  Instance Variables  ======================//
    private int ID;                 //id of entry used for association in the database
    private String creationDate;      //date entry was created
    private String modifiedDate;      //date entry was last modified
    private String creator;     //creator of the entry
    private Technician editor;      //last editor of the entry
    private String text;            //text of the entry
    
    //========================  Constructors  =========================//

    /**
     * this constructor creates a new Entry with the ID, creator, text, and creation date
     * passed in as parameters. Other fields are initialized to default values.
     * @param ID
     * @param creator
     * @param text 
     */
    public Entry(int ID, String creationDate, String creator, String text) {
        this.ID = ID;
        this.creationDate = creationDate;
        this.modifiedDate = null;
        this.creator = creator;
        this.editor = null;
        this.text = text;
    }

    /**
     * This constructor creates a new Entry with all fields initialized to the
     * values passed in as parameters
     * @param ID
     * @param creationDate
     * @param modifiedDate
     * @param creator
     * @param editor
     * @param text 
     */
    public Entry(int ID, String creationDate, String modifiedDate, String creator, Technician editor, String text) {
        this.ID = ID;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
        this.creator = creator;
        this.editor = editor;
        this.text = text;
    }
    
    
    //========================  Getters  =========================//

    /**
     * Get id of entry
     * @return 
     */
    public int getID() {
        return ID;
    }

    /**
     * Get date entry was created
     * @return 
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * get date entry was last modified
     * @return null if the post has no modification date
     */
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * get technician that created the entry
     * @return 
     */
    public String getCreator() {
        return creator;
    }

    /**
     * get last technician to edit the entry
     * @return null if there is no editor associated with the post
     */
    public Technician getEditor() {
        return editor;
    }

    /**
     * get text associated with the entry
     * @return 
     */
    public String getText() {
        return text;
    }
    
    //========================  Setters  =========================//

    /**
     * Set date the entry was last modified.
     * @param modifiedDate
     * @return date of previous modification
     */
    public String setModifiedDate(String modifiedDate) {
        String oldDate = modifiedDate;
        this.modifiedDate = modifiedDate;
        return oldDate;
    }

    /**
     * Set last editor to edit the entry
     * @param editor 
     */
    public void setEditor(Technician editor) {
        this.editor = editor;
    }

    /**
     * Set text of the entry
     * @param text 
     */
    public void setText(String text) {
        this.text = text;
    }
    
    
    //======================  Overrides =========================//

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.ID;
        hash = 71 * hash + Objects.hashCode(this.creationDate);
        hash = 71 * hash + Objects.hashCode(this.modifiedDate);
        hash = 71 * hash + Objects.hashCode(this.creator);
        hash = 71 * hash + Objects.hashCode(this.editor);
        hash = 71 * hash + Objects.hashCode(this.text);
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
        final Entry other = (Entry) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entry{" + "ID=" + ID + ", creationDate=" 
                + creationDate + ", modifiedDate=" + modifiedDate 
                + ", creator=" + creator + ", editor=" + editor 
                + ", text=" + text + '}';
    }
    
    
    
}
