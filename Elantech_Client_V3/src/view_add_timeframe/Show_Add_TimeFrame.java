package view_add_timeframe;

import client_to_server_controller.Client_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model_trucking.Store_Model;
import view_main.Main_View_Controller;

public class Show_Add_TimeFrame {

	@SuppressWarnings("static-access")
	public Show_Add_TimeFrame(Main_View_Controller main_View_Controller, AnchorPane main_view_pane, Client_Controller client, Store_Model store_Model) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view_add_timeframe/Add_TimeFrame_Skin.fxml"));
			AnchorPane root = loader.load();
			Add_TimeFrame_Controller controller = loader.getController();
			
			controller.setMainViewController(main_View_Controller);
			controller.setClientController(client);
			controller.setMainViewPane(main_view_pane);
			controller.setTextFieldInformaition(store_Model);
			
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
