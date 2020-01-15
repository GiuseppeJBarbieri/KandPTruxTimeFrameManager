package server_to_client_controller;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import model_trucking.Remove_Store_Model;
import models.Server_To_Client_Message_Model;

public class Remove_Store_Method {

	public Remove_Store_Method(Object object, Client_Controller client) {
		Alert_Box alertBox = new Alert_Box();
		Remove_Store_Model obj = (Remove_Store_Model) object;
		Server_To_Client_Message_Model message = obj.getMessage();
		if (message.getInfoOrError() == 0) {
			// No Error
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					alertBox.informationAlertBox(message.getTitle(), message.getHeader(), message.getContent());
				}
			});
			
		} else {
			// Error
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					alertBox.errorAlertBox(message.getTitle(), message.getHeader(), message.getContent());
				}
			});
			
		}
	}

}
