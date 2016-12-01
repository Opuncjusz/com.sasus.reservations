package hello.domain.repository;

import hello.domain.User;

import java.time.LocalDateTime;
import java.util.List;

import hello.domain.Reservation;
import hello.domain.Room;

public interface IUserRepository {

	public boolean registerUser(String name , String cardId);
	
	public List<Reservation> getReservations(User u);
	
	public User getUserByName(String name);
	
	public User getUserByIdCard(String name);
}
