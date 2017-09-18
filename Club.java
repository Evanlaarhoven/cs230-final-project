/*
 * Final.java
 * CS230 Final Project 
 * Andrea Leon, Emily Van Laarhoven, Emma Postel
 * Demo date: 12/13/16
 */

//Final class mostly written by Andrea Leon, edited collaboratively.

/*
 * Final class purpose:
 * This class extends creates a Club object which implements the Comparable class.
 * The object holds all relevant club information such as the name, meeting day,
 * time, as well as quantitative information that will be later used for the 
 * PriorityQueue. It also holds an array that represents what time the club meets
 * in order to be compared with the availability array created in the 
 * AvailibilityPanel GUI.
 */

//imports
import java.util.Scanner;
import java.io.*;


public class Club implements Comparable<Club>{
  
  //instance vars
  private String name;
  private String day;
  private int time; // will be input as such 4:30pm == 1630
  
  //instance vars that will be used by PriorityQueue
  private int collab;
  private int offPeople;
  private int appRigor;
  private int composite;
   
  //time array used for availability  
  private Boolean[] timeArray = new Boolean[91];
  final int clubsSize = 40;
  
  //empty club object to be used when loading club objects from file
  public Club(){
    
  }
  
  //constructor
  public Club(String name, String day, int time, int collab, int offPeople, int appRigor){
    this.composite = 0;
    this.name = name;
    this.day = day;
    this.time = time;
    this.collab = collab;
    this.offPeople = offPeople;
    this.appRigor = appRigor;
    int dayNum = 0;
    //statements to assign numerical value to meeting day for use by switch method
    if (day.equals("Monday")){
      dayNum = 1;
    }
    else if (day.equals("Tuesday")){
      dayNum = 2;
    }
    else if (day.equals("Wednesday")){
      dayNum = 3;
    }
    else if (day.equals("Thursday")){
      dayNum = 4;
    }
    else if (day.equals("Friday")){
      dayNum = 5;
    }
    else if (day.equals("Saturday")){
      dayNum = 6;
    }
    else if (day.equals("Sunday")){
      dayNum = 7;
    }
    for (int i=0; i<timeArray.length; i++) {
      timeArray[i] = false;
    }
    
    //switch method to set time array based on meeting day and time
    switch(dayNum){
      case 1:
        timeArray[((time-900)/100)] = true;
        break;
      case 2:
        timeArray[((time-900)/100) + 13] = true;
        break;
      case 3:
        timeArray[((time-900)/100) + 26] = true;
        break;
      case 4:
        timeArray[((time-900)/100) + 39] = true;
        break;
      case 5:
        timeArray[((time-900)/100) + 52] = true;
        break;
      case 6:
        timeArray[((time-900)/100) + 65] = true;
        break;
      case 7:
        timeArray[((time-900)/100) + 78] = true;
        break;
    }
  }
  
  //getters:
  /*
   * @returns: the String representation of the Club name
   */
  public String getName(){
    return name;
  }
  
  /*
   * @returns: the String representation of the Club meeting day
   */
  public String getDay(){
    return day;
  }
  
  /*
   * @returns: Integer of the Club meeting time (e.g. 1400 == 2 pm)
   */
  public int getTime(){
    return time;
  }
  
  /*
   * @returns: Integer of collaborative score to be used by PriorityQueue
   */
  public int getCollab(){
    return collab;
  }
  
  /*
   * @returns: Integer of off-campus interaction score to be used by PriorityQueue
   */
  public int getOffPeople(){
    return offPeople;
  }
  
  /*
   * @returns: Integer of rigor of applcation score to be used by PriorityQueue
   */
  public int getAppRigor(){
    return appRigor;
  }
  
  /*
   * @returns: Integer of composite score of all club preferences 
   * (i.e. collab, offPeople, and appRigor)
   * to be used by PriorityQueue (similar to GradSchools pset)
   */
  public int getComposite(){
    return composite;
  }
  
  /*
   * @returns: Boolean[] of collaborative score to be used by PriorityQueue
   */
  public Boolean[] getArray(){
    return timeArray;
  }
  
  //setters
  
  /*
   * Sets name variable to new input String
   */
  public void setName(String newName){
    name = newName;
  }
  
  /*
   * Sets day variable to new input String
   */
  public void setDay(String newDay){
    day = newDay;
  }
  
  /*
   * Sets day variable to new input String
   */
  public void setTime(int newTime){
    time = newTime;
  }
  
  /*
   * Sets collab variable to new input Integer
   */
  public void setCollab(int newCollab){
    collab = newCollab;
  }
  
  /*
   * Sets offPeople variable to new input Integer
   */
  public void setOffPeople(int newOffPeople){
    offPeople = newOffPeople;
  }
  
  /*
   * Sets appRigor variable to new input Integer
   */
  public void setAppRigor(int newAppRigor){
    appRigor = newAppRigor;
  }
  
  /*
   * Sets composite variable to new input Integer 
   * (will be a combination of collab, offPeople, and appRigor)
   */
  public void setComposite(int newComposite){
    composite = newComposite;
  }
  
  /*
   * @returns: String representation of a Club object
   */
  public String toString(){
    String s = ("Org: " + name + ", Day: " + day + ", Time: " + time +
                ", Collaboration: " + collab + ", Off-Campus Interactions: " 
               + offPeople + ", Application Rigor: " + appRigor);
    return s;
  }
  
  /*
   * @returns: Club[] filled with Club objects that are read in from a given file
   */
   public Club[] clubsFromFile(String fileName){
     Club[] clubs = new Club[clubsSize];
     try{
      Scanner reader = new Scanner(new File(fileName));
      int counter = 0;
      while (reader.hasNext()) {
        String line = reader.nextLine();
        String[] splitStr = line.split(", ");
        Club club1 = new Club(splitStr[0], splitStr[1], Integer.parseInt(splitStr[2]), 
                              Integer.parseInt(splitStr[3]), Integer.parseInt(splitStr[4]), 
                              Integer.parseInt(splitStr[5]));
        clubs[counter] = club1;
        counter++;
      }
      reader.close();
    }

    catch (FileNotFoundException e){
      System.out.println("file not found");
    }
    return clubs;
  }
   
   /*
   * @returns: a positive value, or negative value, or zero
   * (implements Comparable Class)
   */
   public int compareTo(Club otherClub) {
     return (this.composite - otherClub.composite);
   }
  
   //main method - for testing
  public static void main(String [] args){
    //testing deleted for brevity
  }
}