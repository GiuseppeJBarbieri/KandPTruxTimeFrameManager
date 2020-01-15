package server_to_client_controller;

import client_to_server_controller.Client_Controller;
import event_objects.COD_Search_Result_Model;
import javafx.application.Platform;
import view_cods.COD_Controller;

public class Search_COD_Request {

	public Search_COD_Request(Object object, Client_Controller client) {
		COD_Search_Result_Model obj = (COD_Search_Result_Model) object;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				COD_Controller view = client.getCodController();
				view.setCODList(obj.getCodList());
			}
		});
	
	}

}
