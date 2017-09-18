/*
 * ResultsPanel.java
 * CS230 Final Project 
 * Andrea Leon, Emily Van Laarhoven, Emma Postel
 * Demo date: 12/13/16
 */

//written by Andrea Leon

/*
 * TitlePanel Purpose:
 * Displays the image and start button for our project
 */

//imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class TitlePanel extends JPanel {
  
  //instance variables
  private JButton startButt;
  private BufferedImage myPicture;
  private JFrame frame;
  private JPanel prefPanel;
  
  //constructor takes in driver frame and next panel
  public TitlePanel(JFrame frame, PreferencePanel prefPanel){
    
    //layout parameters
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    Color c = new Color(99,79,160); //CUSTOM COLOR +1000 DESIGN POINTS!!!
    setBackground(c);
    this.frame = frame;
    this.prefPanel = prefPanel;
   
    //display image:
    try{
      myPicture = ImageIO.read(new File("weRwizards.png"));
    }
    catch(IOException e){
      System.out.println("Could not find file");
    }
    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
    picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(picLabel);
    
    //add start button
    startButt = new JButton("Start");
    startButt.setAlignmentX(Component.CENTER_ALIGNMENT);
    startButt.addActionListener(new ButtonListener());
    add(startButt);
  }
  
   private class ButtonListener implements ActionListener{
    
    public void actionPerformed (ActionEvent event){
      if (event.getSource() == startButt) {
        //go on to next panel:
        frame.getContentPane().removeAll();
        frame.getContentPane().add(prefPanel);
        frame.pack();
        frame.setVisible(true);
      }
    }
   }
}