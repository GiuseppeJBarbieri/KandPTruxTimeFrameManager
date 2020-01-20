package view_login;

import java.net.URL;
import java.util.ResourceBundle;

import client_to_server_controller.Client_Controller;
import event_objects.Login_Event_Object;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Login_View_Controller implements Initializable {
	@FXML
	private Button closeBtn, createAccountBtn, loginBtn;
	@FXML
	private Hyperlink forgotPasswordLink;
	@FXML
	private PasswordField passwordTxt;
	@FXML
	private TextField usernameTxt;

	private Stage stage;
	private Client_Controller client;
	private Thread t1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginBtn.setOnAction(e -> login());
		closeBtn.setOnAction(e -> onStageClose());
		//createAccountBtn.setOnAction(e -> new Show_Create_Account_View(this, client));

		usernameTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					passwordTxt.requestFocus();
				}
			}
		});

		passwordTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					login();
				}
			}
		});
		//forgotPasswordLink.setOnAction(e -> new Show_Forgot_Password_View(this, client));
		startServer();
	}

	public void startServer() {
		client = new Client_Controller("68.129.33.77", 8001, this);
		t1 = new Thread(client);
		t1.setDaemon(true);
		t1.start();
	}

	private void login() {
		Login_Event_Object loginObj = new Login_Event_Object(this, usernameTxt.getText(), Integer.toString(passwordTxt.getText().hashCode()));
		//System.out.println(usernameTxt.getText());
		//System.out.println(passwordTxt.getText().hashCode());
		client.sendMessage(loginObj);
		client.setUserName(usernameTxt.getText());
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void closeStage() {
		stage.close();
	}

	private void onStageClose() {
		Platform.exit();
	}

	public Stage getStage() {
		return stage;
	}

}
