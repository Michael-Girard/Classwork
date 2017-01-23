/*
Program Name: Doubly-Linked Linked List
Program Purpose: Implement the Doubly-Linked Linked List.
Author: Michael Girard
Date Last Modified: February 15, 2015.
Program created using the comprehensive 10th edition textbook, JDK 8u31, and jGRASP 2.0.1_01.
*/

import java.util.*;

   //-----------------------------------------
   //       TwoWayLinkedList Class
   //-----------------------------------------
class TwoWayLinkedList<E> extends AbstractSequentialList<E>{
   private Node<E> head, tail;
   private int size = 0;

   public static void main(String[] args){
      final int NUMBER_OF_OBJECTS = 1;
      final int NUMBER_OF_CHARACTERS = 5;
      final int INTEGER_DIGIT_LENGTH = 3;
      TwoWayLinkedList<Integer> integerList = new TwoWayLinkedList<Integer>();
      TwoWayLinkedList<String> stringList = new TwoWayLinkedList<String>();
      StringBuilder current = new StringBuilder();
      
      //Filling a LinkedList of Integers and Strings
      for (int index = 0; index < NUMBER_OF_OBJECTS; index++){
         integerList.addLast(new Integer((int)(Math.random() * Math.pow(10, INTEGER_DIGIT_LENGTH))));
         
         for (int index2 = 0; index2 < NUMBER_OF_CHARACTERS; index2++){
            current.append((char)(97 + (int)(Math.random() * 26)));
         }
         stringList.addLast(current.toString());
         current.setLength(0);
      }
      
      //Testing size(), add(int, E e), get(int), set(int, E e), and remove(int)
      System.out.println("------------------------------------\nsize(), add(int, E e), get(int), set(int, E e), and remove(int) tests\n------------------------------------");
      System.out.println("The NUMBER_OF_OBJECTS is " + NUMBER_OF_OBJECTS + ", so both lists should have sizes equal to that.");
      System.out.println("integerList.size() returns: " + integerList.size());
      System.out.println("stringList.size() returns: " + stringList.size());
      
      int newIndex = integerList.size() / 2;
      integerList.add(newIndex, new Integer(25));
      stringList.add(newIndex, new String("Test"));
      System.out.println("\nAdded new Nodes at index " + newIndex); 
      System.out.println("In the integerList, \"25\" was added. The results of integerList.get(25): " + integerList.get(newIndex));
      System.out.println("In the stringList, \"Test\" was added. The results of stringList.get(25): " + stringList.get(newIndex));
      
      System.out.println("\nThe new size of Integer List should be 1 higher than it was before. integerList.size() returns: " + integerList.size());
      System.out.println("The new size of String List should be 1 higher than it was before. stringList.size() returns: " + stringList.size());
      
      integerList.set(newIndex, new Integer(101));
      stringList.set(newIndex, new String("Tset"));
      System.out.println("\nModified the Nodes at index " + newIndex); 
      System.out.println("Getting the 25th node should now display \"101\". integerList.get(25) returns: " + integerList.get(newIndex));
      System.out.println("Getting the 25th node should now display \"Tset\". stringList.get(25) returns: " + stringList.get(newIndex));
      
      integerList.remove(newIndex);
      stringList.remove(newIndex);
      System.out.println("\nRemoved the nodes at index " + newIndex);
      System.out.println("The size of Integer List should be " + NUMBER_OF_OBJECTS + " again. integerList.size() returns: " + integerList.size());
      System.out.println("The size of String List should be " + NUMBER_OF_OBJECTS + " again. integerList.size() returns: " + stringList.size());
      
      //Testing addFirst(E e), getFirst(), and removeFirst()
      System.out.println("\n------------------------------------\naddFirst(E e), getFirst(), and removeFirst() tests\n------------------------------------");
      Integer temp = integerList.getFirst();
      String temp2 = stringList.getFirst();
      System.out.println("integerList.getFirst() returns the first element as being: " + integerList.getFirst());
      System.out.println("stringList.getFirst() returns the first element as being: " + stringList.getFirst());
      
      integerList.addFirst(new Integer(0));
      stringList.addFirst(new String("Hi"));
      System.out.println("\nAdded new first elements to both lists. \"0\" for integerList and \"Hi\" for stringList.");
      System.out.println("integerList.getFirst() returns the first element as being: " + integerList.getFirst());
      System.out.println("stringList.getFirst() returns the first element as being: " + stringList.getFirst());
      
      
      System.out.println("\nThe second elements should be the same as the first getFirst() test now: " + temp.toString() + " and " + temp2.toString());
      System.out.println("integerList.get(1) returns: " + integerList.get(1));
      System.out.println("stringList.get(1) returns: " + stringList.get(1));
      
      integerList.removeFirst();
      stringList.removeFirst();
      if (integerList.size() > 1){
         System.out.println("\nRemoved the first elements from both lists. The second elements should be different now.");
         System.out.println("integerList.get(1) returns: " + integerList.get(1));
         System.out.println("stringList.get(1) returns: " + stringList.get(1));
      }
      
      //Testing addLast(E e), getLast(), and removeLast()
      System.out.println("\n------------------------------------\naddLast(E e), getLast(), and removeLast() tests\n------------------------------------");
      temp = integerList.getLast();
      temp2 = stringList.getLast();
      System.out.println("integerList.getLast() returns: " + integerList.getLast());
      System.out.println("integerList.getLast() returns: " + stringList.getLast());
      
      integerList.addLast(new Integer(999));
      stringList.addLast(new String("There"));
      System.out.println("\nAdded new last elements to the lists. For integer, \"999\". For string, \"There\".");
      System.out.println("integerList.getLast() now returns: " + integerList.getLast());
      System.out.println("integerList.getLast() now returns: " + stringList.getLast());
      
      System.out.println("\nThe second to last elements should be the same as the first getLast() test now: " + temp.toString() + " and " + temp2.toString());
      System.out.println("integerList.get(integerList.size() - 2)) returns: " + integerList.get(integerList.size() - 2));
      System.out.println("stringList.get(stringList.size() - 2)) returns: " + stringList.get(stringList.size() - 2));
      
      integerList.removeLast();
      stringList.removeLast();
      if (integerList.size() > 1){
         System.out.println("\nRemoved the last elements from both lists. The second to last elements should be different now.");
         System.out.println("integerList.get(integerList.size() - 2)) returns: " + integerList.get(integerList.size() - 2));
         System.out.println("stringList.get(stringList.size() - 2)) returns: " + stringList.get(stringList.size() - 2));
      }
      //Testing contains(E e), indexOf(E e), lastIndexOf(E e)
      int randomIndex = (int)(Math.random() * NUMBER_OF_OBJECTS);
      temp = integerList.get(randomIndex);
      temp2 = stringList.get(randomIndex);
      System.out.println("\n------------------------------------\ncontains(E e), indexOf(E e), lastIndexOf(E e) tests\n------------------------------------");
      System.out.println("Getting a value from the list and then using contains(that value) should always return true:");
      System.out.println("integerList.contains(" + temp.toString() + ") returns: " + integerList.contains(temp));
      System.out.println("stringList.contains(" + temp2.toString() + ") returns: " + stringList.contains(temp2));
      
      System.out.println("\nUsing the contains method with an impossible value should always return false:");
      //Create an integer so large and a string so lengthy that they couldn't possibly be in the lists
      Integer impossibleInteger = new Integer((int)Math.pow(10, INTEGER_DIGIT_LENGTH + 1));
      for (int index2 = 0; index2 < NUMBER_OF_CHARACTERS + 1; index2++){
         current.append((char)(97 + (int)(Math.random() * 26)));
      }
      String impossibleString = new String(current.toString());
      current.setLength(0);
      System.out.println("integerList.contains(" + impossibleInteger.toString() + ") returns: " + integerList.contains(impossibleInteger));
      System.out.println("stringList.contains(" + impossibleString + ") returns: " + stringList.contains(impossibleString));
      
      System.out.println("\nTesting indexOf and lastIndexOf:");
      System.out.println("integerList.indexOf(" + temp.toString() + ") should return " + randomIndex + ". It returns: " + integerList.indexOf(temp));
      System.out.println("stringList.indexOf(" + temp2.toString() + ") should return " + randomIndex + ". It returns: " + stringList.indexOf(temp2));
      
      System.out.println("integerList.lastIndexOf(" + temp.toString() + ") will probably return " + randomIndex + ". It returns: " + integerList.lastIndexOf(temp));
      System.out.println("stringList.lastIndexOf(" + temp2.toString() + ") will probably return " + randomIndex + ". It returns: " + stringList.lastIndexOf(temp2));
      
      //Testing toString
      System.out.println("\n------------------------------------\ntoString test\n------------------------------------");
      System.out.println("Both of the lists output using toString():");
      System.out.println(integerList.toString() + "\n" + stringList.toString());
      
      //Creating and testing iterators
      System.out.println("\n------------------------------------\nIterator Tests\n------------------------------------");
      ListIterator<Integer> integerIterator = integerList.listIterator();
      ListIterator<String> stringIterator = stringList.listIterator();
     
      //Testing the iterator's add(E e), next(), and previous() methods
      
      System.out.println("------------------------------------\nadd(E e), next(), and previous() method tests\n------------------------------------");
     
      temp = integerIterator.next();
      integerIterator.previous();
      temp2 = stringIterator.next();
      stringIterator.previous();
      System.out.println("Getting the first element, then using next(), then\nusing previous() should all return the same value:");
      System.out.println("Integers: " + integerList.getFirst() + " = " + integerIterator.next().toString() + " = " + integerIterator.previous().toString());
      System.out.println("Strings: " + stringList.getFirst() + " = " + stringIterator.next().toString() + " = " + stringIterator.previous().toString());
         
      System.out.println("\nAfter using the iterator's add() method to insert \"40404\", the previous() method should return \"40404\".");
      integerIterator.add(new Integer(40404));
      System.out.println("integerIterator.previous() returns: " + integerIterator.previous());
      
      System.out.println("\nAfter using the iterator's add method to insert \"Bingo\", the previous() method should return \"Bingo\".");
      stringIterator.add("Bingo");
      System.out.println("stringIterator.previous() returns: " + stringIterator.previous());
      
      System.out.println("\nThe list sizes should now be " + (NUMBER_OF_OBJECTS + 1) + ". Integers: " + integerList.size() + ". Strings: " + stringList.size() + ".");
      
      //Testing the iterator's hasPrevious()and previousIndex() methods
      System.out.println("\n------------------------------------\npreviousIndex() and hasPrevious() method tests\n------------------------------------");
      integerIterator.previous();
      stringIterator.previous();
      System.out.println("At the first element, hasPrevious()and previousIndex() should return \"false\" and \"-1\": " + integerIterator.hasPrevious() + " " + integerIterator.previousIndex());
      System.out.println("At the first element, hasPrevious()and previousIndex() should return \"false\" and \"-1\": " + stringIterator.hasPrevious() + " " + stringIterator.previousIndex());
      integerIterator.next();
      stringIterator.next();
      System.out.println("At the second element, hasPrevious()and previousIndex() should return \"true\" and \"0\": " + integerIterator.hasPrevious() + " " + integerIterator.previousIndex());
      System.out.println("At the second element, hasPrevious()and previousIndex() should return \"true\" and \"0\": " + stringIterator.hasPrevious() + " " + stringIterator.previousIndex());
      
      //Testing the iterator's hasNext() and nextIndex() methods
      System.out.println("\n------------------------------------\nnextIndex() and hasNext() method tests\n------------------------------------");
      for (int index = 0; index < integerList.size() - 2; index++){
         integerIterator.next();
         stringIterator.next();
      }
      
      System.out.println("At the last element, hasNext() and nextIndex() should return \"true\" and \"" + (NUMBER_OF_OBJECTS + 1) + "\": " + integerIterator.hasNext() + " " + integerIterator.nextIndex());
      System.out.println("At the last element, hasNext() and nextIndex() should return \"true\" and \"" + (NUMBER_OF_OBJECTS + 1) + "\": " + stringIterator.hasNext() + " " + stringIterator.nextIndex());
      integerIterator.previous();
      stringIterator.previous();
      System.out.println("At the second to last element, hasNext() and nextIndex() should return \"true\" and \"" + NUMBER_OF_OBJECTS + "\": " + integerIterator.hasNext() + " " + integerIterator.nextIndex());
      System.out.println("At the second to last element, hasNext() and nextIndex() should return \"true\" and \"" + NUMBER_OF_OBJECTS + "\": " + stringIterator.hasNext() + " " + stringIterator.nextIndex());
      
      //Testing the iterator's remove() and set(E e) methods
      System.out.println("\n------------------------------------\nremove() and set(E e) method tests\n------------------------------------");
      int index;
      for (index = 0; index < newIndex; index++){
         integerIterator.previous();
         stringIterator.previous();
         
      }
      System.out.println("The cursor has been moved to element " + index + ".");
      System.out.println("Element " + index + " in the lists are currently " + integerIterator.next() + " and " + stringIterator.next());
      integerIterator.previous();
      stringIterator.previous();
      integerIterator.set(new Integer(1337));
      stringIterator.set("Success");
      System.out.println("After using previous() again, element " + index + " has been changed to \"1337\" and \"Success\" using the set() method");
      System.out.println("integerIterator.next() returns: " + integerIterator.next());
      System.out.println("stringIterator.next() returns: " + stringIterator.next());
      
      System.out.println("\nNow at element " + (index + 1) + ", the lists still have sizes of " + integerList.size() + " and " + stringList.size() + ".");
      integerIterator.remove();
      stringIterator.remove();
      System.out.println("Using the remove() method to delete element " + (index + 1) + "... The list size should now be one less.");
      System.out.println("integerList.size() returns: " + integerList.size());
      System.out.println("stringList.size() returns: " + stringList.size());
      
      System.out.println("\nAttempting to use the set or remove methods directly after a remove() should throw errors. There should be four errors after this text.");
      integerIterator.set(new Integer(7008));
      stringIterator.set("Failure");
      integerIterator.remove();
      stringIterator.remove();
      
      //Iterator(index) test
      if (integerList.size() >= 5){
         integerIterator = integerList.listIterator(5);
         stringIterator = stringList.listIterator(5);
         
         System.out.println("\nNew iterators have been created with the listIterator(index) method, with an index of 5.");
         System.out.println("The iterator .next() method should return the same as list.get(5) with an iterator created with index 5:");
         System.out.println("integerIterator.next() vs integerList.get(5): " + integerIterator.next() + " vs " + integerList.get(5));
         System.out.println("stringIterator.next() vs stringList.get(5): " + stringIterator.next() + " vs " + stringList.get(5));
      }
   }
   
   //Default TwoWayLinkedList
   public TwoWayLinkedList(){
   }
   
   //Create a TwoWayLinkedList populated with objects from an existing array
   public TwoWayLinkedList(E[] objects){
      for (int index = 0; index < objects.length; index++){
         add(objects[index]);
         size++;
      }
   }
   
   //Create a listIterator with the cursor at index 0
   public ListIterator<E> listIterator(){
      return listIterator(0);
   }
   
   //Create a listIterator with the cursor at the index
   public ListIterator<E> listIterator(int index){
      return new LinkedListIterator(index);
   }
   
   //Get the list's size
   public int size(){
      return size;
   }
   
   //------------------------------------------
   //         MyLinkedList Methods
   //------------------------------------------
   //Get the first element in the list
   public E getFirst() {
      if (size == 0) {
         return null ;
      }
      else {
         return head.element;
      }
   }
   
   //Get the last element in the list
   public E getLast() {
      if (size == 0) {
         return null ;
      }
      else {
         return tail.element;
      }
   }
   
   //Add an element to the beginning of the list
   public void addFirst(E e){
      Node<E> newNode = new Node<>(e);
      newNode.next = head;
      newNode.previous = null;
      head.previous = newNode;
      head = newNode;
      size++;
      
      if (tail == null){
         tail = head;
         newNode.next = null;
      }
   }
   
   //Add an element to the end of the list
   public void addLast(E e){
      Node<E> newNode = new Node<>(e);
      
      if (tail == null){
         head = tail = newNode;
         newNode.previous = null;
      }
      else{
         tail.next = newNode;
         newNode.previous = tail;
         tail = tail.next;
      }
      newNode.next = null;
      size++;
   }
   
   //Override List's add method to use addLast
   @Override
   public boolean add(E e){
      addLast(e);
      return true;
   }
   
   //Add an element at the specified index
   @Override
   public void add(int index, E e){
      if (index == 0){
         addFirst(e);
      }
      else if (index >= size){
         addLast(e);
      }
      else{
         Node<E> current = head;
         for (int i = 1; i < index; i++){
            current = current.next;
         }
         Node<E> temp = current.next;
         current.next = new Node<E>(e);
         (current.next).previous = current;
         (current.next).next = temp;
         ((current.next).next).previous = current.next;
         size++;
      }
   }
   
   //Remove the head and return the head's element
   public E removeFirst(){
      if (size == 0){
         return null;
      }
      else{
         Node<E> temp = head;
         head = head.next;
         head.previous = null;
         size--;
         if (head == null){
            tail = null;
         }
         return temp.element;
      }
   }
   
   //Remove the tail and return the tail's element
   public E removeLast(){
      if (size == 0){
         return null;
      }
      else if (size == 1){
         Node<E> temp = head;
         head = tail = null;
         size = 0;
         return temp.element;
      }
      else{
         Node<E> current = head;
         
         for (int i = 0; i < size - 2; i++){
            current = current.next;
         }
         
         Node<E> temp = tail;
         tail = current;
         tail.next = null;
         size--;
         return temp.element;
      }
   }
   
   //Remove the node at the index and return that node's element
   @Override
   public E remove(int index){
      if (index < 0 || index >= size){
         return null;
      }
      else if (index == 0){
         return removeFirst();
      }
      else if (index == size - 1){
         return removeLast();
      }
      else{
         Node<E> previous = head;
         
         for (int i = 1; i < index; i++){
            previous = previous.next;
         }
         
         Node<E> current = previous.next;
         previous.next = current.next;
         (current.next).previous = previous;
         size--;
         return current.element;
      }
   }
   
   @Override
   public String toString(){
      StringBuilder result = new StringBuilder("[");
      
      Node <E> current = head;
      for (int index = 0; index < size(); index++){
         result.append(current.element);
         current = current.next;
         if (current != null){
            result.append(", ");
         }
         else{
            result.append("]");
         }
      }
      
      return result.toString();
   }
   
   //Clear the list
   @Override
   public void clear(){
      size = 0;
      head = tail = null;
   }
   
   //Check if this list contains an element
   @Override
   public boolean contains(Object o){
      for (E e : this){
         if (e.equals(o)){
            return true;
         }
      }
      return false;
   }
   
   //Return the element at the index
   @Override
   public E get(int index){
      Node<E> current = head;
      
      if (index > size || index < 0){
         return null;
      }
      else{
         for (int i = 0; i < index; i++){
            current = current.next;
         }
      }
      return current.element;
   }
   
   //Return the index of the first matching element. Return -1 if no match
   @Override
   public int indexOf(Object o){
      Node<E> current = head;
      
      for (int i = 0; i < size; i++){
         if (o.equals(current.element)){
            return i;
         }
         current = current.next;
      }
      return -1;
   }
   
   //Return the index of the last matching element. Return -1 if no match
   @Override
   public int lastIndexOf(Object o){
      Node<E> current = head;
      int lastIndex = -1;
      
      for (int i = 0; i < size; i++){
         if (o.equals(current.element)){
            lastIndex = i;
         }
         current = current.next;
      }
      return lastIndex;
   }
   
   //Replace the element at the index with the specified element
   @Override
   public E set(int index, E e){
      Node<E> current = head;
      E oldElement = null;
      
      if (index > size || index < 0){
         return null;
      }
      else{
         for (int i = 0; i < index; i++){
            current = current.next;
         }
      }
      oldElement = current.element;
      current.element = e;
      return oldElement;
   }
   
   //Override iterator() in Iterable
   public Iterator<E> iterator(){
      return new LinkedListIterator();
   }
   
   //-----------------------------------------
   //       LinkedListIterator Class
   //-----------------------------------------
   private class LinkedListIterator implements Iterator<E>, ListIterator<E>{
      private Node<E> current = head;
      private Node<E> previous = current.previous;
      public int index = 0;
      private int canRemoveOrSet = 0;  //Determines whether the remove() and set() methods can be used
      
      public LinkedListIterator(){
      }
      
      public LinkedListIterator(int targetIndex){
         //Moves the cursor to the desired index
         for (int i = 0; i < targetIndex; i++){
            current = current.next;
         }
      }
      
      /*Adds an element to the list. The element appears after the previous() element but before
         the next() element in such a way that invoking previous() will return the added element.*/
      @Override
      public void add(E e){
         Node<E> newNode = new Node<E>(e);
         newNode.previous = current.previous;
         newNode.next = current;
         if (current != head){
            current.previous.next = newNode;
         }
         current.previous = newNode;
         previous = newNode;
         size++;
         index++;
         canRemoveOrSet = 0;
      }
      
      @Override
      public boolean hasNext(){
         return (current != null);
      }
      
      @Override
      public boolean hasPrevious(){
         return (previous != null);
      }
      
      @Override
      public E next(){
         /*If hasNext is true, return the element while moving the cursor forward. I had to add
            the "previous" variable that would keep track of the node previous to the current node,
            because when traversing the list using hasNext and next, eventually current = current.next
            ends up setting current to null, effectively getting the cursor stuck at the end of the list.*/
         if (hasNext()){
            E e = current.element;
            previous = current;
            current = current.next;
            index++;
            canRemoveOrSet = 1;
            return e;
         }
         else{
            return null;
         }
      }
      
      @Override
      public int nextIndex(){
         return index + 1;
      }
      
      @Override
      public E previous(){
         if (hasPrevious()){
            current = previous;
            previous = current.previous;
            E e = current.element;
            index--;
            canRemoveOrSet = -1;
            return e;
         }
         else{
            return null;
         }
         
      }
      
      @Override
      public int previousIndex(){
         return index - 1;
      }
      
      @Override
      public void remove(){
         if (canRemoveOrSet != 0){
            if (canRemoveOrSet == -1){
               /*Changes the previous node to point to the next node,
                  then moves the cursor to the next node and reduces
                  the size of the list*/
               (current.previous).next = current.next;
               current = current.next;
               size--;
            }
            else{
               /*Changes the node 2 links ago to point to the current node,
                  then reduces the size of the list*/
               try{
                  ((current.previous).previous).next = current;
               }
               catch (NullPointerException e){
                  current = head;
               }
               size--;
            }
         }
         else{
            System.out.println("ERROR: You must use previous() or next() before using remove().");
         }
         canRemoveOrSet = 0;
      }
      
      @Override
      public void set(E e){
         if (canRemoveOrSet != 0){
            if (canRemoveOrSet == -1){
               /*Changes the current element to the new element*/
               current.element = e;
            }
            else{
               /*Changes the previous element to the new element*/
               (current.previous).element = e;
            }
         }
         else{
            System.out.println("ERROR: You must use previous() or next() before using set().");
         }
      }
   }
   
   //-----------------------------------------
   //                Node Class
   //-----------------------------------------
   private class Node<E>{
      E element;
      Node<E> next;
      Node<E> previous;
      
      public Node(E e){
         element = e;
      }
   }
}