package view_show_all_times;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client_to_server_controller.Client_Controller;
import event_objects.Search_All_Time_Frame_Event_Object;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model_trucking.All_Time_Frame_Model;
import model_trucking.Store_Model;
import model_trucking.TimeFrame_Model;
import models.User_Account_Information;
import view_main.Main_View_Controller;

public class All_Times_Controller implements Initializable {

	@FXML
	private TableView<All_Time_Frame_Model> timeFrameTableView;
	@FXML
	private TableColumn<All_Time_Frame_Model, String> townCol, timeFrameStartCol, timeFrameEndCol, storePhoneNumCol, storeNameCol, orderIDCol, orderDateCol, driverCol, customerPhoneNumberCol, customerNameCol;
	@FXML
	private TableColumn<All_Time_Frame_Model, Double> codCol;
	
	private Main_View_Controller main_View_Controller;
	private Client_Controller clientController;
	private User_Account_Information user_Account_Model;
	private AnchorPane main_view_pane;
	
	private ArrayList<Store_Model> storeList;
	private ArrayList<TimeFrame_Model> timeFrameList;
	private ArrayList<All_Time_Frame_Model> allTimeFrames;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		storeList = new ArrayList<>();
		timeFrameList = new ArrayList<>();
		allTimeFrames = new ArrayList<>();
		setColumnFactory();
	}
	
	public void getAllTimeFrames() {
		Search_All_Time_Frame_Event_Object itemObj = new Search_All_Time_Frame_Event_Object("");
		clientController.setAllTimesController(this);
		clientController.sendMessage(itemObj);
	}
	
	public void setStoreLists(ArrayList<Store_Model> storeList, ArrayList<TimeFrame_Model> timeFrameList) {
		this.storeList = storeList;
		this.timeFrameList = timeFrameList;
		
		for(int i = 0; i < timeFrameList.size(); i++) {
			for(int j = 0; j < storeList.size(); j++) {
				if(timeFrameList.get(i).getStore_ID().equals(storeList.get(j).getStoreID())) {
					allTimeFrames.add(new All_Time_Frame_Model(storeList.get(j).getStoreName(),
							storeList.get(j).getPhoneNumber(), timeFrameList.get(i).getOrderID(),
							timeFrameList.get(i).getName(), timeFrameList.get(i).getTown(), 
							timeFrameList.get(i).getPhoneNumber(), timeFrameList.get(i).getDriver(), 
							timeFrameList.get(i).getTimeFrameStart(), timeFrameList.get(i).getTimeFrameEnd(), 
							timeFrameList.get(i).getOrderDate(), timeFrameList.get(i).getCod()));
				}
			}
		}
		
		ObservableList<All_Time_Frame_Model> tableList = FXCollections.observableArrayList(allTimeFrames);
		timeFrameTableView.setItems(tableList);

		
	}
	
	public void setColumnFactory() {
		townCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, String>("town"));
		timeFrameStartCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, String>("timeframeStart"));
		timeFrameEndCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, String>("timeframeEnd"));
		storePhoneNumCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, String>("storePhoneNum"));
		storeNameCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, String>("storeName"));
		orderIDCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, String>("orderID"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, String>("orderDate"));
		driverCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, String>("driver"));
		customerPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, String>("customerPhoneNum"));
		customerNameCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, String>("customerName"));
		codCol.setCellValueFactory(new PropertyValueFactory<All_Time_Frame_Model, Double>("cod"));
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
	
}
