/*
 * Final.java
 * CS230 Final Project 
 * Andrea Leon, Emily Van Laarhoven, Emma Postel
 * Demo date: 12/13/16
 */

//Final class mostly written by Emily Van Laarhoven, edited collaboratively.

/*
 * Final class purpose:
 * This class extends JPanel but also allows us to store the instance variables
 * that will be accessed by multiple panels at different stages of the GUI.
 * This class functions as a driver and does not interact directly with any 
 * data structures.
 */

//imports
import javax.swing.JFrame;
import javafoundations.*;

public class Final extends JFrame{
  
  //instance variables:
  
  //array of preferences for collaboration, off campus time, and rigor of application
  private Integer[] prefArray;
  //array of entire week of availability
  private Boolean[] availArray; 
  //list of first 3 clubs to display, which gets set at the end of QuestionPanel
  private Club[] clubList; 
  //the final leaf of the tree is a Question that contains an array of Clubs
  private Question finalLeafQ; 
  //the PriorityQueue is created when we use prefArray to prioritize the array of clubs
  private PriorityQueue<Club> pQ; 
  
  //constructor
  public Final() {
    prefArray = new Integer[3]; //3 categories of preference
    availArray = new Boolean[91]; //7 days from 9am to 9pm (13 hours)
  }
  
  //setters:
  
  /*
   * sets the preference array in the frame, in order of collab, off-campus, and
   * rigor of application, with 1 being least important and 10 being most important.
   * @param: Integer[] of three preferences in order of collab, off-campus, application
   */
  public void setPrefArray(Integer[] inputArray) {
    for (int i=0; i<3; i++) {
      prefArray[i] = inputArray[i];
    }
  }
  
  /*
   * sets the preference array in the frame
   * @param: Boolean[] of 91 slots corresponding to Mon - Sun, 9am - 9pm
   */
  public void setAvailArray(Boolean[] inputArray) {
    for (int i=0; i<91; i++) {
      availArray[i] = inputArray[i];
    }
  }
  
  /*
   * sets the finals Question object in the frame
   * the final Question has no question stored but has a list of clubs
   * this gets set after iterating through the tree
   * @param: Question final leaf in the tree
   */
  public void setQuestion(Question leaf) {
    finalLeafQ = leaf;
  }
  
  /*
   * sets the club list of the frist 3 clubs to be displayed in the results GUI
   * these are determined once the next button is selected in the Questions Panel
   * note that the clubs can be null if there are many scheduling conflicts.
   * @param: Club[] array of 3 clubs, which may be null.
   */
  public void setClubList(Club[] newClubList) {
    clubList = newClubList;
  }
  
  
  /*
   * sets the priority queue of clubs to be stored in the frame
   * this priority queue is created by combining the preferences array with the
   * clubs stored in the final question, using the Question.prioritize(prefArray) 
   * method.
   * @param: PriorityQueue<Club> from Questions panel
   */
  public void setPQ(PriorityQueue<Club> pq) {
    pQ = pq;
  }

  
  //getters:
  /*
   * @returns: the Integer[] of preferences for collaboration, 
   * off-campus time, and application rigor
   */
  public Integer[] getPrefArray() {
    return prefArray;
  }
  
  /*
   * @returns: the Boolean[] of availability of the user, 
   * entered during the Availability Panel
   */
  public Boolean[] getAvailArray() {
    return availArray;
  }
  
  /*
   * @returns: the final leaf in the tree, a Question object
   * which contains a list of clubs that are pertinent to the user's interests
   */
  public Question getQuestion() {
    return finalLeafQ;
  }
  
  /*
   * @returns: Club[] list of 3 clubs that are first to be displayed
   * in the Results Panel, any of these may be null
   */
  public Club[] getClubList() {
    return clubList;
  }
  
  /*
   * @returns: PriorityQueue<Club> that is is created by combining the 
   * preferences array with the clubs stored in the final question, 
   * using the Question.prioritize(prefArray) method.
   */
  public PriorityQueue<Club> getPQ() {
    return pQ;
  }
  
  //main method - driver of all the GUIs
  public static void main (String[] args){
    
    Final frame = new Final ();
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    //the following panels are in reverse order of appearance:
    ResultsPanel rPanel = new ResultsPanel(frame);
    QuestionsPanel qPanel = new QuestionsPanel(frame, rPanel);
    AvailabilityPanel availPanel = new AvailabilityPanel(frame, qPanel);
    PreferencePanel prefPanel = new PreferencePanel(frame,availPanel);
    frame.getContentPane().add(new TitlePanel(frame,prefPanel));
    
    frame.pack();
    frame.setVisible(true);    
    
  }
}
