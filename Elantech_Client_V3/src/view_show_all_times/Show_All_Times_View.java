package view_show_all_times;

import client_to_server_controller.Client_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import models.User_Account_Information;
import view_add_store.Add_Store_Controller;
import view_main.Main_View_Controller;

public class Show_All_Times_View {

	@SuppressWarnings("static-access")
	public Show_All_Times_View(Main_View_Controller main_View_Controller, AnchorPane main_view_pane, Client_Controller client, User_Account_Information user_Account_Model) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view_show_all_times/All_Times_View.fxml"));
			AnchorPane root = loader.load();
			All_Times_Controller controller = loader.getController();
			
			controller.setMainViewController(main_View_Controller);
			controller.setClientController(client);
			controller.setUserAccountModel(user_Account_Model);
			controller.setMainViewPane(main_view_pane);
			controller.getAllTimeFrames();
			
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
