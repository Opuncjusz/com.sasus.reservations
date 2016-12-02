package hello.domain.repository.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import hello.domain.Reservation;
import hello.domain.Room;
import hello.domain.User;
import hello.domain.repository.IUserRepository;

@Repository
public class UserRepository implements IUserRepository {

	Map<String,User> userMapByIdCard;
	Map<String,User> userMapByName;
	
	public UserRepository() {
		userMapByIdCard=new HashMap<String,User>();
		userMapByName=new HashMap<String,User>();
		
		registerUser("Kotweb","123");
		registerUser("Mociak","Szatan666");
		
	}

	@Override
	public boolean registerUser(String name, String cardId) {
		User u = new User(name,cardId);
		userMapByIdCard.put(cardId,u);
		userMapByName.put(name,u);
		System.out.println("Zarejestrowano użytkownika : "+name+ " o numerze karty"+cardId);
		return true;
	}

	@Override
	public List<Reservation> getReservations(User u) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUserByName(String name){
		return userMapByName.get(name);
	}
	
	@Override
	public User getUserByIdCard(String name){
		return userMapByIdCard.get(name);
	}
}
