package server_to_client_controller;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import models.Create_Account_Model;
import models.Server_To_Client_Message_Model;
import view_login_create_account.Create_Account_View_Controller;

public class Create_Account_Method {

	public Create_Account_Method(Object object, Client_Controller client) {
		Alert_Box alertBox = new Alert_Box();
		Create_Account_Model obj = (Create_Account_Model) object;
		Server_To_Client_Message_Model message = obj.getMesasge();
		if (message.getInfoOrError() == 0) {
			// No Error
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					alertBox.informationAlertBox(message.getTitle(), message.getHeader(), message.getContent());
					Create_Account_View_Controller view = client.getCreateAccountScreen();
					view.closeStage();
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
