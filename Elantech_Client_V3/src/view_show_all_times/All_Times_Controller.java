package view_show_all_times;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client_to_server_controller.Client_Controller;
import event_objects.Search_All_Time_Frame_Event_Object;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model_trucking.All_Time_Frame_Model;
import model_trucking.Store_Model;
import model_trucking.TimeFrame_Model;
import view_main.Main_View_Controller;

public class All_Times_Controller implements Initializable {

	@FXML
	private TableView<All_Time_Frame_Model> timeFrameTableView;
	@FXML
	private TableColumn<All_Time_Frame_Model, String> storeNameCol, customerNameCol,  townCol, orderNumCol, driverCol, timeFrameCol;
	@FXML
	private TableColumn<All_Time_Frame_Model, Date> orderDateCol;
	private Main_View_Controller main_View_Controller;
	private Client_Controller clientController;
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
	

	
	public void setColumnFactory() {
		
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
	
}
