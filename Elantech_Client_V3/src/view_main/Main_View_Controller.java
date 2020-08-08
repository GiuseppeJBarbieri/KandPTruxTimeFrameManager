package view_main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client_to_server_controller.Client_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view_dashboard.Show_Dashboard;

public class Main_View_Controller implements Initializable {
	@FXML
	private AnchorPane main_view_pane;
	@FXML
	private HBox dashboardPane, allTimesPane;
	@FXML
	private Label firstNameLbl;

	private Stage stage;
	private ArrayList<HBox> hboxList;
	private Client_Controller clientController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* Add Panes into a list, loop to change color, then change one clicked */
		hboxList = new ArrayList<>();
		hboxList.add(dashboardPane);
		//hboxList.add(allTimesPane);

		dashboardPane.setOnMouseClicked(e -> {
			resetPaneColor(hboxList);
			dashboardPane.setBackground(new Background(
					new BackgroundFill(javafx.scene.paint.Color.web("#85B5CC"), new CornerRadii(3), Insets.EMPTY)));
			new Show_Dashboard(this, main_view_pane, clientController);
		});

//		allTimesPane.setOnMouseClicked(e -> {
//			resetPaneColor(hboxList);
//			allTimesPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#85B5CC"), new CornerRadii(3), Insets.EMPTY)));
//			new Show_All_Times_View(this, main_view_pane, clientController);
//		});

	}

	public void showDashboard() {
		resetPaneColor(hboxList);
		dashboardPane.setBackground(new Background(
				new BackgroundFill(javafx.scene.paint.Color.web("#85B5CC"), new CornerRadii(3), Insets.EMPTY)));
		new Show_Dashboard(this, main_view_pane, clientController);
	}

	private void resetPaneColor(ArrayList<HBox> hboxList) {
		for (HBox h : hboxList) {
			h.setBackground(new Background(
					new BackgroundFill(javafx.scene.paint.Color.web("#3A82A6"), new CornerRadii(3), Insets.EMPTY)));
		}
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setClientController(Client_Controller clientController) {
		this.clientController = clientController;
		clientController.setMainView(this);
	}

	public void closeStage() {
		stage.close();
	}

}
