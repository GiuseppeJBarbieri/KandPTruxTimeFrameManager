package client_to_server_controller_methods;

import client_to_server_controller.Client_Controller;
import event_objects.All_Time_Frame_Request;
import event_objects.Billing_Store_Search_Result_Model;
import event_objects.COD_Search_Result_Model;
import event_objects.Logout_All_Users_Event_Object;
import event_objects.One_Email_Sent_Model;
import model_trucking.Add_Store_Request_Model;
import model_trucking.Add_TimeFrame_Model;
import model_trucking.Edit_Store_Model;
import model_trucking.Edit_TimeFrame_Model;
import model_trucking.Email_Sent_Model;
import model_trucking.Remove_Store_Model;
import model_trucking.Remove_TimeFrame_Model;
import model_trucking.Search_TimeFrame_Model;
import model_trucking.Store_Search_Result_Model;
import models.Create_Account_Model;
import models.Login_Message_Model;
import models.Logout_Model;
import models.Reset_Account_Password_Model;
import server_to_client_controller.Add_Store_Method;
import server_to_client_controller.Add_TimeFrame_Method;
import server_to_client_controller.All_TimeFrames_Method;
import server_to_client_controller.Create_Account_Method;
import server_to_client_controller.Edit_Store_Method;
import server_to_client_controller.Edit_TimeFrame_Method;
import server_to_client_controller.Emails_Sent_Methods;
import server_to_client_controller.Login_Method;
import server_to_client_controller.Logout_All_Users_Method;
import server_to_client_controller.One_Email_Sent_Method;
import server_to_client_controller.Remove_Store_Method;
import server_to_client_controller.Remove_TimeFrame_Method;
import server_to_client_controller.Request_Logout_Method;
import server_to_client_controller.Reset_Account_Password_Method;
import server_to_client_controller.Search_COD_Request;
import server_to_client_controller.Search_Store_Method;
import server_to_client_controller.Search_Stores_Billing_Method;
import server_to_client_controller.Search_TimeFrame_Method;


public class Methods_For_Products {

	public Methods_For_Products(Object object, Client_Controller client) {
		if(object.getClass() == Login_Message_Model.class) {
			new Login_Method(object, client);
		} else if(object.getClass() == Store_Search_Result_Model.class) {
			new Search_Store_Method(object, client);
		} else if(object.getClass() == Add_Store_Request_Model.class) {
			new Add_Store_Method(object, client);
		} else if(object.getClass() == Edit_Store_Model.class) {
			new Edit_Store_Method(object, client);
		} else if(object.getClass() == Remove_Store_Model.class) {
		 	new Remove_Store_Method(object, client);
		} else if(object.getClass() == Search_TimeFrame_Model.class) {
			System.out.println("HERE");
			new Search_TimeFrame_Method(object, client);
		} else if(object.getClass() == Add_TimeFrame_Model.class) {
			new Add_TimeFrame_Method(object, client);
		} else if(object.getClass() == Edit_TimeFrame_Model.class) {
			new Edit_TimeFrame_Method(object, client);
		} else if(object.getClass() == Remove_TimeFrame_Model.class) {
			new Remove_TimeFrame_Method(object, client);
		} else if(object.getClass() == Create_Account_Model.class) {
			new Create_Account_Method(object, client);
		} else if(object.getClass() == Reset_Account_Password_Model.class) {
			new Reset_Account_Password_Method(object, client);
		} else if(object.getClass() == Logout_All_Users_Event_Object.class) {
			new Logout_All_Users_Method(object, client);
		} else if(object.getClass() == Logout_Model.class) {
			new Request_Logout_Method(object, client);
		} else if(object.getClass() == All_Time_Frame_Request.class) {
			new All_TimeFrames_Method(object, client);
		} else if(object.getClass() == COD_Search_Result_Model.class) {
			new Search_COD_Request(object, client);
		} else if(object.getClass() == Email_Sent_Model.class) {
			new Emails_Sent_Methods(object, client);
		} else if(object.getClass() == Billing_Store_Search_Result_Model.class) {
			new Search_Stores_Billing_Method(object, client);
		} else if(object.getClass() == One_Email_Sent_Model.class) {
			new One_Email_Sent_Method(object, client);
		}
		
	}

}
