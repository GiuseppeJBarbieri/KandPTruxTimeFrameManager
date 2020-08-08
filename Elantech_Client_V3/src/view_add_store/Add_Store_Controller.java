package view_add_store;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client_to_server_controller.Client_Controller;
import event_objects.Add_Store_Event_Object;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view_dashboard.Show_Dashboard;
import view_main.Main_View_Controller;

public class Add_Store_Controller implements Initializable {

	@FXML
	private Button addStoreBtn, backBtn, addEmailBtn, removeEmailBtn;
	@FXML
	private TextField storeNameTxt, emailTxt;
	@FXML
	private TableView<String> emailTbl;
	@FXML
	private TableColumn<String, String> emailAddressCol;
	
	private ArrayList<String> emailList;
	private Main_View_Controller main_View_Controller;
	private AnchorPane main_view_pane;
	private Client_Controller clientController;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setProductTableColumnFactory();
		emailList = new ArrayList<>();
		backBtn.setOnAction(e -> goToLastPage());
		addStoreBtn.setOnAction(e -> addProduct());

		BooleanBinding bb2 = new BooleanBinding() {
			{
				super.bind(storeNameTxt.textProperty());
			}

			@Override
			protected boolean computeValue() {
				return (storeNameTxt.getText().isEmpty());
			}
		};
		addStoreBtn.disableProperty().bind(bb2);
		
		addEmailBtn.setOnAction(e -> {
			emailList.add(emailTxt.getText());
			ObservableList<String> tableList = FXCollections.observableArrayList(emailList);
			emailTbl.setItems(tableList);
			emailTxt.clear();
		});
		
		removeEmailBtn.setOnAction(e -> {
			emailList.remove(emailTbl.getSelectionModel().getSelectedItem());
			ObservableList<String> tableList = FXCollections.observableArrayList(emailList);
			emailTbl.setItems(tableList);
		});
		
	}
	
	/* TABLE VIEW METHODS */
	private void setProductTableColumnFactory() {
		emailAddressCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
	}

	private void goToLastPage() {
		new Show_Dashboard(main_View_Controller, main_view_pane, clientController);
	}

	private void addProduct() {
		Add_Store_Event_Object addStoreObj = new Add_Store_Event_Object(storeNameTxt.getText(), emailList);
		clientController.setAddProductScreen(this);
		clientController.sendMessage(addStoreObj);
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
	}

}
