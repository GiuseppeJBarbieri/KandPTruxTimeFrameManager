package server_to_client_controller;

import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import model_trucking.Store_Search_Result_Model;
import view_dashboard.Dashboard_Controller;

public class Search_Store_Method {

	public Search_Store_Method(Object object, Client_Controller client) {
		Store_Search_Result_Model obj = (Store_Search_Result_Model) object;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Dashboard_Controller view = client.getDashboardController();
				view.setStoreList(obj.getStoreResultsList());
			}
		});
		
	}

}
