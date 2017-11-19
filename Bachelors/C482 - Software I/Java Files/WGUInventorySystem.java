/**
 * @author Michael Girard
 * Student ID: #000604671
 * Mentor: Rick Haver
 */
package wguinventorysystem;

import javafx.application.Application;
import javafx.stage.Stage;

public class WGUInventorySystem extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GenerateGUI.mainMenu(primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
