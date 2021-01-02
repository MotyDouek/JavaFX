package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameFiled;
    @FXML
    private Button helloButton, byeButton;
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Label ourLabel;

    @FXML
    public void initialize(){
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

    @FXML
    public void onButtonClick(ActionEvent e) {
        if(e.getSource() == helloButton) {
            System.out.println("Hello, " + nameFiled.getText());
        } else if(e.getSource() == byeButton) {
            System.out.println("Good-Bye, " + nameFiled.getText());
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                    System.out.println("I`m going to sleep " + s);
                    Thread.sleep(1000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                            System.out.println("I`m updating the label on the " + s);
                            ourLabel.setText("We did something!");
                        }
                    });
                } catch (InterruptedException evenr) {
                    //we dont care about this
                }
            }
        };

        new Thread(task).start();

        if(ourCheckBox.isSelected()){
            nameFiled.clear();
            helloButton.setDisable(true);
            byeButton.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased() {
        String text = nameFiled.getText();
        boolean disableButton = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disableButton);
        byeButton.setDisable(disableButton);
    }

    @FXML
    public void handleChange() {
        System.out.println("The checkbox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));
    }

}
