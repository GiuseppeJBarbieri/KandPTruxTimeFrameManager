package server_to_client_controller;

import client_to_server_controller.Client_Controller;
import javafx.application.Platform;

public class Update_Recently_Quoted_Table {

	public Update_Recently_Quoted_Table(Object object, Client_Controller client) {
	//	Recent_Quoted_Request_Object obj = (Recent_Quoted_Request_Object) object;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				//Company_Quotes_Screen_Controller view = client.getCompanyQuotesScreen();
				//view.setRecentlyQuotedList(obj.getRecentlyQuoted());
			}
		});
		
	}

}
