package view_server_down_msg;

import java.net.URL;
import java.util.ResourceBundle;

import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Server_Down_Controller implements Initializable {

	@FXML
	private Button closeAppBtn;
	@FXML
	private Stage stage, prevStage;
	private String hostname;
	private int port;
	private Check_Server checkServer;
	@SuppressWarnings("unused")
	private Client_Controller clientController;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		closeAppBtn.setOnAction(e -> closeApp());
		
	}

	public void shutAppDown() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Platform.exit();
	}
	public void runServer() {
		checkServer = new Check_Server(this, hostname, port);
		Thread t1 = new Thread(checkServer);
		t1.setDaemon(true);
		t1.start();
	}

	private void closeApp() {
		Platform.exit();
	}

	public void closeStage() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				checkServer.closeThread();
			}
		});

	}

	public void setStage(Stage stage, Stage prevStage) {
		this.stage = stage;
		this.prevStage = prevStage;
	}

	public void setSocket(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void setClientController(Client_Controller client_Controller) {
		this.clientController = client_Controller;
	}

}
