package view_login_forgot_password;

import client_to_server_controller.Client_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view_login.Login_View_Controller;

public class Show_Forgot_Password_View {

	public Show_Forgot_Password_View(Login_View_Controller login_View_Controller, Client_Controller clientController) {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view_login_forgot_password/Forgot_Password_View_Skin.fxml"));
			AnchorPane root = loader.load();
			Forgot_Password_View_Controller controller = loader.getController();
			Image image = new Image("/icons/elantech_icon.png");
			stage.getIcons().add(image);
			stage.setTitle("Elantech");
			controller.setStage(stage);
			controller.setClientServerCont(clientController);
			Scene scene = new Scene(root, 681, 601);
			scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
			stage.initOwner(login_View_Controller.getStage());
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
