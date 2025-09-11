import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private NUSYapBot nusYapBot = new NUSYapBot();
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
