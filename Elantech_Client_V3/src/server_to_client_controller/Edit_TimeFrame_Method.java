package server_to_client_controller;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import model_trucking.Edit_TimeFrame_Model;
import models.Server_To_Client_Message_Model;
import view_edit_timeframe.Edit_TimeFrame_Controller;

public class Edit_TimeFrame_Method {

	public Edit_TimeFrame_Method(Object object, Client_Controller client) {
		Alert_Box alertBox = new Alert_Box();
		Edit_TimeFrame_Model obj = (Edit_TimeFrame_Model) object;
		Server_To_Client_Message_Model message = obj.getMessage();
		if (message.getInfoOrError() == 0) {
			// No Error
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					alertBox.informationAlertBox(message.getTitle(), message.getHeader(), message.getContent());
					Edit_TimeFrame_Controller view = client.getEditInventoryController();
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
