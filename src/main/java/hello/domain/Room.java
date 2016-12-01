package hello.domain;

public class Room {

	public String name;
	public Group group;

	
	
	Room(String name, Group g){
		this.name=name;
		this.group=g;
	}
	
	@Override
	public String toString(){
		return group.toString()+" Sala: "+name;
	}

	
}