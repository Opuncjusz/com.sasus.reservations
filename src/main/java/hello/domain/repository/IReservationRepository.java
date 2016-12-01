package hello.domain.repository;

import java.time.LocalDateTime;

import hello.domain.Room;

public interface IReservationRepository {

	public boolean isRoomFree(Room r);
	
	public LocalDateTime getNextReservation(Room r);
	
}
