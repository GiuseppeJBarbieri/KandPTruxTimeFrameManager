package view_edit_store;

import java.net.URL;
import java.util.ResourceBundle;

import client_to_server_controller.Client_Controller;
import event_objects.Edit_Store_Event_Object;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model_trucking.Store_Model;
import models.User_Account_Information;
import view_dashboard.Show_Dashboard;
import view_main.Main_View_Controller;

public class Edit_Store_Controller implements Initializable {

	@FXML
	private TextField storeNameTxt, addressTxt, emailTxt, phoneNumTxt, faxTxt;
	@FXML
	private Button backBtn, editBtn;
	
	private Main_View_Controller main_View_Controller;
	private AnchorPane main_view_pane;
	private Client_Controller clientController;
	private User_Account_Information user_Account_Model;
	private String storeId;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		backBtn.setOnAction(e -> goBack());
		editBtn.setOnAction(e -> submitEditProduct());
		
		BooleanBinding bb2 = new BooleanBinding() {
			{
				super.bind(storeNameTxt.textProperty(), addressTxt.textProperty(),
						emailTxt.textProperty(), phoneNumTxt.textProperty());
			}

			@Override
			protected boolean computeValue() {
				return (storeNameTxt.getText().isEmpty() || addressTxt.getText().isEmpty()
						|| emailTxt.getText().isEmpty() || phoneNumTxt.getText().isEmpty());
			}
		};
		editBtn.disableProperty().bind(bb2);
	}
	
	private void goBack() {
		new Show_Dashboard(main_View_Controller, main_view_pane, clientController, user_Account_Model);
	}
	
	private void submitEditProduct() {
		Edit_Store_Event_Object obj  = new Edit_Store_Event_Object(storeId, storeNameTxt.getText(), addressTxt.getText(), emailTxt.getText(), phoneNumTxt.getText(), faxTxt.getText());
		
		clientController.setEditProductsScreen(this);
		clientController.sendMessage(obj);
	}
	
	public void setTextFields(Store_Model store_Model) {
		this.storeId = store_Model.getStoreID();
		storeNameTxt.setText(store_Model.getStoreName()); 
		addressTxt.setText(store_Model.getAddress());
		emailTxt.setText(store_Model.getEmail());
		phoneNumTxt.setText(store_Model.getPhoneNumber());
		faxTxt.setText(store_Model.getFax());
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
