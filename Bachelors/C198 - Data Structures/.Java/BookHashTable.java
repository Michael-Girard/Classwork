package datastructures;

/*
 * Author: Michael Girard (MJG)
 * Student ID: 000604671
 * Student Mentor: Rick Haver
 */
public class BookHashTable {
    //MJG: BookHashTable class variables.
    //     Table size set to 13 as per A.4 in the instructions.
    //     BookHashTable uses Separate Chaining, so each index holds 
    //          an EntryChain object, a linked chain of Nodes containing 
    //          BookEntry objects.
    private final int TABLE_SIZE = 13;
    private EntryChain[] table;
    
    //MJG: BookHashTable constructor.
    public BookHashTable(){
        table = new EntryChain[TABLE_SIZE];
        
        //MJG: Create empty EntryChain objects at each table index.
        for (int index = 0; index < TABLE_SIZE; index++){
            table[index] = new EntryChain();
        }
    }
    
    //MJG: The method to hash the keys.
    private int hash(String key){
        //MJG Using hashCode(), the method returns a non-negative integer between 0 and 12.
        return Math.abs(key.hashCode() % TABLE_SIZE);
    }
    
    //MJG: The method to insert a new BookEntry. Insertion function for A.1 in the instructions.
    public boolean insert(String fName, String lName, String pNum, String email){
        //MJG: First name and last name are concatenated and then hashed to get the table index.
        String key = fName.toUpperCase() + lName.toUpperCase();
        int tableIndex = hash(key);
        
        //MJG: The index's EntryChain's insert method is called to insert the new BookEntry.
        return table[tableIndex].insert(key, new BookEntry(fName, lName, pNum, email));
    }
    
    //MJG: The method to delete a BookEntry from the HashTable. Deletion function for A.2 in the instructions.
    public BookEntry delete(String fName, String lName){
        //MJG: First name and last name are concatenated and then hashed to get the table index.
        String key = fName.toUpperCase() + lName.toUpperCase();
        int tableIndex = hash(key);
        
        //MJG: The index's EntryChain's delete method is called to delete BookEntry corresponding to the key.
        return table[tableIndex].delete(key);
    }
    
    //MJG: The method to find a BookEntry in the HashTable. Lookup function for A.3 in the instructions.
    public BookEntry search(String fName, String lName){
        //MJG: First name and last name are concatenated and then hashed to get the table index.
        String key = fName.toUpperCase() + lName.toUpperCase();
        int tableIndex = hash(key);
        
        //MJG: The index's EntryChain's search method is called to find the new BookEntry corresponding to the key.
        return table[tableIndex].search(key);
    }
    
    //MJG: The method to have each EntryChain print the values of its BookEntry objects.
    public void printValues(){
        for (int index = 0; index < TABLE_SIZE; index++){
            table[index].printValues();
        }
    }
    
    //MJG: Inner class for EntryChain.
    class EntryChain{
        //MJG: EntryChain class variables.
        private Node head;

        //MJG: EntryChain constructor.
        public EntryChain(){
            head = null;
        }

        //MJG: Method to insert a BookEntry into the EntryChain.
        protected boolean insert(String key, BookEntry value){
            Node newNode = new Node(key, value);
            
            //MJG: Add the Node to the front of the chain, connecting the new Node to the former head.
            //  This method always returns true because the EntryChain accepts any new entries.
            newNode.setNextNode(head);
            head = newNode;
            return true;
        }

        //MJG: Method to delete a BookEntry from the EntryChain.
        protected BookEntry delete(String key){
            //MJG: Variables for the target, currentNode, and the Node before the target.
            Node currentNode = head;
            Node previousNode = null;
            
            //MJG: delete traverses the EntryChain looking for a BookEntry matching the search key.
            while (currentNode != null){
                if (key.equals(currentNode.getKey())){
                    if (previousNode == null){
                        //MJG: If the Node being removed was the head, the second Node in the EntryChain
                        //  is assigned to head. If there was no second Node, head becomes null.
                        head = currentNode.getNextNode();
                    }
                    else{
                        //MJG: If the Node being removed isn't the head, the Node before the target
                        //  is linked to the Node after the target (or null if the target was the tail).
                        previousNode.setNextNode(currentNode.getNextNode());
                    }
                    //MJG: Returns the BookEntry in the Node that was deleted.
                    return currentNode.getValue();
                }
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }
            //MJG: Return null if no BookEntry corresponding to the key was found in the EntryChain.
            return null;
        }

        //MJG: Method to search for a BookEntry in the EntryChain.
        protected BookEntry search(String key){
            //MJG: search traverses the EntryChain looking for a BookEntry matching the search key.
            Node currentNode = head;
            while (currentNode != null){
                if (key.equals(currentNode.getKey())){
                    return currentNode.getValue();
                }
                currentNode = currentNode.getNextNode();
            }
            //MJG: Return null if no BookEntry corresponding to the key was found in the EntryChain.
            return null;
        }

        //MJG: Method to print BookEntry values from the EntryChain.
        protected void printValues(){
            //MJG: printValues traverses the EntryChain and prints BookEntry values.
            Node currentNode = head;
            while (currentNode != null){
                BookEntry currentEntry = currentNode.getValue();
                System.out.println(currentEntry.toString());
                currentNode = currentNode.getNextNode();
            }
        }

        //MJG: Inner inner class for EntryChain Nodes.
        class Node{
            //MJG: Node class variables.
            private String key;
            private BookEntry value;
            private Node next;

            //MJG: Node constructor.
            public Node (String key, BookEntry value){
                this.key = key;
                this.value = value;
                this.next = null;
            }

            //MJG: Node getters and setters begin here.
            public Node getNextNode(){
                return next;
            }

            public void setNextNode(Node next){
                this.next = next;
            }

            public String getKey(){
                return key;
            }

            public BookEntry getValue(){
                return value;
            }
        }
    }
}
