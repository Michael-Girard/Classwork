/**
 * @author Michael Girard
 * Student ID: #000604671
 * Mentor: Rick Haver
 */
package wguinventorysystem;

import javafx.collections.ObservableList;

public class Product {
    protected ObservableList<Part> associatedParts;
    private int productID;
    private int inStock;
    private int minStock;
    private int maxStock;
    private double price;
    private String name;
    
    //Constructor to make a product with everything
    public Product(String name, int inStock, int minStock, int maxStock, double price){
        this.productID = Inventory.getAvailableProductID();
        this.inStock = inStock;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.price = price;
        this.name = name;
    }
    
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    public boolean removeAssociatedPart(int index){
        return associatedParts.remove(index) != null;
    }
    public Part lookupAssociatedPart(int index){
        return associatedParts.get(index);
    }
    
    public void setAssociatedParts(ObservableList<Part> parts){
        associatedParts = parts;
    }
    
    //<editor-fold desc="Getters and Setters">
    public void setProductID(int newID){
        productID = newID;
    }
    public int getProductID(){
        return productID;
    }
    
    public void setInStock(int newStock){
        inStock = newStock;
    }
    public int getInStock(){
        return inStock;
    }
    
    public void setMinStock(int newMinStock){
        minStock = newMinStock;
    }
    public int getMinStock(){
        return minStock;
    }
    
    public void setMaxStock(int newMaxStock){
        maxStock = newMaxStock;
    }
    public int getMaxStock(){
        return maxStock;
    }
    
    public void setPrice(double newPrice){
        price = newPrice;
    }
    public double getPrice(){
        return price;
    }
    
    public void setName(String newName){
        name = newName;
    }
    public String getName(){
        return name;
    }
    //</editor-fold>
    
//    @Override
//    public boolean equals(Object obj){
//        Product source = this;
//        Product target = (Product)obj;
//        return source.getName().equals(target.getName());
//    }
}
