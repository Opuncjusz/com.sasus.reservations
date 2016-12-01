package hello.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {

	public String groupName;
	
	public Group parent;
	public List<Group> subGroups;
	public List<Room> rooms;
	
	
	public Group(String name) {
		this.groupName=name;
		this.parent=null;
		this.subGroups=new ArrayList<Group>();
		this.rooms=new ArrayList<Room>();
	}
	
	public Group(String name, Group parent){
		 this(name);
		 addParent(parent);
		
	}
	
	public void addParent(Group g){
		this.parent=g;
	}

	public void addSubGroup(Group g){
		this.subGroups.add(g);
	}
	
	public void addRoom(Room r){
		this.rooms.add(r);
	}
	
	@Override
	public String toString(){
		String ret="";
		if(parent==null){
			ret=groupName;
		}else{
			ret=parent.toString()+" "+groupName;
		}
		return ret;
	}
	
	public Room getRoom(String name){
		Room r=null;
		for(Room el: rooms){
			if(el.name==name){
				r=el;
				return r;
			}
		
		}
		for(Group el:subGroups){
			r=el.getRoom(name);
		}
		
		if(r==null){
			return null;
		}else{
			return r;
		}
		
		}
	}
	
	
