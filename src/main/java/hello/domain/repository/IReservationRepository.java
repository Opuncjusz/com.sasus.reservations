package hello.domain.repository;

import java.time.LocalDateTime;

import hello.domain.Room;
import hello.domain.User;

public interface IReservationRepository {

	public boolean isRoomFree(Room r);
	
	public LocalDateTime getNextReservation(Room r);

	void reserveRoom(User u, Room r, LocalDateTime start, LocalDateTime stop);
	
}
