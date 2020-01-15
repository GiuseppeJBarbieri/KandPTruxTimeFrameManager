package view_add_store;

import java.net.URL;
import java.util.ResourceBundle;

import client_to_server_controller.Client_Controller;
import event_objects.Add_Store_Event_Object;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.User_Account_Information;
import view_dashboard.Show_Dashboard;
import view_main.Main_View_Controller;

public class Add_Store_Controller implements Initializable {

	@FXML
	private Button addStoreBtn, backBtn;
	@FXML
	private TextField storeNameTxt, addressTxt, emailTxt, phoneNumTxt, faxTxt;

	private Main_View_Controller main_View_Controller;
	private AnchorPane main_view_pane;
	private Client_Controller clientController;
	private User_Account_Information user_Account_Model;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		backBtn.setOnAction(e -> goToLastPage());
		addStoreBtn.setOnAction(e -> addProduct());

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
		addStoreBtn.disableProperty().bind(bb2);
	}

	private void goToLastPage() {
		new Show_Dashboard(main_View_Controller, main_view_pane, clientController, user_Account_Model);
	}

	private void addProduct() {
		Add_Store_Event_Object addStoreObj = new Add_Store_Event_Object(storeNameTxt.getText(), addressTxt.getText(), 
				emailTxt.getText(), phoneNumTxt.getText(), faxTxt.getText());

		clientController.setAddProductScreen(this);
		clientController.sendMessage(addStoreObj);
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
