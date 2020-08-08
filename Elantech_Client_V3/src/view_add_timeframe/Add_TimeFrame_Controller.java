package view_add_timeframe;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import event_objects.Add_TimeFrame_Event_Object;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model_trucking.Store_Model;
import view_dashboard.Show_Dashboard;
import view_main.Main_View_Controller;

public class Add_TimeFrame_Controller implements Initializable {

	@FXML
	private TextField timeframeTxt, orderNumberTxt, storeNameTxt, customerNameTxt, townTxt;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button backBtn, finishBtn;
	@FXML
	private ChoiceBox<String> driverChoiceBox;

	private Main_View_Controller main_View_Controller;
	private AnchorPane main_view_pane;
	private Client_Controller clientController;

	private String choiceBoxList[] = { "Julio", "Jose", "Jose Jr", "Marcos", "Frank", "Rego", "Mike S", "Alfredo",
			"Santos" };
	private int storeID;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		driverChoiceBox.setItems(FXCollections.observableArrayList(choiceBoxList));
		backBtn.setOnAction(e -> goBack());
		finishBtn.setOnAction(e -> finishAdding());
		datePicker.setValue(LocalDate.now());
	}

	private void finishAdding() {
		if (datePicker.getValue() == null || driverChoiceBox.getSelectionModel().getSelectedItem().equals("")
				|| customerNameTxt.getText().equals("") || townTxt.getText().equals("")
				|| timeframeTxt.getText().equals("")) {
			Alert_Box alertBox = new Alert_Box();
			alertBox.errorAlertBox("Error", "Please enter all required information!", "Click ok to continue...");
		} else {
			Add_TimeFrame_Event_Object obj = new Add_TimeFrame_Event_Object(storeID, customerNameTxt.getText(),
					Date.valueOf(datePicker.getValue()), townTxt.getText(), driverChoiceBox.getSelectionModel().getSelectedItem(),
					orderNumberTxt.getText(), timeframeTxt.getText());
			clientController.setAddInventoryController(this);
			clientController.sendMessage(obj);

		}

	}

	private void goBack() {
		new Show_Dashboard(main_View_Controller, main_view_pane, clientController);
		clientController.getDashboardController().searchStore("");
	}

	public void setTextFieldInformaition(Store_Model store_Model) {
		this.storeID = store_Model.getStoreID();
		storeNameTxt.setText(store_Model.getStoreName());
	}

	public void setMainViewController(Main_View_Controller main_View_Controller) {
		this.main_View_Controller = main_View_Controller;
	}

	public void setClientController(Client_Controller client) {
		this.clientController = client;
	}

	public void setMainViewPane(AnchorPane main_view_pane) {
		this.main_view_pane = main_view_pane;
	}

	public void closeStage() {
		new Show_Dashboard(main_View_Controller, main_view_pane, clientController);
		clientController.getDashboardController().searchStore("");
	}

}
