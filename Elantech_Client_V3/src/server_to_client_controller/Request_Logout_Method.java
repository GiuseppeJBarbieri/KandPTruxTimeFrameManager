package server_to_client_controller;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import models.Logout_Model;

public class Request_Logout_Method {

	public Request_Logout_Method(Object object, Client_Controller client) {
		
		Logout_Model obj = (Logout_Model) object;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert_Box alertBox = new Alert_Box();
				alertBox.informationAlertBox(obj.getMessage().getTitle(), obj.getMessage().getHeader(), obj.getMessage().getContent());
			}
		});
	
	}

}
