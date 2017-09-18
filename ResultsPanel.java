/*
 * ResultsPanel.java
 * CS230 Final Project 
 * Andrea Leon, Emily Van Laarhoven, Emma Postel
 * Demo date: 12/13/16
 */

//written and edited collaboratively

/*
 * ResultsPanel Purpose:
 * This panel receives the information from the past 3 stages
 * of club selection and displays the top 3 club choices based on 
 * user input. If the user does not like a club they can opt to replace 
 * it with the next club in the prioritized list that fits in their 
 * schedule, if available.  If there are no clubs that suit the criteria
 * the check boxes are disabled and a message appears.  Ultimately the 
 * user accepts a final selection of three clubs and a message appears
 * wishing them good luck before exiting the program.
 */

//imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javafoundations.*;

public class ResultsPanel extends JPanel {
  
  //instance variables:
  private JLabel clubOptionsHeader;
  private JCheckBox[] checkArray;
  private JCheckBox check1;
  private Club club1;
  private JCheckBox check2;
  private Club club2;
  private JCheckBox check3;
  private Club club3;
  private JButton acceptButt;
  private JButton changeButt;
  private JButton displayButt;
  private Final frame;
  private Boolean[] avail; //stores user availability 
  private PriorityQueue<Club> pq; //prioritized list of clubs
  
  //constructor takes in driver frame to access stored variables
  public ResultsPanel(Final frame){
    
    //get information from frame
    this.frame = frame;
    avail = frame.getAvailArray();
    
    //layout parameters
    setLayout((new BoxLayout(this, BoxLayout.PAGE_AXIS)));
    Color c = new Color(99,79,160);
    setBackground(c);
    
    clubOptionsHeader = new JLabel("Your Club Options:");
    clubOptionsHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    //create list of checkboxes to iterate through:
    check1 = new JCheckBox("");
    check2 = new JCheckBox("");
    check3 = new JCheckBox("");
    checkArray = new JCheckBox[3];
    checkArray[0] = check1; checkArray[1] = check2; checkArray[2] = check3;
    
    //set alignment and add
    for (int i=0; i<3; i++) {
      checkArray[i].setAlignmentX(Component.LEFT_ALIGNMENT);
      add(checkArray[i]);
    }
    
    //make buttons
    displayButt = new JButton("See your results!");
    displayButt.addActionListener(new ButtonListener());
    acceptButt = new JButton("Accept Clubs");
    acceptButt.addActionListener(new ButtonListener());
    changeButt = new JButton("Change Clubs");
    changeButt.addActionListener(new ButtonListener());
    
    //add to panel
    add(clubOptionsHeader);
    add(displayButt);
    add(changeButt);
    add(acceptButt);
    acceptButt.setEnabled(false);
  }
  
  /*
   * helper method to determine if a club fits in the schedule given by the user
   * @param: Club to be checked
   * @param: Boolean[] user's availability array
   * @returns: true if club fits in user schedule, false otherwise
   */
  public static boolean fits(Club club, Boolean[] availabilityArray) {
    Boolean[] clubArray = club.getArray();
    for (int i = 0; i< clubArray.length; i++) {
      if ((clubArray[i]==true) && (availabilityArray[i]==false)) {
        return true;
      }
    }
    return false; 
  }
  
/*
   * helper method to determine the next club in the priority queue 
   * to be displayed (it has to fit to be displayed), invokes fits()
   * @param: Boolean[] user's availability array
   * @returns: next club from priority queue that fits in schedule
   */
  public Club getNextClub(Boolean[] availability) {
    Club first = pq.dequeue(); //dequeue from empty pq sets to null
    while (first!=null && !fits(first, availability)) {
      first = pq.dequeue();  //dequeue from empty pq sets to null
    }
    return first;
  }
  

  private class ButtonListener implements ActionListener{
    
    public void actionPerformed (ActionEvent event){
      
      /*
       * display your results button:
       * Get clubs from frame and check if they are null, meaning
       * there are no more clubs in the priority queue that are free
       * of scheduling conflicts.  If they are null show the message
       * that there are no more clubs to display, otherwise display
       * the string representation of the club.
       */
      if (event.getSource() == displayButt){
        acceptButt.setEnabled(true);
        displayButt.setEnabled(false);
        pq = frame.getPQ();
        Club[] clubList = frame.getClubList();
        club1 = clubList[0];
        club2 = clubList[1];
        club3 = clubList[2]; 
        if (clubList[0]==null) {
          check1.setText("There are no more clubs to display");
          check1.setEnabled(false);
        } else {
          check1.setText(club1.toString());
        }
        if (clubList[1]==null) {
          check2.setText("There are no more clubs to display");
          check2.setEnabled(false);
        } else {
          check2.setText(club2.toString());
        }
        if (clubList[2]==null) {
          check3.setText("There are no more clubs to display");
          check3.setEnabled(false);
        } else {
          check3.setText(club3.toString());
        }
        frame.pack();
      }
      
      /*
       * change button:
       * If user has checked next to any amount of clubs this button
       * will replace these clubs with the next possibilities from the priority
       * queue, assuming that they fit in the schedule.  Otherwise the 
       * message that there are no more clubs is displayed and the check boxes
       * no longer work.
       */
      else if (event.getSource() == changeButt) { 
        if (check1.isSelected()){
          club1 = getNextClub(avail);
          if (club1==null) {
            check1.setText("There are no more clubs to display");
            check1.setEnabled(false);
          } else {
            check1.setLabel(club1.toString()); 
            check1.setSelected(false);
          }
        }
        if (check2.isSelected()){
          club2 = getNextClub(avail);
          if (club2==null) {
            check2.setText("There are no more clubs to display");
            check2.setEnabled(false);
          } else {
            check2.setLabel(club2.toString()); 
            check2.setSelected(false);
          }  
        }
        if (check3.isSelected()){  
          club3 = getNextClub(avail);
          if (club3==null) {
            check3.setText("There are no more clubs to display");
            check3.setEnabled(false);
          } else {
            check3.setLabel(club3.toString()); 
            check3.setSelected(false);
          }
        }
      }  
      
      /*
       * accept button:
       * If user is content with club selection, a dialogue box will 
       * wish them good luck in their clubs, then quit program.
       */
      else if (event.getSource() == acceptButt) {
        //get names to display in final dialogue box:
        String club1name;
        String club2name;
        String club3name;
        if (club1!=null) {
          club1name = club1.getName();
        } else {
          club1name = "";
        }
        if (club2!=null) {
          club2name = club2.getName();
        } else {
          club2name = "";
        }
        if (club3!=null) {
          club3name = club3.getName();
        } else {
          club3name = "";
        }       
        String message = "Enjoy your clubs! \n\n"+club1name+"\n"+club2name+"\n"+club3name;
        JOptionPane.showMessageDialog(null,message);
        System.exit(0);
      }
      
    }
  }
}

