/**
 * @author Michael Girard
 * Student ID: #000604671
 * Mentor: Rick Haver
 */
package wguinventorysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {
    protected static int availablePartID = 0;
    protected static int availableProductID = 0;
    private static ObservableList<Product> products = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    
    //----------------------------------------------------------------------
    //                          Product Methods
    //----------------------------------------------------------------------
    //<editor-fold desc="Hidden" defaultstate="collapsed">
    public static void addProduct(Product product){
        products.add(product);
//        System.out.println("Name: " + product.getName() + "\nStock: " + product.getInStock() + "\nMinStock: " + product.getMinStock() + "\nMaxStock: " + product.getMaxStock() + "\nPrice: " + product.getPrice());
//        System.out.print("Associated Parts: ");
//        for (Part part : product.associatedParts){
//            System.out.print(part.getName() + " ");
//        }
    }
    
    public static boolean removeProduct(int index){
        return products.remove(index) != null;
    }
    
    public static Product lookupProduct(int index){
        return products.get(index);
    }
    
    public static int lookupProduct(String name){
        name = name.toUpperCase();
        for (Product product : products){
            if (product.getName().toUpperCase().equals(name)){
                return products.indexOf(product);
            }
        }
        return -1;
    }
    
    public static int indexOf(Product product){
        for (Product p : products){
            if (product.equals(p)){
                return products.indexOf(p);
            }
        }
        return -1;
    }
    
    public static void updateProduct(int index, Product product){
        //UML diagram wanted this to take just an int, but I don't see how it would work that way
        //This method was unnecessary - I get references to the products from the selected TableView indices
        //  and modify them by using the setter methods in the Product class. 
    }
    
    public static ObservableList<Product> getAllProducts(){
        return products;
    }
    //</editor-fold>
    
    //----------------------------------------------------------------------
    //                          Part Methods
    //----------------------------------------------------------------------
    //<editor-fold desc="Hidden" defaultstate="collapsed">
    public static void addPart(Part part){
        allParts.add(part);
//        for (Part p : allParts){
//            if (p instanceof InHousePart){
//                System.out.println("InHousePart Name: " + p.getName() + "\nStock: " + p.getInStock() + "\nMinStock: " + p.getMinStock() + "\nMaxStock: " + p.getMaxStock() + "\nPrice: " + p.getPrice()); 
//                System.out.println("\n\n");
//            }
//            else{
//                System.out.println("OutsourcedPart Name: " + p.getName() + "\nStock: " + p.getInStock() + "\nMinStock: " + p.getMinStock() + "\nMaxStock: " + p.getMaxStock() + "\nPrice: " + p.getPrice()); 
//                System.out.println("\n\n");
//            }
//        }
    }
    
    public static boolean removePart(int index){
        Part oldPart = allParts.remove(index);
        
        //Remove the part being deleted from the associatedParts list of any existing products
        if (oldPart != null){
            for (Product product : products){
                for (Part p : product.associatedParts){
                    if (p.equals(oldPart)){
                        product.removeAssociatedPart(product.associatedParts.indexOf(p));
                    }
                }
            }
        }
        
        return oldPart != null;
    }
    
    public static Part lookupPart(int index){
        return allParts.get(index);
    }
    
    public static int lookupPart(String name){
        name = name.toUpperCase();
        for (Part part : allParts){
            if (part.getName().toUpperCase().equals(name)){
                return allParts.indexOf(part);
            }
        }
        return -1;
    }
    
    public static int indexOf(Part part){
        for (Part p : allParts){
            if (part.equals(p)){
                return allParts.indexOf(p);
            }
        }
        return -1;
    }
    
    public static void updatePart(int index, Part part){
        //UML diagram wanted this to take just an int, but I don't see how it would work that way
        Part oldPart = allParts.remove(index);
        allParts.add(index, part);
        
        //For each product, if the product had the part being updated in its associatedParts, replace the old part with the updated version
        //  This makes sure that if a part gets converted from Outsourced to Inhouse or vice-versa, the old version of the part doesn't remain
        //  in the associatedParts lists of any of the products
        for (Product product : products){
            for (Part p : product.associatedParts){
                if (p.equals(oldPart)){
                    product.removeAssociatedPart(product.associatedParts.indexOf(p));
                    product.addAssociatedPart(part);
                }
            }
        }
        
        //Display a list of all parts
//        for (Part p : allParts){
//            if (p instanceof InHousePart){
//                System.out.println("InHousePart Name: " + p.getName() + "\nStock: " + p.getInStock() + "\nMinStock: " + p.getMinStock() + "\nMaxStock: " + p.getMaxStock() + "\nPrice: " + p.getPrice()); 
//                System.out.println("\n");
//            }
//            else{
//                System.out.println("OutsourcedPart Name: " + p.getName() + "\nStock: " + p.getInStock() + "\nMinStock: " + p.getMinStock() + "\nMaxStock: " + p.getMaxStock() + "\nPrice: " + p.getPrice()); 
//                System.out.println("\n");
//            }
//        }
    }
    
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    //</editor-fold>
    
    public static int getAvailablePartID(){
        //Return availablePartID after incrementing it
        //Gives a unique ID for each part
        return ++availablePartID;
    }
    public static int getAvailableProductID(){
        //Return availableProductID after incrementing it
        //Gives a unique ID for each product
        return ++availableProductID;
    }
}
