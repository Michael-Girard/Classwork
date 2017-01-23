/*
Program Name: Radix Sort
Program Purpose: Sort 1,000,000 integers using the Radix sort method.
Author: Michael Girard
Date Last Modified: February 5, 2015.
Program created using the comprehensive 10th edition textbook, JDK 8u31, and jGRASP 2.0.1_01.

NOTE: JGrasp's Run I/O log seems to be inconsistent in the number of lines it'll display. I'm
   sending the argument NUMBER_OF_INTEGERS / 3500 to the displayList function to scale the
   number of integers displayed per line with the number of integers to hopefully get all the
   output to fit regardless of the number of integers.
   
   The program takes a bit to sort 1,000,000 integers with values between 0 and 9,999. Reduce
   NUMBER_OF_INTEGERS or INTEGER_DIGIT_LENGTH to make the process faster.
*/

import java.util.*;

public class RadixSort{
   //Class variables
   private static int currentRun = 0;
   private static int neededRuns = 0;

   public static void main(String[] args){
      final int NUMBER_OF_ARRAYLISTS = 20;
      final int NUMBER_OF_INTEGERS = 1000;
      final int INTEGER_DIGIT_LENGTH = 4;
      ArrayList<ArrayList<Integer>> integerList = new ArrayList<ArrayList<Integer>>();
      int currentInteger;
      int remainingIntegers = NUMBER_OF_INTEGERS % 10;
      
      //Create the integer lists
      for (int index = 0; index < NUMBER_OF_ARRAYLISTS; index++){
         integerList.add(new ArrayList<Integer>());
         
         //Evenly add NUMBER_OF_INTEGERS integers to the 0-9 ArrayLists
         if (index < 10){
            //Declare/initialize loop variable
            int index2 = 0;
            
            /*This selection ensures that all integers are added if
               NUMBER_OF_INTEGERS isn't divisible by 10.*/
            if (remainingIntegers > 0){
               index2--;
               remainingIntegers--;
            }
            
            while (index2 < Math.floor(NUMBER_OF_INTEGERS / 10)){
               currentInteger = (int)(Math.random() * Math.pow(10, INTEGER_DIGIT_LENGTH));
               integerList.get(index).add(currentInteger);
               
               //Needed runs should be equal to the number of digits in the highest int
               if (String.valueOf(currentInteger).length() > neededRuns){
                  neededRuns = String.valueOf(currentInteger).length();
               }
               index2++;
            }
         }
      }
      
      //Display the unsorted integers
      //System.out.print("----------Unsorted Integers----------\n");
      //displayList(integerList, NUMBER_OF_INTEGERS / 3500);
      
      //Call the radix sorting method - a message is displayed if the NUMBER_OF_INTEGERS is high
      if (NUMBER_OF_INTEGERS >= 300000){
         System.out.println("Sorting " + NUMBER_OF_INTEGERS + " integers... This may take some time.");
      }
      integerList = radixSort(integerList);
      
      //Display the sorted integers
      System.out.print("\n----------Sorted Integers----------\n");
      displayList(integerList, NUMBER_OF_INTEGERS / 3500);
   }
   
   public static ArrayList<ArrayList<Integer>> radixSort(ArrayList<ArrayList<Integer>> integerList){
      //Method variables
      int currentInteger;
      int radix;
      int listSize;
      
      /*
        On the even runs, remove an item from arraylists 0-9, find the item's radix, and 
        insert it into the appropriate arraylist in 10-19 until arraylists 0-9 are empty. 
         
        On the odd runs, remove an item from arraylists 10-19, find the item's radix, and 
        insert it into the appropriate arraylist in 0-9 until arraylists 10-19 are empty.
         
        Alternate neededRuns times (probably equal to INTEGER_DIGIT_LENGTH) until sorted.
      */
      for (int index = 0, index2 = 10 * (currentRun % 2); index < 10; index++){
         listSize = integerList.get(index + index2).size();
            
         for (int index3 = 0; index3 < listSize; index3++){
            currentInteger = integerList.get(index + index2).remove(0);
            radix = (int)(currentInteger / Math.pow(10, currentRun)) % 10;
            integerList.get(radix + 10 - index2).add(currentInteger);
         } 
      }
      currentRun++;
      
      //If the lists need additional runs to sort, recursively call this method
      if (neededRuns <= currentRun){
         return integerList;
      }
      else{
         return radixSort(integerList); //Tail recursion?
      }
   }
   
   //displayList uses nested loops to display all of the integers
   public static void displayList(ArrayList<ArrayList<Integer>> integerList, final int INTEGERS_PER_LINE){
      //Method variables
      StringBuilder sortedIntegers = new StringBuilder();
      int currentLine = 0;
      
      for (ArrayList<Integer> list : integerList){
         if (list.size() != 0){
            System.out.println();
            
            for (int index = 0; index < list.size(); index++){
               sortedIntegers.append(String.valueOf(list.get(index)) + " "); //Append the list's integers
               currentLine++;
               
               /*If there are at minimum 25 integers to display or the last integer has been added, 
                  display the integers and then clear the StringBuilder.*/
               if (currentLine == Math.max(INTEGERS_PER_LINE, 25) || index + 1 == list.size()){
                  System.out.println(sortedIntegers);
                  sortedIntegers.setLength(0);
                  currentLine = 0;
               }
            }
         }
      }
   }
}