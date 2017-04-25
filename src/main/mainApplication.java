package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class mainApplication extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
         Parent loader = FXMLLoader.load((getClass().getResource("/fxml/mainApp.fxml")));
         Scene scene = new Scene(loader,602,512);
         primaryStage.setTitle("street-fighter");
         primaryStage.setResizable(false);
         primaryStage.setScene(scene);
         primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		       @Override
		       public void handle(WindowEvent e) {
		          Platform.exit();
		          System.exit(0);
		       }
		    });
         primaryStage.show();
	}
	
	public static void main(String...args) {
		launch(args);
	}
}
