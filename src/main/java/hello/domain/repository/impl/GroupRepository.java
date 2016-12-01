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
//		Group create
		
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

	
}
