package hello.domain.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import hello.domain.Reservation;
import hello.domain.Room;
import hello.domain.User;
import hello.domain.repository.IGroupRepository;
import hello.domain.repository.IReservationRepository;
import hello.domain.repository.IUserRepository;

public class ReservationRepository implements IReservationRepository {

	Map<Room,List<Reservation>> reservations;
	
	@Autowired
	IUserRepository users;
	
	@Autowired
	IGroupRepository groups;
	
	public ReservationRepository() {
		reservations=new HashMap<Room,List<Reservation>>();
	}

	
	public Reservation getCurrentReservation(Room r){
		for(Reservation res:reservations.get(r)){
			if(LocalDateTime.now().isAfter(res.timeStart) && LocalDateTime.now().isBefore(res.timeStop)){
				return res;
			}
		}
		return null;
	}
	
	@Override
	public boolean isRoomFree(Room r) {
		return getCurrentReservation(r)==null?true:false;
	}

	@Override
	public LocalDateTime getNextReservation(Room r) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void reserveRoom(User u, Room r, LocalDateTime start, LocalDateTime stop) {
		// TODO Auto-generated method stub

	}

}
