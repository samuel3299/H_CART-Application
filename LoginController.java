package com.example.hcart;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public static  AnchorPane ProductPage;
    @FXML
    TextField email;
    @FXML
    PasswordField pass;
    @FXML
    public void Loggedinn(MouseEvent mouseEvent) throws SQLException, IOException {
        String query = String.format("select emailId,password,UserWork from Login");
        ResultSet res = Main.connect.executeQuery(query);
        boolean hi = false;
        while(res.next()){
            String a = res.getString("emailId");
            String b = res.getString("password");
            String c  = res.getString("UserWork");
//            System.out.println(c);
//            if User is Buyer going to buyer Page
            if(a.equals(email.getText()) && b.equals(pass.getText()) && c.equals("Buyer")){
                Main.email = a;
                ProductPage = new AnchorPane();
                ProductPage = FXMLLoader.load(getClass().getResource("productPage.fxml"));
                Main.root.getChildren().clear();
                Main.root.getChildren().add(ProductPage);
                hi = true;
                break;
            }
//             if User is Seller Going to Seller Page
            else if(a.equals(email.getText()) && b.equals(pass.getText()) &&c.equals("Seller")){
                Main.email = a;
                ProductPage = new AnchorPane();
                ProductPage = FXMLLoader.load(getClass().getResource("SellerPage.fxml"));
                Main.root.getChildren().clear();
                Main.root.getChildren().add(ProductPage);
                hi = true;
                break;
            }
        }
        if(hi == false){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Incorrect email Id or Password Login Again!!");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}
