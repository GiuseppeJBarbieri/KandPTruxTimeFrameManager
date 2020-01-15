package view_login_forgot_password;

import java.net.URL;
import java.util.ResourceBundle;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import event_objects.Reset_Account_Password_Event_Object;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Forgot_Password_View_Controller implements Initializable {

	@FXML
	private TextField usernameTxt, emailTxt;
	@FXML
	private PasswordField passwordTxt, password2Txt;
	@FXML
	private Button resetPasswordBtn;

	private Stage stage;
	private Client_Controller clientToServerController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		resetPasswordBtn.setOnAction(e -> resetPassword());

		BooleanBinding bb1 = new BooleanBinding() {
			{
				super.bind(usernameTxt.textProperty(), emailTxt.textProperty(), passwordTxt.textProperty(),
						password2Txt.textProperty());
			}

			@Override
			protected boolean computeValue() {
				return (usernameTxt.getText().isEmpty() || emailTxt.getText().isEmpty()
						|| passwordTxt.getText().isEmpty() || password2Txt.getText().isEmpty());
			}
		};
		resetPasswordBtn.disableProperty().bind(bb1);
	}

	private void resetPassword() {
		try {
			if (passwordTxt.getText().equals(password2Txt.getText())) {
				Reset_Account_Password_Event_Object obj = new Reset_Account_Password_Event_Object(this,
						usernameTxt.getText(), emailTxt.getText(), Integer.toString(passwordTxt.getText().hashCode()));
				clientToServerController.setForgotPasswordScreen(this);
				clientToServerController.sendMessage(obj);
			} else {
				Alert_Box alertBox = new Alert_Box();
				alertBox.errorAlertBox("Error", "Passwords dont match!", "Please click on to continue...");

			}
		} catch (Exception e) {
			//log.debug(e);
		}

	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setClientServerCont(Client_Controller clientToServerController) {
		this.clientToServerController = clientToServerController;
	}

	public void closeStage() {
		stage.close();
	}

}
