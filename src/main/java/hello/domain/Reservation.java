package hello.domain;

import java.time.LocalDateTime;

public class Reservation {

	public User owner;
	public Room room;
	public LocalDateTime timeStart;
	public LocalDateTime timeStop;
	public boolean confirmed;
	
	
	public Reservation(User u,Room r, LocalDateTime start, LocalDateTime stop) {
		this.owner=u;
		this.room=r;
		this.timeStart=start;
		this.timeStop=stop;
		this.confirmed=false;
		
	}

}
