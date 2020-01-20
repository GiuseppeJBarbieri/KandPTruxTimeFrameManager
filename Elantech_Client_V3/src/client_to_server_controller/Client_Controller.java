package client_to_server_controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.stage.Stage;
import view_add_store.Add_Store_Controller;
import view_add_timeframe.Add_TimeFrame_Controller;
import view_billing.Billing_Controller;
import view_cods.COD_Controller;
import view_dashboard.Dashboard_Controller;
import view_edit_store.Edit_Store_Controller;
import view_edit_timeframe.Edit_TimeFrame_Controller;
import view_login.Login_View_Controller;
import view_login_create_account.Create_Account_View_Controller;
import view_login_forgot_password.Forgot_Password_View_Controller;
import view_main.Main_View_Controller;
import view_server_down_msg.Show_Server_Down_View;
import view_show_all_times.All_Times_Controller;

public class Client_Controller implements Runnable {

	private final String hostname;
	private final int port;

	private ReadThread readThread;
	private WriteThread writeThread;
	private Thread t1;
	private String userName;
	private Socket socket;
	private boolean runThread2 = true;
	private Stage stage;

	private Login_View_Controller viewController;
	private Dashboard_Controller dashboard_Controller;
	private Main_View_Controller mainView;
	private Add_Store_Controller add_Product_Controller;
	private Edit_Store_Controller edit_Product_Controller;
	private All_Times_Controller all_Times_Controller;
	private Add_TimeFrame_Controller add_Inventory_Controller;
	private Edit_TimeFrame_Controller edit_Inventory_Controller;
	private COD_Controller cod_Controller;
	private Billing_Controller billing_Controller;
	
	private Create_Account_View_Controller create_Account_View_Controller;
	private Forgot_Password_View_Controller forgot_Password_View_Controller;
	
	public Client_Controller(String hostname, int port, Login_View_Controller viewController) {
		this.hostname = hostname;
		this.port = port;
		this.viewController = viewController;

	}

	@Override
	public void run() {
		try {
			socket = new Socket(hostname, port);
			writeThread = new WriteThread(socket);
			readThread = new ReadThread(socket, this);
			t1 = new Thread(readThread);
			t1.setDaemon(true);
			t1.start();
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
			showChecker();

		}

	}

	private void showChecker() {
		if (runThread2) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					new Show_Server_Down_View(stage, hostname, port, Client_Controller.this);
				}
			});
		}
	}

	public String getHostName() {
		return hostname;
	}

	public int getPort() {
		return port;
	}

	public void sendMessage(Object obj) {
		writeThread.sendMessage(obj);

	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	String getUserName() {
		return this.userName;
	}

	// Views For Client

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Login_View_Controller getLoginView() {
		return viewController;
	}

	public Dashboard_Controller getDashboardController() {
		return dashboard_Controller;
	}

	public void setDashboardController(Dashboard_Controller dashboard_Controller2) {
		this.dashboard_Controller = dashboard_Controller2;
	}

	public void setMainView(Main_View_Controller mainView) {
		this.mainView = mainView;
	}

	public Main_View_Controller getMainView() {
		return mainView;
	}

	public ReadThread getReadThread() {
		return readThread;
	}

	public void setAddProductScreen(Add_Store_Controller add_Product_Controller) {
		this.add_Product_Controller = add_Product_Controller;
	}

	public Add_Store_Controller getAddProductController() {
		return add_Product_Controller;
	}

	public void setEditProductsScreen(Edit_Store_Controller edit_Product_Controller) {
		this.edit_Product_Controller = edit_Product_Controller;
	}

	public Edit_Store_Controller getEditProductController() {
		return edit_Product_Controller;
	}


	public void setAddInventoryController(Add_TimeFrame_Controller add_Inventory_Controller) {
		this.add_Inventory_Controller = add_Inventory_Controller;
	}

	public Add_TimeFrame_Controller getAddInventoryController() {
		return add_Inventory_Controller;
	}

	public void setEditInventoryScreen(Edit_TimeFrame_Controller edit_Inventory_Controller) {
		this.edit_Inventory_Controller = edit_Inventory_Controller;
	}

	public Edit_TimeFrame_Controller getEditInventoryController() {
		return edit_Inventory_Controller;
	}

	public void setCreateAccountScreen(Create_Account_View_Controller create_Account_View_Controller) {
		this.create_Account_View_Controller = create_Account_View_Controller;
	}

	public Create_Account_View_Controller getCreateAccountScreen() {
		return create_Account_View_Controller;
	}

	public void setForgotPasswordScreen(Forgot_Password_View_Controller forgot_Password_View_Controller) {
		this.forgot_Password_View_Controller = forgot_Password_View_Controller;
	}

	public Forgot_Password_View_Controller getForgotPasswordScreen() {
		return forgot_Password_View_Controller;
	}

	public void setAllTimesController(All_Times_Controller all_Times_Controller) {
		this.all_Times_Controller = all_Times_Controller;
	}

	public All_Times_Controller getAllTimesController() {
		return all_Times_Controller;
	}

	public void setCODController(COD_Controller cod_Controller) {
		this.cod_Controller = cod_Controller;
		
	}
	
	public COD_Controller getCodController() {
		return cod_Controller;
	}

	public void setBillingController(Billing_Controller billing_Controller) {
		this.billing_Controller = billing_Controller;
	}
	
	public Billing_Controller getBillingController() {
		return billing_Controller;
	}
}
