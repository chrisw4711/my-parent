package org.trivadis.websocket.socket;

import java.util.Set;

import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

/**
 * This class represents a web socket; it passes messages to all connected users
 * 
 * @author Andy Moncsek, Trivadis CH
 * 
 */
public class ChatWebSocket implements OnTextMessage {

	private Connection connection;

	private Set<ChatWebSocket> users;

	public ChatWebSocket() {

	}

	public ChatWebSocket(final Set<ChatWebSocket> users) {
		this.users = users;
	}

	@Override
	public void onMessage(final String data) {
		for (ChatWebSocket user : users) {
			try {
				user.connection.sendMessage(System.currentTimeMillis() + ": " + data);
			} catch (Exception e) {
			}
		}

	}

	@Override
	public void onOpen(final Connection connection) {
		this.connection = connection;
		users.add(this);

	}

	@Override
	public void onClose(final int closeCode, final String message) {
		users.remove(this);

	}

}
