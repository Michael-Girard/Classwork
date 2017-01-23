/*
Program Name: Currency Converter
Program Purpose: Input a number of US Dollars and an exchange rate and click a button to convert the US Dollars into
   other types of currency.
Author: Michael Girard
Date Last Modified: March 21, 2015.
Program created using the comprehensive 10th edition textbook, JDK 8u31, and jGRASP 2.0.1_01.
*/

import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.geometry.Insets;
import javafx.geometry.HPos;

public class CurrencyConverter extends Application{
   public static void main(String[] args){
      Application.launch(args);
   }
   
   public void start(Stage primaryStage) {
      //Create and style Layouts
      GridPane pane = new GridPane();
      
      //Create controls
      Label instruction = new Label("Enter Dollar Amount");
      Label labelUSDollars = new Label("US Dollars");
      Label labelCanadianDollars = new Label("Canadian Dollars");
      Label labelEuros = new Label("Euro");
      Label labelBritishPounds = new Label("British Pounds");
      Label labelExchangeRate = new Label("Exchange Rate");
      Label labelConvertedAmount = new Label("Converted Amount");
      
      TextField tfUSDollars = new TextField();
      TextField tfCanadianDollarsRate = new TextField("1");
      TextField tfCanadianDollars = new TextField();
      TextField tfEurosRate = new TextField("1");
      TextField tfEuros = new TextField();
      TextField tfBritishPoundsRate = new TextField("1");
      TextField tfBritishPounds = new TextField();
      
      Button convert = new Button("Convert");
      
      Line separator = new Line();
      
      //Layout and Control Styling
      pane.setPadding(new Insets(5,5,5,5));
      pane.setVgap(3);
      pane.setHgap(5);
      pane.setColumnSpan(separator, 3);
      pane.setHalignment(labelUSDollars, HPos.RIGHT);
      pane.setHalignment(labelCanadianDollars, HPos.RIGHT);
      pane.setHalignment(labelEuros, HPos.RIGHT);
      pane.setHalignment(labelBritishPounds, HPos.RIGHT);
      
      separator.endXProperty().bind(pane.widthProperty().subtract(10));
      
      tfCanadianDollars.setEditable(false);
      tfEuros.setEditable(false);
      tfBritishPounds.setEditable(false);
      
      //Add Controls to Layout
      //Row 1
      pane.add(instruction, 0, 0);
      
      //Row 2
      pane.add(labelUSDollars, 0, 1);
      pane.add(tfUSDollars, 1, 1);
      pane.add(convert, 2, 1);
      
      //Row 3
      pane.add(separator, 0, 3);
      
      //Row 4
      pane.add(labelExchangeRate, 1, 4);
      pane.add(labelConvertedAmount, 2, 4);
      
      //Row 5
      pane.add(labelCanadianDollars, 0, 5);
      pane.add(tfCanadianDollarsRate, 1, 5);
      pane.add(tfCanadianDollars, 2, 5);
      
      //Row 6
      pane.add(labelEuros, 0, 6);
      pane.add(tfEurosRate, 1, 6);
      pane.add(tfEuros, 2, 6);
      
      //Row 7
      pane.add(labelBritishPounds, 0, 7);
      pane.add(tfBritishPoundsRate, 1, 7);
      pane.add(tfBritishPounds, 2, 7);
     
      
      // Finalize and display
      Scene scene = new Scene(pane, 500, 167);
      primaryStage.setTitle("Currency Converter");
      primaryStage.setScene(scene);
      primaryStage.show();
      
      //Convert Button Functionality
      convert.setOnAction(e -> {
         try{
            double convertThis = Double.valueOf(tfUSDollars.getText().replaceAll("[$,]", ""));
            
            //Convert from US Dollars to Canadian Dollars
            NumberFormat numFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
            double exchangeRate = Double.valueOf(tfCanadianDollarsRate.getText());
            tfCanadianDollars.setText(numFormat.format((convertThis * exchangeRate)));
            
            //Convert from US Dollars to Euros (Book says German Marks, then seems to want Euros)
            numFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
            exchangeRate = Double.valueOf(tfEurosRate.getText());
            tfEuros.setText(numFormat.format((convertThis * exchangeRate)));
            
            //Convert from US Dollars to British Pounds
            numFormat = NumberFormat.getCurrencyInstance(Locale.UK);
            exchangeRate = Double.valueOf(tfBritishPoundsRate.getText());
            tfBritishPounds.setText(numFormat.format((convertThis * exchangeRate)));
            
            /* Alternate, but less readable, code for the above:
               tfCanadianDollars.setText(NumberFormat.getCurrencyInstance(Locale.CANADA).format(convertThis * Double.valueOf(tfCanadianDollarsRate.getText())));
               tfEuros.setText(NumberFormat.getCurrencyInstance(Locale.GERMANY).format(convertThis * Double.valueOf(tfEurosRate.getText())));
               tfBritishPounds.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(convertThis * Double.valueOf(tfBritishPoundsRate.getText())));
            */
         }
         catch(Exception ex){
            //Display an error message
            Stage errorStage = new Stage();
            GridPane errorPane = new GridPane();
            errorPane.setPadding(new Insets(5,5,5,5));
            errorPane.setVgap(15);
            
            Button okay = new Button("OK");
            okay.prefWidthProperty().bind(errorPane.widthProperty().subtract(5));
            
            errorPane.add(new Label("Please ensure the dollar amount and\nexchange rates are positive numbers."), 0, 0);
            errorPane.add(okay, 0, 1);
            
            Scene errorScene = new Scene(errorPane, 210, 85);
            errorStage.setScene(errorScene);
            errorStage.setTitle("Error");
            errorStage.show();
            
            okay.setOnAction(e2 -> {
               errorStage.close();
            });
         }
      });
   }
}