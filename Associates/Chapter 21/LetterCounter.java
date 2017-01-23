/*
Program Name: Letter Counter
Program Purpose: Count the occurences of vowels and consonants using a set for the vowels.
Author: Michael Girard
Date Last Modified: January 24, 2015.
Program created using the comprehensive 10th edition textbook, JDK 8u20, and jGRASP 2.0.1_01.
*/

import java.util.*;
import java.io.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.HPos;

public class LetterCounter extends Application{
   public static void main(String[] args){
      Application.launch(args);
   }
   
   public void start(Stage stage){
      final int COLUMN_WIDTH = 50;
      
      //Create the controls
      GridPane pane = new GridPane();
      
      Label instructions = new Label("  Enter the name of a text file and press the" + 
         "\nbutton to count the letters contained within!");
      Label vowels = new Label(" Vowels :");
      Label consonants = new Label(" Consonants :");
      
      TextField fileNameField = new TextField();
      
      Button submit = new Button("Count 'em!");
      
      //Pane styling
      pane.setPadding(new Insets(10,10,10,10));
      pane.setAlignment(Pos.CENTER);
      pane.setHgap(15);
      pane.setVgap(8);
      ColumnConstraints col1 = new ColumnConstraints(COLUMN_WIDTH);
      ColumnConstraints col2 = new ColumnConstraints(COLUMN_WIDTH);
      ColumnConstraints col3 = new ColumnConstraints(COLUMN_WIDTH);
      ColumnConstraints col4 = new ColumnConstraints(COLUMN_WIDTH);
      ColumnConstraints col5 = new ColumnConstraints(COLUMN_WIDTH);
      pane.getColumnConstraints().addAll(col1, col2, col3, col4, col5);
      
      //Label styling
      pane.setColumnSpan(instructions, 5);
      pane.setRowSpan(instructions, 2);
      pane.setHalignment(instructions, HPos.CENTER);
      
      pane.setColumnSpan(vowels, 2);
      vowels.setPrefWidth(COLUMN_WIDTH * 3);
      vowels.setStyle("-fx-border-style: solid");
      
      pane.setColumnSpan(consonants, 3);
      pane.setHalignment(consonants, HPos.RIGHT);
      consonants.setPrefWidth(COLUMN_WIDTH * 3);
      consonants.setStyle("-fx-border-style: solid");
      
      //TextField styling
      pane.setColumnSpan(fileNameField, 5);
      
      //Button styling
      pane.setColumnSpan(submit, 5);
      pane.setHalignment(submit, HPos.CENTER);
      submit.setPrefWidth(150);
      
      //content insertion
      pane.add(instructions, 0, 0);
      pane.add(fileNameField, 0, 2);
      pane.add(submit, 0, 3);
      pane.add(vowels, 0, 4);
      pane.add(consonants, 2, 4);
      
      //Create a scene, insert the pane, and show
      Scene scene = new Scene(pane, 350, 150);
      stage.setTitle("Letter Counter");
      stage.setScene(scene);
      stage.show();
      
      //Button event handler
      submit.setOnMouseClicked(e ->{
         File file = new File(fileNameField.getText());
         try{
            //Call the counting method and update the display with the results
            int[] results = countLetters(file);
            vowels.setText(" Vowels : " + results[0]);
            consonants.setText(" Consonants : " + results[1]);
         }
         catch(FileNotFoundException ex) {
            //An error pops up if the file doesn't exist
            final Stage message = new Stage();
            
            //Create and style the pane
            GridPane messagePane = new GridPane();
            messagePane.setAlignment(Pos.CENTER);
            messagePane.setPadding(new Insets(5,5,5,5));
            messagePane.setVgap(5);
            
            //Create and style the button
            Button close = new Button("Close");
            messagePane.setHalignment(close, HPos.CENTER);
            
            //Insert content
            messagePane.add(new Label("A file by that name does not exist!"), 0, 0);
            messagePane.add(close, 0, 1);
            
            //Create a scene, insert the pane, and show
            Scene messageScene = new Scene(messagePane, 200, 60);
            message.setTitle("Error!");
            message.setScene(messageScene);
            message.show();
            
            //Button event handler
            close.setOnAction(e2 ->{
               message.close();
            });
         }
      });      
   }
   
   static int[] countLetters(File file) throws FileNotFoundException{
      //Declare variables
      int vowelCount = 0;
      int consCount = 0;
      
      //Create the set
      String[] vowels = {"A", "E", "I", "O", "U"};
      Set<String> set = new HashSet<String>(Arrays.asList(vowels));
      
      //Scan the file for vowels
      Scanner input = new Scanner(file);
      
      while (input.hasNext()){
         //Get a word from the file and convert it to upper case
         String currentWord = input.next().toUpperCase();
         
         /*Take each letter in the word and see if it has a match in the set.
           If so, increment vowel count. If it doesn't AND it's a letter,
           increment consonant count instead.*/
         for(int index = 0; index < currentWord.length(); index++){
            String currentLetter = currentWord.substring(index, index + 1);
            if (set.contains(currentLetter)){
               vowelCount++;
            }
            else if (currentLetter.matches("[A-Z]")){
               consCount++;
            }
         }
      }
      
      int[] results = {vowelCount, consCount};
      return results;
   }
}