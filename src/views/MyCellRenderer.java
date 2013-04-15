// David Emery

package views;

import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import models.Movie;
import models.Actor;
import models.*;

import java.util.Collection;

import java.util.*;

public class MyCellRenderer implements ListCellRenderer {
  @Override
  public Component getListCellRendererComponent(JList list, Object obj, 
      int ind, boolean isSelected, boolean hasFocus) {
    JLabel label;
    Movie movie = (Movie) obj;
    TheFrame frame = (TheFrame) list.getTopLevelAncestor();
    Actor actor = frame.getSelectedActor();
    Color fg, bg;
    fg = isSelected ? list.getSelectionForeground() : list.getForeground();
    bg = isSelected ? list.getSelectionBackground() : list.getBackground();
    
    boolean isIn = false;
    
    Movies movies = new Movies();

      
      try {
          isIn = movies.fetchAllForActor(actor.getId()).contains(actor);
      } catch (Exception ex) {
          Logger.getLogger(MyCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
      }

      // Change this to check if actor is in range of fetchAllForActor
      if (actor != null && isIn) {
          label = new JLabel("<html><b>" + movie + "</b>");
          fg = Color.RED;
          if (isSelected) {
              bg = Color.decode("#eeeeaa");
          }
      } else {
          label = new JLabel(movie.toString());
      }

      label.setOpaque(true);
      label.setBackground(bg);
      label.setForeground(fg);

      return label;

  }
}
