package client_to_server_controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import client_to_server_controller_methods.Methods_For_Products;
import javafx.application.Platform;

public class ReadThread implements Runnable {

	private ObjectInputStream input;
	private Client_Controller client;
	private Object object;
	private boolean runThread = true;
	
	public ReadThread(Socket socket, Client_Controller client) {
		this.client = client;

		try {
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (runThread) {
			try {
				object = input.readObject();
				new Methods_For_Products(object, client);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				runThread = false;
			} catch (IOException e) {
				e.printStackTrace();
				runThread = false;
			}
		}
		
		Platform.exit();

	}

	public void closeThread() {
		runThread = false;
	}
}
