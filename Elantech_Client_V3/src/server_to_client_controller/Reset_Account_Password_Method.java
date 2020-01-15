package server_to_client_controller;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import models.Reset_Account_Password_Model;
import models.Server_To_Client_Message_Model;
import view_login_forgot_password.Forgot_Password_View_Controller;

public class Reset_Account_Password_Method {

	public Reset_Account_Password_Method(Object object, Client_Controller client) {
		Alert_Box alertBox = new Alert_Box();
		Reset_Account_Password_Model obj = (Reset_Account_Password_Model) object;
		Server_To_Client_Message_Model message = obj.getMessage();
		if (message.getInfoOrError() == 0) {
			// No Error
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					alertBox.informationAlertBox(message.getTitle(), message.getHeader(), message.getContent());
					Forgot_Password_View_Controller view = client.getForgotPasswordScreen();
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
