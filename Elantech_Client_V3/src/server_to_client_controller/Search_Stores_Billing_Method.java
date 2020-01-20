package server_to_client_controller;

import client_to_server_controller.Client_Controller;
import event_objects.Billing_Store_Search_Result_Model;
import javafx.application.Platform;
import view_billing.Billing_Controller;

public class Search_Stores_Billing_Method {

	public Search_Stores_Billing_Method(Object object, Client_Controller client) {
		Billing_Store_Search_Result_Model obj = (Billing_Store_Search_Result_Model) object;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Billing_Controller view = client.getBillingController();
				view.setStoreTable(obj.getStoreResultsList());
			}
		});
	
	}

}
