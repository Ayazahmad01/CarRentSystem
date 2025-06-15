package com.carrental.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.carrental.db.DBConnector;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        //  Title
        Label titleLabel = new Label("🚗 Car Rental System - Login");
        titleLabel.setFont(Font.font("Arial", 20));
        titleLabel.setTextFill(Color.DARKGREEN);

        //  Username
        Label userLabel = new Label("👤 Username:");
        userLabel.setTextFill(Color.FORESTGREEN);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        // Password
        Label passLabel = new Label("🔑 Password:");
        passLabel.setTextFill(Color.FORESTGREEN);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        //  Message Label
        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);

        //  Login Button
        Button loginButton = new Button("💚 Login");
        loginButton.setStyle(
                "-fx-background-color: #4CAF50;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8 16;" +
                "-fx-background-radius: 10;"
        );
        loginButton.setOnMouseEntered(e -> loginButton.setStyle(
                "-fx-background-color: #66BB6A;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8 16;" +
                "-fx-background-radius: 10;"
        ));
        loginButton.setOnMouseExited(e -> loginButton.setStyle(
                "-fx-background-color: #4CAF50;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8 16;" +
                "-fx-background-radius: 10;"
        ));

        loginButton.setOnAction(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();

            Connection conn = DBConnector.connect();

            if (conn == null) {
                messageLabel.setText("❌ Failed to connect to the database.");
                return;
            }

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";

            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, user);
                stmt.setString(2, pass);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    Dashboard dashboard = new Dashboard();
                    dashboard.showDashboard(primaryStage);
                } else {
                    messageLabel.setText("❌ Invalid username or password.");
                }

                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                messageLabel.setText("❌ Error during login.");
            }
        });

        //  Layout Setup
        VBox layout = new VBox(12);
        layout.getChildren().addAll(
                titleLabel,
                userLabel, usernameField,
                passLabel, passwordField,
                loginButton,
                messageLabel
        );
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30; -fx-background-color: #e0f2f1;"); // light greenish background

        //  Scene
        Scene scene = new Scene(layout, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login - Car Rental System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
