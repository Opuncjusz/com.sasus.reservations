package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Component
public class Handler extends TextWebSocketHandler {

	

    List<WebSocketSession> openSessions = new ArrayList<WebSocketSession>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String messageText = message.getPayload();
		System.out.println("Message: " + messageText);
    	session.sendMessage(new TextMessage("Mojsak: " + messageText));
    }

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("Sock id " + session.getId() + " closed, reason: " + status.getReason());
	}
    
	

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("connection established");
		openSessions.add(session);
	}
	
	
    
	
}
