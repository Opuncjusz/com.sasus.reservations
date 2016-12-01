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

	
	public Reservation getReservation(Room r,LocalDateTime time ){
		for(Reservation res:reservations.get(r)){
			if(time.isAfter(res.timeStart) && time.isBefore(res.timeStop)){
				return res;
			}
		}
		return null;
	}
	
	@Override
	public boolean isRoomFree(Room r, LocalDateTime time) {
		return getReservation(r,time) == null;
	}

	@Override
	public LocalDateTime getNextReservation(Room r) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean reserveRoom(User u, Room r, LocalDateTime start, LocalDateTime stop) {
		if(isRoomFree(r,start) && isRoomFree(r,stop)){
			List res=reservations.get(r);
			if(res==null){
				res=new ArrayList<Reservation>();
				reservations.put(r,res);
			}
			res.add(new Reservation(u,r,start,stop));
			return true;
		}else{
			return false;
		}

	}




}
