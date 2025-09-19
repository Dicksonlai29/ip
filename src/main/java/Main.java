import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Initializes the primary stage, loads the main window layout from FXML, sets the application
 * title and icon, and injects the NUSYapBot instance into the controller.
 */
public class Main extends Application {

    private NUSYapBot nusYapBot = new NUSYapBot();

    /**
     * Starts the JavaFX application by initializing and displaying the main window.
     * <p>
     * Loads the FXML layout for the main window, sets the application title and icon,
     * configures minimum window dimensions, and injects the bot instance into the controller.
     * Handles any IOExceptions that may occur during FXML loading.
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            // Set title and logo
            stage.setTitle("NUSYapBot");
            Image logo = new Image("/images/icon.png");
            stage.getIcons().add(logo);

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(410);
            fxmlLoader.<MainWindow>getController().setBot(nusYapBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
