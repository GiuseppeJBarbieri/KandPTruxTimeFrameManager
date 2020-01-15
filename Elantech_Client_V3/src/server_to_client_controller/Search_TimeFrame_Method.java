package server_to_client_controller;

import java.util.ArrayList;

import client_to_server_controller.Client_Controller;
import javafx.application.Platform;
import model_trucking.Search_TimeFrame_Model;
import model_trucking.TimeFrame_Model;
import view_dashboard.Dashboard_Controller;

public class Search_TimeFrame_Method {

	private ArrayList<TimeFrame_Model> timeFrameList;

	public Search_TimeFrame_Method(Object object, Client_Controller client) {
		Search_TimeFrame_Model obj = (Search_TimeFrame_Model) object;
		timeFrameList = obj.getTimeframeResultsList();
		System.out.println(timeFrameList.size());
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Dashboard_Controller view = client.getDashboardController();
				view.setTimeFrameList(timeFrameList);
			}
		});
		
	}

}
