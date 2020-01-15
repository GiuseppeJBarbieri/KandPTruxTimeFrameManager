package view_cods;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client_to_server_controller.Client_Controller;
import event_objects.Search_COD_Event_Object;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model_trucking.COD_Model;
import models.User_Account_Information;
import view_main.Main_View_Controller;

public class COD_Controller implements Initializable  {
	@FXML
	private TableView<COD_Model> timeFrameTableView;
	@FXML
	private TableColumn<COD_Model, String> orderNumberCol, customerNameCol, storeNameCol, driverCol, orderDateCol;
	@FXML
	private TableColumn<COD_Model, Double> codCol;
	@FXML
	private MenuButton productsMenuBtn;
	@FXML
	private MenuItem saveStoreTableMenuItem, addStoreMenuItem, editStoreMenuItem, removeStoreMenuItem;
	
	private Main_View_Controller main_View_Controller;
	private Client_Controller clientController;
	private User_Account_Information user_Account_Model;
	private AnchorPane main_view_pane;
	private ArrayList<COD_Model> codList;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuListeners();
		setTableColumns();
	}
	
	private void menuListeners() {
		saveStoreTableMenuItem.setOnAction(e -> {
			
		});
		addStoreMenuItem.setOnAction(e -> {
			
			
		});
		editStoreMenuItem.setOnAction(e -> {
			
		});
		
		
		removeStoreMenuItem.setOnAction(e -> {
			
		});
		
	}
	
	private void setTableColumns() {
		orderNumberCol.setCellValueFactory(new PropertyValueFactory<COD_Model, String>("orderNumber"));
		customerNameCol.setCellValueFactory(new PropertyValueFactory<COD_Model, String>("customerName"));
		storeNameCol.setCellValueFactory(new PropertyValueFactory<COD_Model, String>("storeName"));
		driverCol.setCellValueFactory(new PropertyValueFactory<COD_Model, String>("driver"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<COD_Model, String>("orderDate"));
		codCol.setCellValueFactory(new PropertyValueFactory<COD_Model, Double>("cod"));
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

	public void getAllCods() {
		Search_COD_Event_Object itemObj = new Search_COD_Event_Object("");
		clientController.setCODController(this);
		clientController.sendMessage(itemObj);
	}
	
	public void setCODList(ArrayList<COD_Model> codList) {
		this.codList = codList;
		ObservableList<COD_Model> tableList = FXCollections.observableArrayList(codList);
		timeFrameTableView.setItems(tableList);
	}
	
}
