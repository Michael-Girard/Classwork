package datastructures;

/*
 * Author: Michael Girard (MJG)
 * Student ID: 000604671
 * Student Mentor: Rick Haver
 */
public class TestCode {

    public static void main(String[] args) {
        //MJG: Call the Hash Table test code, followed by the BST test code.
        System.out.println("----------Beginning HashTable Test----------");
        TestHashTable(new BookHashTable());
        
        System.out.print("\n\n\n\n");
        System.out.println("----------Beginning BST Test----------");
        TestBST(new BookBST());
    }
    
    private static void TestHashTable(BookHashTable table){
        //MJG: A BookEntry object to accept the return values from the method calls.
        BookEntry result;
        
        
        //MJG: Test Cases 1-10 with console output
        System.out.println("Inserting Bob Smith, Jane Williams, Mohammed al-Salam, Pat Jones, " +
            "Billy Kidd, H. Houdini, Jack Jones, Jill Jones, John Doe, and Jane Doe.");
        System.out.println(table.insert("Bob", "Smith", "555-235-1111", "bsmith@somewhere.com") == true ? "Insertion of Bob Smith Successful" : "Insertion of Bob Smith Unsuccessful");
        System.out.println(table.insert("Jane", "Williams", "555-235-1112", "jw@something.com") == true ? "Insertion of Jane Williams Successful" : "Insertion of Jane Williams Unsuccessful");
        System.out.println(table.insert("Mohammed", "al-Salam", "555-235-1113", "mas@someplace.com") == true ? "Insertion of Mohammed al-Salam Successful" : "Insertion of Mohammed al-Salam Unsuccessful");
        System.out.println(table.insert("Pat", "Jones", "555-235-1114", "pjones@homesweethome.com") == true ? "Insertion of Pat Jones Successful" : "Insertion of Pat Jones Unsuccessful");
        System.out.println(table.insert("Billy", "Kidd", "555-235-1115", "billy_the_kid@nowhere.com") == true ? "Insertion of Billy Kidd Successful" : "Insertion of Billy Kidd Unsuccessful");
        System.out.println(table.insert("H.", "Houdini", "555-235-1116", "houdini@noplace.com") == true ? "Insertion of H. Houdini Successful" : "Insertion of H. Houdini Unsuccessful");
        System.out.println(table.insert("Jack", "Jones", "555-235-1117", "jjones@hill.com") == true ? "Insertion of Jack Jones Successful" : "Insertion of Jack Jones Unsuccessful");
        System.out.println(table.insert("Jill", "Jones", "555-235-1118", "jillj@hill.com") == true ? "Insertion of Jill Jones Successful" : "Insertion of Jill Jones Unsuccessful");
        System.out.println(table.insert("John", "Doe", "555-235-1119", "jdoe@somedomain.com") == true ? "Insertion of John Doe Successful" : "Insertion of John Doe Unsuccessful");
        System.out.println(table.insert("Jane", "Doe", "555-235-1120", "jdoe@somedomain.com") == true ? "Insertion of Jane Doe Successful" : "Insertion of Jane Doe Unsuccessful");
        
        //MJG: Test Cases 1-10 without console output
        /*table.insert("Bob", "Smith", "555-235-1111", "bsmith@somewhere.com");
        table.insert("Jane", "Williams", "555-235-1112", "jw@something.com");
        table.insert("Mohammed", "al-Salam", "555-235-1113", "mas@someplace.com");
        table.insert("Pat", "Jones", "555-235-1114", "pjones@homesweethome.com");
        table.insert("Billy", "Kidd", "555-235-1115", "billy_the_kid@nowhere.com");
        table.insert("H.", "Houdini", "555-235-1116", "houdini@noplace.com");
        table.insert("Jack", "Jones", "555-235-1117", "jjones@hill.com");
        table.insert("Jill", "Jones", "555-235-1118", "jillj@hill.com");
        table.insert("John", "Doe", "555-235-1119", "jdoe@somedomain.com");
        table.insert("Jane", "Doe", "555-235-1120", "jdoe@somedomain.com");*/
        
        //MJG: Test Case 11
        System.out.print("\nLooking up Pat Jones:::");
        result = table.search("Pat", "Jones");
        System.out.println(result == null ? "Pat Jones was not found in the HashTable." : result.toString());
        
        //MJG: Test Case 12
        System.out.print("Looking up Billy Kidd:::");
        result = table.search("Billy", "Kidd");
        System.out.println(result == null ? "Billy Kidd was not found in the HashTable." : result.toString());    

        //MJG: Test Case 13
        System.out.print("\nDeleting John Doe:::");
        result = table.delete("John", "Doe");
        System.out.println(result == null ? "John Doe was not found in the HashTable." : result.toString());
        
        //MJG: Test Cases 14-19 with console output
        System.out.println("\nInserting Test Case, Nadezhda Kanachekhovskaya, Jo Wu, " +
            "Millard Fillmore, Bob vanDyke, and Upside Down.");
        System.out.println(table.insert("Test", "Case", "555-235-1121", "Test_Case@testcase.com") == true ? "Insertion of Test Case Successful" : "Insertion of Test Case Unsuccessful");
        System.out.println(table.insert("Nadezhda", "Kanachekhovskaya", "555-235-1122", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru") == true ? "Insertion of Nadezhda Kanachekhovskaya Successful" : "Insertion of Nadezhda Kanachekhovskaya Unsuccessful");
        System.out.println(table.insert("Jo", "Wu", "555-235-1123", "wu@h.com") == true ? "Insertion of Jo Wu Successful" : "Insertion of Jo Wu Unsuccessful");
        System.out.println(table.insert("Millard", "Fillmore", "555-235-1124", "millard@theactualwhitehouse.us") == true ? "Insertion of Millard Fillmore Successful" : "Insertion of Millard Fillmore Unsuccessful");
        System.out.println(table.insert("Bob", "vanDyke", "555-235-1125", "vandyke@nodomain.com") == true ? "Insertion of Bob vanDyke Successful" : "Insertion of Bob vanDyke Unsuccessful");
        System.out.println(table.insert("Upside", "Down", "555-235-1126", "upsidedown@rightsideup.com") == true ? "Insertion of Upside Down Successful" : "Insertion of Upside Down Unsuccessful");
        
        //MJG: Test Cases 14-19 without console output
        /*table.insert("Test", "Case", "555-235-1121", "Test_Case@testcase.com");
        table.insert("Nadezhda", "Kanachekhovskaya", "555-235-1122", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru");
        table.insert("Jo", "Wu", "555-235-1123", "wu@h.com");
        table.insert("Millard", "Fillmore", "555-235-1124", "millard@theactualwhitehouse.us");
        table.insert("Bob", "vanDyke", "555-235-1125", "vandyke@nodomain.com");
        table.insert("Upside", "Down", "555-235-1126", "upsidedown@rightsideup.com");*/
                
        //MJG: Test Case 20
        System.out.print("\nLooking up Jack Jones:::");
        result = table.search("Jack", "Jones");
        System.out.println(result == null ? "Jack Jones was not found in the HashTable." : result.toString());        
        
        //MJG: Test Case 21
        System.out.print("Looking up Nadezhda Kanachekhovskaya::: ");
        result = table.search("Nadezhda", "Kanachekhovskaya");
        System.out.println(result == null ? "Nadezhda Kanachekhovskaya was not found in the HashTable." : result.toString());        

        //MJG: Test Case 22
        System.out.print("\nDeleting Jill Jones:::");
        result = table.delete("Jill", "Jones");
        System.out.println(result == null ? "Jill Jones was not found in the HashTable." : result.toString()); 
        
        //MJG: Test Case 23
        System.out.print("\nDeleting John Doe:::");
        result = table.delete("John", "Doe");
        System.out.println(result == null ? "John Doe was not found in the HashTable." : result.toString());
        
        //MJG: Test Case 24
        System.out.print("\nLooking up Jill Jones:::");
        result = table.search("Jill", "Jones");
        System.out.println(result == null ? "Jill Jones was not found in the HashTable." : result.toString());
        
        //MJG: Test Case 25
        System.out.print("\nLooking up John Doe:::");
        result = table.search("John", "Doe");
        System.out.println(result == null ? "John Doe was not found in the HashTable." : result.toString());
        
        //MJG: Optional code to print all entries in the HashTable
        System.out.println("\nPrinting all values in the table:");
        table.printValues();
    }
    
    private static void TestBST(BookBST tree){
        //MJG: A BookEntry object to accept the return values from the method calls.
        BookEntry result;
        
        //MJG: Test Cases 1-10
        System.out.println("Inserting Bob Smith, Jane Williams, Mohammed al-Salam, Pat Jones, " +
            "Billy Kidd, H. Houdini, Jack Jones, Jill Jones, John Doe, and Jane Doe.");
        System.out.println(tree.insert("Bob", "Smith", "555-235-1111", "bsmith@somewhere.com").toString());
        System.out.println(tree.insert("Jane", "Williams", "555-235-1112", "jw@something.com").toString());
        System.out.println(tree.insert("Mohammed", "al-Salam", "555-235-1113", "mas@someplace.com").toString());
        System.out.println(tree.insert("Pat", "Jones", "555-235-1114", "pjones@homesweethome.com").toString());
        System.out.println(tree.insert("Billy", "Kidd", "555-235-1115", "billy_the_kid@nowhere.com").toString());
        System.out.println(tree.insert("H.", "Houdini", "555-235-1116", "houdini@noplace.com").toString());
        System.out.println(tree.insert("Jack", "Jones", "555-235-1117", "jjones@hill.com").toString());
        System.out.println(tree.insert("Jill", "Jones", "555-235-1118", "jillj@hill.com").toString());
        System.out.println(tree.insert("John", "Doe", "555-235-1119", "jdoe@somedomain.com").toString());
        System.out.println(tree.insert("Jane", "Doe", "555-235-1120", "jdoe@somedomain.com").toString());
              
        //MJG: Test Cases 1-10 without console output
        /*tree.insert("Bob", "Smith", "555-235-1111", "bsmith@somewhere.com");
        tree.insert("Jane", "Williams", "555-235-1112", "jw@something.com");
        tree.insert("Mohammed", "al-Salam", "555-235-1113", "mas@someplace.com");
        tree.insert("Pat", "Jones", "555-235-1114", "pjones@homesweethome.com");
        tree.insert("Billy", "Kidd", "555-235-1115", "billy_the_kid@nowhere.com");
        tree.insert("H.", "Houdini", "555-235-1116", "houdini@noplace.com");
        tree.insert("Jack", "Jones", "555-235-1117", "jjones@hill.com");
        tree.insert("Jill", "Jones", "555-235-1118", "jillj@hill.com");
        tree.insert("John", "Doe", "555-235-1119", "jdoe@somedomain.com");
        tree.insert("Jane", "Doe", "555-235-1120", "jdoe@somedomain.com");*/
        
        //MJG: Test Case 11
        System.out.print("\nLooking up Pat Jones:::");
        result = tree.search("Pat", "Jones");
        System.out.println(result == null ? "Pat Jones was not found in the Tree." : result.toString());
        
        //MJG: Test Case 12
        System.out.print("Looking up Billy Kidd:::");
        result = tree.search("Billy", "Kidd");
        System.out.println(result == null ? "Billy Kidd was not found in the Tree." : result.toString());
        
        //MJG: Test Case 13
        System.out.print("\nDeleting John Doe:::");
        result = tree.delete("John", "Doe");
        System.out.println(result == null ? "John Doe was not found in the Tree." : result.toString());
        
        //MJG: Test Cases 14-19
        System.out.println("\nInserting Test Case, Nadezhda Kanachekhovskaya, Jo Wu, " +
            "Millard Fillmore, Bob vanDyke, and Upside Down.");
        System.out.println(tree.insert("Test", "Case", "555-235-1121", "Test_Case@testcase.com").toString());
        System.out.println(tree.insert("Nadezhda", "Kanachekhovskaya", "555-235-1122", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru").toString());
        System.out.println(tree.insert("Jo", "Wu", "555-235-1123", "wu@h.com").toString());
        System.out.println(tree.insert("Millard", "Fillmore", "555-235-1124", "millard@theactualwhitehouse.us").toString());
        System.out.println(tree.insert("Bob", "vanDyke", "555-235-1125", "vandyke@nodomain.com").toString());
        System.out.println(tree.insert("Upside", "Down", "555-235-1126", "upsidedown@rightsideup.com").toString());
                
        //MJG: Test Cases 14-19 without console output
        /*tree.insert("Test", "Case", "555-235-1121", "Test_Case@testcase.com");
        tree.insert("Nadezhda", "Kanachekhovskaya", "555-235-1122", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru");
        tree.insert("Jo", "Wu", "555-235-1123", "wu@h.com");
        tree.insert("Millard", "Fillmore", "555-235-1124", "millard@theactualwhitehouse.us");
        tree.insert("Bob", "vanDyke", "555-235-1125", "vandyke@nodomain.com");
        tree.insert("Upside", "Down", "555-235-1126", "upsidedown@rightsideup.com");*/
        
        //MJG: Test Case 20
        System.out.print("\nLooking up Jack Jones:::");
        result = tree.search("Jack", "Jones");
        System.out.println(result == null ? "Jack Jones was not found in the Tree." : result.toString());        
        
        //MJG: Test Case 21
        System.out.print("Looking up Nadezhda Kanachekhovskaya::: ");
        result = tree.search("Nadezhda", "Kanachekhovskaya");
        System.out.println(result == null ? "Nadezhda Kanachekhovskaya was not found in the Tree." : result.toString());
        
        //MJG: Test Case 22
        System.out.print("\nDeleting Jill Jones:::");
        result = tree.delete("Jill", "Jones");
        System.out.println(result == null ? "Jill Jones was not found in the Tree." : result.toString()); 
        
        
        //MJG: Test Case 23
        System.out.print("\nDeleting John Doe:::");
        result = tree.delete("John", "Doe");
        System.out.println(result == null ? "John Doe was not found in the Tree." : result.toString());
        
        //MJG: Test Case 24
        System.out.print("\nLooking up Jill Jones:::");
        result = tree.search("Jill", "Jones");
        System.out.println(result == null ? "Jill Jones was not found in the Tree." : result.toString());
        
        
        //MJG: Test Case 25
        System.out.print("\nLooking up John Doe:::");
        result = tree.search("John", "Doe");
        System.out.println(result == null ? "John Doe was not found in the Tree." : result.toString());
        
        //MJG: Optional code to print all entries in the Tree
        System.out.println("\nPrinting all values in the Tree:");
        tree.printValues();
    }
}
