/*
Program Name: Closest Pair: Divide and Conquer
Program Purpose: Find the closest pair of points by using the Divide and Conquer method.
Author: Michael Girard
Date Last Modified: February 1, 2015.
Program created using the comprehensive 10th edition textbook, JDK 8u31, and jGRASP 2.0.1_01.

NOTE: This note assumes that you saw my angel message.
   After rewriting the recursive method from scratch for a third time, and after a few hours
   of hunting down logic errors, IndexOutOfBoundsExceptions and NullPointerExceptions, somehow 
   I seem to have made it work. I doubt it's bulletproof, but it seems to be returning the 
   expected pair when I feed it a custom set of points that I've already calculated the closest pair for.
   I also think I figured out how Liang wanted me to implement the other methods (the overloaded getClosestPair()
   methods). The method with the Double[][] is still unused, because why create a Double[][] and then make a
   Point[] from it if I could just make the Point[] from the start and be no worse off? I still coded it in.
*/

import java.util.*;

public class ClosestPair{
   public static void main(String[] args){
      final int NUMBER_OF_POINTS = 50;
      Point[] points = new Point[NUMBER_OF_POINTS];
      
      //Create the Points and put them into the array
      for (int index = 0; index < NUMBER_OF_POINTS; index++){
         points[index] = new Point(Math.random() * 100, Math.random() * 100);
      }
      
      //Call the getClosestPair method and print the results
      Pair closestPair = Pair.getClosestPair(points);
      
      System.out.println("The closest pair,");
      System.out.print("[" + closestPair.getP1().getX() + ", " + closestPair.getP1().getY() + "] and ");
      System.out.println("[" + closestPair.getP2().getX() + ", " + closestPair.getP2().getY() + "]");
      System.out.println("has a distance of " + closestPair.getDistance());
   }
}

class Pair{
   //Pair attributes and variables
   private Point p1;
   private Point p2;
   private double distance;
   static ArrayList<Pair> results = new ArrayList<Pair>(); //The results from the recursive method
   
   //A no-arg constructor to make a pair that will never be the closest
   public Pair(){
      new Pair(new Point(-99999999, -99999999), new Point(99999999, 99999999));
   }
      
   public Pair(Point p1, Point p2){
      this.p1 = p1;
      this.p2 = p2;
      this.distance = distance(this.p1, this.p2);
   }
   
   public Point getP1(){
      return p1;
   }
   
   public Point getP2(){
      return p2;
   }
   
   public double getDistance(){
      return distance;
   }
      
   // Return the distance of the closest pair of points
   // Method currently unused.
   public static Pair getClosestPair(double[][] points){
      Pair closestPair;
      
      //Create a Point[]
      Point[] pointsObjectArray = new Point[points[0].length];
      
      //Fill the Point[]
      for (int index = 0; index < points[0].length; index++){
         pointsObjectArray[index] = new Point(points[index][0], points[index][1]);
      }
      
      //Call the other getClosestPair method
      closestPair = Pair.getClosestPair(pointsObjectArray);
      return closestPair;
   }
   
   // Return the distance of the closest pair of points
   public static Pair getClosestPair(Point[] points){
      Pair closestPair;
   
      //Create pointsOrderedOnX and pointsOrderedOnY
      Point[] pointsOrderedOnX = new Point[points.length];
      Point[] pointsOrderedOnY = new Point[points.length];
      
      //Fill/sort pointsOrderedOnX and fill/sort pointsOrderedOnY
      pointsOrderedOnX = points.clone();
      pointsOrderedOnY = pointsOrderedOnX.clone();
      Arrays.sort(pointsOrderedOnX);
      Arrays.sort(pointsOrderedOnY, new CompareY());
      
      //Get and return the closest pair
      closestPair = Pair.distance(pointsOrderedOnX, 0, pointsOrderedOnX.length - 1, pointsOrderedOnY);
      return closestPair; 
   }
   
   /** Return the distance of the closest pair of points
   * in pointsOrderedOnX[low..high]. This is a recursive
   * method. pointsOrderedOnX and pointsOrderedOnY are
   * not changed in the subsequent recursive calls.
   */
   public static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY){
      
      //If there's 0-1 point, return a fictional pair with a huge distance
      if (low >= high){
         return new Pair();
      }
      //If there's 2 points, return those points as a pair
      else if (low + 1 == high){
         return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);
      }
      /*(pointsOrderedOnY.length >= 2)*/
      //Split the Points array into two smaller arrays
      int mid = (int)Math.ceil((high + low) / 2.0);
      
      Point[] leftHalf = new Point[mid - low + 1];
      Point[] rightHalf = new Point[high - mid];
      
      /*Distribute one half of pointsOrderedOnY into leftHalf and the other
         half into rightHalf. Midpoint goes into leftHalf.*/
      for (int index = 0, index2 = 0, index3 = 0; index < pointsOrderedOnY.length; index++){
         if (pointsOrderedOnY[index].compareTo(pointsOrderedOnX[mid]) <= 0){
            leftHalf[index2++] = pointsOrderedOnY[index];
         }
         else{
            rightHalf[index3++] = pointsOrderedOnY[index];
         }
      }
         
      //Recursive method calls
      results.add(distance(pointsOrderedOnX, low, mid, leftHalf));
      results.add(distance(pointsOrderedOnX, mid + 1, high, rightHalf));
      
      //Find the pair in the halves that has the shortest distance
      Pair closestPair = new Pair();
      double currentDistance;
      double lowestDistance = 999999;
      int lowestIndex = 99999;
      
      for (int index = 0; index < results.size(); index++){
         currentDistance = results.get(index).getDistance();
         if (currentDistance != 0 && currentDistance < lowestDistance){
            closestPair = results.get(index);
            lowestDistance = results.get(index).getDistance();
            lowestIndex = index;
         }
      }
         
      //Create Strip L
      int count = 0;
      for (int index = 0; index < leftHalf.length; index++){
         /*If the Point in leftHalf isn't null and is between the leftHalf
            and rightHalf boundary and the leftHalf-lowestDistance boundary, increment count*/
         if (leftHalf[index].getX() >= pointsOrderedOnX[mid].getX() - lowestDistance){
            count++;
         }
      }
      Point[] stripL = new Point[count];
      
      //Fill Strip L
      for (int index = 0, index2 = 0; index < leftHalf.length; index++){
         /*If the Point in leftHalf isn't null and  is between the leftHalf
            and rightHalf boundary and the leftHalf-lowestDistance boundary, add it to stripL*/
         if (leftHalf[index].getX() >= pointsOrderedOnX[mid].getX() - lowestDistance){
            stripL[index2++] = leftHalf[index];
         }
      }
      
      //Create Strip R
      int count2 = 0;
         /*If the Point in rightHalf isn't null and  is between the leftHalf
            and rightHalf boundary and the leftHalf+lowestDistance boundary, increment count*/
      for (int index = 0; index < rightHalf.length; index++){
         if (rightHalf[index] != null && rightHalf[index].getX() <= pointsOrderedOnX[mid].getX() + lowestDistance){
            count2++;
         }
      }
      Point[] stripR = new Point[count2];
      
      //Fill Strip R
      for (int index = 0, index2 = 0; index < rightHalf.length; index++){
         /*If the Point in rightHalf isn't null and  is between the leftHalf
            and rightHalf boundary and the leftHalf+lowestDistance boundary, add it to stripR*/
         if (rightHalf[index] != null && rightHalf[index].getX() <= pointsOrderedOnX[mid].getX() + lowestDistance){
            stripR[index2++] = rightHalf[index];
         }
      }
      
      /*Identify and then compare points in one strip against the points in the other strip as long
         as they're within a certain rectangular distance*/
      Pair currentPair;
      for (int index = 0, index2 = 0, index3; index < stripL.length; index++){
         while (index2 < stripR.length && stripL[index].getY() > stripR[index2].getY() + lowestDistance){
            index2++;
         }
         
         index3 = index2;   
         while (index3 < stripR.length && stripR[index3].getY() <= stripL[index].getY() + lowestDistance){
            currentPair = new Pair(stripL[index], stripR[index3++]);
            if (closestPair.getDistance() > currentPair.getDistance()){
               closestPair = currentPair;
            }
         }
      }
      return closestPair;
   }
   
   /** Compute the distance between two points p1 and p2 */
   public static double distance(Point p1, Point p2){
      return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
   }
   
   /** Compute the distance between points (x1, y1) and (x2, y2) */
   public static double distance(double x1, double y1, double x2, double y2){
      return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(x2 - x1, 2));
   }
}

class Point implements Comparable<Point>{
   private double x;
   private double y;
   
   public Point(double x, double y){
      this.x = x;
      this.y = y;
   }
   
   public double getX(){
      return this.x;
   }
   
   public void setX(int x){
      this.x = x;
   }
   
   public double getY(){
      return this.y;
   }
   
   public void setY(int y){
      this.y = y;
   }

   @Override
   public int compareTo(Point point){
      if (this.getX() > point.getX()){
         return 1;
      }
      else if (this.getX() < point.getX()){
         return -1;
      }
      else{
         if (this.getY() > point.getY()){
         return 1;
         }
         else if (this.getY() < point.getY()){
            return -1;
         }
         else{
            return 0;
         }
      }
   }
}

class CompareY implements Comparator<Point>, java.io.Serializable{
   @Override
   public int compare(Point p1, Point p2){
      if (p1.getY() > p2.getY()){
         return 1;
      }
      else if (p1.getY() < p2.getY()){
         return -1;
      }
      else{
         if (p1.getX() > p2.getX()){
         return 1;
         }
         else if (p1.getX() > p2.getX()){
            return -1;
         }
         else{
            return 0;
         }
      }
   }
}