package hello.domain.repository;

import java.time.LocalDateTime;

import hello.domain.DeviceMessage;
import hello.domain.Group;
import hello.domain.Reservation;
import hello.domain.Room;
import hello.domain.User;

public interface IReservationRepository {
	
	
	public boolean reserveRoom(User u, Room r, LocalDateTime start, LocalDateTime stop);
	
	public Reservation getReservation(Room r,LocalDateTime time );

	public boolean isRoomFree(Room r, LocalDateTime time);

	public void updateReservation(Reservation r,LocalDateTime t);

	public DeviceMessage getRoomStatus(Room r, LocalDateTime timeStamp);

	public Room getFreeRoomInGroup(Group group, LocalDateTime timestamp);

	public Room getNearestFreeRoom(Room startRoom, LocalDateTime timestamp);

	public LocalDateTime getNextReservation(Room r, LocalDateTime timestamp);

	public void cancelReservation(User user, Room room, LocalDateTime timestamp);
	
}
