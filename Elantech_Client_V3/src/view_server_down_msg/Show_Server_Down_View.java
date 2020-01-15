package view_server_down_msg;

import client_to_server_controller.Client_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Show_Server_Down_View {

	private Stage stage;

	public Show_Server_Down_View(Stage prevStage, String hostname, int port, Client_Controller client_Controller) {
		try {
			stage = new Stage();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/view_server_down_msg/Server_Down_MSG_View.fxml"));
			AnchorPane root = loader.load();
			Server_Down_Controller controller = loader.getController();
			controller.setClientController(client_Controller);
			controller.setStage(stage, prevStage);
			controller.setSocket(hostname, port);
			controller.runServer();
			Scene scene = new Scene(root, 742, 532);
			scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(prevStage);
			stage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
