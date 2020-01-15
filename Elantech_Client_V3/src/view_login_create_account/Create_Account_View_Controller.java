package view_login_create_account;

import java.net.URL;
import java.util.ResourceBundle;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import event_objects.Create_Account_Event_Object;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Create_Account_View_Controller implements Initializable {
	@FXML
	private Button createAccountBtn;
	@FXML
	private TextField firstNameTxt, lastNameTxt, emailTxt, usernameTxt, passwordTxt;

	private Stage stage;
	private Client_Controller clientController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createAccountBtn.setOnAction(e -> createAccount());
	}

	private void createAccount() {
		if (firstNameTxt.getText().equals("") || lastNameTxt.getText().equals("") || emailTxt.getText().equals("")
				|| usernameTxt.getText().equals("") || passwordTxt.getText().equals("")) {
			Alert_Box alertBox = new Alert_Box();
			alertBox.errorAlertBox("Error", "One of the fields are empty", "Please click on to continue...");
		} else {
			System.out.println("HERE");
			Create_Account_Event_Object account = new Create_Account_Event_Object(this, usernameTxt.getText(),
					Integer.toString(passwordTxt.getText().hashCode()), emailTxt.getText(), firstNameTxt.getText(),
					lastNameTxt.getText(), "3");
			clientController.setCreateAccountScreen(this);
			clientController.sendMessage(account);

		}

	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void closeStage() {
		stage.close();
	}

	public void setClientServerCont(Client_Controller clientController) {
		this.clientController = clientController;
	}
}
