package hello.domain.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hello.domain.DeviceMessage;
import hello.domain.Group;
import hello.domain.Reservation;
import hello.domain.Room;
import hello.domain.User;
import hello.domain.repository.IGroupRepository;
import hello.domain.repository.IReservationRepository;
import hello.domain.repository.IUserRepository;

@Repository
public class ReservationRepository implements IReservationRepository {

	Map<Room, List<Reservation>> reservations;

	@Autowired
	IUserRepository users;

	@Autowired
	IGroupRepository groups;

	public ReservationRepository() {
		reservations = new HashMap<Room, List<Reservation>>();
	}

	@Override
	public Reservation getReservation(Room r, LocalDateTime time) {
		for (Reservation res : reservations.get(r)) {
			if (time.isAfter(res.timeStart) && time.isBefore(res.timeStop)) {
				return res;
			}
		}
		return null;
	}

	@Override
	public boolean isRoomFree(Room r, LocalDateTime time) {
		return getReservation(r, time) == null;
	}

	@Override
	public LocalDateTime getNextReservation(Room r, LocalDateTime timestamp) {
		LocalDateTime result = null;

		for (Reservation el : reservations.get(r)) {
			if (result == null && el.timeStart.isAfter(timestamp)) {
				result = el.timeStart;
			} else if (el.timeStart.isAfter(timestamp) && el.timeStart.isBefore(result)) {
				result = el.timeStart;
			}
		}
		return result;
	}

	@Override
	public boolean reserveRoom(User u, Room r, LocalDateTime start, LocalDateTime stop) {
		System.out.println("Po tej akcji powinna pójść aktualizacja");
		if (isRoomFree(r, start) && isRoomFree(r, stop)) {
			List res = reservations.get(r);
			if (res == null) {
				res = new ArrayList<Reservation>();
				reservations.put(r, res);
			}
			res.add(new Reservation(u, r, start, stop));
			System.out.println(
					"Użytkownik " + u.getName() + " zarezerwował pokój " + r.name + " od " + start + " do " + stop);
			return true;
		} else {
			Reservation res = getReservation(r, stop);
			if (u == res.owner) {
				updateReservation(res, stop);
				System.out.println("Użyszkodnik " + u.getName() + " potwierdził rezerwację pokoju " + r.name + " od "
						+ res.timeStart + " do " + res.timeStop);
				return true;
			}
			System.out.println("Użytkownik " + u.getName() + " nie może zarezerwować sali " + r.name
					+ " gdyż aktualnie zajął ją " + res.owner.getName());
			return false;
		}
	}

	@Override
	public void updateReservation(Reservation reservation, LocalDateTime stop) {
		reservation.confirmed = true;
		reservation.timeStop = stop;
	}

	@Override
	public Room getFreeRoomInGroup(Group group, LocalDateTime timestamp) {

		Room result = null;
		for (Room el : group.rooms) {
			if (isRoomFree(el, timestamp)) {
				return el;
			}
		}

		for (Group el : group.subGroups) {
			result = getFreeRoomInGroup(el, timestamp);
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	@Override
	public Room getNearestFreeRoom(Room startRoom, LocalDateTime timestamp) {
		if (isRoomFree(startRoom, timestamp)) {
			return startRoom;
		}
		Room result = null;

		Group startGroup = startRoom.group;
		result = getFreeRoomInGroup(startGroup, timestamp);
		if (result != null) {
			return result;
		}
		Group currentMain = startGroup;
		Group currentLeft = startGroup.leftGroup;
		Group currentRight = startGroup.rightGroup;
		while (result == null) {
			if (currentMain.leftGroup == null && currentMain.rightGroup == null && currentMain.parent == null) {
				return null;
			} else if (currentLeft == null && currentRight == null) {
				currentMain = currentMain.parent;
				for (Room el : currentMain.rooms) {
					if (isRoomFree(el, timestamp)) {
						return el;
					}
					currentLeft = currentMain.leftGroup;
					currentRight = currentRight.rightGroup;
				}
			} else {
				if (currentLeft != null) {
					result = getFreeRoomInGroup(currentLeft, timestamp);
					if (result == null) {
						currentLeft = currentLeft.leftGroup;
					}
				}
				if (currentRight != null) {
					result = getFreeRoomInGroup(currentRight, timestamp);
					if (result == null) {
						currentRight = currentRight.rightGroup;
					}
				}

			}
		}
		return result;
	}

	@Override
	public void cancelReservation(User user, Room room, LocalDateTime timestamp) {
		Reservation res = getReservation(room, timestamp);
		if (res != null) {
			if (res.owner == user) {
				reservations.remove(res);
				System.out.println("Po tej akcji powinna pójść alktualizacja na świat");
			}
		}
	}

	@Override
	public DeviceMessage getRoomStatus(Room room, LocalDateTime timestamp) {
		int oldIdStatus = room.idStatus;
		Reservation res = getReservation(room, timestamp);
		DeviceMessage result = new DeviceMessage();

		if (res == null) {
			result.idStatus = 1;
			result.status = "Free";
			result.additionalInfo = "Until " + getNextReservation(room, timestamp);
		} else if (!res.confirmed) {
			result.idStatus = 2;
			result.status = "Reserved";
			result.additionalInfo = "Nearest free: " + getNearestFreeRoom(room, timestamp).toString();
		} else {
			result.idStatus = 3;
			result.status = "Occupied";
			result.additionalInfo = "Nearest free: " + getNearestFreeRoom(room, timestamp).toString();
		}

		if (oldIdStatus != result.idStatus) {
			room.idStatus = result.idStatus;
			switch (oldIdStatus + "_" + result.idStatus) {
			case "1_2":
				result.updateDate = timestamp.plusSeconds(15L);
				break;
			case "2_3":
				result.updateDate = res.timeStop;
				break;
			case "2_1":
			case "3_1":
				result.updateDate = getNextReservation(room, timestamp);
				break;

			}
		}

		return result;
	}

}