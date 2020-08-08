package server_to_client_controller;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import model_trucking.Email_Sent_Model;
import models.Server_To_Client_Message_Model;

public class Emails_Sent_Methods {

	public Emails_Sent_Methods(Object object, Client_Controller client) {
		Email_Sent_Model obj = (Email_Sent_Model) object;
		Server_To_Client_Message_Model message = obj.getMessage();
		
		if (message.getInfoOrError() == 0) {
			// No Error
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					client.getDashboardController().closeAlert();
					Alert_Box alertBox = new Alert_Box();
					alertBox.informationAlertBox(message.getTitle(), message.getHeader(), message.getContent());
					
				}
			});
			
		} else {
			// Error
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					client.getDashboardController().closeAlert();
					Alert_Box alertBox = new Alert_Box();
					alertBox.informationAlertBox(message.getTitle(), message.getHeader(), message.getContent());
				}
			});
			
		}
	}

}
