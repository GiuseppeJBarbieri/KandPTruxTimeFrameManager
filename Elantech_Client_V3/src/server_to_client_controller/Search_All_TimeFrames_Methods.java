package server_to_client_controller;

import client_to_server_controller.Client_Controller;
import event_objects.Search_All_TimeFrame_Model;
import javafx.application.Platform;
import view_dashboard.Dashboard_Controller;

public class Search_All_TimeFrames_Methods {

	public Search_All_TimeFrames_Methods(Object object, Client_Controller client) {
		Search_All_TimeFrame_Model obj = (Search_All_TimeFrame_Model) object;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Dashboard_Controller view = client.getDashboardController();
				view.setAllTimeFrames(obj.getTimeFramesList());
			}
		});
		
	}

}
