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
import models.User_Account_Information;
import view_billing.Show_Billing_View;
import view_dashboard.Show_Dashboard;
import view_show_all_times.Show_All_Times_View;

public class Main_View_Controller implements Initializable {
	@FXML
	private AnchorPane main_view_pane;
	@FXML
	private HBox dashboardPane, allTimesPane, billingPane;
	@FXML
	private Label firstNameLbl;
	
	private Stage stage;
	private ArrayList<HBox> hboxList;
	private User_Account_Information user_Account_Model;
	private Client_Controller clientController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* Add Panes into a list, loop to change color, then change one clicked */
		hboxList = new ArrayList<>();
		hboxList.add(dashboardPane);
		hboxList.add(allTimesPane);

		dashboardPane.setOnMouseClicked(e -> {
			resetPaneColor(hboxList);
			dashboardPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#85B5CC"), new CornerRadii(3), Insets.EMPTY)));
			new Show_Dashboard(this, main_view_pane, clientController, user_Account_Model);
		});
		
		allTimesPane.setOnMouseClicked(e -> {
			resetPaneColor(hboxList);
			allTimesPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#85B5CC"), new CornerRadii(3), Insets.EMPTY)));
			new Show_All_Times_View(this, main_view_pane, clientController, user_Account_Model);
		});
		
		billingPane.setOnMouseClicked(e -> {
			resetPaneColor(hboxList);
			billingPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#85B5CC"), new CornerRadii(3), Insets.EMPTY)));
			new Show_Billing_View(this, main_view_pane, clientController, user_Account_Model);
		});
	}
	
	public void showDashboard() {
		//dashboardPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#3A82A6"), new CornerRadii(3), Insets.EMPTY)));
		new Show_Dashboard(this, main_view_pane, clientController, user_Account_Model);
	}
	private void resetPaneColor(ArrayList<HBox> hboxList) {
		for(HBox h : hboxList) {
			h.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#3A82A6"), new CornerRadii(3), Insets.EMPTY)));
		}
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setUserAccount(User_Account_Information user_Account_Model) {
		this.user_Account_Model = user_Account_Model;
	}

	public void setClientController(Client_Controller clientController) {
		this.clientController = clientController;
		clientController.setMainView(this);
		firstNameLbl.setText(user_Account_Model.getFirst_name());
	}
	
	public void closeStage() {
		stage.close();
	}

}
