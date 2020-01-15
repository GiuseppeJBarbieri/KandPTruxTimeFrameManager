package server_to_client_controller;

import client_to_server_controller.Client_Controller;
import javafx.application.Platform;

public class Logout_All_Users_Method {

	public Logout_All_Users_Method(Object object, Client_Controller client) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				//Alert_Box alertBox = new Alert_Box();
				//alertBox.informationAlertBox("Loggin out", "YOU HAVE BEEN LOGGED OUT BY THE SERVER!", "Click ok to continue...");
				Platform.exit();
			}
		});
		
	}

}
