package server_to_client_controller;

import client_to_server_controller.Client_Controller;
import javafx.application.Platform;

public class Get_Table_Information_Method {

	public Get_Table_Information_Method(Object object, Client_Controller client) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				//client.getMainView().getTableInformation();
			}
		});
		
	}
	
}
