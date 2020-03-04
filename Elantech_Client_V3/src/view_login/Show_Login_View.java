package view_login;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Show_Login_View {

	private static double xOffset = 0;
	private static double yOffset = 0;
	
	public Show_Login_View(Stage stage) {
		try {
			Image image = new Image("/icons/iconfinder-11-512.png");
			stage.getIcons().add(image);
			stage.setTitle("ElantechLLC"); 
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view_login/Login_View_Skin.fxml"));
			AnchorPane root = loader.load();
			Login_View_Controller controller = loader.getController();
			controller.setStage(stage);
			
			Scene scene = new Scene(root, 1310, 570);
			scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(scene);
		
			scene.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY();
	            }
	        });
			scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                stage.setX(event.getScreenX() - xOffset);
	                stage.setY(event.getScreenY() - yOffset);
	            }
	        });
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
