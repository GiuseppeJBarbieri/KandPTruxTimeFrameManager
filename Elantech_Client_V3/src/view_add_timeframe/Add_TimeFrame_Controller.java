	package view_add_timeframe;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import alerts.Alert_Box;
import client_to_server_controller.Client_Controller;
import event_objects.Add_TimeFrame_Event_Object;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model_trucking.Store_Model;
import models.User_Account_Information;
import view_dashboard.Show_Dashboard;
import view_main.Main_View_Controller;

public class Add_TimeFrame_Controller implements Initializable {

	@FXML
	private TextField timeframeStartTxt, timeframeEndTxt, storePhoneNumTxt, storeNameTxt, 
	storeEmailTxt, storeAddTxt, customerPhoneNumberTxt, customerNameTxt, townTxt, codTxt;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button backBtn, finishBtn;
	@FXML
	private ChoiceBox<String> driverChoiceBox;

	private Main_View_Controller main_View_Controller;
	private AnchorPane main_view_pane;
	private Client_Controller clientController;
	private User_Account_Information user_Account_Model;

	private String choiceBoxList[] = { "Julio", "Jose", "Jose Jr", "Marcos", "Frank", "Rego", "Mike S", "Alfredo", "Santos" };
	private String storeID;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		driverChoiceBox.setItems(FXCollections.observableArrayList(choiceBoxList));
		backBtn.setOnAction(e -> goBack());
		finishBtn.setOnAction(e -> finishAdding());
		datePicker.setValue(LocalDate.now());
		
		codTxt.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					codTxt.setText(newValue.replaceAll("[^\\d\\.]", ""));
				}
			}
		});

	}

	private void finishAdding() {
		LocalDate localDate = datePicker.getValue();
		
		if (datePicker.getValue() == null || driverChoiceBox.getSelectionModel().getSelectedItem().equals("") || customerNameTxt.getText().equals("") || townTxt.getText().equals("") 
				|| customerPhoneNumberTxt.getText().equals("") || timeframeStartTxt.getText().equals("") || timeframeEndTxt.getText().equals("") ) {
			Alert_Box alertBox = new Alert_Box();
			alertBox.errorAlertBox("Error", "Please enter all required information!", "Click ok to continue...");
		} else {
			if(codTxt.getText().equals("")) {
				Add_TimeFrame_Event_Object obj = new Add_TimeFrame_Event_Object(storeID, customerNameTxt.getText(), localDate.toString(), townTxt.getText(), driverChoiceBox.getSelectionModel().getSelectedItem(), customerPhoneNumberTxt.getText(),
						timeframeStartTxt.getText(), timeframeEndTxt.getText(), 0.00);	
				clientController.setAddInventoryController(this);
				clientController.sendMessage(obj);
			} else {
				Add_TimeFrame_Event_Object obj = new Add_TimeFrame_Event_Object(storeID, customerNameTxt.getText(), localDate.toString(), townTxt.getText(), driverChoiceBox.getSelectionModel().getSelectedItem(), customerPhoneNumberTxt.getText(),
						timeframeStartTxt.getText(), timeframeEndTxt.getText(), Double.parseDouble(codTxt.getText()));
				clientController.setAddInventoryController(this);
				clientController.sendMessage(obj);
			}
				
			
			
		}
		
	}

	private void goBack() {
		new Show_Dashboard(main_View_Controller, main_view_pane, clientController, user_Account_Model);
		clientController.getDashboardController().searchStore("");
	}

	public void setTextFieldInformaition(Store_Model store_Model) {
		this.storeID = store_Model.getStoreID();
		storeNameTxt.setText(store_Model.getStoreName());
		storeAddTxt.setText(store_Model.getAddress());
		storeEmailTxt.setText(store_Model.getEmail());
		storePhoneNumTxt.setText(store_Model.getPhoneNumber());
	}

	public void setMainViewController(Main_View_Controller main_View_Controller) {
		this.main_View_Controller = main_View_Controller;
	}

	public void setClientController(Client_Controller client) {
		this.clientController = client;
	}

	public void setUserAccountModel(User_Account_Information user_Account_Model) {
		this.user_Account_Model = user_Account_Model;
	}

	public void setMainViewPane(AnchorPane main_view_pane) {
		this.main_view_pane = main_view_pane;
	}

	public void closeStage() {
		new Show_Dashboard(main_View_Controller, main_view_pane, clientController, user_Account_Model);
		clientController.getDashboardController().searchStore("");
	}

}
