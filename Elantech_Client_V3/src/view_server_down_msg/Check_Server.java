package view_server_down_msg;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Check_Server implements Runnable {

	private Server_Down_Controller serverView;
	private String hostname;
	private int port;
	
	private boolean run = true;
	public Check_Server(Server_Down_Controller serverView, String hostname, int port) {
		this.serverView = serverView;
		this.hostname = hostname;
		this.port = port;
	}

	@SuppressWarnings("resource")
	@Override
	public void run() {
		
		while (run) {
			try {
				@SuppressWarnings("unused")
				Socket socket = new Socket(hostname, port);
				run = false;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		serverView.closeStage();
	}
	
	public void closeThread() {
		run = false;
	}
}
