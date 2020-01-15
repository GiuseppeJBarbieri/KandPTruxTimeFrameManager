package server_to_client_controller;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import model_trucking.Add_TimeFrame_Model;
import models.Server_To_Client_Message_Model;
import view_add_timeframe.Add_TimeFrame_Controller;

public class Add_TimeFrame_Method {

	public Add_TimeFrame_Method(Object object, Client_Controller client) {
		Alert_Box alertBox = new Alert_Box();
		Add_TimeFrame_Model obj = (Add_TimeFrame_Model) object;
		Server_To_Client_Message_Model message = obj.getMessage();
		if (message.getInfoOrError() == 0) {
			// No Error
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					alertBox.informationAlertBox(message.getTitle(), message.getHeader(), message.getContent());
					Add_TimeFrame_Controller view = client.getAddInventoryController();
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
