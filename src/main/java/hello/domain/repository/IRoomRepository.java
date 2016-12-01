package hello.domain.repository;

import hello.domain.Room;

public interface IRoomRepository {

	public Room getNearest(Room r);
	
	
}
