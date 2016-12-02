package hello.domain.repository.impl;

import java.io.ObjectOutputStream.PutField;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import hello.domain.Room;
import hello.domain.repository.IRoomRepository;

@Repository
public class RoomRepository implements IRoomRepository {

	private Map<Long, String> rooms = new HashMap<Long, String>();

	public RoomRepository() {
		rooms.put((long) 1, "CYMANON");
		rooms.put((long) 2, "MELASA");
		rooms.put((long) 3, "KORDEMON");
		rooms.put((long) 4, "BRAZYLIA");
	}

	public String getRoomNameForId(long idRoom) {
		return rooms.get(idRoom);
	}

}
