package hello.domain.repository.impl;

import org.springframework.stereotype.Repository;

import hello.domain.Group;
import hello.domain.Room;
import hello.domain.repository.IGroupRepository;

@Repository
public class GroupRepository implements IGroupRepository {

	Group overseer;
	
	public GroupRepository() {
		overseer=new Group("Grupa nadrzędna");
		
		Group b1=createGroup("Budynek MT 2",overseer);
		Group b2=createGroup("Budynek MT 4",overseer);
 		Group f1=createGroup("Piętro 1",b2);
 		Group f2=createGroup("Piętro 2",b2);
 		f1.addRoom(new Room("1.01",f1));
 		f1.addRoom(new Room("1.02",f1));
 		f2.addRoom(new Room("2.01",f2));
		
	}

	@Override
	public Group createGroup(String name, Group parent) {
		Group g = new Group(name);
		parent.addSubGroup(g);
		return g;

	}

	@Override
	public void addRoom(Group parent, Room r) {
		parent.addRoom(r);
		
	}

	@Override
	public Room getRoom(String name) {
		return overseer.getRoom(name);
	}

	
}
