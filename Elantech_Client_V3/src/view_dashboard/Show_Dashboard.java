package view_dashboard;

import client_to_server_controller.Client_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import view_main.Main_View_Controller;

public class Show_Dashboard {

	@SuppressWarnings("static-access")
	public Show_Dashboard(Main_View_Controller main_View_Controller, AnchorPane main_view_pane, Client_Controller client) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view_dashboard/Dashboard_Skin.fxml"));
			AnchorPane root = loader.load();
			Dashboard_Controller controller = loader.getController();
			
			controller.setMainViewController(main_View_Controller);
			controller.setClientController(client);
			controller.setMainViewPane(main_view_pane);
			controller.searchStore("");
			
			main_view_pane.getChildren().add(root);
			main_view_pane.setLeftAnchor(root, 0.0);
			main_view_pane.setRightAnchor(root, 0.0);
			main_view_pane.setTopAnchor(root, 0.0);
			main_view_pane.setBottomAnchor(root, 0.0);
			root.translateXProperty().set(0);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
