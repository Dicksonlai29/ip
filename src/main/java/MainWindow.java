import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Control the main GUI
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox diaCont;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private NUSYapBot nusYapBot;

    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/yapbot.png"));

    /**
     * Initializes the main window by binding the vertical scroll value of the scroll pane
     * to the height property of the dialog container. 
     * This ensures that the scroll pane automatically scrolls to the bottom as 
     * new content is added to the dialog container.
     */
    @FXML
    public void init() {
        scrollPane.vvalueProperty().bind(diaCont.heightProperty());
    }

    public void setBot(NUSYapBot bot) {
        nusYapBot = bot;
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        diaCont.getChildren().addAll(
                DialogBox.getUserDialog(userText ,userImg),
                DialogBox.getBotDialog(nusYapBot.getResponse(userText), dukeImg)
        );
        userInput.clear();
    }

}
