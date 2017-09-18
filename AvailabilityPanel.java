/*
 * AvailabilityPanel.java
 * CS230 Final Project 
 * Andrea Leon, Emily Van Laarhoven, Emma Postel
 * Demo date: 12/13/16
 */

// AvailabilityPanel mostly written by Emma, de-bugged collaboratively

/*
 * AvailabilityPanel Purpose:
 * The purpose of this panel is to provide the user with a grid of 
 * checkboxes that correspond to the 7 days of the week from 9am to 9pm
 * This array will be saved to the frame for use in calculating and 
 * displaying results.
 */

//imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AvailabilityPanel extends JPanel {
  
  //instance variables:
  
  //panel components
  private JCheckBox [][] timeSlots;
  private JButton quitButton;
  private JButton nextButton;
  private JLabel empty, mon, tues, wed, thur, fri, sat, sun;
  private JLabel[] times;
  private JLabel instructions1;
  private JLabel instructions2;
  private JLabel instructions3;
    
  
  //array to store user input
  private Boolean[] availability;
  
  //frame and next panel in process
  private Final frame;
  private QuestionsPanel qPanel;
  
  //constructor takes in as parameters driver frame and next panel
  public AvailabilityPanel(Final frame, QuestionsPanel qPanel) {
    
    //get frame and next panel in process
    this.frame = frame;
    this.qPanel = qPanel;
     
    //initialize availability array
    availability = new Boolean[91];
    for (int i=0; i<91; i++) {
      availability[i] = false;
    }
   
    //layout parameters
    setLayout(new GridLayout(15,8));
    Color c = new Color(99,79,160);
    setBackground(c);
    
    //add labels and checkboxes 
    times = new JLabel[13];
    timeSlots = new JCheckBox[13][7];
    
    empty = new JLabel("");
    add(empty);

    mon = new JLabel("Monday");
    add(mon);
    tues = new JLabel("Tuesday");
    add(tues);
    wed = new JLabel("Wednesday");
    add(wed);
    thur = new JLabel("Thursday");
    add(thur);
    fri = new JLabel("Friday");
    add(fri);
    sat = new JLabel("Saturday");
    add(sat);
    sun = new JLabel("Sunday");
    add(sun);
    
    //add in checkboxes:
    
    //create array of labels:
    times[0] =new JLabel("9AM");
    times[1] =new JLabel("10AM");
    times[2] =new JLabel("11AM");
    times[3] =new JLabel("12AM");
    times[4] =new JLabel("1PM");
    times[5] =new JLabel("2PM");
    times[6] =new JLabel("3PM");
    times[7] =new JLabel("4PM");
    times[8] =new JLabel("5PM");
    times[9] =new JLabel("6PM");
    times[10] =new JLabel("7PM");
    times[11] =new JLabel("8PM");
    times[12] =new JLabel("9PM");
    //add checkboxes to panel:
    for (int j = 0; j<13; j++) {
      add(times[j]);
      for (int i=0; i<7; i++) {
        timeSlots[j][i] = new JCheckBox("");
        timeSlots[j][i].setSelected(false);
        add(timeSlots[j][i]);
      }
    }

    //add buttons:
    
    quitButton = new JButton("Quit Quiz");
    quitButton.addActionListener(new ButtonListener());
    add(quitButton);
    
    nextButton = new JButton("Next");
    nextButton.addActionListener(new ButtonListener());
    add(nextButton);
    
    //add instructions
    instructions1 = new JLabel("INSTRUCTIONS:");
    add(instructions1);
    instructions2 = new JLabel("Select times that");
    add(instructions2);
    instructions3 = new JLabel(" you are BUSY.");
    add(instructions3);
   
  }
  
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      
      //quit button
      if (event.getSource() == quitButton) 
        System.exit(0);
      
      //next button
      if (event.getSource() == nextButton){ 
        //store checkboxes in timeSlots as true in availability
        for (int j=1; j<8; j++) { 
          for (int i=1; i<14; i++) { 
            if (timeSlots[i-1][j-1].isSelected()) {
              availability[(j-1)*13+i-1] = true;
            } 
          }
        }
        
        //sets availaibility array in the final driver
        frame.setAvailArray(availability); 
        
        //moves on to the next panel
        frame.getContentPane().removeAll();
        frame.getContentPane().add(qPanel);
        frame.pack();
        frame.setVisible(true);
      }
    }
  }
  
}