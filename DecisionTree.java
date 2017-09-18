/*
 * AvailabilityPanel.java
 * CS230 Final Project 
 * Andrea Leon, Emily Van Laarhoven, Emma Postel
 * Demo date: 12/13/16
 */

//written by Emily Van Laarhoven

//import from javafoundations - note: I edited this class
import javafoundations.LinkedBinaryTree;
  
  public class DecisionTree {
    
    //instance variables
    private LinkedBinaryTree<Question> tree;
    
    //constructor - creates whole tree
    public DecisionTree() {
      
    //clubs: (edited so that there are no half hours)
    Club c1 = new Club("Ascendance", "Wednesday", 1900, 4, 2, 7);
    Club c2 = new Club("Aiko", "Thursday", 2000, 5, 2, 5);
    Club c3 = new Club("Cielito Lindo", "Monday", 1900, 5, 1, 4);
    Club c4 = new Club("Wushu", "Thursday", 1800, 5, 2, 5);
    Club c5 = new Club("Yanvalou", "Wednesday", 1300, 4, 1, 2);                   
    Club [] q7clubs = {c1, c2, c3, c4, c5};
    Club c6 = new Club("Water Polo", "Tuesday", 2000, 2, 8, 3);
    Club c7 = new Club("Volleyball", "Wednesday", 1900, 3, 7, 3);
    Club c8 = new Club("Rugby", "Wednesday", 1300, 2, 5, 4);
    Club c9 = new Club("Hockey", "Thursday", 1900, 3, 7, 4);
    Club c10 = new Club("Quidditch", "Tuesday", 1600, 2, 8, 1);                                                    
    Club [] q8clubs = {c6, c7, c8, c9, c10};
    Club c11 = new Club("Anime Club", "Friday", 1600, 2, 3, 1);
    Club c12 = new Club("Mariachi", "Monday", 1700, 3, 1, 4);                 
    Club c13 = new Club("Phocus", "Wednesday", 1800, 2, 1, 2);                  
    Club c14 = new Club("Italian Society", "Wednesday", 1200, 3, 2, 3);                   
    Club c15 = new Club("Girl Up", "Thursday", 2100, 4, 3, 1);
    Club [] q9clubs = {c11, c12, c13, c14, c15};
    Club c16 = new Club("Psychology Club", "Sunday", 1900, 1, 7, 1);
    Club c17 = new Club("Botanistas", "Thursday", 1200, 2, 1, 1);
    Club c18 = new Club("Robogals", "Thursday", 1900, 2, 7, 1);
    Club c19 = new Club("CS Club", "Tueday", 1800, 2, 7, 1);
    Club c20 = new Club("Astro Club", "Thursday", 2000, 3, 2, 2);
    Club [] q10clubs = {c16, c17, c18, c19, c20};
    Club c21 = new Club("Wellesley for Caribbean Development", "Wednesday", 2000, 4, 3, 2);
    Club c22 = new Club("Ethos", "Wednesday", 2000, 5, 5, 3);
    Club c23 = new Club("CSA", "Tuesday", 1800, 5, 5, 3);                 
    Club c24 = new Club("Mezcla", "Thursday", 1900, 6, 4, 3);                    
    Club c25 = new Club("Wellesley African Student's Association", "Sunday", 1900, 7, 5, 3); 
    Club [] q11clubs = {c21, c22, c23, c24, c25};
    Club c26 = new Club("SAGA", "Tuesday", 1900, 5, 3, 1);
    Club c27 = new Club("Siblings", "Tueday", 1700, 3, 1, 5);                    
    Club c28 = new Club("Familia", "Sunday", 1100, 4, 1, 5);                  
    Club c29 = new Club("First Gen", "Wednesday", 1300, 3, 1, 6);                  
    Club c30 = new Club("Newman", "Sunday", 1700, 2, 1, 3);
    Club [] q12clubs = {c26, c27, c28, c29, c30};
    Club c31 = new Club("Model UN", "Monday", 1800, 1, 5, 3);
    Club c32 = new Club("Wellesley College Republicans", "Wednesday", 1700, 1, 1, 2);
    Club c33 = new Club("Wellesley College Democrats", "Wednesday", 2000, 3, 1, 2);                    
    Club c34 = new Club("CPLA", "Monday", 2000, 7, 1, 1);                   
    Club c35 = new Club("Amnesty International", "Monday", 1900, 6, 4, 1);                   
    Club [] q13clubs = {c31, c32, c33, c34, c35};                    
    Club c36 = new Club("Students for Justice in Palestine", "Sunday", 1100, 6, 2, 1);
    Club c37 = new Club("Feminists for Reproductive Justice", "Monday", 1800, 3, 1, 1);
    Club c38 = new Club("SAAFE", "Monday", 1800, 3, 2, 2);                   
    Club c39 = new Club("S.H.E.s", "Wednesday", 2000, 6, 1, 4);                  
    Club c40 = new Club("Environmental Action", "Wednesday", 2000, 2, 2, 1);                    
    Club [] q14clubs = {c36, c37, c38, c39, c40};
    
    //create questions (elements stored in tree)
    //root
    Question q0 = new Question(0,"Do your interests tend towards: ","specific hobbies and interests","activism and identity politics");
    //2nd level
    Question q1 = new Question(1,"Do you prefer: ","team physical activities","group learning");
    Question q2 = new Question(2,"Are you more passionate about: ","communities formed around a common identity","political activism and awareness");
    //3rd level
    Question q3 = new Question(3,"Do you prefer: ","performance","games");
    Question q4 = new Question(4,"Do you prefer: ","cultural activities","scientific endeavors");
    Question q5 = new Question(5,"Would you prefer to be part of a: ","cultural community","group formed around another identity (religion,sexuality,etc)");
    Question q6 = new Question(6,"Are you more interested in: ","government and politics","activism surrounding human and environmental rights");
    //4th level (clubs)  
    Question q7 = new Question(7,"","","");
    Question q8 = new Question(8,"","","");
    Question q9 = new Question(9,"","","");
    Question q10 = new Question(10,"","","");
    Question q11 = new Question(11,"","","");
    Question q12 = new Question(12,"","","");
    Question q13 = new Question(13,"","","");
    Question q14 = new Question(14,"","","");
    q7.setClubs(q7clubs);
    q8.setClubs(q8clubs);
    q9.setClubs(q9clubs);
    q10.setClubs(q10clubs);
    q11.setClubs(q11clubs);
    q12.setClubs(q12clubs);
    q13.setClubs(q13clubs);
    q14.setClubs(q14clubs);
    
    //create tree
    //leaves:
    LinkedBinaryTree<Question> l1 = new LinkedBinaryTree<Question>(q7);
    LinkedBinaryTree<Question> l2 = new LinkedBinaryTree<Question>(q8);
    LinkedBinaryTree<Question> l3 = new LinkedBinaryTree<Question>(q9);
    LinkedBinaryTree<Question> l4 = new LinkedBinaryTree<Question>(q10);
    LinkedBinaryTree<Question> l5 = new LinkedBinaryTree<Question>(q11);
    LinkedBinaryTree<Question> l6 = new LinkedBinaryTree<Question>(q12);
    LinkedBinaryTree<Question> l7 = new LinkedBinaryTree<Question>(q13);
    LinkedBinaryTree<Question> l8 = new LinkedBinaryTree<Question>(q14);
    //level 3 trees:
    LinkedBinaryTree<Question> t1 = new LinkedBinaryTree<Question>(q3,l1,l2);
    LinkedBinaryTree<Question> t2 = new LinkedBinaryTree<Question>(q4,l3,l4);
    LinkedBinaryTree<Question> t3 = new LinkedBinaryTree<Question>(q5,l5,l6);
    LinkedBinaryTree<Question> t4 = new LinkedBinaryTree<Question>(q6,l7,l8);
    //level 2 trees:
    LinkedBinaryTree<Question> t5 = new LinkedBinaryTree<Question>(q1,t1,t2);
    LinkedBinaryTree<Question> t6 = new LinkedBinaryTree<Question>(q2,t3,t4);
    //level 1 tree (total tree)
    this.tree = new LinkedBinaryTree<Question>(q0,t5,t6);
    
    }
    
    /*
     * getter - for GUI to interact with
     * @returns: LinkedBinaryTree<Question> entire tree of questions
     */
    public LinkedBinaryTree<Question> getTree() {
      return tree;
    }
   
  //main - for testing
  public static void main (String [] args) {
   //deleted testing code (scripts saved in Emily testing file)
  }
  
}