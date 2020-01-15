package app;


import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import view_login.Show_Login_View;

public class App_Client extends Application {

	
	public static void main(String[] args) {
		URL mySource = App_Client.class.getProtectionDomain().getCodeSource().getLocation();
		File rootFolder = new File(mySource.getPath());
		System.setProperty("app.root", rootFolder.getAbsolutePath());
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		new Show_Login_View(stage);
	}

}
