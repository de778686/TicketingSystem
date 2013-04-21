// David Emery 

package ticketingsystem;


import dataobjects.*;
import dataobjects.TicketClients;
import dataobjects.TicketTechnicians;
import dataobjects.Tickets;
import java.util.Collection;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("CallToThreadDumpStack")
public class TicketingSystem {
//  private TheFrame frame = new TheFrame();
  //private CheckoutDialog checkoutDialog = new CheckoutDialog(frame, true);
//  private AddTicketDialog addTicketDialog = new AddTicketDialog(frame, true);
//  private ModifyTicketDialog modifyTicketDialog = new ModifyTicketDialog(frame, true);
  
  
//  private AddActorDialog addActorDialog = new AddActorDialog(frame, true);
//  private RemoveActorDialog removeActorDialog = new RemoveActorDialog(frame, true);
//  private AddActorToTicketDialog addActorToTicketDialog = new AddActorToTicketDialog(frame,true);
  
  
  private static Clients clients = new Clients();
  private static Tickets tickets = new Tickets();
  
  private static TicketClients ticketClients = new TicketClients();
  private static TicketTechnicians ticketTechnicians = new TicketTechnicians();
  
//  private DefaultListModel actorsModel = new DefaultListModel();
//  private DefaultListModel ticketsModel = new DefaultListModel();
//  //private DefaultListModel addTicketsModel = new DefaultListModel();
//  private DefaultListModel ticketActorsModel = new DefaultListModel();
  
  
//  private <E> void loadListModel(DefaultListModel model, Collection<E> c) {
//    model.clear();
//    for (E elt : c) {
//      model.addElement(elt);
//    }
//  }

//  private boolean confirm(String message) {
//    int response = JOptionPane.showOptionDialog(
//      frame, message, null, JOptionPane.YES_NO_OPTION,
//      JOptionPane.WARNING_MESSAGE, null,
//      new String[]{"Yes", "No"}, "No");
//    if (response == JOptionPane.YES_OPTION) {
//      return true;
//    }
//    return false;
//  }
  
  
//  // Function to refresh lists:
//    private void refreshLists() {
//        try {
//      loadListModel(actorsModel, actors.fetchAll());
//      loadListModel(ticketsModel, tickets.fetchAll());
//    } catch (Exception ex) {
//      ex.printStackTrace();
//      System.exit(0);
//    }
//        
//    }
    
    
//    private boolean isActorSelected(){
//        return (frame.getSelectedActor() != null);
//    }
//    
//    private boolean isTicketSelected(){
//        return (frame.getSelectedTicket() != null);
//    }
  
    
  // used to access TicketDB class members within inner class objects
  private TicketingSystem ticketsdb = this;

  public TicketingSystem() {
    System.out.println("model used: " + DB.getModel());
    
//    frame.setBounds(30, 30, 900, 600);
//    frame.setTitle("TicketsDB");
    //checkoutDialog.setTitle("Available Books");
//    addTicketDialog.setTitle("Add a Ticket");
//    modifyTicketDialog.setTitle("Modify Ticket Description");
//    addActorDialog.setTitle("Add an Actor");
    
    
    
//    frame.setActorsSize(new Dimension(0,0));
//    frame.setTicketsSize(new Dimension(0,0));
//    frame.setTicketActorsSize(new Dimension(0,0));
//    frame.setInfoSize(new Dimension(0,60));
//    
//    frame.setTicketsCellRenderer(new MyCellRenderer());
//
//    frame.setActorsModel(actorsModel);
//    frame.setTicketsModel(ticketsModel);
//    frame.setTicketActorsModel(ticketActorsModel);
//    //checkoutDialog.setBooksModel(addBooksModel);
    
    // initial list loads
//    try {
//      loadListModel(actorsModel, actors.fetchAll());
//      loadListModel(ticketsModel, tickets.fetchAll());
//    } catch (Exception ex) {
//      ex.printStackTrace();
//      System.exit(0);
//    }

//    frame.setVisible(true);

    
//    frame.addActorsListSelectionListener(new ListSelectionListener() {
//      @Override
//      public void valueChanged(ListSelectionEvent evt) {
//        if (evt.getValueIsAdjusting()) {
//          return;
//        }
//        Actor actor = frame.getSelectedActor();
//        if (actor != null) {
//          frame.setInfoText(
//            "Actor Info:\n"
//            + "name:  " + actor.getName() + ", id: " + actor.getId());
//          
//          frame.setRemoveActorEnabled(true);
//          
////          if (isTicketSelected()){
////              frame.setAddActorToTicketEnabled(true);
////              frame.setRemoveActorFromTicketEnabled(true);
////          }
//          
//        } else {
//          frame.setInfoText("");
//        }
//        frame.repaint();  // make the cell renderer kick in
//      }
//    });
//    
    
    
//    frame.addDeselectActorsActionListener(new ActionListener() {
//        
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        frame.clearActorSelection();
//      }
//    });
        
        
        
        
        
    

    
//    frame.addTicketsListSelectionListener(new ListSelectionListener() {
//      @Override
//      public void valueChanged(ListSelectionEvent evt) {
//        if (evt.getValueIsAdjusting()) {
//          return;
//        }
//        Ticket ticket = frame.getSelectedTicket();
//        if (ticket != null) {
//          String held_by = "";
//          try {
//            Actor actor = actors.fetch(ticket.getId());
//            held_by = (actor == null) ? "----" : actor.getName();
//            
//            frame.setModDescriptionEnabled(true);
//            
//            if (isTicketSelected()){
//              frame.setAddActorToTicketEnabled(true);
//              frame.setRemoveActorFromTicketEnabled(true);
//          }
//            
            
            
//          // Fetch Ticket Actors from MySQL: 
//          // populate actors in ticket list from Collection:
//          try {
//                loadListModel(ticketActorsModel, 
//                        actors.fetchAllFromTicket(frame.getSelectedTicket().getId()));
//               
//          } catch (Exception ex) {
//            ex.printStackTrace();
//            System.exit(0);
//            }
//            
//          } catch (Exception ex) {
//            ex.printStackTrace();
//            System.exit(0);
//          }
//          // Sets the box at the bottom of the frame to the selected ticket's description.
//          frame.setInfoText(ticket.getDescription());
//
//        } else {
//          frame.setInfoText("");
//        }
//      }
//    });

   
//    frame.addAddTicketActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        addTicketDialog.setLocation(
//          frame.getLocation().x + 15, frame.getLocation().y + 15);
//        addTicketDialog.setTitleText("");
//        addTicketDialog.setTitleModified(false);
//        addTicketDialog.setVisible(true);
//      }
//    });
//    
        
//    addTicketDialog.addTitleKeyListener(new KeyAdapter(){
//      @Override
//      public void keyTyped(KeyEvent evt) {
//        addTicketDialog.setTitleModified(true);
//        System.out.println("MOD");
//      }
//    });
//    
//    addTicketDialog.addAddActionListener(new ActionListener(){
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        String title = addTicketDialog.getTitleText().trim();
//        String year = addTicketDialog.getYearText().trim();
//        try {
//          if (title.length() < 3) {
//            throw new IllegalArgumentException(
//              "title must have length at least 3");
//          }
//          //                                Change to Integer  =======
//          Ticket ticket = tickets.add(title, Integer.valueOf(year), "");
//          ticketsModel.addElement(ticket);
//          
//          addTicketDialog.setVisible(false);
//        } catch (IllegalArgumentException ex) {
//          JOptionPane.showMessageDialog(frame, ex.getMessage());
//        } catch (java.sql.SQLException ex) {
//          JOptionPane.showMessageDialog(frame, "duplicate title");
//        } catch(Exception ex) {
//          ex.printStackTrace();
//          System.exit(0);
//        }
//      }
//    });
//    
//    addTicketDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//    addTicketDialog.addWindowListener(new WindowAdapter(){
//      @Override
//      public void windowClosing(WindowEvent evt) {
//        if (addTicketDialog.isTitleModified()) {
//          if (!confirm("Close without adding?")) {
//            return;
//          }
//        }
//        addTicketDialog.setVisible(false);
//      }
//    });
//    
//    
//    
//    // Add Actor Dialog:   =====================================================
//    
//    frame.addAddActorActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        addActorDialog.setLocation(
//          frame.getLocation().x + 15, frame.getLocation().y + 15);
//        addActorDialog.setTitleText("");
//        addActorDialog.setTitleModified(false);
//        addActorDialog.setVisible(true);
//      }
//    });
//        
//    addActorDialog.addTitleKeyListener(new KeyAdapter(){
//      @Override
//      public void keyTyped(KeyEvent evt) {
//        addActorDialog.setTitleModified(true);
//        System.out.println("MOD");
//      }
//    });
//    
//    addActorDialog.addAddActionListener(new ActionListener(){
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        String title = addActorDialog.getTitleText().trim();
//        try {
//          if (title.length() < 3) {
//            throw new IllegalArgumentException(
//              "title must have length at least 3");
//          }
//          //                                Change to Integer  =======
//          Actor actor = actors.add(title);
//          actorsModel.addElement(actor);
//          
//          addActorDialog.setVisible(false);
//        } catch (IllegalArgumentException ex) {
//          JOptionPane.showMessageDialog(frame, ex.getMessage());
//        } catch (java.sql.SQLException ex) {
//          JOptionPane.showMessageDialog(frame, "duplicate title");
//        } catch(Exception ex) {
//          ex.printStackTrace();
//          System.exit(0);
//        }
//      }
//    });
//    
//    
//    addActorDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//    addActorDialog.addWindowListener(new WindowAdapter(){
//      @Override
//      public void windowClosing(WindowEvent evt) {
//        if (addActorDialog.isTitleModified()) {
//          if (!confirm("Close without adding?")) {
//            return;
//          }
//        }
//        addActorDialog.setVisible(false);
//      }
//    });
//    
//    
//    // END of Add Actors Dialog ================================================
//    
//    
//    
//    
//    
//    // Add Actor To Ticket Dialog:   ============================================
//    
//    frame.addAddActorToTicketActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        addActorToTicketDialog.setLocation(
//          frame.getLocation().x + 15, frame.getLocation().y + 15);
//        addActorToTicketDialog.setMessageText("Select an actor to add to the ticket "
//                + frame.getSelectedTicket());
//        addActorToTicketDialog.setTitleModified(false);
//        
//        addActorToTicketDialog.setActorsModel(actorsModel);
//        
//        addActorToTicketDialog.setTitle("Add an Actor to " 
//            + frame.getSelectedTicket());
//        
//        addActorToTicketDialog.setVisible(true);
//      }
//    });
//        
//
//    
//    addActorToTicketDialog.addAddActionListener(new ActionListener(){
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        try{
//          //                                Change to Integer  =======
//          ticketActors.add(frame.getSelectedTicket().getId(),
//                  addActorToTicketDialog.getSelectedActor().getId());
//          refreshLists();
//          addActorToTicketDialog.setVisible(false);
//        } catch (IllegalArgumentException ex) {
//          JOptionPane.showMessageDialog(frame, ex.getMessage());
//        } catch (java.sql.SQLException ex) {
//          JOptionPane.showMessageDialog(frame, "This Actor is already in "
//                  + frame.getSelectedTicket());
//        } catch(Exception ex) {
//          ex.printStackTrace();
//          System.exit(0);
//        }
//      }
//    });
//    
//    
//    
//    addActorToTicketDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//    addActorToTicketDialog.addWindowListener(new WindowAdapter(){
//      @Override
//      public void windowClosing(WindowEvent evt) {
//        if (addActorToTicketDialog.isTitleModified()) {
//          if (!confirm("Close without adding?")) {
//            return;
//          }
//        }
//        addActorToTicketDialog.setVisible(false);
//      }
//    });
//    
//    
//    addActorToTicketDialog.addActorsListSelectionListener(new ListSelectionListener() {
//      @Override
//      public void valueChanged(ListSelectionEvent evt) {
//        if (evt.getValueIsAdjusting()) {
//          return;
//        }
//        
//        
//          addActorToTicketDialog.setMessageText("Add " 
//                  + addActorToTicketDialog.getSelectedActor()
//                  + " to the ticket "
//                  + frame.getSelectedTicket() + "?");
//          
//          
////        frame.repaint();  // make the cell renderer kick in
//      }
//    });
//    
//    
//    addActorToTicketDialog.addCancelActionListener(new ActionListener(){
//      @Override
//              public void actionPerformed(ActionEvent evt) {
//                  addActorToTicketDialog.setVisible(false);
//
//      }
//      });
//    
//    // END of Add Actor To Ticket Dialog ========================================
//    
//    
//    
//    // Remove Actor from Ticket =================================================
//    frame.addRemoveActorFromTicketActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        
//          if (confirm("Are you Sure You want to remove "
//                  + frame.getSelectedActor().getName()
//                  + " from " 
//                  + frame.getSelectedTicket().getTitle() + "?") 
//                  && frame.getSelectedActor() != null){
//              // Do the removal
//              try{
//              ticketActors.remove(frame.getSelectedActor().getId(),
//                      frame.getSelectedTicket().getId());
//              }catch(Exception ex){
//                  ex.printStackTrace();
//                  System.exit(0);
//              }
//          }
//      }
//    });
    
    
    
    // Remove Actor Dialog:   ==================================================
    
//    frame.addRemoveActorActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        removeActorDialog.setLocation(
//          frame.getLocation().x + 15, frame.getLocation().y + 15);
//        removeActorDialog.setTitleText(frame.getSelectedActor().getName());
//        removeActorDialog.setTitleModified(false);
//        removeActorDialog.setTitle("Remove " + frame.getSelectedActor());
//        removeActorDialog.setVisible(true);
//      }
//    });
        
//    removeActorDialog.addTitleKeyListener(new KeyAdapter(){
//      @Override
//      public void keyTyped(KeyEvent evt) {
//        removeActorDialog.setTitleModified(true);
//        System.out.println("MOD");
//      }
//    });
    
    
    
    
    
//    removeActorDialog.addCancelActionListener(new ActionListener(){
//      @Override
//              public void actionPerformed(ActionEvent evt) {
//                  removeActorDialog.setVisible(false);
//      }
//      });
              
              
              
              
    
//    removeActorDialog.addRemoveActionListener(new ActionListener(){
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        String title = removeActorDialog.getTitleText().trim();
//        try {
//          if (title.length() < 3) {
//            throw new IllegalArgumentException(
//              "title must have length at least 3");
//          }
//          //                                Change to Integer  =======
////          Actor actor = actors.remove(title);
//          actors.remove(frame.getSelectedActor().getName());
//          actorsModel.removeElement(frame.getSelectedActor());
//          
//          removeActorDialog.setVisible(false);
//        } catch (IllegalArgumentException ex) {
//          JOptionPane.showMessageDialog(frame, ex.getMessage());
//        } 
//        
//        catch (java.sql.SQLException ex) {
//          JOptionPane.showMessageDialog(frame, "duplicate title");
//        } 

//
//        catch(Exception ex) {
//          ex.printStackTrace();
//          System.exit(0);
//        }
//      }
//    });
//    
//    removeActorDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//    removeActorDialog.addWindowListener(new WindowAdapter(){
//      @Override
//      public void windowClosing(WindowEvent evt) {
//        if (removeActorDialog.isTitleModified()) {
//          if (!confirm("Close without adding?")) {
//            return;
//          }
//        }
//        removeActorDialog.setVisible(false);
//      }
//    });
//    
    
    // END of Remove Actor Dialog =============================================
    
    
   
    
  
    // ModifyTicketDialog: ===========================================
    
//    frame.addModifyDescriptionActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        modifyTicketDialog.setLocation(
//          frame.getLocation().x + 15, frame.getLocation().y + 15);
//        //modifyTicketDialog.setTitleText("");
//        modifyTicketDialog.setTitleModified(false);
//        
//        modifyTicketDialog.setTicketTitle("Title: " + frame.getSelectedTicket().getTitle());
//        modifyTicketDialog.setDescriptionText(frame.getSelectedTicket().getDescription());
//        
//        
//        modifyTicketDialog.setVisible(true);
//      }
//    });
        
//    modifyTicketDialog.addTitleKeyListener(new KeyAdapter(){
//      @Override
//      public void keyTyped(KeyEvent evt) {
//        modifyTicketDialog.setTitleModified(true);
//        System.out.println("MOD");
//      }
//    });
    
//    modifyTicketDialog.addAddActionListener(new ActionListener(){
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        String title = modifyTicketDialog.getDescriptionText().trim();
//        try {
//          if (title.length() < 3) {
//            throw new IllegalArgumentException(
//              "description must have length at least 3");
//          }
//          
          
          
          //                                Change to Integer  =======
          //Ticket ticket = tickets.add(title, Integer.valueOf(year), "");
          //ticketsModel.addElement(ticket);
          
          // Fetch the Ticket: ==============
          //tickets.fetch(frame.getSelectedTicket());
          // Modify Ticket: =================
//          tickets.modify(frame.getSelectedTicket(), modifyTicketDialog.getDescriptionText());
//          
//          
//          modifyTicketDialog.setVisible(false);
//          
//          // Frame lists must now be refreshed
//          refreshLists();
//          
//          
//        } catch (IllegalArgumentException ex) {
//          JOptionPane.showMessageDialog(frame, ex.getMessage());
//        } catch (java.sql.SQLException ex) {
//          JOptionPane.showMessageDialog(frame, "duplicate title");
//        } catch(Exception ex) {
//          ex.printStackTrace();
//          System.exit(0);
//        }
//      }
//    });
    
//    modifyTicketDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//    modifyTicketDialog.addWindowListener(new WindowAdapter(){
//      @Override
//      public void windowClosing(WindowEvent evt) {
//        if (modifyTicketDialog.isTitleModified()) {
//          if (!confirm("Close without adding?")) {
//            return;
//          }
//        }
//        modifyTicketDialog.setVisible(false);
//      }
//    });
//    
//    
    
    
    
    
    
 
    
  }// Main()

  public static void main(String[] args) throws Exception {
    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    new TicketingSystem();
    
    
    System.out.println("====== Welcome to the Ticketing System ===== \n\n");
    
      System.out.println("Clients: ");
      // Not the best way
      for (int i = 0; i < clients.fetchAll().size(); i++) {
          System.out.println(clients.fetchAll().toArray()[i]);
      }
      
      
      System.out.println("\n\nTickets: ");
      // Not the best way
      for (int i = 0; i < tickets.fetchAll().size(); i++) {
          System.out.println(tickets.fetchAll().toArray()[i]);
      }
      
      
      System.out.println("\n\nThe Technician for ticket no1 is " + tickets.fetchTechnician(1).getName());
      System.out.println("\n\nThe Technician for ticket no2 is " + tickets.fetchTechnician(2).getName());
      
  } // End of main
}
