package view_main;

import client_to_server_controller.Client_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.User_Account_Information;

public class Show_Main_View {

	public Show_Main_View(Stage stage, User_Account_Information user_Account_Model, Client_Controller client)	{
		try {
			Image image = new Image("/icons/logo_sm.png");
			stage.getIcons().add(image);
			stage.setTitle("ElantechLLC"); 
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view_main/main_view_skin.fxml"));
			AnchorPane root = loader.load();
			Main_View_Controller controller = loader.getController();
			controller.setStage(stage);
			controller.setUserAccount(user_Account_Model);
			controller.setClientController(client);
			controller.showDashboard();
			Scene scene = new Scene(root, 1517, 796);
			scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
