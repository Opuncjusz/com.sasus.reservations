package hello.domain;

public class Room {

	public String name;
	public Group group;
	public int idStatus;

	
	
	public Room(String name, Group g){
		this.name=name;
		this.group=g;
		this.idStatus = 1; //wolny
	}
	
	@Override
	public String toString(){
		return group.toString()+" Sala: "+name;
	}

	
}