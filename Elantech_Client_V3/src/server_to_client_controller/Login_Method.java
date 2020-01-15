package server_to_client_controller;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import javafx.stage.Stage;
import models.Login_Message_Model;
import view_main.Show_Main_View;

public class Login_Method {

	public Login_Method(Object object, Client_Controller client) {
		Login_Message_Model message = (Login_Message_Model) object;
		if (message.getMessage().getInfoOrError() == 0) {
			// No Error
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					new Show_Main_View(new Stage(), message.getAccount(), client);
					client.getLoginView().closeStage();
					
				}
			});
			
		
		} else if (message.getMessage().getInfoOrError() == 1) {
			// Error
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert_Box alertBox = new Alert_Box();
					alertBox.errorAlertBox(message.getMessage().getTitle(), message.getMessage().getHeader(), message.getMessage().getContent());
				}

			});

		}

	}

}
