package client_to_server_controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class WriteThread {
	
	private ObjectOutputStream writer;
	private Socket socket;
	
	public WriteThread(Socket socket) {
		this.socket = socket;

		try {
			writer = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	
	public void sendMessage(Object obj) {
		try {
			writer.writeObject(obj);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
