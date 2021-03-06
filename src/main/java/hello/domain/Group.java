package hello.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {

	public String groupName;

	public Group parent;
	public Group leftGroup;
	public Group rightGroup;
	public List<Group> subGroups;
	public List<Room> rooms;

	public Group(String name) {
		this.groupName = name;
		this.parent = null;
		this.leftGroup = null;
		this.rightGroup = null;
		this.subGroups = new ArrayList<Group>();
		this.rooms = new ArrayList<Room>();
	}

	public Group(String name, Group parent) {
		this(name);
		addParent(parent);

	}

	public void addParent(Group g) {
		this.parent = g;
		if (g.subGroups.size() > 0) {
			this.leftGroup = g.subGroups.get(g.subGroups.size() - 1);
			this.leftGroup.rightGroup = this;
		}
		g.addSubGroup(this);

	}

	public void addSubGroup(Group g) {
		this.subGroups.add(g);
	}

	public void addRoom(Room r) {
		this.rooms.add(r);
	}

	@Override
	public String toString() {
		String ret = "";
		if (parent == null) {
			ret = groupName;
		} else {
			ret = parent.toString() + " " + groupName;
		}
		return ret;
	}

	public Room getRoom(String name) {
		// Room roomReturn = null;
		//
		// for(Room roomIteration : rooms){
		// if(roomIteration.name == name){
		// roomReturn = roomIteration;
		// return roomReturn;
		// }
		//
		// }
		//
		// for(Group el : subGroups){
		// roomReturn = el.getRoom(name);
		//
		// }
		//
		// return roomReturn;
		return new Room("wydmuszka", new Group("grupa-wydmuszka"));
	}
}
