/*
 * QuestionsPanel.java
 * CS230 Final Project 
 * Andrea Leon, Emily Van Laarhoven, Emma Postel
 * Demo date: 12/13/16
 */

//written and edited collaboratively

/*
 * QuestionsPanel Purpose:
 * This is a succession of three questions from the decision tree that the
 * user will either click answer A or B on and navigate through the tree
 * until a Question containing a list of clubs pertaining to their interests
 * is stored to the frame.
 */

//imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javafoundations.*;

public class QuestionsPanel extends JPanel {
 
  //instance variables:
  
  //panel components
  private JButton A;
  private JButton B;
  private JButton quitButton;
  private JButton next;
  private JLabel currentQLabel;
  private String qTextA;
  private String qTextB;
  
  private int counter = 0; //keeps track of what # question you're on
  private Question currentQ; //stores last question in tree
  private LinkedBinaryTree<Question> tree; //will need to access tree to get questions
  private PriorityQueue<Club> pq; //will need to create and store priority queue to frame
  
  //driver frame and next panel in process
  private Final frame;
  private ResultsPanel rPanel;
  
  //constructor takes in driver frame and next panel in process
  public QuestionsPanel(Final frame, ResultsPanel rPanel) {
    
    //sets the frame and next panel in process
    this.frame = frame;
    this.rPanel = rPanel;
    
    //get the tree
    DecisionTree dtree = new DecisionTree();
    tree = dtree.getTree();
    
    //get the root element to start with
    currentQ = tree.getRootElement();
    qTextA = currentQ.getAnsA(); 
    qTextB = currentQ.getAnsB();

    //layout parameters
    setLayout(new BorderLayout());
    Color c = new Color(99,79,160);
    setBackground(c);
    
    //add buttons A, B, Next, Quit
    
    A = new JButton (qTextA);
    A.addActionListener(new ButtonListener());
    add(A, BorderLayout.WEST);
    A.setEnabled(true);
    
    B = new JButton (qTextB);
    B.addActionListener(new ButtonListener());
    add(B, BorderLayout.EAST);
    B.setEnabled(true);
    
    currentQLabel = new JLabel(currentQ.getQuestion());
    add(currentQLabel, BorderLayout.NORTH);
    
    next = new JButton("Next");
    next.addActionListener(new ButtonListener());
    add(next, BorderLayout.CENTER);
    next.setEnabled(false);
    
    quitButton = new JButton ("Quit Quiz");
    quitButton.addActionListener(new ButtonListener());
    add(quitButton, BorderLayout.SOUTH);
    
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
    while (first!= null && !fits(first, availability)) {
      first = pq.dequeue();  //dequeue from empty pq sets to null
    }
    return first;
  }
  
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      
      //quit button
      if (event.getSource() == quitButton) 
        System.exit(0);
      
      // choose answer A
      if (event.getSource() == A) {
        currentQ = tree.getLeftChild(currentQ);
        qTextA = currentQ.getAnsA();
        qTextB = currentQ.getAnsB();
        A.setText(qTextA);
        B.setText(qTextB);
        currentQLabel.setText(currentQ.getQuestion());
        frame.pack();
        counter ++;
        if (counter == 3) { //if this is the last question asked:
          currentQLabel.setText("CLICK NEXT NOW TO SEE YOUR RESULTS!!");
          next.setEnabled(true);
          A.setEnabled(false);
          B.setEnabled(false);
          frame.pack();
          frame.setQuestion(currentQ); 
        }
      }
      
      //choose Answer B
      if (event.getSource() == B) {
        currentQ = tree.getRightChild(currentQ);
        qTextA = currentQ.getAnsA();
        qTextB = currentQ.getAnsB();
        A.setText(qTextA);
        B.setText(qTextB);
        currentQLabel.setText(currentQ.getQuestion());
        frame.pack();
        counter ++;
        if (counter == 3) { //if this is the last question asked:
          currentQLabel.setText("CLICK NEXT NOW TO SEE YOUR RESULTS!!");
          next.setEnabled(true);
          A.setEnabled(false);
          B.setEnabled(false);
          frame.pack();
          frame.setQuestion(currentQ); 
        }
      }
      
      //next button
      if (event.getSource() == next) {
        //get info from frame
        Integer[] pref = frame.getPrefArray();
        Boolean[] avail = frame.getAvailArray();
        Question finalQ = frame.getQuestion();
        //make priority queue
        pq = finalQ.prioritize(pref);
        Club[] clubList = new Club[3];
          clubList[0] = getNextClub(avail);
          clubList[1] = getNextClub(avail);
          clubList[2] = getNextClub(avail); 
        //set club list in frame
        frame.setClubList(clubList);
        frame.setPQ(pq);
        //go to next panel
        frame.getContentPane().removeAll();
        frame.getContentPane().add(rPanel);
        frame.pack();
        frame.setVisible(true);
      }
    }
  } 
  
}
