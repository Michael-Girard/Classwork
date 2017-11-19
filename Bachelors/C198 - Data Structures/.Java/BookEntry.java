package datastructures;

/*
 * Author: Michael Girard (MJG)
 * Student ID: 000604671
 * Student Mentor: Rick Haver
 */
public class BookEntry {
    //MJG: BookEntry class variables
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    
    //MJG BookEntry constructors start here
    public BookEntry(){
        this(null, null, null, null);
    }
    
    public BookEntry(String fName, String lName, String pNum, String email){
        this.firstName = fName;
        this.lastName = lName;
        this.phoneNumber = pNum;
        this.email = email;
    }
    
    //MJG: BookEntry get methods start here.
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public String getEmail(){
        return email;
    }
    
    //MJG: Method to compare to BookEntry objects
    public int compareTo(BookEntry that){
        //MJG: Get the two keys for the BookEntry objects by concatenating the first name and last name
        String one = this.getFirstName() + this.getLastName();
        String two = that.getFirstName() + that.getLastName();
        
        //MJG: Return the value returned by the standard String compareTo method
        return one.toUpperCase().compareTo(two.toUpperCase());
    }
    
    //MJG: Method to convert a BookEntry object to a string
    @Override
    public String toString(){
        return "NAME: " + this.getFirstName() + " " + this.getLastName() +
                ", PHONE NUMBER: " + this.getPhoneNumber() + ", EMAIL: " + 
                this.getEmail() + "";
    }
}
