package hello.domain.repository;

import java.util.List;

import hello.domain.Group;
import hello.domain.Room;

public interface IGroupRepository {

	public Group createGroup(String name, Group parent);

	public void addRoom(Group parent, Room r);

	public Room getRoom(String name);

	public List<Room> getAllRoomsInGroup(Group group);

	public List<Room> getAllRooms();

}
