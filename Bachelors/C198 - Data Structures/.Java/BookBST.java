package datastructures;

/*
 * Author: Michael Girard (MJG)
 * Student ID: 000604671
 * Student Mentor: Rick Haver
 */
public class BookBST{
    //MJG: BookBST class variables.
    private static BSTNode root;
    
    //MJG: BookBST constructor method.
    public BookBST(){
        root = null;
    }
    
    //MJG: Helper method for the recursive insert method. Insertion function for B.1 in the instructions.
    public BookEntry insert(String fName, String lName, String pNum, String email){
        BookEntry newEntry = new BookEntry(fName, lName, pNum, email);
        
        //MJG: If the root doesn't exist, the tree is empty and the value is inserted at the tree's root.
        if (root == null){
            root = new BSTNode(newEntry);
            return root.getValue();
        }
        
        //MJG: Otherwise, recursively look through the tree to find where the value should be inserted.
        return insert(newEntry, root);
    }
    
    //MJG: Recursive method to insert a BookEntry into the tree.
    private BookEntry insert(BookEntry value, BSTNode root){
        //MJG: Compare the keys (first name + last name).
        int comparison = value.compareTo(root.getValue());
        
        //MJG: If the keys match, replace the current BookEntry with the new BookEntry and return the old BookEntry.
        if (comparison == 0){
            BookEntry oldValue = root.getValue();
            root.setValue(value);
            return oldValue;
        }
        
        //MJG: If the key is smaller, check if the left child exists. If the left child DOESN'T exist, set the new value as the
        //       root's left child. If the left child DOES exist, check the left subtree for the spot the value should be inserted.
        else if (comparison < 0){
            if (root.hasLeftChild()){
                return insert(value, root.getLeftChild());
            }
            else{
                root.setLeftChild(new BSTNode(value));
            }
        }
        
        //MJG: If the key is larger, check if the right child exists. If the right child DOESN'T exist, set the new value as the
        //       root's right child. If the right child DOES exist, check the right subtree for the spot the value should be inserted.
        else{
           if (root.hasRightChild()){
                return insert(value, root.getRightChild());
            }
            else{
                root.setRightChild(new BSTNode(value));
            }
        }
        return value;
    }
    
    //MJG: Delete method taking a first name and last name. Deletion function for B.2 in the instructions.
    public BookEntry delete(String fName, String lName){
        //MJG: Call the main delete method.
        return delete(new BookEntry(fName, lName, null, null), root, null);
    }
    
    //MJG: The main delete method to remove a BookEntry from the BST.
    private BookEntry delete(BookEntry entry, BSTNode current, BSTNode previous){
        boolean found = false;
        int comparison;
        
        //MJG: While a matching entry isn't found and current doesn't become null.
        while (!found && current != null){
            //MJG: Compare the entry to be deleted with the entry in current.
            comparison = entry.compareTo(current.getValue());
            
            //MJG: If the comparison returns 0, a match has been found!
            if (comparison == 0){
                found = true;
            }
            //MJG: If comparison is larger than 0, go down to the right child.
            //      Otherwise, go down to the left child.
            //      In either case, previous continues to be the parent of current.
            else if (comparison > 0){
                previous = current;
                current = current.getRightChild();
            }
            else{
                previous = current;
                current = current.getLeftChild();
            }
        }
        
        //MJG: If current is null at this point, no match was found.
        if (current != null){
            BookEntry oldValue = current.getValue();
            
            //MJG: If current has multiple children, replace current's value with the value of the largest
            //      child in the left subtree.
            if (current.hasLeftChild() && current.hasRightChild()){
                current.setValue(getReplacement(current.getLeftChild(), current));
            }
            else if (current.hasLeftChild()){
                //MJG: If previous is null, current is the root. 
                if (previous != null){
                    //MJG: If current wasn't the root, check current's parent to see if current was the left or right child.
                    if (previous.getLeftChild() == current){
                        //MJG: If current was the left child, set the left child to be current's left child.
                        previous.setLeftChild(current.getLeftChild());
                    }
                    else{
                        //MJG: If current was the right child, set the right child to be current's left child.
                        previous.setRightChild(current.getLeftChild());
                    }
                }
                else{
                    //MJG: If previous was null, current is the root. Set the root to be current's left child.
                    setRoot(current.getLeftChild());
                }
            }
            else{
                //MJG: If previous is null, current is the root. 
                if (previous != null){
                    //MJG: If current wasn't the root, check current's parent to see if current was the left or right child.
                    if (previous.getLeftChild() == current){
                        //MJG: If current was the left child, set the left child to be current's right child.
                        previous.setLeftChild(current.getRightChild());
                    }
                    else{
                        //MJG: If current was the right child, set the left child to be current's right child.
                        previous.setRightChild(current.getRightChild());
                    }
                }
                else{
                    //MJG: If previous was null, current is the root. Set the root to be current's right child.
                    setRoot(current.getRightChild());
                }
            }
            return oldValue;
        }
        return null;
    }
    
    //MJG: The method to get the rightmost entry in the left subtree.
    private static BookEntry getReplacement(BSTNode current, BSTNode previous){
        //MJG: Traverse down the right side of the left subtree.
        while (current.hasRightChild()){
            previous = current;
            current = current.getRightChild();
        }
        BookEntry result = current.getValue();
        
        // MJG: If the current node has a left child, the child becomes the right child of the parent.
        //       Otherwise, set the right child of the current node's parent to null.
        if (current.hasLeftChild()){
            previous.setRightChild(current.getLeftChild());
        }
        else{
            previous.setRightChild(null);
        }
        return result;
    }
    
    //MJG: Helper method to call the main search method. Lookup function for B.3 in the instructions.
    public BookEntry search(String fName, String lName){
        return search (new BookEntry(fName, lName, null, null), root);
    }
    
    //MJG: Recursive method to search the tree.
    private BookEntry search(BookEntry entry, BSTNode root){
        //MJG: If the root is null, the entry isn't in the table.
        if (root == null){
            return null;
        }
        //MJG: Compare the keys (first name + last name).
        int comparison = entry.compareTo(root.getValue());
        
        //MJG: If the keys match, return the found value.
        if (comparison == 0){
            return root.getValue();
        }
        
        //MJG: If the key was smaller, look in the left subtree, otherwise look in the right subtree.
        else if (comparison < 0){
            return search(entry, root.getLeftChild());
        }
        else{
            return search(entry, root.getRightChild());
        }
    }
    
    //MJG: Method to print all the values in the tree.
    public void printValues(){
        printValues(root);
    }
    
    //MJG: Method to print all the values in a tree or subtree. Uses In-order Traversal.
    private void printValues(BSTNode node){
        if (node != null){
            printValues(node.getLeftChild());
            System.out.println(node.getValue().toString());
            printValues(node.getRightChild());
        }
    }
    
    //MJG: Setter method to replace the tree's root.
    private static void setRoot(BSTNode newRoot){
        root = newRoot;
    }
    
    //MJG: Inner class implementing the nodes in the tree.
    class BSTNode{
        //MJG: BSTNode class variables.
        BookEntry value;
        BSTNode leftChild;
        BSTNode rightChild;
        
        //MJG: BSTNode constructor.
        public BSTNode(BookEntry value){
            this.value = value;
            leftChild = null;
            rightChild = null;
        }
        
        //MJG: BSTNode getters and setters begin here.
        public BSTNode getLeftChild(){
            return leftChild;
        }
        
        public void setLeftChild(BSTNode newLeft){
            leftChild = newLeft;
        }
        
        public boolean hasLeftChild(){
            return leftChild != null;
        }
        
        public BSTNode getRightChild(){
            return rightChild;
        }
        
        public void setRightChild(BSTNode newRight){
            rightChild = newRight;
        }
        
        public boolean hasRightChild(){
            return rightChild != null;
        }
        
        public BookEntry getValue(){
            return value;
        }
        
        public void setValue(BookEntry newValue){
            value = newValue;
        }
    }
}
