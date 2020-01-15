package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alert_For_Inactivity {

	public Alert_For_Inactivity() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Session Timed Out!");
		alert.setHeaderText("User has been inactive for 10+ minutes!");
		alert.setContentText("Click ok to relogin.");
		alert.showAndWait();
	}
}
