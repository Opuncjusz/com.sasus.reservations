package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hello.domain.Device;
import hello.domain.repository.IRoomRepository;
import hello.domain.repository.impl.RoomRepository;
import hello.domain.service.ReservationService;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

@Component
public class Handler extends TextWebSocketHandler {

	Gson gson = new Gson();

	BiMap<WebSocketSession, Device> openDeviceSessions = HashBiMap.create();

	@Autowired
	ReservationService reservationService;

	@Autowired
	RoomRepository roomRepository;

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String messageText = message.getPayload();
		System.out.println("Message: " + messageText);

		try {
			CommonData commonData = gson.fromJson(messageText, CommonData.class);
			CommonData responseData = gson.fromJson(messageText, CommonData.class);

			if (commonData.getState() == 0) {
				System.out.println("NOWE URZĄDZENIE! Przedstawiło się pokojem: { roomId : " + commonData.getRoomId()
						+ ", roomName : " + commonData.getRoomName() + " }");
				Device device = reservationService.handleConnection(commonData);
				openDeviceSessions.put(session, device);
				responseData.setRoomName(roomRepository.getRoomNameForId(commonData.getRoomId()));
				responseData.setState(1);
			} else {
				System.out.println("NOWE ŻĄDANIE! Treść: " + messageText);
			}

			session.sendMessage(new TextMessage(gson.toJson(responseData)));

		} catch (Exception exceptionFirst) {
			System.out.println("BŁĄD! Nie rozpoznano rodzaju komunikatu: " + messageText);
			session.sendMessage(new TextMessage("Jako serwer wypraszam sobie takie wiadomości."));
		}

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("Sock id " + session.getId() + " closed, reason: " + status.getReason());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("connection established");
	}

}
