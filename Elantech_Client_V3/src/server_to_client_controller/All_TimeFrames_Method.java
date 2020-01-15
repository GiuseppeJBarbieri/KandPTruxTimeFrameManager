package server_to_client_controller;

import client_to_server_controller.Client_Controller;
import event_objects.All_Time_Frame_Request;
import javafx.application.Platform;
import view_show_all_times.All_Times_Controller;

public class All_TimeFrames_Method {

	public All_TimeFrames_Method(Object object, Client_Controller client) {
	
		All_Time_Frame_Request obj = (All_Time_Frame_Request) object;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				All_Times_Controller view = client.getAllTimesController();
				view.setStoreLists(obj.getStoreList(), obj.getTimeFramesList());
			}
		});
	
	
	}

}
