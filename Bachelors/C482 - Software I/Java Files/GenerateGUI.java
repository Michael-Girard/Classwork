/**
 * @author Michael Girard
 * Student ID: #000604671
 * Mentor: Rick Haver
 */
package wguinventorysystem;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//Helper class with static methods to construct GUI elements
class GenerateGUI {
    //Instantiate class variables
    protected static final int INSET_SIZE = 20;
    
    protected static final int MAIN_WIDTH = 1500;
    protected static final int MAIN_HEIGHT = 500;
    
    protected static final int PART_WIDTH = 650;
    protected static final int PART_HEIGHT = 425;
    
    protected static final int PRODUCT_WIDTH = 1100;
    protected static final int PRODUCT_HEIGHT = 700;
    
    protected static final int ERROR_WIDTH = 500;
    protected static final int ERROR_HEIGHT = 100;
    
    protected static final int CONFIRMATION_WIDTH = 400;
    protected static final int CONFIRMATION_HEIGHT = 150;
    
    
    
    //Private constructor to prevent instantiation
    private GenerateGUI(){
        
    }
    
    //Method to handle the generation of the main menu
    protected static void mainMenu(Stage stage){
        //----------------------------------------------------------------------
        //                          Root
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        BorderPane root = new BorderPane();
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        root.setCenter(separator);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          MenuBar
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        MenuBar menu = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuExit = new MenuItem("Exit");
        
        //Build Menu and add to Root
        menuFile.getItems().add(menuExit);
        menu.getMenus().add(menuFile);
        root.setTop(menu);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Part Pane
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        GridPane gridParts = new GridPane();
        
        Label lblParts = new Label("Parts");    
        TextField tfPartSearch = new TextField();
        Button btnPartSearch = new Button("Search");
        Button btnPartAdd = new Button("Add");
        Button btnPartModify = new Button("Modify");
        Button btnPartDelete = new Button("Delete");
        TableView<Part> tblParts = new TableView<>();
        
        //Populate tblParts
            //Instantiate TableColumns
        TableColumn<Part, Integer> tcPartID = new TableColumn<>("Part ID");
        TableColumn<Part, String> tcPartName = new TableColumn<>("Part Name");
        TableColumn<Part, Integer> tcPartStock = new TableColumn<>("# in Inventory");
        TableColumn<Part, Double> tcPartPrice = new TableColumn<>("Price per Unit");
        
            //Assign ValueFactories
        tcPartID.setCellValueFactory(part -> new SimpleIntegerProperty(part.getValue().getPartID()).asObject());
        tcPartName.setCellValueFactory(part -> new SimpleStringProperty(part.getValue().getName()));
        tcPartStock.setCellValueFactory(part -> new SimpleIntegerProperty(part.getValue().getInStock()).asObject());
        tcPartPrice.setCellValueFactory(part -> new SimpleDoubleProperty(part.getValue().getPrice()).asObject());
        
        tblParts.setItems(Inventory.getAllParts());
        
        tblParts.getColumns().addAll(tcPartID, tcPartName, tcPartStock, tcPartPrice);
        
        //Style gridParts
            //Size gridParts to the Root
        gridParts.prefWidthProperty().bind(root.widthProperty().divide(2));
        
            //Size gridParts' columns evenly at 10% of Root size
        ColumnConstraints cols = new ColumnConstraints();
        cols.prefWidthProperty().bind(root.widthProperty().divide(10));
        gridParts.getColumnConstraints().addAll(cols, cols, cols, cols, cols);
        
            //Size tblParts columns evenly and disable multiselect
        tblParts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tcPartID.prefWidthProperty().bind(tblParts.widthProperty().divide(4));
        tcPartName.prefWidthProperty().bind(tblParts.widthProperty().divide(4));
        tcPartStock.prefWidthProperty().bind(tblParts.widthProperty().divide(4));
        tcPartPrice.prefWidthProperty().bind(tblParts.widthProperty().divide(4));
        
            //Give gridParts padding
        gridParts.setPadding(new Insets(INSET_SIZE));
        gridParts.setVgap(5);
            
            //Align Components
        GridPane.setHalignment(lblParts, HPos.CENTER);
        GridPane.setHalignment(btnPartSearch, HPos.CENTER);
        GridPane.setHalignment(btnPartAdd, HPos.CENTER);
        GridPane.setHalignment(btnPartModify, HPos.CENTER);
        GridPane.setHalignment(btnPartDelete, HPos.CENTER);
        
            //Text Styling
        lblParts.setFont(new Font(30));
        lblParts.setStyle("-fx-font-weight: bold;");
        btnPartSearch.setStyle("-fx-font-weight: bold;");
        btnPartAdd.setStyle("-fx-font-weight: bold;");
        btnPartModify.setStyle("-fx-font-weight: bold;");
        btnPartDelete.setStyle("-fx-font-weight: bold;");
        
        //Build gridParts and add to Root
        gridParts.add(lblParts, 0, 0, 2, 1);
        gridParts.add(tfPartSearch, 3, 0, 2, 1);
        gridParts.add(btnPartSearch, 2, 0);
        gridParts.add(tblParts, 0, 1, 5, 4);
        gridParts.add(btnPartAdd, 1, 5);
        gridParts.add(btnPartModify, 2, 5);
        gridParts.add(btnPartDelete, 3, 5);
        
        root.setLeft(gridParts);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Product Pane
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        GridPane gridProducts = new GridPane();
        
        Label lblProducts = new Label("Products");
        TextField tfProductSearch = new TextField();
        Button btnProductSearch = new Button("Search");
        Button btnProductAdd = new Button("Add");
        Button btnProductModify = new Button("Modify");
        Button btnProductDelete = new Button("Delete");
        TableView<Product> tblProducts = new TableView<>();
        
        //Populate tblParts
            //Instantiate TableColumns
        TableColumn<Product, Integer> tcProductID = new TableColumn<>("Product ID");
        TableColumn<Product, String> tcProductName = new TableColumn<>("Product Name");
        TableColumn<Product, Integer> tcProductStock = new TableColumn<>("# in Inventory");
        TableColumn<Product, Double> tcProductPrice = new TableColumn<>("Price per Unit");
        
            //Assign ValueFactories
        tcProductID.setCellValueFactory(product -> new SimpleIntegerProperty(product.getValue().getProductID()).asObject());
        tcProductName.setCellValueFactory(product -> new SimpleStringProperty(product.getValue().getName()));
        tcProductStock.setCellValueFactory(product -> new SimpleIntegerProperty(product.getValue().getInStock()).asObject());
        tcProductPrice.setCellValueFactory(product -> new SimpleDoubleProperty(product.getValue().getPrice()).asObject());
        
        tblProducts.setItems(Inventory.getAllProducts());
        
        tblProducts.getColumns().addAll(tcProductID, tcProductName, tcProductStock, tcProductPrice);
        
        //Style gridParts
            //Size gridProduct to the Root
        gridProducts.prefWidthProperty().bind(root.widthProperty().divide(2));
        
            //Size gridProduct' columns evenly at 10% of Root size
        gridProducts.getColumnConstraints().addAll(cols, cols, cols, cols, cols);
        
            //Size tblParts columns evenly and disable multiselect
        tblProducts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tcProductID.prefWidthProperty().bind(tblParts.widthProperty().divide(4));
        tcProductName.prefWidthProperty().bind(tblParts.widthProperty().divide(4));
        tcProductStock.prefWidthProperty().bind(tblParts.widthProperty().divide(4));
        tcProductPrice.prefWidthProperty().bind(tblParts.widthProperty().divide(4));
        
            //Give gridProduct spacing
        gridProducts.setPadding(new Insets(INSET_SIZE));
        gridProducts.setVgap(5);
        
            //Align Components
        GridPane.setHalignment(lblProducts, HPos.CENTER);
        GridPane.setHalignment(btnProductSearch, HPos.CENTER);
        GridPane.setHalignment(btnProductAdd, HPos.CENTER);
        GridPane.setHalignment(btnProductModify, HPos.CENTER);
        GridPane.setHalignment(btnProductDelete, HPos.CENTER);
            
            //Text Styling
        lblProducts.setFont(new Font(30));
        lblProducts.setStyle("-fx-font-weight: bold;");
        btnProductSearch.setStyle("-fx-font-weight: bold;");
        btnProductAdd.setStyle("-fx-font-weight: bold;");
        btnProductModify.setStyle("-fx-font-weight: bold;");
        btnProductDelete.setStyle("-fx-font-weight: bold;");
        
        //Build gridProduct and add to Root
        gridProducts.add(lblProducts, 0, 0, 2, 1);
        gridProducts.add(tfProductSearch, 3, 0, 2, 1);
        gridProducts.add(btnProductSearch, 2, 0);
        gridProducts.add(tblProducts, 0, 1, 5, 4);
        gridProducts.add(btnProductAdd, 1, 5);
        gridProducts.add(btnProductModify, 2, 5);
        gridProducts.add(btnProductDelete, 3, 5);
        root.setRight(gridProducts);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Exit Button
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        HBox exitPane = new HBox();
        Button btnExit = new Button("Exit");
        
        //Styling
            //Add Spacing
        exitPane.setPadding(new Insets(INSET_SIZE, 0, INSET_SIZE, 0));
            
            //Font Styling
        btnExit.setStyle("-fx-font-weight: bold;");
        BorderPane.setAlignment(btnExit, Pos.CENTER);
        
            //Align Controls
        exitPane.setAlignment(Pos.CENTER);
         
        //Build exitPane and add to Root
        exitPane.getChildren().add(btnExit);
        root.setBottom(exitPane);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Event Handling
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        
        //Handle Menu Events
        menuExit.setOnAction(e -> {
            //Hiding the stage and then exiting seems more responsive than just exiting
            stage.hide();
            System.exit(0);
        });
        
        //Handle gridParts Events
        btnPartSearch.setOnAction(e ->{
            //Look up the part by name. If one is found, select the correct entry in the TableView.
            //  If there are multiple parts with the same name, only the first one is selected.
            int result = Inventory.lookupPart(tfPartSearch.getText());
            if (result >= 0){
                tblParts.getSelectionModel().select(result);
                tblParts.scrollTo(result);
            }
            else{
                tblParts.getSelectionModel().clearSelection();
            }
        });
        
        btnPartAdd.setOnAction(e -> {
            //Build "Add Part" screen.
            GenerateGUI.part(new Stage(), null);
        });
        
        btnPartModify.setOnAction(e ->{
            //Only modify a part if the user has one selected in the table
            if (!tblParts.getSelectionModel().isEmpty()){
                Stage newStage = new Stage();

                //Set an event handler that refreshes tblParts when a modification is complete
                newStage.setOnCloseRequest(e2 ->{
                    tblParts.refresh();
                });

                GenerateGUI.part(newStage, tblParts.getSelectionModel().getSelectedItem());
            }
        });
        
        btnPartDelete.setOnAction(e ->{
            //Only delete a part if the user has one selected in the table
            if (!tblParts.getSelectionModel().isEmpty()){
                Stage newStage = new Stage();
                    
                //Ask for confirmation
                GenerateGUI.confirmation(newStage, "Are you sure you want\nto delete these parts?");

                //Set an event handler for the confirmation box that runs if Yes is selected
                newStage.setOnCloseRequest(e2 ->{
                    //Get a list of all of the parts the user has selected
                    ObservableList<Integer> selected = tblParts.getSelectionModel().getSelectedIndices();

                    //Autounbox the Integers and use the ints as indices
                    //This will only run once, since you can only select a single item, but this code will
                    //allow it to work with multiple selections if the implementation is ever changed
                    for (int partIndex : selected){
                        Inventory.removePart(partIndex);
                    }
                });
            }
        });
        
        //Handle gridProduct Events
        btnProductSearch.setOnAction(e ->{
            //Look up the product by name. If one is found, select the product in the Tableview.
            //  If there are multiple products with the same name, only the first one is selected.
            int result = Inventory.lookupProduct(tfProductSearch.getText());
            if (result >= 0){
                tblProducts.getSelectionModel().select(result);
                tblProducts.scrollTo(result);
            }
            else{
                tblProducts.getSelectionModel().clearSelection();
            }
        });
        
        btnProductAdd.setOnAction(e ->{
            //Build "Add Product" screen.
            GenerateGUI.product(new Stage(), null);
        });
        
        btnProductModify.setOnAction(e ->{
            //Only modify a product if the user has one selected in the table
            if (!tblProducts.getSelectionModel().isEmpty()){
                Stage newStage = new Stage();

                //Set an event handler that refreshes tblProducts when a modification is complete
                newStage.setOnCloseRequest(e2 ->{
                    tblProducts.refresh();
                });

                GenerateGUI.product(newStage, tblProducts.getSelectionModel().getSelectedItem());
            }
        });
        
        btnProductDelete.setOnAction(e ->{
            //Only delete a product if the user has one selected in the table
            if (!tblProducts.getSelectionModel().isEmpty()){
                Stage newStage = new Stage();
                
                //Send confirmation
                GenerateGUI.confirmation(newStage, "Are you sure you want\nto delete these products?");
                
                //Set an event handler for the confirmation box to run on "Yes"
                newStage.setOnCloseRequest(e2 ->{
                    //Create a list for errors
                    List<String> errors = new ArrayList<>();
                    
                    //For each product selected, delete it if it doesn't have any associated parts
                    //  Currently only one product can be selected, but this should work with multiple selections.
                    for (Product product : tblProducts.getSelectionModel().getSelectedItems()){
                        if (product.associatedParts.isEmpty()){
                            Inventory.removeProduct(Inventory.indexOf(product));
                        }
                        else{
                            errors.add("Product " + product.getName() + " has an associated part and cannot be deleted.");
                        }
                    }
                    if (!errors.isEmpty()){
                        GenerateGUI.error(new Stage(), errors.toArray(new String[errors.size()]));
                    }
                });
            }
        });
        
        //Handle exitPane Events
        btnExit.setOnAction(e -> {
            menuExit.fire();
        });
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Set Scene and Show
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Build Stage
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, MAIN_WIDTH, MAIN_HEIGHT));
        stage.show();
        //</editor-fold>
    }
    
    //Method to handle the generation of the add part and modify part menus
    protected static void part(Stage stage, Part part){
        //----------------------------------------------------------------------
        //                          Root
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        BorderPane root = new BorderPane();
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          MenuBar
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        MenuBar menu = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuSave = new MenuItem("Save Part");
        MenuItem menuCancel = new MenuItem("Cancel");
          
        //Build Menu and add to Root
        menuFile.getItems().add(menuSave);
        menuFile.getItems().add(menuCancel);
        menu.getMenus().add(menuFile);
        root.setTop(menu);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Part Info Grid
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        GridPane gridParts = new GridPane();
        
        RadioButton rbInHouse = new RadioButton("In-House");
        RadioButton rbOutsourced = new RadioButton("Outsourced");
        
            //Assign Radio Buttons to a Group and set default selected
        ToggleGroup radios = new ToggleGroup();
        radios.getToggles().addAll(rbInHouse, rbOutsourced);
        if (part != null && part instanceof OutsourcedPart){
            rbOutsourced.setSelected(true);
        }
        else{
            rbInHouse.setSelected(true);
        }
        
        Label lblPartID = new Label("ID");
        Label lblPartName = new Label("Name");
        Label lblPartStock = new Label("Inv");
        Label lblPartPrice = new Label("Price/Cost");
        Label lblMaxStock = new Label("Max");
        Label lblMinStock = new Label("Min");
        Label lblCompanyOrMachine = new Label(rbInHouse.isSelected() ? "Machine ID" : "Company Name");
        //Label errorMessage = new Label();
        
        TextField tfPartID = new TextField(part == null ? "Auto-Generated" : String.valueOf(part.getPartID()));
        TextField tfPartName = new TextField(part == null ? "" : part.getName());
        TextField tfPartStock = new TextField(part == null ? "" : String.valueOf(part.getInStock()));
        TextField tfPartPrice = new TextField(part == null ? "" : String.valueOf(part.getPrice()));
        TextField tfMaxStock = new TextField(part == null ? "" : String.valueOf(part.getMaxStock()));
        TextField tfMinStock = new TextField(part == null ? "" : String.valueOf(part.getMinStock()));
        TextField tfCompanyOrMachine = new TextField();
        if (part != null){
            if (part instanceof InHousePart){
                tfCompanyOrMachine.setText(String.valueOf(((InHousePart) part).getMachineID()));
            }
            else if (part instanceof OutsourcedPart){
                tfCompanyOrMachine.setText(String.valueOf(((OutsourcedPart) part).getCompanyName()));
            }
        }
        
        //Style gridParts
            //Add padding
        gridParts.setHgap(10);
        gridParts.setVgap(10);
        
            //Make the columns in gridMain equally large
        ColumnConstraints cols = new ColumnConstraints();
        cols.prefWidthProperty().bind(gridParts.widthProperty().divide(4));
        gridParts.getColumnConstraints().addAll(cols, cols, cols, cols);
        
            //Align Controls
        GridPane.setHalignment(lblPartID, HPos.RIGHT);
        GridPane.setHalignment(lblPartName, HPos.RIGHT);
        GridPane.setHalignment(lblPartStock, HPos.RIGHT);
        GridPane.setHalignment(lblPartPrice, HPos.RIGHT);
        GridPane.setHalignment(lblMaxStock, HPos.RIGHT);
        GridPane.setHalignment(lblMinStock, HPos.CENTER);
        GridPane.setHalignment(lblCompanyOrMachine, HPos.RIGHT);
        GridPane.setHalignment(tfMinStock, HPos.CENTER);
        GridPane.setHalignment(rbInHouse, HPos.RIGHT);
        GridPane.setHalignment(rbOutsourced, HPos.LEFT);
        //GridPane.setHalignment(errorMessage, HPos.CENTER);
        
            //Change MinStock Max TextField width
        tfMinStock.maxWidthProperty().bind(gridParts.widthProperty().divide(6));
        
            //Make the ID TextField disabled and add prompt text to the others
        tfPartID.setDisable(true);
        tfPartName.setPromptText("Part Name");
        tfPartStock.setPromptText("Amt in Inv");
        tfPartPrice.setPromptText("Price/Cost");
        tfMaxStock.setPromptText("Max Stock");
        tfMinStock.setPromptText("Min Stock");
        tfCompanyOrMachine.setPromptText((rbInHouse.isSelected() ? "Machine ID" : "Company Name"));
        
        //Build gridParts and add to Root
        gridParts.add(rbInHouse, 0, 1, 2, 1);
        gridParts.add(rbOutsourced, 2, 1, 2, 1);
        gridParts.add(lblPartID, 0, 2);
        gridParts.add(lblPartName, 0, 3);
        gridParts.add(lblPartStock, 0, 4);
        gridParts.add(lblPartPrice, 0, 5);
        gridParts.add(lblMaxStock, 0, 6);
        gridParts.add(lblMinStock, 2, 6);
        gridParts.add(lblCompanyOrMachine, 0, 7);
        gridParts.add(tfPartID, 1, 2);
        gridParts.add(tfPartName, 1, 3);
        gridParts.add(tfPartStock, 1, 4);
        gridParts.add(tfPartPrice, 1, 5);
        gridParts.add(tfMaxStock, 1, 6);
        gridParts.add(tfMinStock, 2, 6, 2, 1);
        gridParts.add(tfCompanyOrMachine, 1, 7);
        //gridParts.add(errorMessage, 0, 8, 4, 1);
        root.setCenter(gridParts);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Save and Cancel
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        HBox buttonPane = new HBox();
        Button btnSave = new Button("Save Part");
        Button btnCancel = new Button("Cancel");
        
        //Styling
            //Align Controls
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(INSET_SIZE, 0, INSET_SIZE, 0));
        buttonPane.setSpacing(INSET_SIZE);
        
            //Change font styling on buttons
        btnCancel.setMinSize(75, 45);
        btnCancel.setStyle("-fx-font-weight: bold;");
        btnSave.setMinSize(75, 45);
        btnSave.setStyle("-fx-font-weight: bold;");
        
        //Build buttonPane and add to Root
        buttonPane.getChildren().addAll(btnSave, btnCancel);
        root.setBottom(buttonPane);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Event Handling
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Handle Menu Events
        menuSave.setOnAction(e -> {
            String numbers = "\\d+";                //Matches numbers
            String price = "(\\d)+(\\.\\d+)?";      //Matches numbers possibly with decimals
            List<String> errorMessage = new ArrayList<>();
            boolean valid = true;
            
            //Validate that name isn't empty
            if (tfPartName.getText().isEmpty()){
                valid = false;
                errorMessage.add("Part Name: Cannot be empty.");
                tfPartName.setStyle("-fx-border-color: red ;");
            }
            else{
                tfPartName.setStyle("");
            }
            
            //Validate that stock is a positive whole number
            if (!tfPartStock.getText().matches(numbers)){
                valid = false;
                errorMessage.add("Inventory: must have a positive whole number.");
                tfPartStock.setStyle("-fx-border-color: red ;");
            }
            else{
                tfPartStock.setStyle("");
            }
            
            //Validate that price is a positive number
            if (!tfPartPrice.getText().matches(price)){
                valid = false;
                errorMessage.add("Part Price: must have a positive number.");
                tfPartPrice.setStyle("-fx-border-color: red ;");
            }
            else{
                tfPartPrice.setStyle("");
            }
            
            //Validate that Max Stock is a positive whole number
            if (!tfMaxStock.getText().matches(numbers)){
                valid = false;
                errorMessage.add("Max Stock: must have a positive whole number.");
                tfMaxStock.setStyle("-fx-border-color: red ;");
            }
            else{
                tfMaxStock.setStyle("");
            }
            
            //Validate that Min Stock is a positive whole number
            if (!tfMinStock.getText().matches(numbers)){
                valid = false;
                errorMessage.add("Min Stock: must have a positive whole number.");
                tfMinStock.setStyle("-fx-border-color: red ;");
            }
            else{
                tfMinStock.setStyle("");
            }
            
            //Validate that machine number is a positive whole number or company name isn't blank
            if (rbInHouse.isSelected() && !tfCompanyOrMachine.getText().matches(numbers)){
                valid = false;
                errorMessage.add("Machine ID: must have a positive whole number.");
                tfCompanyOrMachine.setStyle("-fx-border-color: red ;");
            }
            else if (tfCompanyOrMachine.getText().isEmpty()){
                valid = false;
                errorMessage.add("Company Name: cannot be empty.");
                tfCompanyOrMachine.setStyle("-fx-border-color: red ;");
            }
            else{
                tfCompanyOrMachine.setStyle("");
            }
            
            //Validate that Min Stock <= Max Stock, Min Stock <= Stock, and Stock <= Max Stock
            try{
                if(Integer.valueOf(tfMinStock.getText()) > Integer.valueOf(tfMaxStock.getText())){
                    valid = false;
                    errorMessage.add("Max Stock: must be greater than or equal to the Min Stock.");     
                    tfMinStock.setStyle("-fx-border-color: red ;");
                    tfMaxStock.setStyle("-fx-border-color: red ;");
                }
                else if(Integer.valueOf(tfPartStock.getText()) < Integer.valueOf(tfMinStock.getText())){
                    valid = false;
                    errorMessage.add("Part Stock: must be equal or greater than the Min Stock.");     
                    tfPartStock.setStyle("-fx-border-color: red ;");
                    tfMinStock.setStyle("-fx-border-color: red ;");
                }
                else if(Integer.valueOf(tfPartStock.getText()) > Integer.valueOf(tfMaxStock.getText())){
                    valid = false;
                    errorMessage.add("Part Stock: must be less than or equal to the Max Stock.");     
                    tfPartStock.setStyle("-fx-border-color: red ;");
                    tfMaxStock.setStyle("-fx-border-color: red ;");
                }
            }
            catch (NumberFormatException ex){
                //Doesn't need to be handled. Error message should tell the user if any of the three
                //Stock numbers aren't numbers
            }
            
            //If all was valid
            if (valid){
                boolean modifying = part != null;
                Part newPart;
                if (rbInHouse.isSelected()){
                    //If a new part is being created or a part needs to be converted from an
                    //  OutsourcedPart to an InHousePart
                    if (!modifying || modifying && part instanceof OutsourcedPart){
                        newPart = new InHousePart(
                            (modifying ? Integer.valueOf(tfPartID.getText()) : -1),
                            tfPartName.getText(),
                            Integer.valueOf(tfCompanyOrMachine.getText()),
                            Integer.valueOf(tfPartStock.getText()),
                            Integer.valueOf(tfMinStock.getText()),
                            Integer.valueOf(tfMaxStock.getText()),
                            Double.valueOf(tfPartPrice.getText())
                        );
                        
                        //If a part is being created, add it to the inventory
                        if (!modifying){
                            Inventory.addPart(newPart);
                        }
                        //If a part is being converted, update the part in the inventory
                        else{
                            Inventory.updatePart(Inventory.indexOf(part), newPart);
                        }
                    }
                    else{
                        //If modifying an InHousePart that doesn't need to be converted, use setters to modify variables
                        part.setName(tfPartName.getText());
                        ((InHousePart) part).setMachineID(Integer.valueOf(tfCompanyOrMachine.getText()));
                        part.setInStock(Integer.valueOf(tfPartStock.getText()));
                        part.setMinStock(Integer.valueOf(tfMinStock.getText()));
                        part.setMaxStock(Integer.valueOf(tfMaxStock.getText()));
                        part.setPrice(Double.valueOf(tfPartPrice.getText()));
                    }
                }
                if (rbOutsourced.isSelected()){
                    //If a new part is being created or a part needs to be converted from an
                    //  InHousePart to an OutsourcedPart
                    if (!modifying || modifying && part instanceof InHousePart){
                        newPart = new OutsourcedPart(
                            (modifying ? Integer.valueOf(tfPartID.getText()) : -1),
                            tfPartName.getText(),
                            tfCompanyOrMachine.getText(),
                            Integer.valueOf(tfPartStock.getText()),
                            Integer.valueOf(tfMinStock.getText()),
                            Integer.valueOf(tfMaxStock.getText()),
                            Double.valueOf(tfPartPrice.getText())
                        );
                        
                        //If a part is being created, add it to the inventory
                        if (!modifying){
                            Inventory.addPart(newPart);
                        }
                        //If a part is being converted, update the part in the inventory
                        else{
                            Inventory.updatePart(Inventory.indexOf(part), newPart);
                        }
                    }
                    else{
                        //If modifying an OutsourcedPart that doesn't need to be converted, use setters to modify variables
                        part.setName(tfPartName.getText());
                        ((OutsourcedPart) part).setCompanyName(tfCompanyOrMachine.getText());
                        part.setInStock(Integer.valueOf(tfPartStock.getText()));
                        part.setMinStock(Integer.valueOf(tfMinStock.getText()));
                        part.setMaxStock(Integer.valueOf(tfMaxStock.getText()));
                        part.setPrice(Double.valueOf(tfPartPrice.getText()));
                    }
                }
                //Fires event to cause tblParts to refresh on the main menu
                stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
                stage.close();
            }
            else{
                GenerateGUI.error(new Stage(), errorMessage.toArray(new String[errorMessage.size()]));
            }
        });
        
        menuCancel.setOnAction(e -> {
            Stage newStage = new Stage();
            
            //Cancellation confirmation prompt
            GenerateGUI.confirmation(newStage, "Are you sure you\nwant to cancel?");

            //Set an event handler for the confirmation box
            newStage.setOnCloseRequest(e2 ->{
                stage.close();
            });
        });
        
        //Handle gridParts Events
        rbInHouse.setOnAction(e -> {
            //Change label and prompt text if the radio button is selected
            lblCompanyOrMachine.setText("Machine ID");
            tfCompanyOrMachine.setPromptText("Machine ID");
        });
        
        rbOutsourced.setOnAction(e -> {
            //Change label and prompt text if the radio button is selected
            lblCompanyOrMachine.setText("Company Name");
            tfCompanyOrMachine.setPromptText("Company Name");
        });
        
        //Handle buttonPane Events
        btnCancel.setOnAction(e ->{
            menuCancel.fire(); //Same as clicking Exit from menu
        });
        
        btnSave.setOnAction(e ->{
            menuSave.fire(); //Same as clicking Save Part from menu
        });
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Set Scene and Show
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Build Stage
        stage.setScene(new Scene(root, PART_WIDTH, PART_HEIGHT));
        stage.setTitle((part == null ? "Add Part" : "Modify Part"));
        stage.show();
        //</editor-fold>
    }
    
    //Method to handle the generation of the add product and modify product menus
    protected static void product(Stage stage, Product product){
        //----------------------------------------------------------------------
        //                          Root
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        BorderPane root = new BorderPane();
        //</editor-fold>

        //----------------------------------------------------------------------
        //                          MenuBar
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        MenuBar menu = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuSave = new MenuItem("Save Product");
        MenuItem menuCancel = new MenuItem("Cancel");
        
        //Build Menu and add to Root
        menuFile.getItems().add(menuSave);
        menuFile.getItems().add(menuCancel);
        menu.getMenus().add(menuFile);
        root.setTop(menu);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Product Info Grid
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        GridPane gridProductInfo = new GridPane();

        Label lblProductInfo = new Label("Product Information");
        Label lblProductID = new Label("ID");
        Label lblProductName = new Label("Name");
        Label lblProductStock = new Label("Inv");
        Label lblProductPrice = new Label("Price/Cost");
        Label lblMaxStock = new Label("Max");
        Label lblMinStock = new Label("Min");
        //Label errorMessage = new Label();
        
        TextField tfProductID = new TextField(product == null ? "Auto-Generated" : String.valueOf(product.getProductID()));
        TextField tfProductName = new TextField(product == null ? "" : product.getName());
        TextField tfProductStock = new TextField(product == null ? "" : String.valueOf(product.getInStock()));
        TextField tfProductPrice = new TextField(product == null ? "" : String.valueOf(product.getPrice()));
        TextField tfMaxStock = new TextField(product == null ? "" : String.valueOf(product.getMaxStock()));
        TextField tfMinStock = new TextField(product == null ? "" : String.valueOf(product.getMinStock()));
        
        //Style gridProductInfo
            //Size gridProductInfo to half of the root's width
        gridProductInfo.prefWidthProperty().bind(root.widthProperty().divide(2));
        
            //Size gridProductInfo's columns evenly at 25% each
        ColumnConstraints cols = new ColumnConstraints();
        cols.prefWidthProperty().bind(root.widthProperty().divide(8));
        gridProductInfo.getColumnConstraints().addAll(cols, cols, cols, cols);
        
            //Give gridProductInfo padding and spacing
        Insets productInfoInset = new Insets(INSET_SIZE * 8, 0, 0, INSET_SIZE);
        gridProductInfo.setPadding(productInfoInset);
        gridProductInfo.setHgap(10);
        gridProductInfo.setVgap(10);
        
            //Align Controls
        GridPane.setHalignment(lblProductInfo, HPos.CENTER);
        GridPane.setHalignment(lblProductID, HPos.RIGHT);
        GridPane.setHalignment(lblProductName, HPos.RIGHT);
        GridPane.setHalignment(lblProductStock, HPos.RIGHT);
        GridPane.setHalignment(lblProductPrice, HPos.RIGHT);
        GridPane.setHalignment(lblMaxStock, HPos.RIGHT);
        GridPane.setHalignment(lblMinStock, HPos.CENTER);
        GridPane.setHalignment(tfMinStock, HPos.CENTER);
        //GridPane.setHalignment(errorMessage, HPos.CENTER);
        
            //Change MinStock Max TextField width
        tfMinStock.maxWidthProperty().bind(gridProductInfo.widthProperty().divide(6));
        
            //Font adjustments
        lblProductInfo.setFont(new Font(30));
        
            //Make the ID TextField disabled and add prompt text to the others
        tfProductID.setDisable(true);
        tfProductName.setPromptText("Part Name");
        tfProductStock.setPromptText("Amt in Inv");
        tfProductPrice.setPromptText("Price/Cost");
        tfMaxStock.setPromptText("Max Stock");
        tfMinStock.setPromptText("Min Stock");
        
        //Build gridProductInfo and Add to Root
        gridProductInfo.add(lblProductInfo, 0, 0, 4, 1);
        gridProductInfo.add(lblProductID, 0, 1);
        gridProductInfo.add(lblProductName, 0, 2);
        gridProductInfo.add(lblProductStock, 0, 3);
        gridProductInfo.add(lblProductPrice, 0, 4);
        gridProductInfo.add(lblMaxStock, 0, 5);
        gridProductInfo.add(lblMinStock, 2, 5);
        gridProductInfo.add(tfProductID, 1, 1);
        gridProductInfo.add(tfProductName, 1, 2);
        gridProductInfo.add(tfProductStock, 1, 3);
        gridProductInfo.add(tfProductPrice, 1, 4);
        gridProductInfo.add(tfMaxStock, 1, 5);
        gridProductInfo.add(tfMinStock, 2, 5, 2, 1);
        //gridProductInfo.add(errorMessage, 0, 6, 4, 1);
        root.setLeft(gridProductInfo);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Part Tables Grid
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        GridPane gridParts = new GridPane();
        
        Label lblAllParts = new Label("All Parts");
        Label lblAsscParts = new Label("Parts Associated with this Product");
        TextField tfPartSearch = new TextField();
        Button btnPartSearch = new Button("Search");
        Button btnPartAdd = new Button("Add");
        Button btnPartDelete = new Button("Delete");
        TableView<Part> tblAsscParts = new TableView();
        TableView<Part> tblAllParts = new TableView();
        
        //Set up TableViews
            //Instantiate TableColumns
        TableColumn<Part, Integer> tcAsscPartID = new TableColumn<>("Part ID");
        TableColumn<Part, String> tcAsscPartName = new TableColumn<>("Part Name");
        TableColumn<Part, Integer> tcAsscPartStock = new TableColumn<>("# in Inventory");
        TableColumn<Part, Double> tcAsscPartPrice = new TableColumn("Price per Unit");
        
        TableColumn<Part, Integer> tcPartID = new TableColumn<>("Part ID");
        TableColumn<Part, String> tcPartName = new TableColumn<>("Part Name");
        TableColumn<Part, Integer> tcPartStock = new TableColumn<>("# in Inventory");
        TableColumn<Part, Double> tcPartPrice = new TableColumn<>("Price per Unit");
        
            //Assign ValueFactories
        tcAsscPartID.setCellValueFactory(part -> new SimpleIntegerProperty(part.getValue().getPartID()).asObject());
        tcAsscPartName.setCellValueFactory(part -> new SimpleStringProperty(part.getValue().getName()));
        tcAsscPartStock.setCellValueFactory(part -> new SimpleIntegerProperty(part.getValue().getInStock()).asObject());
        tcAsscPartPrice.setCellValueFactory(part -> new SimpleDoubleProperty(part.getValue().getPrice()).asObject());
        
        tcPartID.setCellValueFactory(part -> new SimpleIntegerProperty(part.getValue().getPartID()).asObject());
        tcPartName.setCellValueFactory(part -> new SimpleStringProperty(part.getValue().getName()));
        tcPartStock.setCellValueFactory(part -> new SimpleIntegerProperty(part.getValue().getInStock()).asObject());
        tcPartPrice.setCellValueFactory(part -> new SimpleDoubleProperty(part.getValue().getPrice()).asObject());
        
            //Set items in tables and add columns
        ObservableList<Part> listTempAsscParts = FXCollections.observableArrayList();
        
            //If modifying a product, fill the temporary part list with parts from the products associatedParts list
        if (product != null){
            for (Part p : product.associatedParts){
                listTempAsscParts.add(p);
            }
        }
        
        tblAsscParts.setItems(listTempAsscParts);
        tblAllParts.setItems(Inventory.getAllParts());
        
        tblAsscParts.getColumns().addAll(tcAsscPartID, tcAsscPartName, tcAsscPartStock, tcAsscPartPrice);
        tblAllParts.getColumns().addAll(tcPartID, tcPartName, tcPartStock, tcPartPrice);
        
        //Style gridParts and Controls
            //Size gridParts to half of the root's width
        gridParts.prefWidthProperty().bind(root.widthProperty().divide(2));
        
            //Size gridParts' columns evenly at 25% each
        ColumnConstraints cols2 = new ColumnConstraints();
        cols2.prefWidthProperty().bind(root.widthProperty().divide(8));
        gridParts.getColumnConstraints().addAll(cols2, cols2, cols2, cols2);
        
            //Size TableView columns and set selection mode
        tblAsscParts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblAllParts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        tcAsscPartID.prefWidthProperty().bind(tblAsscParts.widthProperty().divide(4));
        tcAsscPartName.prefWidthProperty().bind(tblAsscParts.widthProperty().divide(4));
        tcAsscPartStock.prefWidthProperty().bind(tblAsscParts.widthProperty().divide(4));
        tcAsscPartPrice.prefWidthProperty().bind(tblAsscParts.widthProperty().divide(4));
        
        tcPartID.prefWidthProperty().bind(tblAllParts.widthProperty().divide(4));
        tcPartName.prefWidthProperty().bind(tblAllParts.widthProperty().divide(4));
        tcPartStock.prefWidthProperty().bind(tblAllParts.widthProperty().divide(4));
        tcPartPrice.prefWidthProperty().bind(tblAllParts.widthProperty().divide(4));
            
            //Give gridParts padding and insets
        gridParts.setHgap(10);
        gridParts.setVgap(10);
        gridParts.setPadding(new Insets(20));
        
            //Align Controls
        GridPane.setHalignment(btnPartSearch, HPos.CENTER);
        GridPane.setHalignment(btnPartAdd, HPos.CENTER);
        GridPane.setHalignment(btnPartDelete, HPos.CENTER);
        
            //Text Styling
        btnPartSearch.setStyle("-fx-font-weight: bold;");
        btnPartAdd.setStyle("-fx-font-weight: bold;");
        btnPartDelete.setStyle("-fx-font-weight: bold;");
        lblAllParts.setFont(new Font(30));
        lblAsscParts.setFont(new Font(30));
        
        
        //Build gridParts and Add to Root
        gridParts.add(lblAllParts, 0, 0);
        gridParts.add(btnPartSearch, 1, 0);
        gridParts.add(tfPartSearch, 2, 0, 2, 1);
        gridParts.add(tblAllParts, 0, 1, 4, 4);
        gridParts.add(btnPartAdd, 3, 5);
        gridParts.add(new Separator(), 0, 6, 4, 1);
        gridParts.add(lblAsscParts, 0, 7, 4, 1);
        gridParts.add(tblAsscParts, 0, 8, 4, 4);
        gridParts.add(btnPartDelete, 3, 12);
        root.setRight(gridParts);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Save and Cancel
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        HBox buttonPane = new HBox();
        Button btnSave = new Button("Save Product");
        Button btnCancel = new Button("Cancel");
        
        //Styling
            //Align Controls
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(INSET_SIZE, 0, INSET_SIZE, 0));
        buttonPane.setSpacing(INSET_SIZE);
        
            //Change font styling on buttons
        btnCancel.setMinSize(75, 45);
        btnCancel.setStyle("-fx-font-weight: bold;");
        btnSave.setMinSize(75, 45);
        btnSave.setStyle("-fx-font-weight: bold;");
        
        //Build buttonPane and add to Root
        buttonPane.getChildren().addAll(btnSave, btnCancel);
        root.setBottom(buttonPane);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Event Handling
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Handle Menu events
        menuSave.setOnAction(e -> {
            String numbers = "\\d+";                //Matches positive whole numbers
            String price = "(\\d)+(\\.\\d+)?";      //Matches positive whole numbers possibly with decimals
            List<String> errorMessage = new ArrayList<>();
            boolean valid = true;
            
            //Validate that name isn't blank
            if (tfProductName.getText().isEmpty()){
                valid = false;
                errorMessage.add("Product Name: Cannot be empty.");
                tfProductName.setStyle("-fx-border-color: red ;");
            }
            else{
                tfProductName.setStyle("");
            }
            
            //Validate that Stock is a positive whole number
            if (!tfProductStock.getText().matches(numbers)){
                valid = false;
                errorMessage.add("Inventory: must have a positive whole number.");
                tfProductStock.setStyle("-fx-border-color: red ;");
            }
            else{
                tfProductStock.setStyle("");
            }
            
            //Validate that Max Stock is a positive whole number
            if (!tfMaxStock.getText().matches(numbers)){
                valid = false;
                errorMessage.add("Max Stock: must have a positive whole number.");
                tfMaxStock.setStyle("-fx-border-color: red ;");
            }
            else{
                tfMaxStock.setStyle("");
            }
            
            //Validate that Min Stock is a positive whole number
            if (!tfMinStock.getText().matches(numbers)){
                valid = false;
                errorMessage.add("Min Stock: must have a positive whole number.");
                tfMinStock.setStyle("-fx-border-color: red ;");
            }
            else{
                tfMinStock.setStyle("");
            }
            
            //Validate that Min Stock <= Max Stock, Min Stock <= Stock, and Stock <= Max Stock
            try{
                if(Integer.valueOf(tfMinStock.getText()) > Integer.valueOf(tfMaxStock.getText())){
                    valid = false;
                    errorMessage.add("Max Stock: must be greater than or equal to the Min Stock.");     
                    tfMinStock.setStyle("-fx-border-color: red ;");
                    tfMaxStock.setStyle("-fx-border-color: red ;");
                }
                else if(Integer.valueOf(tfProductStock.getText()) < Integer.valueOf(tfMinStock.getText())){
                    valid = false;
                    errorMessage.add("Product Stock: must exceed the Min Stock.");     
                    tfProductStock.setStyle("-fx-border-color: red ;");
                    tfMinStock.setStyle("-fx-border-color: red ;");
                }
                else if(Integer.valueOf(tfProductStock.getText()) > Integer.valueOf(tfMaxStock.getText())){
                    valid = false;
                    errorMessage.add("Product Stock: must be less than or equal to the Max Stock.");     
                    tfProductStock.setStyle("-fx-border-color: red ;");
                    tfMaxStock.setStyle("-fx-border-color: red ;");
                }
            }
            catch (NumberFormatException ex){
                //Doesn't need to be handled. Error message should tell the user if any of the three
                //Stock numbers aren't numbers
            }
            
            //Validate that the product has at least one part if it isn't being modified
            //---------------------------NOTE---------------------------
            //I allow the removal of the final associatedPart during modification
            //The instructions say "Ensuring that a product must always have at least one part" - note the use of ALWAYS
            //However, the instructions also say "Preventing the user from deleting a product that has a part assigned to it"
            //If I follow both of those, no product could ever be deleted from the system, so I only ensure that the product
            //  has 1+ associatedParts when the product is being CREATED, not when it's being modified. That way, the associatedParts
            //  can be removed during modification, then the product deleted
            if (product == null && tblAsscParts.getItems().isEmpty()){
                valid = false;
                errorMessage.add("Products being created must have at least one associated part.");
                tblAsscParts.setStyle("-fx-border-color: red ;");
            }
            //validate that the product costs at least as much as the sum of its parts
            else{
                double totalPrice = 0;
                tblAsscParts.setStyle("");
                
                for (Part part : tblAsscParts.getItems()){
                    totalPrice += part.getPrice();
                }
                
                try {
                    if (Double.valueOf(tfProductPrice.getText()) < totalPrice){
                        throw new NumberFormatException();
                    }
                    tfProductPrice.setStyle("");
                }
                catch (NumberFormatException ex){
                    valid = false;
                    errorMessage.add("Product Price: must be greater than $" + totalPrice + ", the sum of its parts.");
                    tfProductPrice.setStyle("-fx-border-color: red ;");  
                }
            }
            
            
            //If all was valid
            if (valid){
                if (product == null){
                    //If we were creating a new product, create it and add it to the Inventory
                    Product newProduct = new Product(
                        tfProductName.getText(),
                        Integer.valueOf(tfProductStock.getText()),
                        Integer.valueOf(tfMinStock.getText()),
                        Integer.valueOf(tfMaxStock.getText()),
                        Double.valueOf(tfProductPrice.getText())
                    );
                    newProduct.setAssociatedParts(listTempAsscParts);
                    Inventory.addProduct(newProduct);
                }
                else{
                    //If we were modifying a product, perform the modifications
                    product.setName(tfProductName.getText());
                    product.setInStock(Integer.valueOf(tfProductStock.getText()));
                    product.setMaxStock(Integer.valueOf(tfMaxStock.getText()));
                    product.setMinStock(Integer.valueOf(tfMinStock.getText()));
                    product.setPrice(Double.valueOf(tfProductPrice.getText()));
                    product.setAssociatedParts(listTempAsscParts);
                    
                    //Fires event to cause tblProducts to refresh on the main menu
                    stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
                }
                stage.close();
            }
            else{
                GenerateGUI.error(new Stage(), errorMessage.toArray(new String[errorMessage.size()]));
            }
        });
        
            //Cancel product creation with confirmation box
        menuCancel.setOnAction(e -> {
            Stage newStage = new Stage();
            
            //Cancellation confirmation prompt
            GenerateGUI.confirmation(newStage, "Are you sure you\nwant to cancel?");

            //Set an event handler for the confirmation box
            newStage.setOnCloseRequest(e2 ->{
                stage.close();
            });
        });
        
        //Handle Part-related Events
            //Search for a part with the name in the search text box
        btnPartSearch.setOnAction(e ->{
            //Look up a part by name. If one is found, select it in the TableView
            //  If multiple parts have the same name, only the first is selected.
            int result = Inventory.lookupPart(tfPartSearch.getText());
            if (result >= 0){
                tblAllParts.getSelectionModel().select(result);
                tblAllParts.scrollTo(result);
            }
            else{
                tblAllParts.getSelectionModel().clearSelection();
            }
        });
        
            //Add an associated part
        btnPartAdd.setOnAction(e ->{
            ObservableList<Part> selected = tblAllParts.getSelectionModel().getSelectedItems();
            
            //You can only select one at a time for now, but this code lets it work with multiple selections
            for (Part part : selected){
                if(!listTempAsscParts.contains(part)){
                    listTempAsscParts.add(part);
                }
            }
        });
        
            //Delete associated parts
        btnPartDelete.setOnAction(e ->{
            ObservableList<Part> selected = tblAsscParts.getSelectionModel().getSelectedItems();
            
            //You can only select one at a time for now, but this code lets it work with multiple selections
            for (Part part : selected){
                listTempAsscParts.remove(part);
            }
        });
        
        //Handle buttonPane Events
        btnCancel.setOnAction(e ->{
            menuCancel.fire();      //Same as selecting File -> Cancel
        });
        
        btnSave.setOnAction(e ->{
            menuSave.fire();        //Same as selecting File -> Save
        });
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Set Scene and Show
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Build Stage
        stage.setScene(new Scene(root, PRODUCT_WIDTH, PRODUCT_HEIGHT));
        stage.setTitle((product == null ? "Add Product" : "Modify Product"));
        stage.show();
        //</editor-fold>
    }
    
    //Method to handle the generation of the error messages
    protected static void error(Stage stage, String[] messages){
        //----------------------------------------------------------------------
        //                          Root
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        BorderPane root = new BorderPane();
        StringBuilder buildMessage = new StringBuilder();
        Label lblMessage = new Label();
        Button btnOkay = new Button("Okay");
        
        //Styling
            //Create padding
        root.setPadding(new Insets(INSET_SIZE));
        
            //Align components
        BorderPane.setAlignment(lblMessage, Pos.CENTER);
        BorderPane.setAlignment(btnOkay, Pos.CENTER);
        
        //Build root
            //Build the messages
        for (String message : messages){
            buildMessage.append(message + "\n");
        }
        lblMessage.setText(buildMessage.toString());
        
            //Add components to root
        root.setCenter(lblMessage);
        root.setBottom(btnOkay);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Event Handling
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        btnOkay.setOnAction(e -> {
            stage.close();
        });
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Set Scene and Show
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Build Stage
        stage.setScene(new Scene(root, ERROR_WIDTH, ERROR_HEIGHT + (25 * messages.length)));
        stage.setTitle("Error");
        stage.show();
        //</editor-fold>
    }
    
    //Method to handle the generation of the confirmation prompts
    protected static void confirmation (Stage stage, String message){
        //----------------------------------------------------------------------
        //                          Root
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        BorderPane root = new BorderPane();
        Label lblMessage = new Label(message);
        
        //Add to root
        root.setCenter(lblMessage);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Buttons
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Instantiate Objects
        HBox boxButtons = new HBox();
        Button btnConfirm = new Button("Yes");
        Button btnCancel = new Button("No");
        
        //Styling
            //Add padding and spacing
        boxButtons.setPadding(new Insets(INSET_SIZE));
        boxButtons.setSpacing(INSET_SIZE);
        
            //Center the buttons
        boxButtons.setAlignment(Pos.CENTER);
        
        //Build the HBox and add to root
        boxButtons.getChildren().addAll(btnConfirm, btnCancel);
        root.setBottom(boxButtons);
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Event Handling
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        btnCancel.setOnAction(e -> {
            stage.close();
        });
        
        btnConfirm.setOnAction(e -> {
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
            stage.close();
        });
        //</editor-fold>
        
        //----------------------------------------------------------------------
        //                          Set Scene and Show
        //----------------------------------------------------------------------
        //<editor-fold desc="Hidden" defaultstate="collapsed">
        //Build Stage
        stage.setScene(new Scene(root, CONFIRMATION_WIDTH, CONFIRMATION_HEIGHT));
        stage.setTitle("Confirm Action");
        stage.show();
        //</editor-fold>
    }
}
