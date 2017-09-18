/*
 * PreferencePanel.java
 * CS230 Final Project 
 * Andrea Leon, Emily Van Laarhoven, Emma Postel
 * Demo date: 12/13/16
 */

// PreferencePanel mostly written by Emma Postel, edited collaboratively.

/*
 * PreferencePanel Purpose:
 * The purpose of this panel is to present the user with 3 text boxes in
 * which they enter in how important the following club characteristics are to them:
 * amount of collaboration, working with off-campus people, and rigor of application
 * They then rate these on a scale from 1 (least important) to 10 (most important)
 * The GUI also has to control for invalid inputs, such as strings or numbers 
 * that are not in the range 1-10.
 */

//imports:
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class PreferencePanel extends JPanel {
  
  //instance variables:
  
  //preferences to store
  private Integer[] preferences = new Integer[3];
  private int pref1, pref2, pref3;
  
  //labels
  private JLabel instructions;
  private JLabel preference1;
  private JLabel preference2;
  private JLabel preference3;
  
  //buttons
  private JButton quitButton;
  private JButton nextButton;
  
  //text boxes
  private JTextField ans1;
  private JTextField ans2;
  private JTextField ans3;
  
  //frame and next panel in progression
  private Final frame;
  private AvailabilityPanel availPanel;
  
  //constructor takes in driver frame and next panel in progression
  public PreferencePanel(Final frame, AvailabilityPanel availPanel) {
    
    // set frame and next panel in progression
    this.frame = frame;
    this.availPanel = availPanel;
    
    //layout parameters
    setLayout(new GridLayout(10,1));
    Color c = new Color(99,79,160);
    setBackground(c);
    
    //adding labels and text fields:
    String howTo = "Please enter your preference level for each characteristic ";
    String specs = "as an integer from 1-10 (10 being most preferred)";
    instructions = new JLabel(howTo+specs);
    add(instructions);
    
    preference1 = new JLabel("Collaboration");
    add(preference1);
    ans1 = new JTextField(5);
    add(ans1);
    
    preference2 = new JLabel("Time spent off-campus");
    add(preference2);
    ans2 = new JTextField(5);
    add(ans2);
    
    preference3 = new JLabel("Rigor of application");
    add(preference3);
    ans3 = new JTextField(5);
    add(ans3);
    
    //adding buttons:
    
    nextButton = new JButton("Next");
    nextButton.addActionListener(new ButtonListener());
    add(nextButton);
    
    quitButton = new JButton("Quit Quiz");
    quitButton.addActionListener(new ButtonListener());
    add(quitButton);
    
  }
  
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) throws InputMismatchException {
      
      String message = "Please enter an integer between 1 and 10";
      InputMismatchException problem = new InputMismatchException(message);
      
      //quit button
      if (event.getSource() == quitButton) {
        System.exit(0);
      }  
     
      //next button
      if (event.getSource() == nextButton) {
        
        //establish appropriate range of input values:
        LinkedList<Integer> range = new LinkedList<Integer>();
        range.add(1); range.add(2); range.add(3); range.add(4); range.add(5);
        range.add(6); range.add(7); range.add(8); range.add(9); range.add(10);
        
        //text from textboxes:
        String input1 = ans1.getText();
        String input2 = ans2.getText();
        String input3 = ans3.getText();

        //verifying that the inputs are correct
          try{ //verify that input is integer
            pref1 = Integer.parseInt(input1);
            pref2 = Integer.parseInt(input2);
            pref3 = Integer.parseInt(input3);
          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,"All inputs must be integers");
          } //check if input is integer in {1,2,3,4,5,6,7,8,9,10}
          if (!range.contains(pref1) || !range.contains(pref2) || !range.contains(pref3)) {
            JOptionPane.showMessageDialog(null,"All inputs must be within range 1-10");
            throw problem; //throws exception to halt progression until proper input added
          }

        //preferences to be stored (will definitely be appropriate values)  
        preferences[0] = pref1;
        preferences[1] = pref2;
        preferences[2] = pref3;
        //store to frame  
        frame.setPrefArray(preferences);
        
        //move on to next panel
        frame.getContentPane().removeAll();
        frame.getContentPane().add(availPanel);
        frame.pack();
        frame.setVisible(true);
      }
    }
  }
    
}
