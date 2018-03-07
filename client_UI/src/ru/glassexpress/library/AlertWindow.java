package ru.glassexpress.library;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;

import java.awt.*;
import java.util.Optional;

public class AlertWindow {
    private static Alert alert;
    private static TextInputDialog dialog;
    private static void alertWindow(String title, String msg, Alert.AlertType type){
        Platform.runLater(() -> {
            alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }
    public static void errorMessage(String msg) {

        alertWindow("Это фиаско, братан!", msg, Alert.AlertType.ERROR );
    }

    public static void infoMessage(String msg) {
        alertWindow("Info", msg, Alert.AlertType.INFORMATION );
    }
    public static void warningMessage(String msg) {
        alertWindow("Warning", msg, Alert.AlertType.WARNING );
    }

    public static boolean confirmationWindow(String title, String object){
        boolean answer=false;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(object);


        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            answer=true;
        }
        return answer;
    }

    public static String dialogWindow(String title, String msg){

            dialog = new TextInputDialog( );

            dialog.setTitle(title);
            dialog.setHeaderText(null);
            dialog.setContentText(msg);

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                String res = result.get();
                return res;
            }
               // System.out.println("Your name: " + result.get());

           // dialog.showAndWait();

        return null;
    }

    public static String dialogIndexWindow(String title, String msg){

        dialog = new TextInputDialog( );

        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(msg);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String res = result.get();
            return res;
        }
        // System.out.println("Your name: " + result.get());

        // dialog.showAndWait();

        return null;
    }
}
