package hello.domain.repository;

import hello.domain.Group;
import hello.domain.Room;

public interface IGroupRepository {

	public Group createGroup(String name, Group parent);
	
	public void addRoom(Group parent, Room r);
	
}
