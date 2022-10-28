/*
Terminordle (Terminal + Wordle), inspired by New York Times

AP Computer Science Principles 2022 Create Preformance Task

All code is written by me and there was no collaboration

* The file that includes the list of words is not included as it would take up thousands of lines.
* The file is a .txt and is in the same folder as this program.
* The file includes 5 letter words, line by line, in which are used as the winning word the user needs to guess

*/


//import nessesary libraries
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;



public class terminordle
{

  //create constant variables for colors
  public static final String RESET = "\033[0m";  

  public static final String BLACK_BACKGROUND = "\033[40m";  
  public static final String GREEN_BACKGROUND = "\033[42m";  
  public static final String YELLOW_BACKGROUND = "\033[43m"; 
  public static final String WHITE_BACKGROUND = "\033[47m"; 

  public static final String BLACK_BOLD = "\033[1;30m";  
  public static final String WHITE_BOLD = "\033[1;37m";  

  // --------- MAIN METHOD -----------
  public static void main(String[] args) 
  {
    //------CREATING OBJECTS-----------
    // create new input, random, array(game board), and arraylist objects 
    Scanner keyboard = new Scanner(System.in);
    Random rgen = new Random();
    String[][] game = new String[6][5];
    ArrayList<String> words = new ArrayList<>();



    //-------CREATING WORDS FOR GAME----------
    // create a word list from text file
    words = wordList();
    // define guess (user will input)
    String guess;
    // set play to true (these booleans are used by the program to see if user has won or not, and if they want to play again)
    boolean play = true;
    boolean playGame = true;

    // while the user wants to play
    while (playGame)
    {
      play = true;
      
      //print spaces
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();

      // take a random word from that list
      String word = getRandomWordFromList(words); 

      //-----------WELCOME!-------------------
      System.out.println("WELCOME TO " + WHITE_BACKGROUND + BLACK_BOLD + " T " + RESET + " " + WHITE_BACKGROUND + BLACK_BOLD + " E " + RESET + " " + WHITE_BACKGROUND + BLACK_BOLD + " R " + RESET + " " + WHITE_BACKGROUND + BLACK_BOLD + " M " + RESET + " " + WHITE_BACKGROUND + BLACK_BOLD + " I " + RESET + " " + WHITE_BACKGROUND + BLACK_BOLD + " N " + RESET + " " + WHITE_BACKGROUND + BLACK_BOLD + " O " + RESET + " " + WHITE_BACKGROUND + BLACK_BOLD + " R " + RESET + " " + WHITE_BACKGROUND + BLACK_BOLD + " D " + RESET + " " + WHITE_BACKGROUND + BLACK_BOLD + " L " + RESET + " " + WHITE_BACKGROUND + BLACK_BOLD + " E " + RESET);

      //---------prepare the game---------
      // for every element in the game board
      for (int row = 0; row < game.length; row++)
      {
        // initialize the initial board with empty strings
        for (int col = 0; col < game[row].length; col++)
        {
          game[row][col] = BLACK_BACKGROUND + "   " + RESET + "";
        }
      } 

      // ask user to input a guess
      System.out.println("Enter word guess: ");

      //-------------game----------------
      // for every time they guess another word
      for (int turns = 0; turns < game.length && play; turns++)
      {
        // guess (defined at top) is their input
        guess = keyboard.next();
        // if the word is too long/short, return error and redo their turn
        if (guess.length() != 5)
        {
          System.out.println("Please enter a 5 letter word.");
          turns--;
        }
        else
        {
          // for every letter in the word they guess, format the row in the 2d game array
          for (int i = 0; i < word.length(); i++)
          {
            // format the letter in the word that should be green
            if (letterInWord(guess.substring(i,i+1), word) && letterInPosition(i, guess.substring(i,i+1), word))
            {
              game[turns][i] = GREEN_BACKGROUND + WHITE_BOLD + " " + guess.substring(i,i+1) + " " + RESET;
            }
            // format the letter in the word that should be yellow
            else if (letterInWord(guess.substring(i,i+1),word))
            {
              game[turns][i] = YELLOW_BACKGROUND + WHITE_BOLD + " " + guess.substring(i,i+1) + " " + RESET;
            }
            // else dont format the letter
            else
            {
              game[turns][i] = BLACK_BACKGROUND + WHITE_BOLD + " " + guess.substring(i,i+1) + " " + RESET;
            }
          }
          // print a black space after every turn for a cleaner game space
          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println();


          // print the formatted board from above
          for (int row1 = 0; row1 < game.length; row1++)
          {
            for (int col1 = 0; col1 < game[row1].length; col1++)
            {
              // print and add a space at the end for clarity
              System.out.print(game[row1][col1] + " ");
            }
            System.out.println("");
          }
          
          //more spaces
          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println();

          // if they get the word right, set play to false (since they are done playing), tell them they won, and break from the loop
          if (guess.equals(word))
          {
            play = false;
            turns++;
            System.out.println("You got the word in " + turns + " turns!");
            break;
          }
        }

      }
      // else if they ran out of space, tell them they lost and print what the word was
      if (play)
      {
        System.out.println("You lose :( - the word was: " + word);
        play = !play;
      }

      // ask if they want to play again (only ask if their game is finished)
      if (play == false)
      {
        System.out.println("Do you want to play again? (Y/N): ");
        String continuePlay = keyboard.next();
        playGame = continuePlay.equals("Y");
      }
      
    }
  }
  
  
  // ------------- (additional) STUDENT DEVELOPED PROCEDURES: -----------------

  // function to see if guess single letter is in word
  public static boolean letterInWord(String letter, String word)
  {
    for (int i = 0; i < word.length(); i++)
    {
      if (letter.equals(word.substring(i,i+1)))
      {
        return true;
      }
    }
    return false;
  }

  // fucntion to see if letter in word is in correct position
  public static boolean letterInPosition(int index, String letter, String word)
  {
    if (letter.equals(word.substring(index,index+1)))
    {
      return true;
    }
    return false;
  }

  // creates an arrayList of type string with words from an external file
  public static ArrayList<String> wordList()  
  {
    // create ArrayList of Strings to store the data, line by line
    ArrayList<String> list = new ArrayList<>();
    try 
    {
      // create a new file with parameter of the file name (file is in the same folder so this words)
      File file = new File("words.txt");
      
      // create a new scanner with the file
      Scanner s = new Scanner(file);

      // while the file has a next word, add it to the list
      while (s.hasNextLine())
      { list.add(s.nextLine()); }

      // close the scanner
      s.close();
    }

    // catch exeption if file isnt found
    catch(Exception e) 
    { e.printStackTrace(); }

    // return the arraylist
    return list;
  }

  // chooses a random word from an arraylist of strings
  public static String getRandomWordFromList(ArrayList<String> wordList)
  {
    Random rgen = new Random();
    String word = wordList.get(rgen.nextInt(wordList.size()));
    word = word.toLowerCase();
    return word;
  }



}
