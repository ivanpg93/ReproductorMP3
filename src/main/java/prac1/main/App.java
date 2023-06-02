package prac1.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        
        try {
        
            String fxml = "vista/mainScreen";

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource(fxml + ".fxml"));

            Parent p = fxmlLoader.load();

            scene = new Scene(p);
            
            primaryStage.setScene(scene);
            
            primaryStage.show();
            
        } catch (IOException ex){
            
             System.out.println("Ni idea del que pot haver passat... ");
             System.out.println(ex.toString());
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void starter(String[] args) {
        launch();
    }
}