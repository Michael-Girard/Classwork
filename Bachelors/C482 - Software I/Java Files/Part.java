/**
 * @author Michael Girard
 * Student ID: #000604671
 * Mentor: Rick Haver
 */
package wguinventorysystem;


abstract class Part {
    protected int partID;
    protected int inStock;
    protected int minStock;
    protected int maxStock;
    protected double price;
    protected String name;
    
    abstract void setPartID(int newID);
    abstract int getPartID();
    
    abstract void setInStock(int newStock);
    abstract int getInStock();
    
    abstract void setMinStock(int newMinStock);
    abstract int getMinStock();
    
    abstract void setMaxStock(int newMaxStock);
    abstract int getMaxStock();
    
    abstract void setPrice(double newPrice);
    abstract double getPrice();
    
    abstract void setName(String newName);
    abstract String getName();
}

//----------------------------------------------------------------------
//                      InHousePart Class
//----------------------------------------------------------------------
//<editor-fold desc="Hidden" defaultstate="collapsed">
class InHousePart extends Part{
    protected int machineID;
    
    public InHousePart(int partID, String name, int machineID, int inStock, int minStock, int maxStock, double price){
        this.partID = (partID < 0 ? Inventory.getAvailablePartID() : partID);
        this.name = name;
        this.machineID = machineID;
        this.inStock = inStock;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.price = price;
    }
    
    //<editor-fold desc="Overridden Methods Inherited from Part Class" defaultstate="collapsed">
    @Override
    public void setPartID(int newID)
    {
        partID = newID;
    }
    @Override
    public int getPartID(){
        return partID;
    }
    
    @Override
    public void setInStock(int newStock){
        inStock = newStock;
    }
    @Override
    public int getInStock(){
        return inStock;
    }
    
    @Override
    public void setMinStock(int newMinStock){
        minStock = newMinStock;
    }
    @Override
    public int getMinStock(){
        return minStock;
    }
    
    @Override
    public void setMaxStock(int newMaxStock){
        maxStock = newMaxStock;
    }
    @Override
    public int getMaxStock(){
        return maxStock;
    }
    
    @Override
    public void setPrice(double newPrice){
        price = newPrice;
    }
    @Override
    public double getPrice(){
        return price;
    }
    
    @Override
    public void setName(String newName){
        name = newName;
    }
    @Override
    public String getName(){
        return name;
    }
    //</editor-fold>
    
    public void setMachineID(int newID){
        machineID = newID;
    }
    public int getMachineID(){
        return machineID;
    }
}
//</editor-fold>

//----------------------------------------------------------------------
//                      OutsourcedPart Class
//----------------------------------------------------------------------
//<editor-fold desc="Hidden" defaultstate="collapsed">
class OutsourcedPart extends Part{
    protected String companyName;
    
    public OutsourcedPart(int partID, String name, String companyName, int inStock, int minStock, int maxStock, double price){
        this.partID = (partID < 0 ? Inventory.getAvailablePartID() : partID);
        this.name = name;
        this.companyName = companyName;
        this.inStock = inStock;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.price = price;
    }
    
    //<editor-fold desc="Overridden Methods Inherited from Part Class" defaultstate="collapsed">
    @Override
    public void setPartID(int newID)
    {
        partID = newID;
    }
    @Override
    public int getPartID(){
        return partID;
    }
    
    @Override
    public void setInStock(int newStock){
        inStock = newStock;
    }
    @Override
    public int getInStock(){
        return inStock;
    }
    
    @Override
    public void setMinStock(int newMinStock){
        minStock = newMinStock;
    }
    @Override
    public int getMinStock(){
        return minStock;
    }
    
    @Override
    public void setMaxStock(int newMaxStock){
        maxStock = newMaxStock;
    }
    @Override
    public int getMaxStock(){
        return maxStock;
    }
    
    @Override
    public void setPrice(double newPrice){
        price = newPrice;
    }
    @Override
    public double getPrice(){
        return price;
    }
    
    @Override
    public void setName(String newName){
        name = newName;
    }
    @Override
    public String getName(){
        return name;
    }
    //</editor-fold>
    
    public void setCompanyName(String newName){
        companyName = newName;
    }
    public String getCompanyName(){
        return companyName;
    }
}
//</editor-fold>