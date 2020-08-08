package app;


import java.io.File;
import java.net.URL;

import client_to_server_controller.Client_Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import view_main.Show_Main_View;


public class App_Client extends Application {

	private Client_Controller clientController;
	private Thread t1;
	
	public static void main(String[] args) {
		URL mySource = App_Client.class.getProtectionDomain().getCodeSource().getLocation();
		File rootFolder = new File(mySource.getPath());
		System.setProperty("app.root", rootFolder.getAbsolutePath());
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		startServer();
		new Show_Main_View(stage, clientController);
	}

	public void startServer() {
		clientController = new Client_Controller("67.86.195.172", 8000);
		t1 = new Thread(clientController);
		t1.setDaemon(true);
		t1.start();
	}
}
