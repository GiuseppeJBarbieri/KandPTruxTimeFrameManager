package server_to_client_controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.EventObject;

import models.Server_Message_Model;

public class Receive_Message_Method {

	public Receive_Message_Method(EventObject ev, ObjectInputStream fromServer) {
		System.out.println("HERE CLIENT");
		try {
			Server_Message_Model message = (Server_Message_Model) fromServer.readObject();
			System.out.println(message.toString());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
